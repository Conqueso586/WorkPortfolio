import java.awt.Dimension;

import javax.swing.JFrame;

public class MyTunesGUI {
	public static void main(String[] args){
	JFrame frame = new JFrame("MyTunes");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(new MyTunesGUIPanel());
	frame.setPreferredSize(new Dimension(1000,800));
	frame.pack();
	frame.setVisible(true);
}
}
