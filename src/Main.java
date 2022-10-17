import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws Exception {
         int tarefasPorProcessador = 1;
         int totalProcessadores = Runtime.getRuntime().availableProcessors();
         int numTarefas = (tarefasPorProcessador) * totalProcessadores;

        System.out.println("\nTotal de processadores: " + totalProcessadores);
        System.out.println("Numero de tarefas: " + numTarefas);
        System.out.println("Tarefas por processador: " + tarefasPorProcessador);

        //Exemplo 1
        runMatrixExample(1, 2, numTarefas, "MatrixB.txt", "Matrixf.txt");

        //Exemplo 2
        //runMatrixExample(2, 6, numTarefas, "MatrixA.txt", "MatrixB.txt");

    }

    public static void runMatrixExample(int example, int totalProcessadores, int numTarefas, String file1, String file2) throws Exception {
        System.out.println("\nMatriz " + example + ": ");
        double[][] matrixA = WriteFile.ReadFile(file1);
        double[][] matrixB = WriteFile.ReadFile(file2);

        System.out.println("Total de processadores: " + totalProcessadores);
        System.out.println("Tamanho da matriz: " + matrixA.length + "x" + matrixB[0].length);

        double[][] matrixC = multiplyMatrixByThread(matrixA, matrixB, numTarefas);

        //System.out.println(Arrays.deepToString(matrixC));
    }

    public static double[][] multiplyMatrixByThread(double[][] matrixA, double[][] matrixB, int numTarefas) throws InterruptedException {
        double[][] threadTasks = generateTaskList(numTarefas, matrixA, matrixB);

        double[][] matrixC = new double[matrixA.length][matrixB[0].length];
        Semaphore mutex = new Semaphore(1);

        long t0 = System.currentTimeMillis();
        for(int i = 0; i < threadTasks.length; i++){
            MatrixMultiply multiply = new MatrixMultiply(matrixA, matrixB, matrixC, threadTasks[i], mutex);
            multiply.start();

            if(i == threadTasks.length - 1){
                multiply.join();
            }
        }
        long t1 = System.currentTimeMillis();

        System.out.println("Tempo de calculo: " + (t1 - t0) + "ms");
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
