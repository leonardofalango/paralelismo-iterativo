import java.util.Arrays;
import java.util.concurrent.Semaphore;

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

        double[][] matrixC = multiplyMatrixByThread(matrixA, matrixB, numTarefas);

        System.out.println(Arrays.deepToString(matrixC));

    }

    public static double[][] multiplyMatrixByThread(double[][] matrixA, double[][] matrixB, int numTarefas) throws InterruptedException {
        double[][] threadTasks = generateTaskList(numTarefas, matrixA, matrixB);

        double[][] matrixC = new double[matrixA.length][matrixB[0].length];
        Semaphore mutex = new Semaphore(1);

        for(int i = 0; i < threadTasks.length; i++){
            MatrixMultiply multiply = new MatrixMultiply(matrixA, matrixB, matrixC, threadTasks[i], mutex);
            multiply.start();

            if(i == threadTasks.length - 1){
                multiply.join();
            }
        }

        return matrixC;
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
