package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//import View.Map.MouseMoved;

public class MOBdescription extends JFrame {

	private BufferedImage backgroundImage;
	private JButton dahmerImage1, dahmerImage2, dahmerImage3, dahmerImage4;
	private JButton ramirezImage1, ramirezImage2, ramirezImage3, ramirezImage4;
	private JButton andreChikatilo1, andreChikatilo2, andreChikatilo3, andreChikatilo4;
	private JTextArea infoArea;

	public MOBdescription(String mobName) {

		this.setTitle("Save Yo Ass MOB Description");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			backgroundImage = ImageIO.read(new File("images/scary-wall.jpg"));
			this.setContentPane(new JLabel(new ImageIcon(backgroundImage)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		this.setSize(backgroundImage.getWidth(), backgroundImage.getHeight()-250);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		if (mobName.equals("jeffery dahmer") || mobName.equals("dahmer")) {
			infoArea = new JTextArea(
					"\n jeffery dahmer info\n\n Notorious sex offender and serial killer.\n Sentenced to 15 consecutive life terms.\n born in Milwaukee on May 21, 1960.\n killed his first victim with a blow to the head in 1978.\n killed 17 men total, molesting some of them, between 1978 to 1991.");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/dahmer2.jpeg");
			dahmerImage1 = new JButton(iconOne);
			dahmerImage1.setContentAreaFilled(false);
			dahmerImage1.setBorderPainted(false);
			dahmerImage1.setLocation(50, 250);
			dahmerImage1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			dahmerImage2 = new JButton(iconOne);
			dahmerImage2.setContentAreaFilled(false);
			dahmerImage2.setBorderPainted(false);
			dahmerImage2.setLocation(250, 250);
			dahmerImage2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			dahmerImage3 = new JButton(iconOne);
			dahmerImage3.setContentAreaFilled(false);
			dahmerImage3.setBorderPainted(false);
			dahmerImage3.setLocation(450, 250);
			dahmerImage3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			dahmerImage4 = new JButton(iconOne);
			dahmerImage4.setContentAreaFilled(false);
			dahmerImage4.setBorderPainted(false);
			dahmerImage4.setLocation(650, 250);
			dahmerImage4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			this.add(dahmerImage1);
			this.add(dahmerImage2);
			this.add(dahmerImage3);
			this.add(dahmerImage4);
			
		} else if (mobName.equals("lawrence bittaker")
				|| mobName.equals("roy norris") || mobName.equals("bittaker")
				|| mobName.equals("norris")
				|| mobName.equals("toolbox killers")) {
			infoArea = new JTextArea("\n\tthe toolbox killers info");
			// add images
		} else if (mobName.equals("richard ramirez")
				|| mobName.equals("ramirez")) {
			infoArea = new JTextArea("\n richard ramirez info\n\n AKA the Night Stalker\n Richard Ramirez has broke into Californian homes, raping and torturing more than 25 victims.\n he has killed at least 13, over a two-year rampage.");
			//add images, later make so text showed when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/ramirez4.jpeg");
			ImageIcon iconTwo = new ImageIcon("images/ramirez2.jpeg");
			ramirezImage1 = new JButton(iconOne);
			ramirezImage1.setContentAreaFilled(false);
			ramirezImage1.setBorderPainted(false);
			ramirezImage1.setLocation(50, 250);
			ramirezImage1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			ramirezImage2 = new JButton(iconOne);
			ramirezImage2.setContentAreaFilled(false);
			ramirezImage2.setBorderPainted(false);
			ramirezImage2.setLocation(250, 250);
			ramirezImage2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			ramirezImage3 = new JButton(iconTwo);
			ramirezImage3.setContentAreaFilled(false);
			ramirezImage3.setBorderPainted(false);
			ramirezImage3.setSize(ramirezImage3.getWidth(), ramirezImage3.getHeight()-100);
			ramirezImage3.setLocation(442, 250);
			ramirezImage3.setSize(iconOne.getIconWidth(),iconTwo.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			ramirezImage4 = new JButton(iconOne);
			ramirezImage4.setContentAreaFilled(false);
			ramirezImage4.setBorderPainted(false);
			ramirezImage4.setLocation(635, 250);
			ramirezImage4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			this.add(ramirezImage1);
			this.add(ramirezImage2);
			this.add(ramirezImage3);
			this.add(ramirezImage4);
		} else if (mobName.equals("andre chikatilo")
				|| mobName.equals("chikatilo") || mobName.equals("red ripper")) {
			infoArea = new JTextArea("\n andre chikatilo info\n\n a former school teacher.\n murdered more than 50 young people in the Soviet Union.");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/chikatilo.jpeg");
			andreChikatilo1 = new JButton(iconOne);
			andreChikatilo1.setContentAreaFilled(false);
			andreChikatilo1.setBorderPainted(false);
			andreChikatilo1.setLocation(50, 250);
			andreChikatilo1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andreChikatilo2 = new JButton(iconOne);
			andreChikatilo2.setContentAreaFilled(false);
			andreChikatilo2.setBorderPainted(false);
			andreChikatilo2.setLocation(250, 250);
			andreChikatilo2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andreChikatilo3 = new JButton(iconOne);
			andreChikatilo3.setContentAreaFilled(false);
			andreChikatilo3.setBorderPainted(false);
			andreChikatilo3.setLocation(450, 250);
			andreChikatilo3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andreChikatilo4 = new JButton(iconOne);
			andreChikatilo4.setContentAreaFilled(false);
			andreChikatilo4.setBorderPainted(false);
			andreChikatilo4.setLocation(650, 250);
			andreChikatilo4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			this.add(andreChikatilo1);
			this.add(andreChikatilo2);
			this.add(andreChikatilo3);
			this.add(andreChikatilo4);
		} else if (mobName.equals("richard trenton chase")
				|| mobName.equals("richard chase") || mobName.equals("chase")) {
			infoArea = new JTextArea("\n richard trenton chase \"The Vampire of Sacramento\" info\n\n killed six people in a span of a month in Sacramento, California.\n he drank his victims' blood and cannibalized their remains.");
			// add images
		} else if (mobName.equals("henry lee lucus")
				|| mobName.equals("henry lucus") || mobName.equals("lucus")) {
			infoArea = new JTextArea("\n henry lee lucus info\n\n born on August 23, 1936, in Blacksburg, Virginia.\n Born to alcoholic parents, including a mother who prostituted herself.\n In 1960, he was sentenced for the murder of his mother.\n Paroled in 1970, Lucas went back to jail for the attempted kidnapping of a 15-year-old girl.\n Released again in 1975, he killed two more women, and was arrested in 1983.\n He confessed to murdering hundreds of people, though no proof existed beyond three known victims.");
			// add images
		} else if (mobName.equals("ed gein") || mobName.equals("gein")
				|| mobName.equals("psycho")) {
			infoArea = new JTextArea("\n ed gein info");
			// add images
		}
		else if(mobName.equals("hannibal lecter") || mobName.equals("hannibal") || mobName.equals("lecter")){
			infoArea = new JTextArea("\n Hannibal Lecter info");
			// add images
		}
		else if(mobName.equals("henry howard holmes") || mobName.equals("holmes") || mobName.equals("hhh")){
			infoArea = new JTextArea("\n henry howard holmes info");
			// add images
		}
		else
			infoArea = new JTextArea("why you here idiot");

		infoArea.setFont(getFont("fonts/trajan.ttf").deriveFont(12f));
		infoArea.setEditable(false);
		infoArea.setSize(800, 200);
		infoArea.setLocation(50, 50);
		infoArea.setBackground(Color.BLACK);
		infoArea.setForeground(Color.WHITE);

		this.add(infoArea);
		this.setVisible(true);
	}

	private static Font getFont(String filename) {
		Font font = null;

		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return font;
	} // end of private method getFont

}
