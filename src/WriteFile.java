import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class WriteFile {
    public static void WriteFilePath(String caminho, String texto) {
        try (
            FileWriter fileWriter = new FileWriter(caminho, false);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter pw = new PrintWriter(buffer))
        {
            pw.append(texto);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static double[][] ReadFile(String name, int linha, int coluna) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(name));
        Scanner scanner = new Scanner(new FileReader("C:\\Users\\leonardo.falango\\Desktop\\leonardo falango\\paralelismo-iterativo\\MatrixA.txt"));

        double matrix[][] = new double[linha][coluna];
        int j = 0;
        int i = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            j = 0;
            for (String numero : line.split(" ")) {
                matrix[i][j] = Double.parseDouble(numero);
                j += 1;
            }
            i += 1;
        }

        return matrix;
    }

    public static Matrix test(String name) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(name));
        Scanner scanner = new Scanner(new FileReader("C:\\Users\\leonardo.falango\\Desktop\\leonardo falango\\paralelismo-iterativo\\MatrixA.txt"));

        Matrix matrix = new Matrix(3,3);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (String numero : line.split(" ")) {
                matrix.add(Double.parseDouble(numero));
            }
        }

        return matrix;
    }

}