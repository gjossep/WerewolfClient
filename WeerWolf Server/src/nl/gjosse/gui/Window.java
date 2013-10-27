package nl.gjosse.gui;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

import nl.gjosse.ext.StretchIcon;
import nl.gjosse.internet.Client;

public class Window {

	private JFrame frame;
	public static GameWindow gameWindow;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Client.start();
			}
		}, 1000L);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 913, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		gameWindow = new GameWindow();
		gameWindow.setBounds(6, 0, 901, 496);
		frame.getContentPane().add(gameWindow);
		
		JLabel backgroundImage = new JLabel("");
		backgroundImage.setIcon(new StretchIcon(Window.class.getResource("/nl/gjosse/res/background.jpg")));
		backgroundImage.setBounds(-12, 0, 960, 563);
		frame.getContentPane().add(backgroundImage);

	}
}
