package entities;

import maths.Vector2d;

public abstract class Entity implements Drawable{
	protected Shape shape;
	
	public abstract void update(long delta);
	
}
