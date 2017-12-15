package entities;

import java.awt.Color;

import maths.Vector2d;

public abstract class Shape implements Drawable{
	protected Color color;
	
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public abstract Vector2d getPosition();
	public abstract void setPosition(Vector2d position);
	protected abstract void setDimensions(int[] dimensions);
}
