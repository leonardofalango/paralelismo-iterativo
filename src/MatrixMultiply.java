import java.util.concurrent.Semaphore;

public class MatrixMultiply extends Thread {
    private double[][] matrixA;
    private double[][] matrixB;
    private double[] taskList;
    private double[][] matrixC;
    private Semaphore mutex;

    public MatrixMultiply(double[][] matrixA, double[][] matrixB, double[][] matrixC, double[] taskList, Semaphore mutex){
        this.matrixA = matrixA;
        this.matrixC = matrixC;
        this.matrixB = matrixB;
        this.taskList = taskList;
        this.mutex = mutex;
    }

    public void run() {
        Element[] calculatedList = new Element[taskList.length];

        Matrix A = new Matrix(matrixA);
        Matrix B = new Matrix(matrixB);

        for(int i = 0; i < taskList.length; i++){
            double elementPosition = taskList[i];
            int[] positionInC = getCurrentPosition((int) elementPosition, matrixC);

            double calculatedElement = multiplyLineAndColumn(A.get_row(positionInC[0]), B.get_column(positionInC[1]));

            Element currentElement = new Element(calculatedElement, positionInC[0], positionInC[1]);
            calculatedList[i] = currentElement;
        }

        try {
            mutex.acquire();
                for(Element element : calculatedList){
                    matrixC[element.getLine()][element.getColumn()] = element.getValue();
                }
            mutex.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] getCurrentPosition(int target, double[][] matrixC){
        int element = 0;
        int[] position = new int[2];

        for(int i = 0; i < matrixC.length; i++){
            for(int j = 0; j < matrixC[i].length; j++){
                if(element == target){
                    position[0] = i;
                    position[1] = j;

                    return position;
                }

                element++;
            }
        }

        return position;
    }

    public double multiplyLineAndColumn(double[] lineValue, double[] columnValue){
        if(lineValue.length != columnValue.length) return -1;

        double result = 0;

        for (int i = 0; i < lineValue.length; i++){
            result += lineValue[i] * columnValue[i];
        }

        return result;
    }

}
