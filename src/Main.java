import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        final int tarefasPorProcessador = 1;
        final int totalProcessadores = Runtime.getRuntime().availableProcessors();
        final int numTarefas = (tarefasPorProcessador) * totalProcessadores;

        System.out.println("Total de processadores: " + totalProcessadores);
        System.out.println("Numero de tarefas: " + numTarefas);
        System.out.println("Tarefas por processador: " + tarefasPorProcessador);


        double[][] matrixA = WriteFile.ReadFile("MatrixA.txt");
        double[][] matrixB = WriteFile.ReadFile("MatrixB.txt");

        Matrix matrix_A = new Matrix(matrixA);
        Matrix matrix_B = new Matrix(matrixB);

        double[][] threadTasks = generateTaskList(numTarefas, matrixA, matrixB);

        System.out.println(Arrays.deepToString(threadTasks));
    }

    public static double[][] generateTaskList(int numTarefas, double[][] matrixA, double[][] matrixB){
        int totalElementsC = matrixA.length * matrixB[0].length;
        double[][] threadTasks = new double[numTarefas][(int) ((totalElementsC % numTarefas != 0) ? Math.floor(totalElementsC / numTarefas) + 1 : Math.floor(totalElementsC / numTarefas))];

        int count = 0;
        int addToNext = 0;

        for(int i = 0; i < numTarefas; i++) {
            double limit = (addToNext > 0) ? Math.floor(totalElementsC / numTarefas) + addToNext : Math.floor(totalElementsC / numTarefas);
            addToNext = 0;

            if (totalElementsC % numTarefas != 0){
                addToNext++;
            }

            for(int j = 0; j < limit; j++){
                threadTasks[i][j] = count;
                count++;
            }
        }

        return threadTasks;
    }
}
