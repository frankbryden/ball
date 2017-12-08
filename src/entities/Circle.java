package entities;

import java.awt.Graphics2D;

import maths.Vector2d;

public class Circle extends Shape{
	Vector2d position;
	int radius;
	
	public Circle(Vector2d position, int r) {
		this.position = new Vector2d(position.x, position.y);
		this.radius = r;
	}
	
	public Circle(int x, int y, int r) {
		this.position = new Vector2d(x, y);
		this.radius = r;
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		if (graphics == null) {
			System.err.println("Failed in circle draw");
		}
		graphics.setColor(this.color);
		graphics.fillOval(this.position.x, this.position.y, this.radius, this.radius);
	}

	@Override
	public Vector2d getPosition() {
		return position;
	}
	
	@Override
	public String toString(){
		return "Circle at (" + this.position.x + "; " + this.position.y + ")";
	}
	
	@Override
	public void setDimensions(int[] dimensions) {
		// first element should be radius
		if (dimensions.length < 1) {
			System.err.println("not enough dimensions to get radius");
		}
		this.radius = dimensions[0];
	}
	
	

}
