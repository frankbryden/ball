package display;

import entities.Drawable;
import entities.Entity;

public class Renderer {
	private Canvas canvas;
	
	public Renderer() {
		this.canvas = new Canvas();
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public void processDrawable(Drawable d){
		this.canvas.addDrawable(d);
	}

}
