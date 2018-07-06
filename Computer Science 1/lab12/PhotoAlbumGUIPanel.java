import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Displays a JList of Photos and a preview of the photo. This class manages
 * layout of controls and also handles events.
 *
 * 1. I updated the index depending on which button was pressed. If the
 * nextbutton was pressed, then the index would increase one, and the opposite
 * result when prevbutton is pressed. Then I update the displayed photo at the
 * new index. 2. This program has a lot of great examples for creating GUI
 * interfaces, using this as a reference will make project 5 a lot easier.
 *
 *
 *
 *Lab 13 Reflection
 *
 *1. I assigned the event.getSource() to a variable called "clicked", I then run through all the photos
 * in the array and when the element is found the listener calls the displayPhoto method.
 *2. In the same method I assigned the selected value of photolist to the value of the photosquare.
 *3. This program will help me understand to how to put together my panels and set up listeners 
 *so that the functions of the buttons work properly.
 *
 * @author CS121 Jesse Lovitt
 * @author Connor Nagel
 */
@SuppressWarnings("serial")
public class PhotoAlbumGUIPanel extends JPanel {
	/** The data representing the list of photos in the album (the "model") */
	private PhotoAlbum album;

	/**
	 * The GUI representation of the list of photos in the album (the "view")
	 */
	private JList<Photo> photoList;

	private JLabel imageLabel; // Displays the image icon inside of the preview
								// panel
	private JButton nextButton; // Selects the next image in the photoList
	private JButton prevButton; // Selects the previous image in the photoList
	private JButton AddPhoto;							// */
	private Photo[][] photoSquare;
	private JButton[][] photoSquareButtons;

	/**
	 * Instantiates the photo album and adds all of the components to the
	 * JPanel.
	 */
	public PhotoAlbumGUIPanel() {
		setLayout(new BorderLayout());
		JPanel organizer = new JPanel();
		
		organizer.setLayout(new BoxLayout(organizer, BoxLayout.Y_AXIS));
		// Instantiate the album object and print it to the command line
		// to make sure it worked.
		album = new PhotoAlbum("Boise", "photos/album.dat");
		System.out.println(album);
		PhotoListListener PSL = new PhotoListListener();

		photoList = new JList<Photo>(album.getPhotoArray());
		photoList.setSelectedIndex(0);

		photoList.addListSelectionListener(PSL);

		JScrollPane JSP = new JScrollPane(photoList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		organizer.add(JSP);
		JPanel previewPanel = new JPanel();
		imageLabel = new JLabel();
		previewPanel.add(imageLabel);
		organizer.add(previewPanel);
		

	

	
	
	displayPhoto(photoList.getSelectedValue());
			JPanel controlPanel = new JPanel();
	
			ControlListener ControlList= new ControlListener();
			prevButton = new JButton("prev");
			nextButton = new JButton("next");
			AddPhoto = new JButton("Add Photo");
			prevButton.addActionListener(ControlList);
			nextButton.addActionListener(ControlList);
			AddPhoto.addActionListener(ControlList);
	
			controlPanel.add(prevButton);
			controlPanel.add(nextButton);
			organizer.add(controlPanel);
			photoSquare = album.getPhotoSquare();
			PhotoSquareListener PSL2= new PhotoSquareListener();
			photoSquareButtons = new JButton[photoSquare.length][photoSquare.length];
			for(int i = 0; i < photoSquare.length; i++){
				for(int j = 0; j < photoSquare.length; j++){
					
					photoSquareButtons[i][j] = new JButton();
					try {
						ImageIcon icon = new ImageIcon(ImageIO.read(photoSquare[i][j].getFile()));
						photoSquareButtons[i][j].setIcon(icon);
					} catch (IOException ex) {
						imageLabel.setText("Image not found :(");
					}
					photoSquareButtons[i][j].addActionListener(PSL2);
					
					
				}
					
				}
			
			JPanel imageGrid = new JPanel();
			imageGrid.setLayout(new GridLayout(photoSquare.length, photoSquare.length));
			imageGrid.setPreferredSize(new Dimension(300,300));
			for(int i = 0; i < photoSquare.length; i++){
				for(int j = 0; j < photoSquare.length; j++){
					imageGrid.add(photoSquareButtons[i][j]);
					
				}
				
			}
			this.add(organizer, BorderLayout.CENTER);
			this.add(imageGrid, BorderLayout.EAST);
			
	}

	private class PhotoSquareListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
		JButton clicked = (JButton) event.getSource(); 
		for(int i = 0; i < photoSquare.length; i++){
			for(int j = 0; j < photoSquare.length; j++){
				if(clicked == photoSquareButtons[i][j]){
					
					displayPhoto(photoSquare[i][j]);
					photoList.setSelectedValue(photoSquare[i][j], true);
					}	
				
			}
		}
		}
		
	}
	
	private class PhotoListListener implements ListSelectionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ListSelectionListener#valueChanged(java.awt.event.
		 * ListSelectionEvent)
		 */
		@Override
		public void valueChanged(ListSelectionEvent event) {
			// photo currently selected in the photoList.
			displayPhoto(photoList.getSelectedValue());
		}
	}

	private class ControlListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {

			int index = photoList.getSelectedIndex();
			JButton clicked = (JButton) event.getSource();
			if (clicked == prevButton) {
				index--;
				if (index < 0) {
					photoList.setSelectedIndex(5);

				}
			} else if (clicked == nextButton) {
				index++;
				if (index >= album.getNumPhotos()) {
					photoList.setSelectedIndex(0);
				}

			}
		

			photoList.setSelectedIndex(index);
		}
	}

	/**
	 * Updates the photo on the preview panel.
	 * 
	 * @param photo
	 *            The photo to display.
	 */
	private void displayPhoto(Photo photo) {
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(photo.getFile()));
			imageLabel.setIcon(icon);
		} catch (IOException ex) {
			imageLabel.setText("Image not found :(");
		}
	}

}
