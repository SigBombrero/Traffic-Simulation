import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Road extends JComponent{

	private int w;
	private int h;
	private ArrayList<Car> carList;
	private boolean isGreen;
	
	public Road(int x, int y){
		isGreen = true;
		w = x;
		h = y;
		carList = new ArrayList <Car>();
		
	}
	
	public void paintComponent(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g;
		
		if (isGreen) {
			g2.setColor(Color.GREEN);
			Ellipse2D.Double e1 = new Ellipse2D.Double(w/2-20, h/2+22.5, 15.0, 15.0);
			g2.fill(e1);
			Ellipse2D.Double e2 = new Ellipse2D.Double(w/2+5, h/2-37.5, 15.0, 15.0);
			g2.fill(e2);
		}
		else {
			g2.setColor(Color.RED);
			Ellipse2D.Double e1 = new Ellipse2D.Double(w/2-20, h/2+22.5, 15.0, 15.0);
			g2.fill(e1);
			Ellipse2D.Double e2 = new Ellipse2D.Double(w/2+5, h/2-37.5, 15.0, 15.0);
			g2.fill(e2);
		}
		
		
		g2.setColor(Color.BLACK);
		
		// Lines of the road (horizontal)
		Line2D.Double l1 = new Line2D.Double(0, h/2-20, w, h/2-20);
		g2.draw(l1);
		Line2D.Double l2 = new Line2D.Double(0, h/2+20, w, h/2+20);
		g2.draw(l2);
		
		// Lines where cars stop
		Line2D.Double l3 = new Line2D.Double(w/2-20, h/2, w/2-20, h/2+20);
		g2.draw(l3);
		Line2D.Double l4 = new Line2D.Double(w/2+20, h/2-20, w/2+20, h/2);
		g2.draw(l4);
		
		g2.setColor(Color.BLUE);
		
		for (Car c : carList) {
			c.draw(g2);
		}
		
	}
	
	
	public void addCar(Car c) {
		carList.add(c);
	}
	
	
	public void moveCar() {
		for (Car c : carList) {
			c.translate();
		}
	}
	
	public void recoverCars() {
		for(Car c: carList){
			if(isGreen && !c.getMovable())
				c.setMovable(true);
		}
	}
	
	
	public void setLight() {
		if (isGreen) {
			isGreen = false;
			return;
		}
		isGreen = true;
	}
	
	
	public void stopCars() {
		for(Car c : carList){
			if (c.getSpeed() > 0) {
				if(c.getX() + c.getWidth() + c.getSpeed() > w/2 - 20 && c.getX() + c.getWidth() + c.getSpeed() < w/2+20 && !isGreen)
					c.setX(w/2-20 - c.getWidth()); //have problem to fix later
				if (c.getX() + c.getWidth() == w/2-20 && !isGreen) {
					c.setMovable(false);
				}
			}else{
				if(c.getX() + c.getSpeed() > w/2 - 20 && c.getX() + c.getSpeed() < w/2+20 && !isGreen)
					c.setX(w/2+20); //have problem to fix later
				if (c.getX() == w/2+20 && !isGreen) {
					c.setMovable(false);
				}
			}
		}
		
	}
	
	public void checkPastWindow() {
		for(Car c : carList){
			if (c.getX() >= w && c.getSpeed()>0) {
				c.setX(0);
			}
			if(c.getX() <= 0 && c.getSpeed()<0){
				c.setX(w);
			}
		}
		
	}
	
	
}
