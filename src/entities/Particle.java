package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import display.Window;
import maths.Vector2d;
import maths.Vector2f;

public abstract class Particle extends Entity {
	protected Vector2f velocity;
	protected int velocity_multiplier;
	protected int lifeTime;
	protected final long birth_time = System.currentTimeMillis();;
	private boolean dead;
	
	public Particle(Vector2d position, Vector2f initialVelocity) {
		this(position, initialVelocity, 3000);

	}
	
	public Particle(Vector2d position, Vector2f initialVelocity, int lifeTime) {
		super();
		
		this.velocity = initialVelocity;
		
		//set a default lifetime to 3s
		this.lifeTime = 3000;
		
		this.dead = false;
	}
	
	public Particle() {}
	
	@Override
	public void draw(Graphics2D graphics) {
		this.shape.draw(graphics);
	}
	
	public void setVelMultiplier(int multiplier) {
		this.velocity_multiplier = multiplier;
	}
	
	public void checkDeath() {
		if (!Window.inScreen(this.shape.getPosition())){
			this.kill();
		} else if ((System.currentTimeMillis() - this.birth_time) > this.lifeTime) {
			this.kill();
		}
	}
	
	public abstract void update(long delta);
	
	protected void kill() {
		this.dead = true;
	}
	
	public boolean isDead(){
		return this.dead;
	}
	
	@Override
	public String toString(){
		return "Particle at (" + this.shape.getPosition().x + "; " + this.shape.getPosition().y + ")";
	}
	
	public Vector2d getPosition() {
		return this.shape.getPosition();
	}
	
	protected float getAge() {
		return (System.currentTimeMillis() - this.birth_time)/this.lifeTime;
	}
}
