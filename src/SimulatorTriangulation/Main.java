package SimulatorTriangulation;

public class Main {

	public static void main(String[] args) {
		int count = 0;
		
		for(int i = 0; i < 1000; i++) {
			Simulation s = new Simulation();
			if(s.testCalculPosition()) {
				count++;
			}
		}
		System.out.println(count);		

	}

}
