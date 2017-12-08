package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import display.Window;
import maths.Vector2d;
import maths.Vector2f;

public class Dot extends Particle{
	
	public Dot(Vector2d position, Vector2f initialVelocity, int radius, Color color, int lifeTime) {
		super(position,initialVelocity);
		
		this.shape = new Circle(position, radius);
		this.shape.setColor(color);
	}
	
	public Dot(Vector2d position, Vector2f initialVelocity, int radius, Color color) {
		this(position, initialVelocity, radius, color, 3000);
	}

	@Override
	public void update(long delta) {
		int before = this.shape.getPosition().x;
		
		this.shape.getPosition().x += this.velocity.x * delta/10 * velocity_multiplier;
		this.shape.getPosition().y += this.velocity.y * delta/10 * velocity_multiplier; 
		checkDeath();
		int after = this.shape.getPosition().x;
		
	}
	
	public void setColor(Color color) {
		this.shape.setColor(color);
	}

}
