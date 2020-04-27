//import javafx.beans.binding.IntegerBinding;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Matrix {
    public static double[][] multiply(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix[0].length != secondMatrix.length ||
                firstMatrix.length != secondMatrix[0].length) {
            throw new IllegalArgumentException();
        }

        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }
    private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static double[][] sum(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length ||
                firstMatrix[0].length != secondMatrix[0].length) {
            throw new IllegalArgumentException();
        }

        double resMatrix[][] = new double[firstMatrix.length][firstMatrix[0].length];

        for (int i = 0; i < resMatrix.length; i++) {
            for (int j = 0; j < resMatrix[0].length; j++) {
                resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }

        return resMatrix;
    }

    public static double[][] sub(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix.length != secondMatrix.length ||
                firstMatrix[0].length != secondMatrix[0].length) {
            throw new IllegalArgumentException();
        }

        double resMatrix[][] = new double[firstMatrix.length][firstMatrix[0].length];

        for (int i = 0; i < resMatrix.length; i++) {
            for (int j = 0; j < resMatrix[0].length; j++) {
                resMatrix[i][j] = firstMatrix[i][j] - secondMatrix[i][j];
            }
        }

        return resMatrix;
    }

    public static double[][] createRandMatrix(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new IllegalArgumentException();
        }

        Random rand = new Random();
        double[][] res = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = (double)(rand.nextInt(100) + 1);
            }
        }

        return res;
    }

    public static void print(double[][] matrix) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        for(int i=0;i<rows;i++) {
            for(int j=0;j<cols;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }

    public static double[][] getMatrixFromFile(String fileName) {
        try {
            List<String> linesMatrix = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());

            int rows = Integer.parseInt(linesMatrix.get(0));
            int cols = Integer.parseInt(linesMatrix.get(1));

            double[][] matrix = new double[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String[] line = linesMatrix.get(i + 2).split(" ");
                    matrix[i][j] = Double.parseDouble(line[j]);
                }
            }

            return matrix;
        }
        catch (IOException e) {
            System.out.println(e.getClass());
        }

        return null;
    }

    public static boolean checkEqual(double[][] firstMatrix, double[][] secondMatrix) {
        boolean check = true;

        for (int i = 0; i < firstMatrix.length; i++) {
            for (int j = 0; j < firstMatrix[0].length; j++) {
                if (firstMatrix[i][j] != secondMatrix[i][j]) {
                    check = false;
                    break;
                }
            }
        }

        return check;
    }
}
