package entities;

import java.awt.Graphics2D;

import display.Window;
import maths.Vector2d;
import maths.Vector2f;

public class Missile extends Character{
	private enum STATE {
		flying,
		exploding,
		dead
	}
	private STATE state;
	private long lifetime;
	private boolean alive = true;
	private Explosion explosion;

	public Missile(String pathToTexture, Vector2d initialPosition, Vector2f velocity, long lifetime) {
		super(pathToTexture, initialPosition);
		System.out.println("New missile");
		this.velocity = velocity;
		this.state = STATE.flying;
		this.lifetime = lifetime;
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		if (state == STATE.flying) {
			graphics.drawImage(texture, position.x, position.y, null);
		} else if (state == STATE.exploding) {
			this.explosion.draw(graphics);
		}
		
	}
	
	@Override
	public void update(long delta) {
		if (state == STATE.flying) {
			this.velocity.x += 0.1f;
			this.position.x += this.velocity.x;
			this.position.y += this.velocity.y;
			this.lifetime -= delta;
			if (this.lifetime < 0) {
				this.state = STATE.exploding;
				this.explosion = new Explosion(500, this.position, 20, 600);
			}
		
			if (!Window.inScreen(this.position)) {
				kill();
			}
		} else if (state == STATE.exploding) {
			this.explosion.update(delta);
			if (explosion.isDead()) {
				kill();
			}
		}
	}
	
	public void kill() {
		this.alive = false;
	}
	
	public boolean isDead() {
		return !this.alive;
	}
	
}
