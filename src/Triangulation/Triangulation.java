package Triangulation;

import ObjectTriangulation.*;

public class Triangulation {

	/*
	 * @param coordinates is an array of Coordinates. The size have be 4.
	 * 
	 * @param distance is an array that contains the distance between the nano
	 * drone you search the coordinates and the other nano drones
	 * 
	 * @return array of float. Values in array are the values to calculate.
	 */
	public static double[][] createMatrix(Coordinates[] coordinates, double[] distance) {
		double[][] matrix = new double[4][4];
		for (int i = 0; i < coordinates.length; i++) {
			double dist = (Math.pow(coordinates[i].getX(), 2)
					+ Math.pow(coordinates[i].getY(), 2) + Math.pow(
					coordinates[i].getZ(), 2));
			matrix[i][0] = -2 * coordinates[i].getX();
			matrix[i][1] = -2 * coordinates[i].getY();
			matrix[i][2] = -2 * coordinates[i].getZ();
			matrix[i][3] = distance[i] - dist;
		}
		return matrix;
	}
	
	public static void printMatrix(double[][] matrix){
		for(int i = 0; i < matrix.length; i++ ) {
			for(int j = 0; j < matrix[0].length; j++ ) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	

	public static double[][] createMatrixCompute(double[][] matrix) {
		double[][] matrixCompute = new double[3][4];
		for(int i = 0; i < matrixCompute.length; i++) {
			matrixCompute[i][0] = matrix[i+1][0] - matrix[0][0] ;
			matrixCompute[i][1] = matrix[i+1][1] - matrix[0][1] ;
			matrixCompute[i][2] = matrix[i+1][2] - matrix[0][2] ;
			matrixCompute[i][3] = matrix[i+1][3] - matrix[0][3] ;
		}
		return matrixCompute;
	}

	public static Coordinates triangulate(int[][] distance,
			Coordinates[] coordinates) {
			
		return new Coordinates(0, 0, 0);
	}
	

}
