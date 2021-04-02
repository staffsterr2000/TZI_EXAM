package com.kn171.roshchenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Application {
    // знайти числа за критеріями: просте, взаємно просте з f. Вибрати одне наосліп
    public static Integer getRandomPrimeNumber(int f) {
        if (f < 2) throw new IllegalStateException("less than 2");
        List<Integer> primeNumbers = new ArrayList<>();

        for (int e = 2; e < f; e++) {
            if (isNumberPrime(e) && isBothPrime(e, f)) primeNumbers.add(e);
        }

        return primeNumbers.get((int) (Math.random() * primeNumbers.size()));
    }

    // перевірка на простоту
    public static boolean isNumberPrime(int number) {
        if (number >= 2) {
            for (int i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        } else
            return false;

        return true;
    }

    // перевірка на взаємну простоту чисел
    public static boolean isBothPrime(int e, int f) {
        return !(f % e == 0 || e % f == 0);
    }

    // взяти обернене число (d) до e за модулем f
    public static Integer getD(int e, int f) {
        int d = 1;

        while (d < Integer.MAX_VALUE) {
            if ((++d * e) % f == 1) return d;
        }

        throw new IllegalStateException("No such d");
    }

    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            // metadata Roshchenko = 18, Stanislav = 19, Ihorovich = 10
            int X = 18, C = 19, B = 10;

            // зчитування з консолі
            System.out.println("Enter p:");
            int p = Integer.parseInt(console.readLine());
            System.out.println("Enter q:");
            int q = Integer.parseInt(console.readLine());

            // розрахунок змінних
            int n = p * q;
            System.out.printf("n = %d%n", n);
            int f = (p - 1) * (q - 1);
            System.out.printf("f = %d%n", f);
            int e = getRandomPrimeNumber(f);
            System.out.printf("e = %d%n", e);
            int d = getD(e, f);
            System.out.printf("d = %d%n", d);

            System.out.println();

            // шифрування
            int encryptedX = encryptAndPrint(X, 'X', e, n);
            int encryptedC = encryptAndPrint(C, 'C', e, n);
            int encryptedB = encryptAndPrint(B, 'B', e, n);

            System.out.println();

            // дешифрування
            int decryptedX = decryptAndPrint(encryptedX, 'X', d, n);
            int decryptedC = decryptAndPrint(encryptedC, 'C', d, n);
            int decryptedB = decryptAndPrint(encryptedB, 'B', d, n);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // шифрування та вивід до консолі
    public static int encryptAndPrint(int numberToEncrypt, char letter, int e, int n) {
        int encrypted = BigDecimal.valueOf(numberToEncrypt).pow(e).toBigInteger().mod(BigInteger.valueOf(n)).intValue();
        System.out.printf("Відкритий текст m(%c) = %d; Шифр c(%1$c) = %d;%n", letter, numberToEncrypt, encrypted);
        return encrypted;
    }

    // дешифрування та вивід до консолі
    public static int decryptAndPrint(int numberToDecrypt, char letter, int d, int n) {
        int decrypted = BigDecimal.valueOf(numberToDecrypt).pow(d).toBigInteger().mod(BigInteger.valueOf(n)).intValue();
        System.out.printf("Шифрований текст c(%c) = %d; Дешифрований текст(%1$c) = %d;%n", letter, numberToDecrypt, decrypted);
        return decrypted;
    }

}
