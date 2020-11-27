package ImageManipulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

	ImageHandler img;

	public ButtonListener(ImageHandler image) {
		img = image;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String buttonText = event.getActionCommand();

		System.out.println("Option: " + buttonText);
		if (buttonText.equals("Change Color")) {
			try {
				img.changeColor();
			} catch (Exception e) {
				e.getMessage();
			}

		} else if (buttonText.equals("Rotate Image")) {
			try {
				img.rotateImage();
			} catch (Exception e) {
				e.getMessage();
			}

		} else if (buttonText.equals("Re-Size")) {
			try {
				img.reSize();
			} catch (Exception e) {
				e.getMessage();
			}

		} else if (buttonText.equals("Change Border")) {
			try {
				img.addBorder();
			} catch (Exception e) {
				e.getMessage();
			}
		} else if (buttonText.equals("Reset")) {
			try {
				img.resetImage();
			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			try {
				img.downloadImg();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

}
