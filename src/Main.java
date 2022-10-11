public class Main {
    public static void main(String[] args) throws Exception {
        WriteFile.WriteFilePath("../MatrixA.txt", "notextallowed");
        double matrixA[][] = WriteFile.ReadFile("MatrixA.txt");
        Matrix matrix = new Matrix(matrixA);
        //matrix.printMatrix();

        //double coluna0[] = matrix.get_column(0);
        //double linha0[] = matrix.get_row(0);

        //for (int i=0;i<coluna0.length; i++) System.out.println(coluna0[i]);
    }
}
