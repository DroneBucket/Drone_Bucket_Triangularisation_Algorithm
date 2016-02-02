package ObjectTriangulation;

public class Coordinates {

	private final int x;
	private final int y;
	private final int z;

	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public boolean equals(Object o){		
		if(o != null && this != null){ 		
			if(o instanceof Coordinates) {
				Coordinates c  = (Coordinates) o;
				if(c.x == this.x && c.y == this.y && c.z == this.z) {
					return true;
				}
			}
		}
		return (o == null && this == null);
	
	}

}
