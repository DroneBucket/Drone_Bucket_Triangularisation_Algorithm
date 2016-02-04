package Triangulation;

public class GaussianElimination {

	private static void addition(double[][] matrix, int rowI, int rowJ, double mu){
		if(rowI >= matrix.length || rowJ >= matrix.length) {
			throw new IllegalArgumentException("rowI or rowJ is out of array matrix");
		}
		if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0){
			throw new IllegalArgumentException("rowI or rowJ is out of array matrix");
		}
		int m = matrix[0].length;
		for(int i = 0; i < m; i++) {
			matrix[rowI][i] += mu * matrix[rowJ][i];
		}
	}

	private static void exchangeLine(double[][] matrix, int rowI, int rowJ){
		if(rowI >= matrix.length || rowJ >= matrix.length) {
			throw new IllegalArgumentException("rowI or rowJ is out of array matrix");
		}
		if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0){
			throw new IllegalArgumentException("rowI or rowJ is out of array matrix");
		}
		double tmp = 0;
		for(int i = 0; i < matrix[0].length; i++){
			tmp = matrix[rowI][i];
			matrix[rowI][i] = matrix[rowJ][i];
			matrix[rowJ][i] = tmp;
		}
	}

	private static int searchPivot(double matrix[][], int line) {
		int pivot = line;
		for(int i = line+1; i < matrix.length; i++){
			if(Math.abs(matrix[i][line]) > Math.abs(matrix[pivot][line])) {
				pivot = i; 
			}
		}
		return pivot;
	}

	public static double[] gauss(double[][] matrix){
		for(int i = 0; i < matrix.length; i++) {
			int j = searchPivot(matrix,i);
			if(j != i){
				exchangeLine(matrix, i, j);
			}
			for(j = i+1; j < matrix.length; j++){
				double mu = -matrix[j][i]/matrix[i][i];
				addition(matrix, j, i, mu);
			}

		}
		int matrixS = matrix.length;
		double[] x = new double[matrixS];
		for (int i = matrixS - 1; i >= 0; i--) {
			double sum = 0;
			for (int j = i + 1; j < matrixS; j++) {
				sum += matrix[i][j] * x[j];
			}
			x[i] = (matrix[i][matrixS] - sum) / matrix[i][i];
		}
		return x;
	}
	
	public static void Test(double[][] matrix){
		addition(matrix, 0, 1, 2);
	}
}
