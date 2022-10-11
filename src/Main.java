public class Main {
    public static void main(String[] args) throws Exception {
        double matrixA[][] = WriteFile.ReadFile("MatrixA.txt");
        Matrix matrix_A = new Matrix(matrixA);
        double matrixB[][] = WriteFile.ReadFile("MatrixB.txt");
        Matrix matrix_B = new Matrix(matrixB);
        //matrix.printMatrix();

        //double coluna0[] = matrix.get_column(0);
        //double linha0[] = matrix.get_row(0);

        //for (int i=0;i<coluna0.length; i++) System.out.println(coluna0[i]);

        matrix_A.printMatrix();
    }
}
