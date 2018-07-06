
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Draws gradients across the width of the panel
 * @author ?
 */
@SuppressWarnings("serial")
public class GradientLooperFourColors extends JPanel {
	/* This method draws on the Graphics context.
	 * This is where your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics canvas) 
	{
		//ready to paint
		super.paintComponent(canvas);
		
		//account for changes to window size
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		
		final int GRADIENT_DIVISIONS = 256;
		final int NUM_GRADIENT_BARS = 1;

		//TODO: Your code goes here
		
		for(int x = NUM_GRADIENT_BARS; x < GRADIENT_DIVISIONS ; x++){
	for(int j = 0; j < 4; j++){
		switch(j){
			case 0:
			
				Color Gradient_color_grey = new Color(x,x,x);
				canvas.setColor(Gradient_color_grey);
				canvas.fillRect((width*x)/GRADIENT_DIVISIONS, j*(height/4), (width/GRADIENT_DIVISIONS) + 1, height/4);
				break;
			case 1: 
				Color Gradient_color_red = new Color(x,0,0);
				canvas.setColor(Gradient_color_red);
				canvas.fillRect((width*x)/GRADIENT_DIVISIONS, j*(height/4), (width/GRADIENT_DIVISIONS) + 1, height/4);
				break;
			case 2:
				Color Gradient_color_green = new Color(0,x,0);
				canvas.setColor(Gradient_color_green);
				canvas.fillRect((width*x)/GRADIENT_DIVISIONS, j*(height/4), (width/GRADIENT_DIVISIONS) + 1, height/4);
				break;
			case 3:
				Color Gradient_color_blue = new Color(0,0,x);
				canvas.setColor(Gradient_color_blue);
				canvas.fillRect((width*x)/GRADIENT_DIVISIONS, j*(height/4), (width/GRADIENT_DIVISIONS) + 1, height/4);
				break;
		}
	}}
		}	



	/**
	 * DO NOT MODIFY
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 */
	public GradientLooperFourColors() 
	{
		setBackground(Color.black);
		int initWidth = 768;
		int initHeight = 512;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);
	}

	/**
	 * DO NOT MODIFY
	 * Starting point for the program
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("GradientLooperFourColors");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GradientLooperFourColors());
		frame.pack();
		frame.setVisible(true);
	}
}