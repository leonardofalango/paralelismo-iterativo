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
        for (int i=0; i<50; i++){
            runMatrixExample(1, 8, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(1, 8, numTarefas, "matrixb.txt", "matrixf.txt");
            runMatrixExample(1, 8, numTarefas, "matrixh.txt", "matrixc.txt");
            runMatrixExample(1, 8, numTarefas, "matrix8.txt", "matrix4.txt");
        }

        //Exemplo 2
        for (int i=0; i<50; i++){
            runMatrixExample(2, 1, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(2, 4, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(2, 6, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(2, 8, numTarefas, "matrixa.txt", "matrixe.txt");
        }

        //Calcular o speedup e a eficiencia
        for (int i=0; i<50; i++){
            runMatrixExample(2, 1, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(2, 4, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(2, 6, numTarefas, "matrixa.txt", "matrixe.txt");
            runMatrixExample(2, 8, numTarefas, "matrixa.txt", "matrixe.txt");
        }
    }

    public static void runMatrixExample(int example, int totalProcessadores, int numTarefas, String file1, String file2) throws Exception {
        System.out.println("\nMatriz " + example + ": ");

        long inicio = System.currentTimeMillis();
        double[][] matrixA = WriteFile.ReadFile(file1);
        double[][] matrixB = WriteFile.ReadFile(file2);
        long tempo = System.currentTimeMillis() - inicio;
        System.out.println("Tempo para leitura das matrizes: " + tempo);

        System.out.println("Total de processadores: " + totalProcessadores);
        System.out.println("Tamanho da matriz: " + matrixA.length + "x" + matrixB[0].length);

        double[][] matrixC = multiplyMatrixByThread(matrixA, matrixB, numTarefas);

        inicio = System.currentTimeMillis();
        WriteFile.writeMatrix("matrix.txt", matrixC);
        tempo = System.currentTimeMillis() - inicio;
        System.out.println("Tempo para a escrita da matriz: " + tempo);

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
