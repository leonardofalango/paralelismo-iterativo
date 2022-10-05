public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int tarefasPorProcessador = 1;
        final int totalProcessadores = Runtime.getRuntime().availableProcessors();
        final int numTarefas = (tarefasPorProcessador) * totalProcessadores;

        System.out.println("Total de processadores: " + totalProcessadores);
        System.out.println("Numero de tarefas: " + numTarefas);
        System.out.println("Tarefas por processador: " + tarefasPorProcessador);

        final int min = 15;
        final int max = 28;

        String SEP = ", ";
        System.out.println("N" + SEP + "Tamanho da Sequencia" + SEP + "Tempo Sequencial" + SEP + "Tempo Paralelo" + SEP + "Razao entre os Tempos");

        for (int N = min; N <= max; N++) {
            long tamSequencia = (long) Math.pow(2, N);
            long T_exec_sequencial = execute_sequencial(tamSequencia);
            long T_exec_paralelo = execute_paralelo(tamSequencia, numTarefas);
            double razao = (double) exec_sequencial / T_exec_paralelo;

            System.out.println(N + SEP + tamSequencia + SEP + T_exec_sequencial + SEP +
                    T_exec_paralelo + SEP + razao);
        }
    }
}