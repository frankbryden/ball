import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

import display.Renderer;
import display.Window;
import entities.Circle;
import entities.Explosion;
import entities.Missile;
import entities.ParticleSystem;
import entities.Player;
import entities.Rain;
import events.EventListener;
import maths.Vector2d;
import maths.Vector2f;

public class Game {
	
	private Window myWindow;
	private ParticleSystem rain;
	private ArrayList<Explosion> explosions;
	private ArrayList<Missile> missiles;
	private Renderer renderer;
	private long timer;
	private MyClickListener clickListener;
	private Player player;
	
	private Circle circle;
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
		missiles = new ArrayList<Missile>();
		circle = new Circle(20, 20, 10);
		circle.setColor(Color.BLUE);
		player = new Player("character.png", new Vector2d(Window.getMyWidth()/2, Window.getMyHeight()/2));
		clickListener = new MyClickListener();
		myWindow.attachCanvas(renderer.getCanvas());
		Window.attachEventListener(clickListener);
	}
	
	public void run(){
		ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                gameLoop();
            }
        };
        
		Timer timer = new Timer(10, taskPerformer);
		timer.start();
	}
	
	public void gameLoop(){
		long now = System.currentTimeMillis();
		rain.update(now - timer);
		player.update(now - timer);
		
		/*Iterator<Explosion> it = explosions.iterator();
		while (it.hasNext()){
			Explosion e = it.next();
			e.update(now - timer);
			if (e.isDead()){
				it.remove();
			}
		}*/
		
		Iterator<Missile> it = missiles.iterator();
		while (it.hasNext()){
			Missile m = it.next();
			m.update(now - timer);
			if (m.isDead()){
				it.remove();
			}
		}
		
		
		renderer.getCanvas().clearEntities();
		/*renderer.processDrawable(rain);
		for (Explosion e: explosions){
			renderer.processDrawable(e);
		}*/
		for (Missile m: missiles){
			renderer.processDrawable(m);
		}
		renderer.processDrawable(player);
		renderer.processDrawable(circle);
		myWindow.redraw();
		
		
		timer = now;
	}
	
	class MyClickListener implements EventListener{

		@Override
		public void click(Vector2d position) {
			/*Explosion e = new Explosion(800, position, 50, 5000);
			explosions.add(e);*/
			//double angle = Math.atan2(player.getPosition().y - position.y, player.getPosition().x - position.x);
			Missile m = new Missile("missile.png", position, new Vector2f(1, 0), 1000);
			missiles.add(m);
			circle.setPosition(new Vector2d(position.x - 13, position.y - 35));
			
		}

		@Override
		public void keyPress(int keycode) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyRelease(int keycode) {
			// TODO Auto-generated method stub
			
		}
		
	}

}


