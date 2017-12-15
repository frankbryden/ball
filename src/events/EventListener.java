package events;

import maths.Vector2d;

public interface EventListener {
	public abstract void click(Vector2d position);
	public abstract void keyPress(int keycode);
	public abstract void keyRelease(int keycode);
}
