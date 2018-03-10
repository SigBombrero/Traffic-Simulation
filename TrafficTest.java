import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TrafficTest {
	
	private static int DELAY = 100;
	private static int counter = 0;

	public static ActionListener changeSpeed(JTextField tf, Car c) {
		return e -> {
			String s = tf.getText();
			c.setSpeed(Integer.parseInt(s), 0);
		} ;
	}
	
	public static void main (String args[]) {
			
		JFrame window = new JFrame("Traffic Simulation");
		window.setBounds(400, 100, 600, 600);
		window.setBackground(Color.WHITE);
		
		Road road = new Road(600, 600);
		Car c1 = new Car(0, 600/2, 40, 20, 0, 50);
		Car c2 = new Car(600, 600/2-20, 40, -7, 0, -50);

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2, 2));

		JTextField tf1 = new JTextField(20);
		tf1.setText("Speed of Car1");
        	JButton button1 = new JButton("Change");

		JTextField tf2 = new JTextField(20);
		tf2.setText("Speed of Car2");
        	JButton button2 = new JButton("Change");
		
		button1.addActionListener(TrafficTest.changeSpeed(tf1, c1));

        	button2.addActionListener(TrafficTest.changeSpeed(tf2, c2));

        	controlPanel.add(tf1);
        	controlPanel.add(button1);
        	controlPanel.add(tf2);
        	controlPanel.add(button2);
		
		window.setLayout(new BorderLayout());
		road.addCar(c1);
		road.addCar(c2);

		window.add(controlPanel, BorderLayout.NORTH);
		window.add(road, BorderLayout.CENTER);

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
