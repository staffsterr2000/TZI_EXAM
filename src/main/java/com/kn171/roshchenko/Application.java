package com.kn171.roshchenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {
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

    public static Integer getRandomPrimeNumber(int f) {
        if (f < 2) throw new IllegalStateException("less than 2");
        List<Integer> primeNumbers = new ArrayList<>();

        for (int i = 2; i < f; i++) {
            if (isNumberPrime(i)) primeNumbers.add(i);
        }

        return primeNumbers.get((int) (Math.random() * primeNumbers.size()));
    }

    public static Integer getD(int e, int f) {
        int d = 1;

        while ((d * e) % f != 1) {
            for (int i = 2; i < Math.sqrt(d); i++) {
                if (d % i == 0) {
                    d++;
                }
            }
        }

        return d;
    }

    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));) {
            // metadata Roshchenko = 18, Stanislav = 19, Ihorovich = 10
            int X = 18, C = 19, B = 10;

//            System.out.println("Enter p:");
//            int p = Integer.parseInt(console.readLine());
//            System.out.println("Enter q:");
//            int q = Integer.parseInt(console.readLine());

            int p = 3;
            int q = 11;

            int n = p * q;
            int f = (p - 1) * (q - 1);
            int e = getRandomPrimeNumber(f);
            int d = getD(e, f);

            String patternToEncrypt = "Відкритий текст m(%1$s) = %d; Шифр c(%1$s) = %d";
            String patternToDecrypt = "Шифрований текст m(%1$s) = %d; Дешифрований текст(%1$s) = %d; Оригінальний текст = (%d)";

            int encryptedX = (int) Math.pow(X, e) % n;
            System.out.printf(patternToEncrypt, "X", X, encryptedX);
            int encryptedC = (int) Math.pow(C, e) % n;
            System.out.printf(patternToEncrypt, "C", C, encryptedC);
            int encryptedB = (int) Math.pow(B, e) % n;
            System.out.printf(patternToEncrypt, "B", B, encryptedB);


            int decryptedX = (int) Math.pow(encryptedX, d) % n;
            System.out.printf(patternToDecrypt, "X", encryptedX, decryptedX, X);
            int decryptedC = (int) Math.pow(encryptedC, d) % n;
            System.out.printf(patternToDecrypt, "C", encryptedC, decryptedC, C);
            int decryptedB = (int) Math.pow(encryptedB, d) % n;
            System.out.printf(patternToDecrypt, "B", encryptedB, decryptedB, B);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
