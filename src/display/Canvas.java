package display;

import entities.Drawable;
import entities.Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Drawable> drawables;
	//private Graphics2D graphics;
	
	public Canvas() {
		this.drawables = new ArrayList<Drawable>();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics = (Graphics2D) g;
		
		graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Window.getMyWidth(), Window.getMyHeight());
		for(Drawable d : drawables){
			if (graphics == null){
				System.err.println("G2D is NULL");
			}
			if (d == null){
				System.err.println("DRAWABLE is NULL");
			}
			if (graphics != null){
				d.draw(graphics);
			}
		}
	}
	
	public void draw(){
		
	}
	
	public void clearEntities(){
		this.drawables.clear();
	}
	
	public void addDrawable(Drawable d){
		if (!this.drawables.contains(d)){
			this.drawables.add(d);
		}
	}

}
