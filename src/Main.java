public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int tarefasPorProcessador = 1;
        final int totalProcessadores = Runtime.getRuntime().availableProcessors();
        final int numTarefas = (tarefasPorProcessador) * totalProcessadores;

        System.out.println("Total de processadores: " + totalProcessadores);
        System.out.println("Numero de tarefas: " + numTarefas);
        System.out.println("Tarefas por processador: " + tarefasPorProcessador);

        Matrix A = new Matrix();
        Matrix B = new Matrix();

        for (int i = 0; i < numTarefas; i++){
            double linesA = A.getLines();
            double columnsB = A.getColumns();
            double totalElementsC = linesA * columnsB;

            double matrixLength = (numTarefas - 1 == i) ? Math.ceil(totalElementsC / numTarefas) : Math.floor(totalElementsC / numTarefas);
            int[][] matrix = new int[(int) matrixLength][];
            //ajustar lógica para atribuição das linhasXcolunas ao array
            for(int j = 0; j < matrix.length; j++){
                int[] lineXcolumn = new int[2];

                lineXcolumn[0] = i;
                lineXcolumn[1] = j;

                matrix[j] = lineXcolumn;
            }
        }
    }

    public static int[][] multiplyMatrix(){

    }
}