package ObjectTriangulation;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Coordinates {

	private double x;
	private double y;
	private double z;
	private final Object lock = new Object();

	public Coordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setX(double x) {
		synchronized (lock) {
			this.x = x;
		}
	}

	public void setY(double y) {
		synchronized (lock) {
			this.y = y;
		}		
	}

	public void setZ(double z) {
		synchronized (lock) {
			this.z = z;
		}
		
	}

	public double getX() {
		synchronized (lock) {
			return x;
		}
	}

	public double getY() {
		synchronized (lock) {
			return y;
		}
	}

	public double getZ() {
		synchronized (lock) {
			return z;
		}		
	}

	@Override
	public boolean equals(Object o){		
		synchronized (lock) {
			if (o != null) { 		
				if (o instanceof Coordinates) {
					Coordinates c  = (Coordinates) o;
					if (c.x == this.x && c.y == this.y && c.z == this.z) {
						return true;
					}
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