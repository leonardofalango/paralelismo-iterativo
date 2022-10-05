public class Matrix {
    private int m;
    private int n;
    private int indexLinha;
    private double[][] matrix;

    private int indexCol;
    public Matrix(int m,int n){
        this.m = m;
        this.n = n;
        indexLinha = 0;
        indexCol = 0;
    }
    public void add(double num){
        if (indexLinha == m){
            return;
        }
        if (indexCol == n){
            indexLinha += 1;
        }
        else{
            this.matrix[indexLinha][indexCol] = num;
            indexCol += 1;
        }
    }
}
