package SimulatorTriangulation;

import java.util.stream.IntStream;

import ObjectTriangulation.Coordinates;
import Triangulation.Triangulation;


public class Main {

	private static final int nbDrones = 4;
	private final static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
	  /*int counter = 0;

		for(int i = 0; i < 1000; i++) {
			Simulation s = new Simulation();
			if(s.testComputePosition()) {
				counter++;
			}
		}
		System.out.println(counter);*/
		final Drone[] drones = new Drone[nbDrones];
		for (int i = 0; i < nbDrones; i++) {
			final Drone drone = new Drone(i);
			drones[i] = drone;
		}		
		Thread[] threads = new Thread[nbDrones];
		/*
		 * We launch a thread for each drone who receive coordinates from each drone,
		 * triangulate their positions and then move to a random position
		 **/
		IntStream.range(0, nbDrones).forEach(j -> {
			final int k = j;
			threads[j] = new Thread(() -> {
				while (!Thread.interrupted()) {
					double[] distance = new double[nbDrones];
					int count = 0;
					synchronized (lock) {
						for (int i = 0 ; i < drones.length; i++) {
							if (i != k) {
								drones[k].receiveCoordinates(drones[i].sendCoordinates());
								distance[count++] = Math.pow(Triangulation.computeDistance(drones[k].getCoordinates(), drones[i].getCoordinates()), 2);
								
							}
						}



						Coordinates result = drones[k].computePosition(distance);
						
						if(drones[k].getCoordinates().equals(result)) {
							System.out.println("Drone "+ k);
						}
						else {
							for(Thread thread : threads) {
								thread.interrupt();
							}
							System.out.println("Drone "+ k + "coord : "+drones[k].getCoordinates().toString()+"result: "+result.toString());
						}

						drones[k].move();
					}
				}
			});
		});
		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		System.out.println("end");
	}
}

