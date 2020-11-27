package ImageManipulation;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonUtils {
	
	final Dimension btnSize = new Dimension(250, 55);
	
	private ButtonListener listener;

	public ButtonUtils(ButtonListener listener) {
		this.listener = listener;
	}
	
	public JPanel getBtnAPanel() {
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		JButton colorButton = new JButton("Change Color");		
		colorButton.setPreferredSize(btnSize);
		
		String tips = "<html><div style='margin:0 -3 0 -3; padding: 0 3 0 3; "
				+ "background:#40E0D0;'>"
				+ "<b>Change the color of the image.</b></html>";		
		colorButton.setToolTipText(tips);
		
		btnPanel.add(colorButton);
		colorButton.addActionListener(listener);
		
		return btnPanel;
	}
	
	public JPanel getBtnBPanel() {
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		JButton rotateButton = new JButton("Rotate Image");		
		rotateButton.setPreferredSize(btnSize);
		
		String tips = "<html><div style='margin:0 -3 0 -3; padding: 0 3 0 3; "
				+ "background:#40E0D0;'>"
				+ "<b>Rotate the image 180 &#176</b></html>";		
		rotateButton.setToolTipText(tips);
		
		btnPanel.add(rotateButton);
		
		rotateButton.addActionListener(listener);
		
		return btnPanel;
	}
	
	public JPanel getBtnCPanel() {
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton sizeButton = new JButton("Re-Size");
		sizeButton.setPreferredSize(btnSize);
		
		String tips = "<html><div style='margin:0 -3 0 -3; padding: 0 3 0 3; "
				+ "background:#40E0D0;'>"
				+ "<b>Shrink the image to half it's size with each click.<br>"
				+ "You can see the image previous size and new size below.</b></html>";		
		sizeButton.setToolTipText(tips);
		
		btnPanel.add(sizeButton);
		
		sizeButton.addActionListener(listener);
		
		return btnPanel;
	}
	
	public JPanel getBtnDPanel() {
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton borderButton = new JButton("Change Border");		
		borderButton.setPreferredSize(btnSize);
		
		String tips = "<html><div style='margin:0 -3 0 -3; padding: 0 3 0 3; "
				+ "background:#40E0D0;'>"
				+ "<b>Add a green border to the image.<br>NOTE: Click \"Re-Size\" "
				+ "if you can not see the boarder."
				+ "<br>To get rid of the border press again."
				+ "</b></html>";		
		borderButton.setToolTipText(tips);
		
		btnPanel.add(borderButton);
		
		borderButton.addActionListener(listener);
		
		return btnPanel;
	}
	
	public JPanel getBtnEPanel() {
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
		JButton downloadButton = new JButton("Download");
		downloadButton.setPreferredSize(new Dimension(120, 55));
		
		String tips = "<html><div style='margin:0 -3 0 -3; padding: 0 3 0 3; "
				+ "background:#40E0D0;'>"
				+ "<b>Download a new image.<br>Check in project file for \"newImage.jpg\""
				+ "</b></html>";		
		downloadButton.setToolTipText(tips);

		btnPanel.add(downloadButton);
				
		downloadButton.addActionListener(listener);
		
		return btnPanel;
	}
	
	public JPanel getBtnFPanel() {
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
		JButton resetButton = new JButton("Reset");
		resetButton.setPreferredSize(new Dimension(120, 55));
		
		String tips = "<html><div style='margin:0 -3 0 -3; padding: 0 3 0 3; "
				+ "background:#40E0D0;'>"
				+ "<b>Reset to original image.</b></html>";		
		resetButton.setToolTipText(tips);

		btnPanel.add(resetButton);
				
		resetButton.addActionListener(listener);
		
		return btnPanel;
	}
	
}