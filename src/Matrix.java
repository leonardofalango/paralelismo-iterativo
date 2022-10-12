public class Matrix {
    private double matrix[][];

    public Matrix(double matrix[][]){
        this.matrix = matrix;
    }

    public double[] get_column(int index_column){
        double row[] = new double[this.matrix.length];
        for (int i = 0; i<row.length; i++) row[i] = this.matrix[i][index_column];
        return row;
    }

    public double[] get_row(int index_row){
        double[] column = new double[this.matrix[index_row].length];
        for (int i = 0; i<column.length; i++) column[i] = this.matrix[index_row][i];
        return column;
    }

    public void printMatrix(){
        for (double[] i : matrix) {
            for (int j = 0; j < i.length; j++) {
                if(j == i.length - 1) System.out.println(i[j]);
                else System.out.print(i[j] + " | ");
            }
        }
    }
}
