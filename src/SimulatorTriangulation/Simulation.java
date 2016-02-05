package SimulatorTriangulation;

import java.util.Random;
import ObjectTriangulation.Coordinates;
import Triangulation.*;


public class Simulation {
		
	private final Coordinates[] drones = new Coordinates[5];
	private final double[][] distances = new double[5][5];
	
	public Simulation() {
		Random r = new Random();
		for( int i = 0; i < drones.length; i++) {
			drones[i] = new Coordinates(r.nextInt(3000) + 1, r.nextInt(3000) + 1, r.nextInt(3000) + 1);
		}
		for( int i = 0; i < distances.length; i++) {
			for(int j = 0; j < distances.length; j++) {
				if(i == j) {
					distances[i][j] = 0;
				}
				else {
					double calculX = Math.pow(drones[i].getX() - drones[j].getX(), 2);
					double calculY = Math.pow(drones[i].getY() - drones[j].getY(), 2);
					double calculZ = Math.pow(drones[i].getZ() - drones[j].getZ(), 2);
					distances[i][j] = calculX + calculY + calculZ;
				}				
			}
		}
	}
	
	public static Coordinates computePosition(Coordinates[] drones, double[] distance ) {
		double[][] matrix = Triangulation.createMatrix(drones, distance);
		double[][] matrix2 = Triangulation.createMatrixCompute(matrix);
		double[] resultat = GaussianElimination.gauss(matrix2);
		System.out.println("resultat1 :" + resultat[0] + " " + resultat[1] + " " + resultat[2]);
		return new Coordinates(Math.round(resultat[0]), Math.round(resultat[1]), Math.round(resultat[2]));
	}	
	
	public boolean testComputePosition() {
		for(int i = 0; i < drones.length; i++) {
			Coordinates[] calculDrone = new Coordinates[4];
			double[] dist = new double[4];
			int cDrone = 0;
			for(int j = 0; j < drones.length; j++) {
				if( i != j) {
					calculDrone[cDrone] = drones[j];
					dist[cDrone] = distances[i][j];
					cDrone++;
				}				
			}
			
			System.out.println("drone " + drones[i].toString());
			Coordinates c = computePosition(calculDrone, dist);
			System.out.println("resultat " + c);
			if(c != null && !c.equals(drones[i])){
				return false;
			}
		}
		return true;
	}
	
	
}