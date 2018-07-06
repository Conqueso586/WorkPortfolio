import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class creates a GUI interface that allows the user to load and
 * manipulate a playlist. It also keeps track of the amount of times a song is
 * played and based the number of plays changes the color of the song's grid
 * button gradually to red.
 * 
 * @author Connor Nagel
 */

@SuppressWarnings("serial")
public class MyTunesGUIPanel extends JPanel {

	private ImageIcon prevButtonImage, nextButtonImage, playButtonImage, stopButtonImage, UpArrowImage, DownArrowImage;

	private JButton prevButton, nextButton, playButton, stopButton, upArrow, downArrow;

	private JList<Song> Songlist;
	private MyTunesPlayList playlist;

	private Timer timer;

	private int squareNum = 4;

	private JPanel nowPlayingPanel, selectionPanel, organizer, songGridPanel, southPanel;

	private Song[][] musicSquare;
	JButton[][] musicSquareButtons;

	private JLabel songtitle, songartist, totalPlaytime, numberOfSongs, playlistName;

	Color buttonColor;

	Font font = new Font(Font.MONOSPACED, Font.BOLD, 12);

	public MyTunesGUIPanel() {
		setLayout(new BorderLayout());

		nowPlayingPanel = new JPanel();
		nowPlayingPanel.setLayout(new BoxLayout(nowPlayingPanel, BoxLayout.Y_AXIS));

		File file = new File("sounds/playlist.txt");
		playlist = new MyTunesPlayList(file);
		squareNum = (int) Math.ceil(Math.sqrt(playlist.getNumSongs()));
		playlistName = new JLabel("Playlist:" + playlist.getName());
		nowPlayingPanel.add(playlistName);

		totalPlaytime = new JLabel("Total Playtime:" + playlist.getTotalPlayTime());
		nowPlayingPanel.add(totalPlaytime);
		numberOfSongs = new JLabel("# of Songs:" + playlist.getNumSongs());
		nowPlayingPanel.add(numberOfSongs);

		Song[] songArray = playlist.getSongArray();
		Songlist = new JList<Song>(songArray);
		Songlist.setSelectedIndex(0);
		Songlist.setFont(font);
		ListListener LL = new ListListener();
		Songlist.addListSelectionListener(LL);

		organizer = new JPanel();
		organizer.setLayout(new BoxLayout(organizer, BoxLayout.Y_AXIS));

		JScrollPane JSP = new JScrollPane(Songlist, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		AddSongActionListener AddSongAL = new AddSongActionListener();
		RemoveSongActionListener RemoveSongAL = new RemoveSongActionListener();
		JButton addSong = new JButton("Add Song");
		addSong.addActionListener(AddSongAL);
		JButton removeSong = new JButton("Remove Song");
		removeSong.addActionListener(RemoveSongAL);

		// Selection Panel and ListControl methods.
		selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		ListControl LC = new ListControl();

		UpArrowImage = new ImageIcon("images/move-up-16.gif");
		DownArrowImage = new ImageIcon("images/move-down-16.gif");
		upArrow = new JButton(UpArrowImage);
		downArrow = new JButton(DownArrowImage);
		selectionPanel.add(upArrow);
		selectionPanel.add(downArrow);
		upArrow.addActionListener(LC);
		downArrow.addActionListener(LC);

		JPanel addRemove = new JPanel();
		addRemove.add(addSong);
		addRemove.add(removeSong);

		organizer.add(JSP);
		organizer.add(addRemove);

		// where buttons are created for the grid
		songGridPanel = new JPanel();
		songGridPanel.setLayout(new GridLayout(squareNum, squareNum));
		songGridPanel.setPreferredSize(new Dimension(500, 300));

		int songIndex = 0;
		musicSquare = playlist.getMusicSquare();
		musicSquareListener PSL2 = new musicSquareListener();
		musicSquareButtons = new JButton[musicSquare.length][musicSquare.length];
		for (int i = 0; i < musicSquare.length; i++) {
			for (int j = 0; j < musicSquare.length; j++) {

				buttonColor = getHeatMapColor(musicSquare[i][j].getTimesPlayed());
				songIndex = (i * musicSquare.length + j) % playlist.getNumSongs();

				Song[] songtitles = playlist.getSongArray();
				Song song = songtitles[songIndex];
				musicSquareButtons[i][j] = new JButton(song.getTitle());
				musicSquareButtons[i][j].addActionListener(PSL2);

				musicSquareButtons[i][j].setBackground(buttonColor);
				songGridPanel.add(musicSquareButtons[i][j]);

			}

		}

		// ControlPanel
		southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		ControlPanelListener CPL = new ControlPanelListener();
		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(1000, 200));
		prevButtonImage = new ImageIcon("images/media-skip-backward-32.gif");
		prevButton = new JButton(prevButtonImage);
		prevButton.addActionListener(CPL);
		nextButtonImage = new ImageIcon("images/media-skip-forward-32.gif");
		nextButton = new JButton(nextButtonImage);
		nextButton.addActionListener(CPL);
		TimerListener TL = new TimerListener();

		playButtonImage = new ImageIcon("images/play-32.gif");
		playButton = new JButton(playButtonImage);
		playButton.addActionListener(TL);
		playButton.addActionListener(CPL);
		stopButtonImage = new ImageIcon("images/stop-32.gif");
		stopButton = new JButton(stopButtonImage);
		stopButton.addActionListener(CPL);

		controlPanel.add(prevButton);
		controlPanel.add(playButton);
		controlPanel.add(stopButton);
		controlPanel.add(nextButton);

		// Song Info
		JPanel songInfoPanel = new JPanel();
		songInfoPanel.setLayout(new BoxLayout(songInfoPanel, BoxLayout.Y_AXIS));
		JLabel songInfo = new JLabel("Song Info:");
		songtitle = new JLabel("Title:" + Songlist.getSelectedValue().getTitle());
		songartist = new JLabel("Artist:" + Songlist.getSelectedValue().getArtist());

		songInfoPanel.add(songInfo);
		songInfoPanel.add(songtitle);
		songInfoPanel.add(songartist);
		southPanel.add(songInfoPanel);
		southPanel.add(controlPanel);

		this.add(selectionPanel, BorderLayout.WEST);
		this.add(organizer, BorderLayout.CENTER);
		this.add(songGridPanel, BorderLayout.EAST);
		this.add(nowPlayingPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);

		timer = new Timer(0, new TimerListener());
		timer.setRepeats(false);

	}

