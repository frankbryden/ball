package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import display.Window;
import maths.Vector2d;
import maths.Vector2f;

public class Rain extends ParticleSystem{
	private static final int WIDTH = 1;
	private static final int HEIGHT = 5;

	public Rain(int numberOfParticles) {
		super(numberOfParticles);
		
		
		
	}
	
	@Override
	public void spawnParticles(int length){
		Random r = new Random(System.currentTimeMillis());
		
		int window_center_x = Window.getMyWidth()/2; 
		float wind = (Window.getMousePos().x - window_center_x)/50;
		
		float gravity = Window.getMousePos().y * 10 /Window.getMyHeight();
		
		for (int i = 0; i < length; i++){
			particles.add(new Line(new Vector2d(r.nextInt(Window.getMyWidth()), r.nextInt(Window.getMyHeight()/2)), new Vector2f((r.nextFloat() + wind), (r.nextFloat() + gravity)), WIDTH, HEIGHT, getRandColor(r)));
		}
	}
	
	@Override
	public void update(long delta) {
		Iterator<Particle> it = particles.iterator();
		while (it.hasNext()){
			Line particle = (Line) it.next();
			/*int distance_from_mouse = particle.shape.getPosition().distance(Window.getMousePos());
			if (particle.shape.getPosition().y < Window.getMousePos().y) {
				distance_from_mouse *= -1;
			}
			distance_from_mouse = (distance_from_mouse == 0) ? 1 : distance_from_mouse;*/
			particle.setVelMultiplier(1);
			particle.update(delta);
			if (particle.isDead()){
				it.remove();
			}
		}
		if (getCount() < MAX_PARTICLES){
			spawnParticles(MAX_PARTICLES - getCount());
		}
		//System.out.printf("Particles : %d%n", getCount());
		
	}
	

}
