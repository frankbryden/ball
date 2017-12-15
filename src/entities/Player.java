package entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import display.Window;
import events.EventListener;
import maths.Vector2d;

public class Player extends Character{
	private MyEventListener eventListener;
	
	public Player(String pathToTexture, Vector2d initialPosition) {
		super(pathToTexture, initialPosition);
		eventListener = new MyEventListener();
		Window.attachEventListener(eventListener);
	}

	@Override
	public void update(long delta) {
		this.position.x += this.velocity.x;
		this.position.y += this.velocity.y;
	}
	
	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(texture, position.x, position.y, null);
		
	}
	
	class MyEventListener implements EventListener{

		@Override
		public void click(Vector2d position) {
			
		}

		@Override
		public void keyPress(int keycode) {
			System.out.println("Move");
			switch (keycode) {
				case KeyEvent.VK_Z:
					moveUp();
					break;
				case KeyEvent.VK_S:
					moveDown();
					break;
				case KeyEvent.VK_Q:
					moveLeft();
					break;
				case KeyEvent.VK_D:
					moveRight();
					break;
			}
		}

		@Override
		public void keyRelease(int keycode) {
			System.out.println("Move");
			switch (keycode) {
				case KeyEvent.VK_Z:
				case KeyEvent.VK_S:
					stopY();
					break;
				case KeyEvent.VK_Q:
				case KeyEvent.VK_D:
					stopX();
					break;
			}
			
		}
		
	}
	
	
	public void moveUp() {
		this.velocity.y = -2;
	}
	
	public void moveDown() {
		this.velocity.y = 2;
	}
	
	public void moveLeft() {
		this.velocity.x = -2;
	}
	
	public void moveRight() {
		this.velocity.x = 2;
	}
	
	public void stopX() {
		this.velocity.x = 0;
	}
	
	public void stopY() {
		this.velocity.y = 0;
	}

}