	/**
	 * The ControlPanelListener allows the play stop next and prev buttons to
	 * play the songs on the Jlist.
	 *
	 */
	private class ControlPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton clicked = (JButton) e.getSource();
			if (clicked == playButton) {
				int index = Songlist.getSelectedIndex();
				Songlist.getSelectedValue().play();

				startTimer();
				Update();
				Songlist.setSelectedIndex(index);
			}
			if (clicked == stopButton) {
				Songlist.getSelectedValue().stop();
				stopTimer();
			}
			if (clicked == nextButton) {
				Songlist.getSelectedValue().stop();
				int index = Songlist.getSelectedIndex() + 1;
				if (index > playlist.getNumSongs()) {
					index = 0;

				}
				playlist.playNext();
				timer.restart();
				startTimer();
				Update();
				Songlist.setSelectedIndex(index);

			}
			if (clicked == prevButton) {
				Songlist.getSelectedValue().stop();
				int index = Songlist.getSelectedIndex() - 1;
				if (index < 0) {
					index = playlist.getNumSongs();
					Songlist.setSelectedIndex(index);
				}
				playlist.playPrev();
				timer.restart();
				startTimer();
				Update();
				Songlist.setSelectedIndex(index);
			}

		}
	}

	/**
	 * When a grid button is pressed, the corresponding song has its play count incremented and the color of the button is then shifted red.
	 *
	 */
	private class musicSquareListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			JButton clicked = (JButton) event.getSource();
			String buttonText = clicked.getText();

			for (int i = 0; i < musicSquare.length; i++) {
				for (int j = 0; j < musicSquare.length; j++) {
					String buttonTest = musicSquareButtons[i][j].getText();

					if (buttonText.equals(buttonTest)) {

						musicSquare[i][j].play();
						Update();
						Songlist.setSelectedValue(musicSquare[i][j], true);
					}

				}
			}

		}
	}

	/**
	 * When the selected song is changed, the song info is then updated to represent the current song.
	 *
	 */
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {

			UpdateInfo();

		}

	}

	/**
	 * This allows the up and down buttons to move the selected song around the playlist.
	 *
	 */
	private class ListControl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			int index = Songlist.getSelectedIndex();
			JButton clicked = (JButton) event.getSource();
			if (clicked == upArrow) {
				playlist.moveUp(index);
				Update();
				Songlist.setSelectedIndex(index - 1);
			}
			if (clicked == downArrow) {
				playlist.moveDown(index);
				Update();
				Songlist.setSelectedIndex(index + 1);
			}

		}

	}

	/**
	 * Opens a panel that allows user input for a new song, then the new song is added to the JList.
	 *
	 */
	private class AddSongActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			showForm();
			Update();
		}
	}

	/**
	 * Opens a panel that asks for confirmation, then removes the selected song.
	 *
	 */
	private class RemoveSongActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			showForm2();
			Update();
		}
	}

	/**
	 * The listener that keeps track of how much time has passed since the timer started.
	 *
	 */
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			

		}
	}

	/**
	 * Given the number of times a song has been played, this method will return
	 * a corresponding heat map color.
	 *
	 * Sample Usage: Color color = getHeatMapColor(song.getTimesPlayed());
	 *
	 * This algorithm was borrowed from:
	 * http://www.andrewnoske.com/wiki/Code_-_heatmaps_and_color_gradients
	 *
	 * @param plays
	 *            The number of times the song that you want the color for has
	 *            been played.
	 * @return The color to be used for your heat map.
	 */
	private Color getHeatMapColor(int plays) {
		double minPlays = 0, maxPlays = PlayableSong.MAX_PLAYS; // upper/lower
																// bounds
		double value = (plays - minPlays) / (maxPlays - minPlays); // normalize
																	// play
																	// count

		// The range of colors our heat map will pass through. This can be
		// modified if you
		// want a different color scheme.
		Color[] colors = { Color.CYAN, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
		int index1, index2; // Our color will lie between these two colors.
		float dist = 0; // Distance between "index1" and "index2" where our
						// value is.

		if (value <= 0) {
			index1 = index2 = 0;
		} else if (value >= 1) {
			index1 = index2 = colors.length - 1;
		} else {
			value = value * (colors.length - 1);
			index1 = (int) Math.floor(value); // Our desired color will be after
												// this index.
			index2 = index1 + 1; // ... and before this index (inclusive).
			dist = (float) value - index1; // Distance between the two indexes
											// (0-1).
		}

		int r = (int) ((colors[index2].getRed() - colors[index1].getRed()) * dist) + colors[index1].getRed();
		int g = (int) ((colors[index2].getGreen() - colors[index1].getGreen()) * dist) + colors[index1].getGreen();
		int b = (int) ((colors[index2].getBlue() - colors[index1].getBlue()) * dist) + colors[index1].getBlue();

		return new Color(r, g, b);
	}

	/**
	 * Sets the duration of the timer based on the playtime of the song. The timer is then started.
	 */
	private void startTimer() {
		try {

			int delay = Songlist.getSelectedValue().getPlayTime() * 1000;

			timer.setInitialDelay(delay);

			timer.start();

		}

		catch (NumberFormatException e) {

		}
	}

	/**
	 * Stops the timer.
	 */
	private void stopTimer() {
		timer.stop();

		playlist.stop();
	}


	/**
	 * Updates the information of the selected song.
	 */
	public void UpdateInfo() {

		if (Songlist.getSelectedValue() != null) {
			songtitle.setText("Title:" + Songlist.getSelectedValue().getTitle());
			songartist.setText("Artist:" + Songlist.getSelectedValue().getArtist());
		}

	}

	/**
	 * Replaces all panels on the GUI so that changes in the panels are shown.
	 */
	public void Update() {
		Songlist.setListData(playlist.getSongArray());
		musicSquare = playlist.getMusicSquare();

		playlistName.setText("Playlist: " + playlist.getName());
		totalPlaytime.setText("Total Playtime: " + playlist.getTotalPlayTime());
		numberOfSongs.setText("# Of Songs:" + playlist.getNumSongs());
		remove(songGridPanel);
		int songIndex = 0;
		Song[][] musicSquare = playlist.getMusicSquare();
		musicSquareListener PSL2 = new musicSquareListener();
		JButton[][] musicSquareButtons = new JButton[musicSquare.length][musicSquare.length];
		for (int i = 0; i < musicSquare.length; i++) {
			for (int j = 0; j < musicSquare.length; j++) {

				buttonColor = getHeatMapColor(musicSquare[i][j].getTimesPlayed());
				songIndex = (i * musicSquare.length + j) % playlist.getNumSongs();

				Song[] songtitles = playlist.getSongArray();
				Song song = songtitles[songIndex];
				musicSquareButtons[i][j] = new JButton(song.getTitle());
				musicSquareButtons[i][j].addActionListener(PSL2);

				musicSquareButtons[i][j].setBackground(buttonColor);
				songGridPanel.add(musicSquareButtons[i][j]);

			}

		}

		songGridPanel = new JPanel();
		songGridPanel.setLayout(new GridLayout(squareNum, squareNum));
		songGridPanel.setPreferredSize(new Dimension(500, 300));

		for (int i = 0; i < musicSquareButtons.length; i++) {
			for (int j = 0; j < musicSquareButtons[i].length; j++) {

				songGridPanel.add(musicSquareButtons[i][j]);
			}
		}
		revalidate();

		remove(selectionPanel);
		this.add(selectionPanel, BorderLayout.WEST);

		remove(organizer);
		this.add(organizer, BorderLayout.CENTER);

		this.add(songGridPanel, BorderLayout.EAST);

		remove(nowPlayingPanel);

		this.add(nowPlayingPanel, BorderLayout.NORTH);

		remove(southPanel);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * The panel that is created when the addSong button is pressed.
	 */
	private void showForm() {
		JPanel formInputPanel = new JPanel();
		formInputPanel.setLayout(new BoxLayout(formInputPanel, BoxLayout.Y_AXIS));

		JTextField songField = new JTextField(20);
		JTextField artistField = new JTextField(20);
		JTextField playtimeField = new JTextField(5);
		JTextField fileField = new JTextField(20);

		formInputPanel.add(new JLabel("Song Name:"));
		formInputPanel.add(songField);
		formInputPanel.add(new JLabel("Artist Name: "));
		formInputPanel.add(artistField);
		formInputPanel.add(new JLabel("Playtime:"));
		formInputPanel.add(playtimeField);
		formInputPanel.add(new JLabel("File name: "));
		formInputPanel.add(fileField);

		int result = JOptionPane.showConfirmDialog(null, formInputPanel, "Add Song", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			String title = songField.getText();
			String artist = artistField.getText();
			int playtime = 0;
			String filename = fileField.getText();

			try {
				playtime = Integer.parseInt(playtimeField.getText());
				if (playtime < 0) {
					JOptionPane.showMessageDialog(null, "You can't have a negative playtime.");

				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Your playtime needs to be a number.");
			}
			Song newSong = new Song(title, artist, playtime, filename);
			playlist.addSong(newSong);
			Songlist.updateUI();

		}
	}

	/**
	 * The panel that is created when the remove button is pressed.
	 */
	private void showForm2() {
		JPanel formInputPanel = new JPanel();
		formInputPanel.setLayout(new BoxLayout(formInputPanel, BoxLayout.Y_AXIS));
		formInputPanel.add(new JLabel("Are you sure you want to remove the selected song?"));

		int result = JOptionPane.showConfirmDialog(null, formInputPanel, "Add Song", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			playlist.removeSong(Songlist.getSelectedIndex());

		}
	}
}
