import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import display.Renderer;
import display.Window;
import entities.ParticleSystem;
import entities.Rain;
import entities.Explosion;
import events.EventListener;
import maths.Vector2d;

public class Game {
	
	private Window myWindow;
	private ParticleSystem rain;
	private ArrayList<Explosion> explosions;
	private Renderer renderer;
	private long timer;
	private MyClickListener clickListener;
	public static void main(String args[]){
		Game game = new Game();
		game.run();
	}
	
	public Game(){
		myWindow = new Window();
		rain = new Rain(50);
		timer = System.currentTimeMillis();
		renderer = new Renderer();
		explosions = new ArrayList<Explosion>();
		clickListener = new MyClickListener();
		myWindow.attachCanvas(renderer.getCanvas());
		myWindow.attachEventListener(clickListener);
	}
	
	public void run(){
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameLoop();
            }
        };
        /*
		Timer timer = new Timer(10, taskPerformer);
		timer.start();*/
        while (true){
        	gameLoop();
        	try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	public void gameLoop(){
		long now = System.currentTimeMillis();
		rain.update(now - timer);
		
		/*Iterator<Explosion> it = explosions.iterator();
		while (it.hasNext()){
			Explosion e = it.next();
			e.update(now - timer);
			if (e.isDead()){
				it.remove();
			}
		}*/
		
		
		//renderer.getCanvas().clearEntities();
		renderer.processDrawable(rain);
		for (Explosion e: explosions){
			renderer.processDrawable(e);
		}
		
		myWindow.redraw();
		
		
		timer = now;
	}
	
	class MyClickListener implements EventListener{

		@Override
		public void click(Vector2d position) {
			System.out.println("Click!");
			Explosion e = new Explosion(800, position, 50, 5000);
			explosions.add(e);
			
		}
		
	}

}
