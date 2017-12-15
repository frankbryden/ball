package entities;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import maths.Vector2d;
import maths.Vector2f;

public abstract class Character implements Drawable{
	protected Vector2d position;
	protected Vector2f velocity;
	protected Image texture;
	protected int width, height;
	
	public Character(String pathToTexture, Vector2d initialPosition) {
		this.position = new Vector2d(initialPosition);
		this.velocity = new Vector2f(0, 0);
		this.texture = new ImageIcon(pathToTexture).getImage();
		this.width = this.texture.getWidth(null);
		this.height = this.texture.getHeight(null);
		this.position.x -= this.width/2;
		this.position.y -= 2*this.height;
	}
	
	public abstract void draw(Graphics2D graphics);
	
	public Vector2d getPosition() {
		return this.position;
	}
	
	public abstract void update(long delta);
}
