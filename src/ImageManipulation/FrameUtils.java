package ImageManipulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrameUtils {

	final int gap = 10;

	private ButtonListener listener;
	private ButtonUtils btnUtils;
	private ImageHandler img;

	// Used static so it can be use in ImageHandler
	public static JTextField imgSizeInfo; // To display the image size

	public FrameUtils() {
		img = new ImageHandler();
		listener = new ButtonListener(img);
		btnUtils = new ButtonUtils(listener);
		imgSizeInfo = new JTextField();
	}

	public JPanel createTopPanel() {

		JPanel topPanel = new JPanel(new GridLayout(1, 1));
		topPanel.setPreferredSize(new Dimension(700, 500));
		topPanel.add(img);
		topPanel.setBackground(Color.gray);
		return topPanel;
	}

	public JPanel createMiddlePanel() {

		JPanel middlePanel = new JPanel(new GridLayout(2, 2, 60, 10));

		JPanel btnAPanel = btnUtils.getBtnAPanel(); // Color Button
		JPanel btnBPanel = btnUtils.getBtnBPanel(); // Rotate Button
		JPanel btnCPanel = btnUtils.getBtnCPanel(); // Size Button
		JPanel btnDPanel = btnUtils.getBtnDPanel(); // Border Button

		middlePanel.add(btnAPanel);
		middlePanel.add(btnBPanel);
		middlePanel.add(btnCPanel);
		middlePanel.add(btnDPanel);

		btnAPanel.setBackground(Color.lightGray);
		btnBPanel.setBackground(Color.lightGray);
		btnCPanel.setBackground(Color.lightGray);
		btnDPanel.setBackground(Color.lightGray);
		middlePanel.setBackground(Color.lightGray);

		return middlePanel;
	}

	public JPanel createBottomPanel() {

		JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 60, 30));
		JPanel bottomButtonPanel = new JPanel(new GridLayout(1, 2));

		// Add some space gaps
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
		bottomButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, gap, 0, gap));

		JPanel btnEPanel = btnUtils.getBtnEPanel(); // Download Button
		JPanel btnFPanel = btnUtils.getBtnFPanel(); // Reset Button

		imgSizeInfo.setHorizontalAlignment(JTextField.CENTER);
		imgSizeInfo.setEditable(false);
		imgSizeInfo.setFont(imgSizeInfo.getFont().deriveFont(Font.BOLD, 12f));
		imgSizeInfo.setBackground(new Color(72, 209, 204));
		imgSizeInfo.setText("HINT: Click on Re-size button");

		bottomButtonPanel.add(btnEPanel);
		bottomButtonPanel.add(btnFPanel);

		bottomPanel.add(imgSizeInfo);
		bottomPanel.add(bottomButtonPanel);

		btnEPanel.setBackground(Color.lightGray);
		btnFPanel.setBackground(Color.lightGray);
		bottomButtonPanel.setBackground(Color.lightGray);
		bottomPanel.setBackground(Color.lightGray);

		return bottomPanel;
	}
}
