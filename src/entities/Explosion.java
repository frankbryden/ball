package entities;

import java.awt.Color;
import java.util.Iterator;
import java.util.Random;

import display.Window;
import maths.Vector2d;
import maths.Vector2f;

public class Explosion extends ParticleSystem{
	private static final Color INITIAL_COLOR = new Color(208, 241, 109);
	private static final Color FINAL_COLOR = new Color(211, 38, 38);
	private Vector2d position;
	private Color color;
	private Random r;
	private final int explosion_radius;
	private final int centre_radius;
	private boolean dead = false;
	private long birthTime;
	private long lifeTime;

	public Explosion(int numberOfParticles, Vector2d position, int explosion_radius, long lifeTime) {
		super(numberOfParticles);
		
		this.lifeTime = lifeTime;
		this.birthTime =  System.currentTimeMillis();
		this.position = position;
		this.color = new Color(0, 0, 0);
		this.r = new Random(System.currentTimeMillis());
		this.explosion_radius = explosion_radius;
		this.centre_radius = this.explosion_radius/2;
		
	}
	
	private Color getColorAtAge(float age) {
		int r = (int) lerp(INITIAL_COLOR.getRed(), FINAL_COLOR.getRed(), age);
		int g = (int) lerp(INITIAL_COLOR.getGreen(), FINAL_COLOR.getGreen(), age);
		int b = (int) lerp(INITIAL_COLOR.getBlue(), FINAL_COLOR.getBlue(), age);
		return new Color(r, g, b);
	}
	
	private Color getColoAtDist(float distance){
		int r = (int) lerp(INITIAL_COLOR.getRed(), FINAL_COLOR.getRed(), distance);
		int g = (int) lerp(INITIAL_COLOR.getGreen(), FINAL_COLOR.getGreen(), distance);
		int b = (int) lerp(INITIAL_COLOR.getBlue(), FINAL_COLOR.getBlue(), distance);
		return new Color(r, g, b);
	}
	
	private float lerp(int a, int b, float delta) 
	{
	    return (float) ((a * (1.0 - delta)) + (b * delta));
	}

	@Override
	public void update(long delta) {
		Iterator<Particle> it = particles.iterator();
		while (it.hasNext()){
			Particle particle = it.next();
			int distance_from_center = particle.getPosition().distance(this.position)/this.explosion_radius;
			if (distance_from_center > 1){
				particle.kill();
			} else {
				particle.shape.setColor(getColoAtDist(distance_from_center));
				particle.setVelMultiplier(1);
				particle.update(delta);
			}
			if (particle.isDead()){
				it.remove();
			}
		}
		
		
		if (getCount() < MAX_PARTICLES && (System.currentTimeMillis() - birthTime) < lifeTime){
			spawnParticles(r.nextInt(MAX_PARTICLES - getCount()));
		}
		checkDeath();
		//System.out.printf("Particles : %d%n", getCount());
		
	}
	
	private void checkDeath(){
		if ((System.currentTimeMillis() - birthTime) > lifeTime){
			this.dead = true;
		}
	}
	
	public boolean isDead(){
		return this.dead;
	}

	@Override
	public void spawnParticles(int length) {
		
		for (int i = 0; i < length; i++){
			float dir = r.nextFloat() * 2 * 3.14f;
			float x_vel = (float) Math.cos(dir) * directionModifier() * 1.5f;//((r.nextFloat()/2) + 1f) * directionModifier();
			float y_vel = (float) Math.sin(dir) * directionModifier() * 1.5f;//((r.nextFloat()/2) + 1f) * directionModifier();
			Vector2d spawn_position = new Vector2d(position.x - r.nextInt(centre_radius), position.y - r.nextInt(centre_radius));
			Dot p = new Dot(spawn_position, new Vector2f(x_vel, y_vel), RADIUS/2 + r.nextInt(6) , getRandColor(r), 500);
			
			p.setColor(new Color(200, 50, 50));
			particles.add(p);
		}
		
	}
	
	private int directionModifier() {
		return r.nextBoolean() ? 1 : -1;
	}
	
}
