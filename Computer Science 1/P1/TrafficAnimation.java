import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a Frog attempting to cross a busy street.
 *
 * @author Connor Nagel
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color GRASS_COLOR = new Color(0,153,0);
	private final Color SKY_COLOR = new Color(204,255,255);
	private final Color FROG_COLOR = new Color(102,204,0);
	 

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(GRASS_COLOR);
		g.fillRect(0, 0, width, height);

		g.setColor(SKY_COLOR);
		g.fillRect(0,0, width, height/3);
		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % width;

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen

		g.setColor(Color.gray);
		g.fillRect(0,(height/2)+(height/8),width,height/8);

		int squareSide = Math.min(width, height)/5;
		int squareY = height/2 - squareSide/2;
		g.setColor(Color.blue);
		g.fillRect(xOffset, squareY+(squareSide /4), squareSide, (squareSide/2)+(squareSide/4));
		
		g.setColor(Color.white);
		g.fillRect(xOffset + squareSide, squareY + (squareSide/2), squareSide /2, squareSide/2);
		
		g.setColor(Color.black);
		g.fillRect(xOffset + squareSide +(squareSide/6), squareY + (squareSide/2)+(squareSide/12), squareSide /3, (squareSide/3)-(squareSide/8));
		
		int oval_Height = squareSide / 3;
		int oval_Width = squareSide / 3;
		g.setColor(Color.black);
		g.fillOval(xOffset , squareY + squareSide,oval_Width , oval_Height);
		
		g.setColor(Color.black);
		g.fillOval(xOffset + squareSide, squareY + squareSide,oval_Width , oval_Height);
		
		
		
		//Avatar drawing
		int frogWidth = width / 16;
		int frogHeight = height/4;
		int frogHeadheight = frogHeight/3;
		int frogBodyheight =frogHeadheight * 2;
		
		
		g.setColor(Color.yellow);
		g.fillOval(width-width/8,0 - height/8,width/4,height/4);
		//Frog Head
		g.setColor(FROG_COLOR);
		g.fillOval((width / 2) - frogWidth /4, height - frogHeight +(frogHeight/8), frogWidth /2, frogHeadheight);
		
		//Frog Legs
		g.fillOval((width / 2) - (frogWidth /2),height -(frogBodyheight/4),frogWidth/4,frogBodyheight/4);
		g.fillOval((width / 2) + (frogWidth/2)-(frogWidth/4),height - (frogBodyheight/4),frogWidth/4,frogBodyheight/4);
		g.fillOval((width / 2) - (frogWidth /2),height -frogBodyheight,frogWidth/4,frogBodyheight/4);
		g.fillOval((width / 2) + (frogWidth/2)-(frogWidth/4),height - frogBodyheight,frogWidth/4,frogBodyheight/4);
		
		//Frog Body
		
		g.fillOval((width / 2) - (frogWidth /2),height - (2 * frogHeadheight),frogWidth, frogBodyheight );
		
		//Frog Face 
		g.setColor(Color.black);
		g.drawArc((width / 2) - frogWidth /4 , height - frogHeight +(frogHeight/8) +frogHeadheight/3 , frogWidth/2 , frogHeadheight /2 , 60 , 60);
		
		g.fillOval((width / 2) - frogWidth /4, height -frogBodyheight - (frogHeadheight/8), frogWidth/8, frogBodyheight/8);
		g.fillOval((width / 2) +(frogWidth /8), height - frogBodyheight - (frogHeadheight/8), frogWidth/8, frogBodyheight/8);
		//Text Line
		g.setColor(Color.black);
		g.setFont(new Font("Ariel", Font.ITALIC, 36));
		g.drawString("Go Frogger, Go!",20, 80);
		Toolkit.getDefaultToolkit().sync();
		
		
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
