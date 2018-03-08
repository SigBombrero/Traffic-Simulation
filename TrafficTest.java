import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TrafficTest {
	
	private static int DELAY = 100;
	private static int counter = 0;	
	
	public static void main (String args[]) {
			
		JFrame window = new JFrame("Traffic Simulation");
		window.setBounds(400, 100, 600, 600);
		window.setBackground(Color.WHITE);
		
		Road road = new Road(600, 600);
		Car c1 = new Car(0, 600/2, 40, 20, 0, 50);
		Car c2 = new Car(600, 600/2-20, 40, -7, 0, -50);
		
		JTextField tf1 = new JTextField(20);
		tf1.setText("Speed of Car1");
		JButton button1 = new JButton("Change");

		/*JTextField tf2 = new JTextField(20);
		tf1.setText("Speed of Car2");
		JButton button2 = new JButton("Change");*/
		
		
		button1.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent event) {
				String s = tf1.getText();
				c1.setSpeed(Integer.parseInt(s), 0);
			}
		});

		/*button2.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent event) {
				String s = tf2.getText();
				c2.setSpeed(Integer.parseInt(s), 0);
			}
		});*/
		
		road.setLayout(new FlowLayout());
		road.addCar(c1);
		road.addCar(c2);
		
		road.add(tf1);
		road.add(button1);
		// road.add(tf2);
		// road.add(button2);
		
		window.add(road);
		
		
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		
		Timer t = new Timer (DELAY, new ActionListener() 
		{
			public void actionPerformed(ActionEvent event) {
				
				road.recoverCars();
				
				counter += DELAY;
				if (counter%5000 == 0) {
					counter = 0;
					road.setLight();
				}
				road.moveCar();
				road.checkPastWindow();
				road.repaint();
				road.stopCars();
			}
		});
		
		t.start();
		
	}
	
	
}
