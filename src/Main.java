public class Main {
    public static void main(String[] args) throws Exception {
        WriteFile.WriteFilePath("../MatrixA.txt", "notextallowed");
        double matrixA[][] = WriteFile.ReadFile("MatrixA.txt");
    }
}
