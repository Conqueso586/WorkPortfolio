import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Partial class for lab exercise using Random, Math and Color classes to draw
 * random color blobs.
 *
 * @author CS121 instructors (starter code)
 * @author Your name
 */
@SuppressWarnings("serial")
public class ColorBlobs extends JPanel
{
	// The variables below are instance variables, which are visible and usable from all the
	// methods in this class. They represent the state of the object of this class.

	private final int TIMER_DELAY = 500; //milliseconds
	private Random rand = new Random();

	//Note: no other instance variables are necessary for this project

	/**
	 * This method draws on the panel. It is called periodically by the
	 * animation thread.
	 */
	public void paintComponent(Graphics page)
	{
		int width = getWidth(); // width of the drawing panel
		int height = getHeight(); // height of the drawing panel
		
		int w = rand.nextInt(width/2 -width/20)+ width/20;
		int h = rand.nextInt(height/2 - height/20)+height/20;
		
		int x = rand.nextInt(width - w);
		int y = rand.nextInt(height - h);
		
		page.setColor(new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));

		page.fillOval(x,y,w,h);
		




   		//Make the animation smoother (don't modify!)
		Toolkit.getDefaultToolkit().sync();
	}

	//========================================================================
	// DO NOT MODIFY BEYOND THIS POINT
	//========================================================================

	/**
	 * Initialize the ColorBlobs class.
	 */
	public ColorBlobs()
	{
		setBackground(Color.black);
		startAnimation();
		setPreferredSize(new Dimension(600, 600));
	}


	/**
	 * Create an animation thread that runs periodically. DO NOT MODIFY
	 */
	private void startAnimation()
	{
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				repaint(); // redraw the panel
			}
		};
		new Timer(TIMER_DELAY, taskPerformer).start();
	}

	/**
	 * The main method that starts up the application. DO NOT MODIFY.
	 * @param args
	 */
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Color Blobs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new ColorBlobs());
		frame.pack();
		frame.setVisible(true);
	}
}