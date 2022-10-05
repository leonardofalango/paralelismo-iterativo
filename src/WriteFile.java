import java.io.*;
import java.util.ArrayList;
import java.io.IOException;

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

    public static double[][] ReadFile(String name) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(name));
        ArrayList<Integer> lista = new ArrayList<Integer>();
        String linha = br.readLine();

        int i = 0;
        int j = 0;

        for (String linhaMatrix : linha.split("\n"))for (String numero : linhaMatrix.split(" "))j += 1;i += 1;

        double matrix[][] = new double[i][j];
        i = 0;
        j = 0;

        for (String linhaMatrix : linha.split("\n")) {
            for (String numero : linhaMatrix.split(" ")) {
                matrix[i][j] = Double.valueOf(numero);
                j += 1;
            }
            i += 1;
        }

        return matrix;
    }

}