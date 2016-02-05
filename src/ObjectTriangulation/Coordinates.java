package ObjectTriangulation;

public class Coordinates {

	private final double x;
	private final double y;
	private final double z;

	public Coordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	@Override
	public boolean equals(Object o){		
		if (o != null) { 		
			if (o instanceof Coordinates) {
				Coordinates c  = (Coordinates) o;
				if (c.x == this.x && c.y == this.y && c.z == this.z) {
					return true;
				}
			}
		}
		return false;	
	}
	
	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}