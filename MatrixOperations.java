package k22ug;
import java.util.Scanner;

public class MatrixOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Matrix Operations");
        System.out.print("Enter the number of rows for Matrix A: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter the number of columns for Matrix A: ");
        int colsA = scanner.nextInt();
        double[][] matrixA = inputMatrix(rowsA, colsA);

        System.out.print("Enter the number of rows for Matrix B: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter the number of columns for Matrix B: ");
        int colsB = scanner.nextInt();
        double[][] matrixB = inputMatrix(rowsB, colsB);

        System.out.println("Matrix A:");
        printMatrix(matrixA);
        System.out.println("Matrix B:");
        printMatrix(matrixB);

        if (colsA != rowsB) {
            System.out.println("Matrix dimensions do not allow multiplication.");
        } else {
            double[][] product = multiplyMatrices(matrixA, matrixB);
            System.out.println("Matrix A * Matrix B:");
            printMatrix(product);
        }

        System.out.println("Matrix A + Matrix B:");
        double[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        System.out.println("Matrix A - Matrix B:");
        double[][] difference = subtractMatrices(matrixA, matrixB);
        printMatrix(difference);

        if (rowsA == colsA) {
            double[][] inverseA = invertMatrix(matrixA);
            System.out.println("Inverse of Matrix A:");
            printMatrix(inverseA);
        }
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
        // Implement matrix inversion logic (e.g., Gaussian elimination or other methods)
        // This part requires more complex math and is beyond the scope of this basic example.
        // You can use libraries like Apache Commons Math for matrix inversion.
        // Example: https://commons.apache.org/proper/commons-math/userguide/linear.html#matrix-inversion
        return matrix; // Placeholder for the inversion result
    }
}
