package SimulatorTriangulation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import ObjectTriangulation.Coordinates;
import Triangulation.GaussianElimination;
import Triangulation.Triangulation;

public class Drone {

	private final Coordinates coordinates;
	private final HashMap<Integer, Coordinates> neighbors;
	private final int id;
	private final Object lock = new Object();

	public Drone(int id) {
		this.id = id;
		Random r = new Random();
		coordinates = new Coordinates(r.nextInt(3000) + 1, r.nextInt(3000) + 1,
				r.nextInt(3000) + 1);
		neighbors = new HashMap<Integer, Coordinates>(3);
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public HashMap<Integer, Coordinates> getNeighbors() {
		synchronized (lock) {
			return neighbors;
		}
	}

	public int getId() {
		return id;
	}

	public void move() {
		Random r = new Random();
		synchronized (lock) {
			coordinates.setX(r.nextInt(3000) + 1);
			coordinates.setY(r.nextInt(3000) + 1);
			coordinates.setY(r.nextInt(3000) + 1);			
		}
	}

	public Coordinates computePosition(double[] distance) {
		Coordinates[] tmp = new Coordinates[4];
		int count = 0;
		synchronized (lock) {
			Iterator<Entry<Integer, Coordinates>> it = neighbors.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, Coordinates> entry = (Entry<Integer, Coordinates>) it.next();
				it.remove();
				tmp[count++] = entry.getValue();
			}

			double[][] matrix = Triangulation.createMatrix(tmp, distance);
			double[][] matrixCompute = Triangulation.createMatrixCompute(matrix);
			double[] result = GaussianElimination.gauss(matrixCompute);
			return new Coordinates(Math.round(result[0]), Math.round(result[1]), Math.round(result[2]));
		}
		
	}

	public Frame sendCoordinates() {
		synchronized (lock) {
			return new Frame(id, coordinates);
		}
	}

	public void receiveCoordinates(Frame frame) {
		synchronized (lock) {
			neighbors.put(frame.id, frame.coordinates);
		}
	}
}
