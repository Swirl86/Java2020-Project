package ImageManipulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

	private JFrame frame;
	private FrameUtils frameUtils; // Class to build components for the frame

	public UserInterface() {
		frame = new JFrame("Image Design Interface");
		frameUtils = new FrameUtils();
	}

	@Override
	public void run() {

		frame.setPreferredSize(new Dimension(700, 800));
		frame.setMinimumSize(new Dimension(350, 700));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Set the layout
		createComponents(frame.getContentPane());

		frame.pack();
		frame.setLocationRelativeTo(null); // Open the frame in the middle of the screen
		frame.setVisible(true);
	}

	public void createComponents(Container container) {

		container.setLayout(new BorderLayout(5, 0));

		JPanel topPanel = frameUtils.createTopPanel();
		JPanel middlePanel = frameUtils.createMiddlePanel();
		JPanel bottomPanel = frameUtils.createBottomPanel();

		container.add(topPanel, BorderLayout.NORTH);
		container.add(middlePanel, BorderLayout.CENTER);
		container.add(bottomPanel, BorderLayout.SOUTH);
		container.setBackground(Color.lightGray);
	}

	public JFrame getFrame() {
		return frame;
	}

}
