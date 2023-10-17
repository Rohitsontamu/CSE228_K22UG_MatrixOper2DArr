package k22ug;

import java.util.Scanner;

public class MatrixOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Matrix Operations");
        System.out.print("Enter the number of rows for MatrixA: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter the number of columns for MatrixA: ");
        int colsA = scanner.nextInt();
        double[][] matrixA = inputMatrix(rowsA, colsA);

        System.out.print("Enter the number of rows for MatrixB: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter the number of columns for MatrixB: ");
        int colsB = scanner.nextInt();
        double[][] matrixB = inputMatrix(rowsB, colsB);

        System.out.println("MatrixA:");
        printMatrix(matrixA);
        System.out.println("MatrixB:");
        printMatrix(matrixB);

        int choice;
        do {
            System.out.println("Matrix Operations Menu:");
            System.out.println("1. MatrixA * MatrixB (Multiplication)");
            System.out.println("2. MatrixA + MatrixB (Addition)");
            System.out.println("3. MatrixA - MatrixB (Subtraction)");
            System.out.println("4. Invert MatrixA");
            System.out.println("5. Transpose MatrixA");
            System.out.println("6. MatrixA ^ n (Matrix Power)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (colsA != rowsB) {
                        System.out.println("Matrix dimensions do not allow multiplication.");
                    } else {
                        double[][] product = multiplyMatrices(matrixA, matrixB);
                        System.out.println("MatrixA * MatrixB:");
                        printMatrix(product);
                    }
                    break;
                case 2:
                    double[][] sum = addMatrices(matrixA, matrixB);
                    System.out.println("MatrixA + MatrixB:");
                    printMatrix(sum);
                    break;
                case 3:
                    double[][] difference = subtractMatrices(matrixA, matrixB);
                    System.out.println("MatrixA - MatrixB:");
                    printMatrix(difference);
                    break;
                case 4:
                    if (rowsA == colsA) {
                        double[][] inverseA = invertMatrix(matrixA);
                        System.out.println("Inverse of MatrixA:");
                        printMatrix(inverseA);
                    } else {
                        System.out.println("MatrixA must be square for inversion.");
                    }
                    break;
                case 5:
                    double[][] transposeA = transposeMatrix(matrixA);
                    System.out.println("Transpose of MatrixA:");
                    printMatrix(transposeA);
                    break;
                case 6:
                    if (rowsA == colsA) {
                        System.out.print("Enter the power (n) for MatrixA: ");
                        int power = scanner.nextInt();
                        double[][] result = matrixPower(matrixA, power);
                        System.out.println("MatrixA ^ " + power + ":");
                        printMatrix(result);
                    } else {
                        System.out.println("MatrixA must be square for matrix power operation.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 7);
    }

    public static double[][] inputMatrix(int rows, int cols) {
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[rows][cols];

        System.out.println("Enter elements of the matrix:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] addMatrices(double[][] matrixA, double[][] matrixB) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        double[][] sum = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return sum;
    }

    public static double[][] subtractMatrices(double[][] matrixA, double[][] matrixB) {
        int rows = matrixA.length;
        int cols = matrixA[0].length;
        double[][] difference = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                difference[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        return difference;
    }

    public static double[][] multiplyMatrices(double[][] matrixA, double[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        double[][] product = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    product[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return product;
    }

    public static double[][] invertMatrix(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square");
        }

        int n = matrix.length;
        double[][] augmentedMatrix = new double[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                augmentedMatrix[i][j] = matrix[i][j];
            }

            for (int j = n; j < 2 * n; j++) {
                augmentedMatrix[i][j] = (i == j - n) ? 1.0 : 0.0;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = augmentedMatrix[j][i] / augmentedMatrix[i][i];
                for (int k = 0; k < 2 * n; k++) {
                    augmentedMatrix[j][k] -= factor * augmentedMatrix[i][k];
                }
            }
        }

        double[][] inverseMatrix = new double[n][n];
        for (int i = n - 1; i >= 0; i--) {
            inverseMatrix[i][i] = 1.0 / augmentedMatrix[i][i];
            for (int j = i - 1; j >= 0; j--) {
                inverseMatrix[i][j] = -augmentedMatrix[i][j + n] / augmentedMatrix[i][i];
            }
        }

        return inverseMatrix;
    }

    public static double[][] transposeMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transpose = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }

        return transpose;
    }

    public static double[][] matrixPower(double[][] matrix, int power) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square for matrix power operation.");
        }

        if (power < 0) {
            throw new IllegalArgumentException("Power must be a non-negative integer.");
        }

        int n = matrix.length;
        double[][] result = new double[n][n];
        double[][] temp = new double[n][n];

        // Initialize result as the identity matrix
        for (int i = 0; i < n; i++) {
            result[i][i] = 1.0;
        }

        while (power > 0) {
            if (power % 2 == 1) {
                // Multiply result by matrix when the power is odd
                result = multiplyMatrices(result, matrix);
            }
            // Square the matrix and reduce power by half
            temp = multiplyMatrices(matrix, matrix);
            matrix = temp;
            power /= 2;
        }

        return result;
    }
}
