package display;


import java.awt.AWTEvent;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import events.EventListener;
import maths.Vector2d;

public class Window extends JFrame{
	private static final int WIDTH = 1080;
	private static final int HEIGHT = 720;
	private static ArrayList<EventListener> eventListeners;
	
	private Canvas canvas;
	
	public Window() {
		super("Window");
		super.setSize(WIDTH, HEIGHT);
		super.setVisible(true);
		super.setAlwaysOnTop(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	System.out.println("Exiting...");
		        System.exit(0);
		        System.out.println("Done.");
		    }
		});
		eventListeners = new ArrayList<EventListener>();
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if(event instanceof MouseEvent){
                    MouseEvent evt = (MouseEvent)event;
                    if(evt.getID() == MouseEvent.MOUSE_CLICKED){
                        for (EventListener eventListener: eventListeners){
                        	Vector2d clickPos = new Vector2d(evt.getPoint().x, evt.getPoint().y);
                        	eventListener.click(clickPos);
                        }
                    }
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
	}
	
	public void attachCanvas(Canvas c){
		this.canvas = c;
		this.add(canvas);
		this.canvas.revalidate();
	}
	
	public static void attachEventListener(EventListener eventListener){
		eventListeners.add(eventListener);
	}
	
	public static int getMyWidth(){
		return WIDTH;
	}
	
	public static Vector2d getMousePos() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		return new Vector2d(p.x, p.y);
	}
	
	public static boolean inScreen(Vector2d position){
		if (position.x > 0 && position.x < WIDTH){
			/* We now know the object is within the left-right bounds of the screen */
			if (position.y > 0 && position.y < HEIGHT){
				/* We now know the object is within the top-down bounds of the screen */
				
				/* It therefore must be in the screen */
				return true;
			}
		}
		return false;
	}
	
	public static int getMyHeight(){
		return HEIGHT;
	}
	
	public void redraw(){
		if (this.canvas != null){
			this.canvas.repaint();
		}
	}
}
