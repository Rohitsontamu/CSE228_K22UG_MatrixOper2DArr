package k22ug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Matrix Operations");
        System.out.print("Enter the number of rows for MatrixA: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter the number of columns for MatrixA: ");
        int colsA = scanner.nextInt();
        List<List<Double>> matrixA = inputMatrix(rowsA, colsA);

        System.out.print("Enter the number of rows for MatrixB: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter the number of columns for MatrixB: ");
        int colsB = scanner.nextInt();
        List<List<Double>> matrixB = inputMatrix(rowsB, colsB);

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
                        List<List<Double>> product = multiplyMatrices(matrixA, matrixB);
                        System.out.println("MatrixA * MatrixB:");
                        printMatrix(product);
                    }
                    break;
                case 2:
                    List<List<Double>> sum = addMatrices(matrixA, matrixB);
                    System.out.println("MatrixA + MatrixB:");
                    printMatrix(sum);
                    break;
                case 3:
                    List<List<Double>> difference = subtractMatrices(matrixA, matrixB);
                    System.out.println("MatrixA - MatrixB:");
                    printMatrix(difference);
                    break;
                case 4:
                    if (rowsA == colsA) {
                        List<List<Double>> inverseA = invertMatrix(matrixA);
                        System.out.println("Inverse of MatrixA:");
                        printMatrix(inverseA);
                    } else {
                        System.out.println("MatrixA must be square for inversion.");
                    }
                    break;
                case 5:
                    List<List<Double>> transposeA = transposeMatrix(matrixA);
                    System.out.println("Transpose of MatrixA:");
                    printMatrix(transposeA);
                    break;
                case 6:
                    if (rowsA == colsA) {
                        System.out.print("Enter the power (n) for MatrixA: ");
                        int power = scanner.nextInt();
                        List<List<Double>> result = matrixPower(matrixA, power);
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

    public static List<List<Double>> inputMatrix(int rows, int cols) {
        Scanner scanner = new Scanner(System.in);
        List<List<Double>> matrix = new ArrayList<>();

        System.out.println("Enter elements of the matrix:");

        for (int i = 0; i < rows; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(scanner.nextDouble());
            }
            matrix.add(row);
        }
        return matrix;
    }

    public static void printMatrix(List<List<Double>> matrix) {
        for (List<Double> row : matrix) {
            for (Double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Double>> addMatrices(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        int rows = matrixA.size();
        int cols = matrixA.get(0).size();
        List<List<Double>> sum = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(matrixA.get(i).get(j) + matrixB.get(i).get(j));
            }
            sum.add(row);
        }
        return sum;
    }

    public static List<List<Double>> subtractMatrices(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        int rows = matrixA.size();
        int cols = matrixA.get(0).size();
        List<List<Double>> difference = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(matrixA.get(i).get(j) - matrixB.get(i).get(j));
            }
            difference.add(row);
        }
        return difference;
    }

    public static List<List<Double>> multiplyMatrices(List<List<Double>> matrixA, List<List<Double>> matrixB) {
        int rowsA = matrixA.size();
        int colsA = matrixA.get(0).size();
        int colsB = matrixB.get(0).size();
        List<List<Double>> product = new ArrayList<>();

        for (int i = 0; i < rowsA; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < colsB; j++) {
                double value = 0.0;
                for (int k = 0; k < colsA; k++) {
                    value += matrixA.get(i).get(k) * matrixB.get(k).get(j);
                }
                row.add(value);
            }
            product.add(row);
        }
        return product;
    }

    public static List<List<Double>> invertMatrix(List<List<Double>> matrix) {
        int n = matrix.size();
        List<List<Double>> augmentedMatrix = new ArrayList<>();

        // Initialize the augmented matrix
        for (int i = 0; i < n; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < 2 * n; j++) {
                if (j < n) {
                    row.add(matrix.get(i).get(j));
                } else {
                    row.add((i == j - n) ? 1.0 : 0.0);
                }
            }
            augmentedMatrix.add(row);
        }

        // Perform Gaussian elimination
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = augmentedMatrix.get(j).get(i) / augmentedMatrix.get(i).get(i);
                for (int k = 0; k < 2 * n; k++) {
                    double value = augmentedMatrix.get(j).get(k) - factor * augmentedMatrix.get(i).get(k);
                    augmentedMatrix.get(j).set(k, value);
                }
            }
        }

        // Back-substitution
        List<List<Double>> inverseMatrix = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            List<Double> row = new ArrayList<>();
            for (int j = n - 1; j >= 0; j--) {
                if (i == j) {
                    row.add(augmentedMatrix.get(i).get(j + n) / augmentedMatrix.get(i).get(i));
                } else {
                    row.add(-augmentedMatrix.get(i).get(j + n) / augmentedMatrix.get(i).get(i));
                }
            }
            inverseMatrix.add(row);
        }

        return inverseMatrix;
    }

    public static List<List<Double>> transposeMatrix(List<List<Double>> matrix) {
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        List<List<Double>> transpose = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                row.add(matrix.get(j).get(i));
            }
            transpose.add(row);
        }

        return transpose;
    }

    public static List<List<Double>> matrixPower(List<List<Double>> matrix, int power) {
        int n = matrix.size();
        List<List<Double>> result = new ArrayList<>();
        List<List<Double>> temp = new ArrayList<>();

        // Initialize the result matrix as the identity matrix
        for (int i = 0; i < n; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    row.add(1.0);
                } else {
                    row.add(0.0);
                }
            }
            result.add(row);
        }

        while (power > 0) {
            if (power % 2 == 1) {
                result = multiplyMatrices(result, matrix);
            }
            temp = multiplyMatrices(matrix, matrix);
            matrix = temp;
            power /= 2;
        }

        return result;
    }
}





