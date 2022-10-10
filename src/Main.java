public class Main {
    public static void main(String[] args) throws Exception {
        WriteFile.WriteFilePath("../MatrixA.txt", "notextallowed");
        double matrixA[][] = WriteFile.ReadFile("MatrixA.txt", 3, 3);
        for (int i =0;i< matrixA.length;i++) for (int j=0;j< matrixA[i].length;j++) System.out.println(matrixA[i][j]);

    }
}
