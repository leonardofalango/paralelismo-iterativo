import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.*;

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

    public static ArrayList<Integer> ReadFile(String name) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(name));
        ArrayList<Integer> lista = new ArrayList<Integer>();
        String linha = br.readLine();
        for (String numero : linha.split(" ")) {
            lista.add(Integer.valueOf(numero));
        }

        return lista;
    }

}