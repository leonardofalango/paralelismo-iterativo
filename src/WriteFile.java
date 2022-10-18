import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class WriteFile {
    public static void writeMatrix(String filename, double[][] matrix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    bw.write(matrix[i][j] + ",");
                }
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {}
    }

    public static double[][] ReadFile(String name) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(name));
        Scanner scanner = new Scanner(new FileReader(name));

        int j = 0;
        int i = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            j = 0;
            for (String numero : line.split(" ")) {
                j += 1;
            }
            i += 1;
        }


        double matrix[][] = new double[i][j];

        Scanner scanner1 = new Scanner(new FileReader(name));
        j = 0; i = 0;
        while (scanner1.hasNextLine()) {
            String line = scanner1.nextLine();
            j = 0;
            for (String numero : line.split(" ")) {
                matrix[i][j] = Double.parseDouble(numero);
                j += 1;
            }
            i += 1;
        }

        return matrix;
    }


}