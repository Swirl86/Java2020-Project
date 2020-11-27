package ImageManipulation;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		UserInterface ui = new UserInterface();
		SwingUtilities.invokeLater(ui);
		System.out.println("Start Image Design Interface!");
	}

}
