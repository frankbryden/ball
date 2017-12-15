package maths;

public class Vector2d extends Vector{
	public int x, y;
	
	public Vector2d(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d(Vector2d copyOf) {
		this.x = copyOf.x;
		this.y = copyOf.y;
	}
	
	public int distance(Vector2d position) {
		return (int) Math.sqrt(Math.pow(this.x - position.x, 2) + Math.pow(this.y - position.y, 2));
	}

}
