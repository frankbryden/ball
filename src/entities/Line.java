package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import maths.Vector2d;
import maths.Vector2f;

public class Line extends Particle{
	
	private Vector2d previousPosition;
	
	public Line(Vector2d position, Vector2f initialVelocity, int width, int height, Color color) {
		super(position, initialVelocity);
		this.previousPosition = new Vector2d(position.x, position.y);
		this.shape = new Rect(position, new Vector2d(width, height));
		this.shape.setColor(color);
	}

	@Override
	public void update(long delta) {
		previousPosition.x = this.shape.getPosition().x;
		previousPosition.y = this.shape.getPosition().y;
		
		this.shape.getPosition().x += this.velocity.x * delta/10;
		this.shape.getPosition().y += this.velocity.y * delta/10;
		
		checkDeath();
	}
	

}
