package SimulatorTriangulation;

import java.util.Random;

import ObjectTriangulation.Coordinates;

public class Simulation {
		
	private final Coordinates[] drones = new Coordinates[5];
	private final double[][] distances = new double[5][5];
	
	public Simulation() {
		Random r = new Random();
		for( int i = 0; i < drones.length; i++){
			drones[i] = new Coordinates(r.nextInt(), r.nextInt(), r.nextInt());
		}
		for( int i = 0; i < distances.length; i++){
			for(int j = 0; j < distances.length; j++){
				if(i == j){
					distances[i][j] = 0;
				}
				else {
					double calculX = Math.pow(drones[i].getX() - drones[j].getX(), 2);
					double calculY = Math.pow(drones[i].getY() - drones[j].getY(), 2);
					double calculZ = Math.pow(drones[i].getZ() - drones[j].getZ(), 2);
					distances[i][j] = Math.sqrt(calculX+calculY+calculZ);
				}				
			}
		}
	}
	
	public static Coordinates calculPosition(Coordinates[] drones, double[] distance ) {
		return null;
	}
	
	public boolean testCalculPosition() {
		for(int i = 0; i < drones.length; i++) {
			Coordinates[] calculDrone = new Coordinates[5];
			double[] dist = new double[5];
			int cDrone = 0;
			for(int j = 0; j < drones.length; j++){
				if( i != j) {
					calculDrone[cDrone] = drones[j];
					dist[cDrone] = distances[i][j];
					cDrone++;
				}				
			}
			Coordinates c = calculPosition(calculDrone, dist);
			
			if(c != null && !c.equals(drones[i])){
				return false;
			}
		}
		return true;
	}
	
	
}