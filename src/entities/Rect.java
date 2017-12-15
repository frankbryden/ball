package entities;

import java.awt.Graphics2D;

import maths.Vector2d;

public class Rect extends Shape{
	Vector2d position;
	Vector2d dimensions;
	
	public Rect(int x, int y, int w, int h) {
		this.position = new Vector2d(x, y);
		this.dimensions = new Vector2d(w, h);
	}
	
	public Rect(Vector2d position, Vector2d dimensions) {
		this.position = new Vector2d(position.x, position.y);
		this.dimensions = new Vector2d(dimensions.x, dimensions.y);
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.setColor(this.color);
		graphics.fillRect(this.position.x, this.position.y, this.dimensions.x, this.dimensions.y);
	}
	
	@Override
	public Vector2d getPosition(){
		return this.position;
	}
	
	@Override
	public void setPosition(Vector2d position) {
		this.position = position;
	}
	
	public void setDimensions(Vector2d dimensions) {
		this.dimensions = dimensions;
	}
	
	@Override
	public void setDimensions(int[] dimensions) {
		this.dimensions.x = dimensions[0];
		this.dimensions.y = dimensions[1];
	}
	
	
}
