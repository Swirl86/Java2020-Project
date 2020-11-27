package ImageManipulation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class ImageHandler extends JComponent {

	final public String space = "       "; // Get correct output spacing

	private BufferedImage img;
	private BufferedImage orginalImg; // Will contain the original image and not be changed
	private boolean blackWhiteColor;
	private boolean borderAdded;
	private boolean rotaded;

	public ImageHandler() {
		img = null;
		orginalImg = null;
		blackWhiteColor = false;
		borderAdded = false;
		rotaded = false;

		loadImage();
	}

	public void paint(Graphics g) {
		// x,y coordinates = middle of the image JPanel
		Dimension dimension = this.getBounds().getSize();
		int x = (int) ((dimension.getWidth() / 2) - (img.getWidth() / 2));
		int y = (int) ((dimension.getHeight() / 2) - (img.getHeight() / 2));

		// Make img always centered in JFrame
		g.drawImage(img, x, y, img.getWidth(), img.getHeight(), this);
	}

	public void loadImage() {
		try {

			img = ImageIO.read(new File("image.jpg"));
//			getCorrectSize(); // Get the image size as the JPanel, no overflow
			orginalImg = deepCopy(img);
			System.out.println("Image imported! A image copy has been added.");

		} catch (IOException e) {
			// If something goes wrong a pop up warning will be shown
			UIManager.put("OptionPane.background", new Color(255, 51, 51));
			UIManager.put("Panel.background", new Color(255, 51, 51));

			JOptionPane.showMessageDialog(null, "The image could not be loaded!"
					+ "\nCheck the image name and location.",
					"WARNING", JOptionPane.WARNING_MESSAGE);
		}
	}

	// Resize the image to a wished size
	public void getCorrectSize(int width, int height) {
		BufferedImage newdBI = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = newdBI.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();

		img = deepCopy(newdBI);
	}

	// Don't loose the the image
	public BufferedImage deepCopy(BufferedImage orgin) {
		ColorModel cm = orgin.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = orgin.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public void changeColor() {

		String sizeInfo = space + " Prev Size: " + img.getWidth() 
		+ " : " + img.getHeight();

		if (blackWhiteColor) {
			addCurrentSizeOfImg(); // Set correct img size
			blackWhiteColor = false;
			// Make sure the img keeps the changed characteristics
			checkIfRotated();
			checkIfBorderExist();
		} else {
			int width = img.getWidth();
			int height = img.getHeight();

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color c = new Color(img.getRGB(j, i));
					int red = (int) (c.getRed() * 0.299);
					int green = (int) (c.getGreen() * 0.587);
					int blue = (int) (c.getBlue() * 0.114);
					Color newColor = new Color(red + green + blue, 
							red + green + blue, red + green + blue);
					img.setRGB(j, i, newColor.getRGB());
				}
			}
			blackWhiteColor = true;
		}

		repaint(); // get a component to repaint itself

		printImgSize(sizeInfo);
	}

	public void rotateImage() {

		// Used for checks to keep the img in correct rotated state
		if (rotaded) {
			rotaded = false;
		} else {
			rotaded = true;
		}

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight() / 2; j++) {

				int tmp = img.getRGB(i, j);
				img.setRGB(i, j, img.getRGB(i, img.getHeight() - j - 1));
				img.setRGB(i, img.getHeight() - j - 1, tmp);

			}
		}

		repaint();
	}

	public void reSize() {
		int width = ((img.getWidth() / 2) + 50);
		int height = ((img.getHeight() / 2) + 50);

		String sizeInfo = space + "Prev Size: " + img.getWidth() 
		+ " : " + img.getHeight();

		BufferedImage newdBI = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = newdBI.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();

		img = deepCopy(newdBI);
		printImgSize(sizeInfo);

		revalidate(); // Used when a component is added / removed, or changes size
		repaint();
	}

	public void addBorder() {
		if (borderAdded) {
			addCurrentSizeOfImg(); // Set correct img size
			checkIfRotated();
			checkColorChange();
			borderAdded = false;
		} else {
			Graphics2D g2 = (Graphics2D) img.getGraphics();
			g2.setStroke(new BasicStroke(50));
			g2.setColor(Color.GREEN);
			g2.drawRect(10, 10, img.getWidth() - 20, img.getHeight() - 20);

			borderAdded = true;
		}

		revalidate();
		repaint();
	}

	public void downloadImg() {

		File outputfile = new File("newImage.jpg");

		System.out.println("File name: " + outputfile.getName());
		try {
			ImageIO.write(img, "jpg", outputfile);

			if (outputfile.exists() && outputfile.canRead()) {
				JOptionPane.showMessageDialog(null, "The image succesfully downloaded!");
			}

		} catch (Exception e) {

			e.printStackTrace();

			UIManager.put("OptionPane.background", new Color(255, 51, 51));
			UIManager.put("Panel.background", new Color(255, 51, 51));

			JOptionPane.showMessageDialog(null, "The image could not be downloaded!", "Faild",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void resetImage() {

		img = deepCopy(orginalImg);

		String sizeInfo = "RESET! Current Size: " + img.getWidth() + " : " + img.getHeight() + space;
		FrameUtils.imgSizeInfo.setText(sizeInfo);

		revalidate();
		repaint();

		blackWhiteColor = false;
		borderAdded = false;
		rotaded = false;
	}

	public void printImgSize(String sizeInfo) {
		sizeInfo += "      New Size: " + img.getWidth() + " : " + img.getHeight() + space;
		FrameUtils.imgSizeInfo.setText(sizeInfo);
	}

	public void addCurrentSizeOfImg() {
		// Save current img size
		int width = img.getWidth();
		int height = img.getHeight();

		img = deepCopy(orginalImg); // Resets the image
		getCorrectSize(width, height); // Set correct size
	}

	/*
	 * Make sure to keep the image options and boolean value correct
	 */
	public void checkIfRotated() {
		if (rotaded) {
			rotateImage();
			rotaded = true;
		}
	}

	public void checkIfBorderExist() {
		if (borderAdded) {
			borderAdded = false;
			addBorder();
			borderAdded = true;
		}
	}

	public void checkColorChange() {
		if (blackWhiteColor) {
			blackWhiteColor = false;
			changeColor();
			blackWhiteColor = true;
		}
	}
}
