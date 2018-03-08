import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Car {
	
	private int carX, carY, carWidth, speedX, speedY, maxSpeed;
	private boolean movable;	
	
	public Car(int x, int y, int width, int dx, int dy, int ls) {
		carX = x;
		carY = y;
		carWidth = width;
		speedX = dx;
		speedY = dy;
		maxSpeed = ls;
		movable = true;
	}
	
	public int getMaxSpeed()
	{
		return maxSpeed;
	}
	
	public int getX() {
		return carX;
	}
	
	public int getY() {
		return carY;
	}
	
	public void setX(int x) {
		carX = x;
	}
	
	public void setY(int y) {
		carY = y;
	}
	
	public void setSpeed(int dx, int dy) {
		if (maxSpeed > 0) {
			if(dx <= maxSpeed && dy <= maxSpeed)
			{
				speedX = dx;
				speedY = dy;
			}
		}
		else {
			if(dx >= maxSpeed && dy >= maxSpeed && dx < 0) 
			{
				speedX = dx;
				speedY = dy;
			}
		}

	}
	
	
	public int getSpeed() {
		if (speedX == 0) {
			return speedY;
		}
		return speedX;
	}
	
	public int getWidth() {
		return carWidth;
	}
	
	public boolean getMovable() {
		return movable;
	}
	
	public void setMovable (boolean p) {
		movable = p;
	}
	
	public void translate () {
		if(movable) {
			carX += speedX;
			carY += speedY;
		}
	}
	
	public void draw(Graphics2D g2)
	   {
	      Rectangle2D.Double body
	            = new Rectangle2D.Double(carX, carY + carWidth / 6, 
	            		carWidth - 1, carWidth / 6);
	      Ellipse2D.Double frontTire
	            = new Ellipse2D.Double(carX + carWidth / 6, carY + carWidth / 3, 
	            		carWidth / 6, carWidth / 6);
	      Ellipse2D.Double rearTire
	            = new Ellipse2D.Double(carX + carWidth * 2 / 3, carY + carWidth / 3,
	            		carWidth / 6, carWidth / 6);

	      // The bottom of the front windshield
	      Point2D.Double r1
	            = new Point2D.Double(carX + carWidth / 6, carY + carWidth / 6);
	      // The front of the roof
	      Point2D.Double r2
	            = new Point2D.Double(carX + carWidth / 3, carY);
	      // The rear of the roof
	      Point2D.Double r3
	            = new Point2D.Double(carX + carWidth * 2 / 3, carY);
	      // The bottom of the rear windshield
	      Point2D.Double r4
	            = new Point2D.Double(carX + carWidth * 5 / 6, carY + carWidth / 6);
	      Line2D.Double frontWindshield
	            = new Line2D.Double(r1, r2);
	      Line2D.Double roofTop
	            = new Line2D.Double(r2, r3);
	      Line2D.Double rearWindshield
	            = new Line2D.Double(r3, r4);
	      
	      g2.draw(body);
	      g2.draw(frontTire);
	      g2.draw(rearTire);
	      g2.draw(frontWindshield);
	      g2.draw(roofTop);
	      g2.draw(rearWindshield);
	   }
	
}
