public class Main {
    public static void main(String[] args) throws Exception {
        final int tarefasPorProcessador = 1;
        final int totalProcessadores = Runtime.getRuntime().availableProcessors();
        final int numTarefas = (tarefasPorProcessador) * totalProcessadores;

        System.out.println("Total de processadores: " + totalProcessadores);
        System.out.println("Numero de tarefas: " + numTarefas);
        System.out.println("Tarefas por processador: " + tarefasPorProcessador);

        WriteFile.WriteFilePath("../MatrixA.txt", "notextallowed");
        double A[][] = WriteFile.ReadFile("MatrixA.txt");
        double B[][] = WriteFile.ReadFile("MatrixB.txt");

        double[] threadTasks = new double[numTarefas];
        int totalElementsC = A.length * B.getColuna(0);

        for(int i = 0; i < numTarefas; i++) {
            threadTasks[i] = (numTarefas - 1 == i) ? Math.ceil(totalElementsC / numTarefas) : Math.floor(totalElementsC / numTarefas);
        }



    }

    public static double[] generateMatrixList(double[][] A, double[][] B){

    }

    public static void multiplyMatrix(double[][] A, double[][] B) {
        double[][] C = new double[A.length][B[0].length];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                for (int k = 0; k < A[i].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}
