package ru.vsu.cs;

import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        double x = readVariable("x");
        double numberOfMembers = readVariable("numberOfMembers");
        double eps = readVariable("epsilon");

        double sumOfSequence = calculateSumOfSequence(x, numberOfMembers);
        printResult("The sum of N elements", sumOfSequence);

        double sumOfSequenceWithEpsilon = calculateSumOfSequenceWithEpsilon(x, numberOfMembers, eps);
        printResult("The sum of N elements whose absolute value exceeds epsilon", sumOfSequenceWithEpsilon);

        double sumOfSequenceWithEpsilonDividedBy10 = calculateSumOfSequenceWithEpsilon(x, numberOfMembers, eps / 10);
        printResult("The sum of N elements whose absolute value exceeds epsilon divided by 10", sumOfSequenceWithEpsilonDividedBy10);

        if (x == 0) {
            printResult("The sum cannot be calculated because x ", x);
        } else {
            double sumOfSequenceWithMath = Math.sin(x) / x;
            printResult("The sum using Math", sumOfSequenceWithMath);
        }
    }

    private static double readVariable(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter %s = ", name);
        return scanner.nextDouble();
    }

    private static double calculatePower(double value, int powValue) {
        double result = 1;
        for (int i = 0; i < powValue; i++) {
            result = result * value;
        }
        return result;
    }

    private static int calculateFactorial(int value) {
        for (int i = 1; i <= value; i++) {
            value = value * i;
        }
        return value;
    }

    private static double calculateAbs(double x) {
        if (x < 0)
            return -x;
        else
            return x;
    }

    private static double calculateNthMemberOfSequence(double x, int n) {
        double numerator = calculatePower(-1, n-1) * calculatePower(x, 2 * n - 2);
        double denominator = calculateFactorial(2 * n - 1);
        return numerator / denominator;
    }

    private static double calculateSumOfSequence(double x, double numberOfMembers) {
        double sum;
        if (numberOfMembers == 0) {
            sum = 0;
        } else {
            sum = 1;
            for (int i = 0; i <= numberOfMembers; i++) {
                sum += calculateNthMemberOfSequence(x, i);
            }
        }
        return sum;
    }

    private static double calculateSumOfSequenceWithEpsilon(double x, double numberOfMembers, double eps) {
        double sum = 0;
        for (int i = 0; i <= numberOfMembers; i++) {
            if (calculateAbs(calculateNthMemberOfSequence(x, i)) > eps) {
                sum = sum + calculateNthMemberOfSequence(x, i);
            }
        }
        return sum;
    }

    private static void printResult(String text, double variable) {
        System.out.printf(" %s = %2$.3f\n", text, variable);
    }
}


