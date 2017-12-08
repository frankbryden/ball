package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import display.Window;
import maths.Vector2d;
import maths.Vector2f;

public abstract class ParticleSystem implements Drawable{
	
	private final int BASE_RED = 150;
	private final int BASE_GREEN = 170;
	private final int BASE_BLUE = 210;
	protected final int RADIUS = 5;
	protected final int MAX_PARTICLES = 500;
	
	protected ArrayList<Particle> particles;
	protected int particleCount;
	protected float spawnTime;
	protected Random r;
		
	
	public ParticleSystem(int numberOfParticles) {
		this.r = new Random(System.currentTimeMillis());
		this.particles = new ArrayList<Particle>();
	}
	
	
	public abstract void update(long delta);
		/*
		Iterator<Particle> it = particles.iterator();
		while (it.hasNext()){
			Particle particle = it.next();
			int distance_from_mouse = particle.shape.getPosition().distance(Window.getMousePos());
			if (particle.shape.getPosition().y < Window.getMousePos().y) {
				distance_from_mouse *= -1;
			}
			particle.setVelMultiplier(500/distance_from_mouse);
			particle.update(delta);
			if (particle.isDead()){
				it.remove();
			}
		}
		if (getCount() < MAX_PARTICLES){
			spawnParticles(r.nextInt(MAX_PARTICLES) - getCount());
		}
		//System.out.printf("Particles : %d%n", getCount());
		
	}*/
	
	public int getCount(){
		return this.particles.size();
	}
	
	public void draw(Graphics2D graphics) {
		if (particles == null){
			System.err.println("Particles is null");
			Scanner s = new Scanner(System.in);
			s.nextInt();
		}
		for (Particle particle : particles) {
			particle.draw(graphics);
		}
	}
	
	
	public abstract void spawnParticles(int length);/*{
		Random r = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < length; i++){
			particles.add(new Dot(new Vector2d(r.nextInt(Window.getMyWidth()), r.nextInt(Window.getMyHeight())), new Vector2f((r.nextFloat() + 0.4f) * 2, (r.nextFloat() + 0.2f) * 2), RADIUS, getRandColor(r)) );
		}
		
	}*/
	
	protected Color getRandColor(Random r){
		
		return new Color(BASE_RED + r.nextInt(30) - 15, BASE_GREEN + r.nextInt(30) - 15, BASE_BLUE + r.nextInt(30) - 15, 255);
	}
	
}
