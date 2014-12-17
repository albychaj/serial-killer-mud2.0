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
	private JButton andreChikatilo1, andreChikatilo2, andreChikatilo3, andreChikatilo4, andraeChikatilo5;
	private JButton chase1, chase2, chase3, chase4, chase5;
	private JButton lucus1, lucus2, lucus3, lucus4;
	private JButton gein1, gein2, gein3, gein4;
	private JButton hannibal1, hannibal2, hannibal3, hannibal4;
	private JButton holmes1, holmes2, holmes3, holmes4;
	private JButton toolBox1, toolBox2, toolBox3, toolBox4;
	private JTextArea infoArea;

	public MOBdescription(String mobName) {

		this.setTitle("Save Yo Ass MOB Description");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			backgroundImage = ImageIO.read(new File("images/scary-wall.JPG"));
			this.setContentPane(new JLabel(new ImageIcon(backgroundImage)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		this.setSize(backgroundImage.getWidth(), backgroundImage.getHeight()-250);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		if (mobName.equals("jeffrey dahmer") || mobName.equals("dahmer")) {
			infoArea = new JTextArea(
					"\n name: jeffrey dahmer\n\n height: 6'1''\n weight: about 180lbs\n born: May 21, 1960. Milwaukee.\narrest: Notorious sex offender and serial killer.\n Sentenced to 15 consecutive life terms.\n killed his first victim with a blow to the head in 1978.\n killed 17 men total, molesting some of them, between 1978 to 1991.");
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
			infoArea = new JTextArea("\n\tthe toolbox killers info\n"
					+ "name: Lawrence bittaker\n"
					+ "height: N/A\n "
					+ "weight: N/A\n "
					+ "name: roy norris\n"
					+ "height: N/A\n"
					+ "weight: N/a n"
					+ "arrest: committed the kidnap, rape, torture and murder of five teenage girls over a period of five months\n"
					+ " in southern California in 1979");
			ImageIcon iconOne = new ImageIcon("images/toolKillers.jpg");
			toolBox1 = new JButton(iconOne);
			toolBox1.setContentAreaFilled(false);
			toolBox1.setBorderPainted(false);
			toolBox1.setLocation(50, 250);
			toolBox1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(toolBox1);
			// add images
		} else if (mobName.equals("richard ramirez")
				|| mobName.equals("ramirez")) {
			infoArea = new JTextArea("\n name: richard ramirez AKA the Night Stalker\n\n ehight: 6'1''\n weight: about 140lbs\narrest: broke into Californian homes, raping and torturing more than 25 victims.\n killed at least 13, over a two-year rampage.");
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
			ramirezImage3.setSize(ramirezImage3.getWidth(), ramirezImage3.getHeight());
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
			infoArea = new JTextArea("\n name: andre chikatilo\n\n height: 6'4''\nweight: 170lbs\n arrest: murdered more than 50 young people in the Soviet Union.\n background: former school teacher.");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/chikatilo.jpeg");
			andreChikatilo1 = new JButton(iconOne);
			andreChikatilo1.setContentAreaFilled(false);
			andreChikatilo1.setBorderPainted(false);
			andreChikatilo1.setLocation(62, 250);
			andreChikatilo1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andreChikatilo2 = new JButton(iconOne);
			andreChikatilo2.setContentAreaFilled(false);
			andreChikatilo2.setBorderPainted(false);
			andreChikatilo2.setLocation(215, 250);
			andreChikatilo2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andreChikatilo3 = new JButton(iconOne);
			andreChikatilo3.setContentAreaFilled(false);
			andreChikatilo3.setBorderPainted(false);
			andreChikatilo3.setLocation(365, 250);
			andreChikatilo3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andreChikatilo4 = new JButton(iconOne);
			andreChikatilo4.setContentAreaFilled(false);
			andreChikatilo4.setBorderPainted(false);
			andreChikatilo4.setLocation(515, 250);
			andreChikatilo4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			andraeChikatilo5 = new JButton(iconOne);
			andraeChikatilo5.setContentAreaFilled(false);
			andraeChikatilo5.setBorderPainted(false);
			andraeChikatilo5.setLocation(665, 250);
			andraeChikatilo5.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			//dahmerImage.addMouseListener(new MouseMoved("espana"));
			
			this.add(andreChikatilo1);
			this.add(andreChikatilo2);
			this.add(andreChikatilo3);
			this.add(andreChikatilo4);
			this.add(andraeChikatilo5);
		} else if (mobName.equals("richard trenton chase")
				|| mobName.equals("richard chase") || mobName.equals("chase")) {
			infoArea = new JTextArea("\n name: richard trenton chase AKA \"The Vampire of Sacramento\"\n\n height: 6'1''\n weight: 180lbs\n born: May 23, 1950 Santa Clara County, California\n arrest: \n killed six people in a span of a month.\n\n warning: he drank his victims' blood and cannibalized their remains.");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/chase.jpeg");
			chase1 = new JButton(iconOne);
			chase1.setContentAreaFilled(false);
			chase1.setBorderPainted(false);
			chase1.setLocation(50, 250);
			chase1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			chase2 = new JButton(iconOne);
			chase2.setContentAreaFilled(false);
			chase2.setBorderPainted(false);
			chase2.setLocation(200, 250);
			chase2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			chase3 = new JButton(iconOne);
			chase3.setContentAreaFilled(false);
			chase3.setBorderPainted(false);
			chase3.setLocation(370, 250);
			chase3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			chase4 = new JButton(iconOne);
			chase4.setContentAreaFilled(false);
			chase4.setBorderPainted(false);
			chase4.setLocation(525, 250);
			chase4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			chase5 = new JButton(iconOne);
			chase5.setContentAreaFilled(false);
			chase5.setBorderPainted(false);
			chase5.setLocation(675, 250);
			chase5.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(chase1);
			this.add(chase2);
			this.add(chase3);
			this.add(chase4);
			this.add(chase5);
			
		} else if (mobName.equals("henry lee lucus")
				|| mobName.equals("henry lucus") || mobName.equals("lucus")) {
			infoArea = new JTextArea("\n name: henry lee lucus\n\n born: August 23, 1936 Blacksburg, Virginia.\n arrest: sentenced for the murder of his mother in 1960.\n\tParoled in 1970, went back to jail for the attempted kidnapping of a 15-year-old girl.\n\tReleased again in 1975, killed two more women, and was arrested in 1983.\n\tHe confessed to murdering hundreds of people, though no proof existed beyond three known victims.\n\n background: alcoholic parents and prostitute mother.");
			
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/lucus.jpeg");
			System.out.println(iconOne.getIconWidth());
			lucus1 = new JButton(iconOne);
			lucus1.setContentAreaFilled(false);
			lucus1.setBorderPainted(false);
			lucus1.setLocation(50, 250);
			lucus1.setSize(iconOne.getIconWidth()-25,iconOne.getIconHeight());
			
			lucus2 = new JButton(iconOne);
			lucus2.setContentAreaFilled(false);
			lucus2.setBorderPainted(false);
			lucus2.setLocation(250, 250);
			lucus2.setSize(iconOne.getIconWidth()-25,iconOne.getIconHeight());
			
			lucus3 = new JButton(iconOne);
			lucus3.setContentAreaFilled(false);
			lucus3.setBorderPainted(false);
			lucus3.setLocation(450, 250);
			lucus3.setSize(iconOne.getIconWidth()-25,iconOne.getIconHeight());
			
			lucus4 = new JButton(iconOne);
			lucus4.setContentAreaFilled(false);
			lucus4.setBorderPainted(false);
			lucus4.setLocation(600, 250);
			lucus4.setSize(iconOne.getIconWidth()-25,iconOne.getIconHeight());
			
			this.add(lucus1);
			this.add(lucus2);
			this.add(lucus3);
			this.add(lucus4);
			
		} else if (mobName.equals(" name: ed gein\n height: 5'7''\n weight: about 160lbs\n born: \n arrest: murderer/body snatcher") || mobName.equals("gein")
				|| mobName.equals("psycho")) {
			infoArea = new JTextArea("\n ed gein info");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("");
			
			gein1 = new JButton(iconOne);
			gein1.setContentAreaFilled(false);
			gein1.setBorderPainted(false);
			gein1.setLocation(50, 250);
			gein1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			gein2 = new JButton(iconOne);
			gein2.setContentAreaFilled(false);
			gein2.setBorderPainted(false);
			gein2.setLocation(50, 250);
			gein2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			gein3 = new JButton(iconOne);
			gein3.setContentAreaFilled(false);
			gein3.setBorderPainted(false);
			gein3.setLocation(50, 250);
			gein3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			gein4 = new JButton(iconOne);
			gein4.setContentAreaFilled(false);
			gein4.setBorderPainted(false);
			gein4.setLocation(50, 250);
			gein4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(gein1);
			this.add(gein2);
			this.add(gein3);
			this.add(gein4);
			
		}
		else if(mobName.equals("hannibal lecter") || mobName.equals("hannibal") || mobName.equals("lecter")){
			infoArea = new JTextArea("\n name: Hannibal Lecter\n height: 6'0''\n weight: 190lbs\n born: \n arrest: unknown body count");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/hannibal");
			ImageIcon iconTwo = new ImageIcon("images/hannibal2");
			
			hannibal1 = new JButton(iconOne);
			hannibal1.setContentAreaFilled(false);
			hannibal1.setBorderPainted(false);
			hannibal1.setLocation(50, 250);
			hannibal1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			hannibal2 = new JButton(iconOne);
			hannibal2.setContentAreaFilled(false);
			hannibal2.setBorderPainted(false);
			hannibal2.setLocation(50, 250);
			hannibal2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			hannibal3 = new JButton(iconOne);
			hannibal3.setContentAreaFilled(false);
			hannibal3.setBorderPainted(false);
			hannibal3.setLocation(50, 250);
			hannibal3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			hannibal4 = new JButton(iconOne);
			hannibal4.setContentAreaFilled(false);
			hannibal4.setBorderPainted(false);
			hannibal4.setLocation(50, 250);
			hannibal4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(hannibal1);
			this.add(hannibal2);
			this.add(hannibal3);
			this.add(hannibal4);
		}
		else if(mobName.equals("henry howard holmes") || mobName.equals("holmes") || mobName.equals("hhh")){
			infoArea = new JTextArea("\n name: henry howard holmes\n height: 5'10''\n weight: 170lbs\n born: \n arrest: possible body count of 200.");
			//add images, later make so text showes when hovering over the images
			ImageIcon iconOne = new ImageIcon("images/holmes2");
			ImageIcon inconTwo = new ImageIcon("images/holmes");
			
			holmes1 = new JButton(iconOne);
			holmes1.setContentAreaFilled(false);
			holmes1.setBorderPainted(false);
			holmes1.setLocation(50, 250);
			holmes1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			holmes2 = new JButton(iconOne);
			holmes2.setContentAreaFilled(false);
			holmes2.setBorderPainted(false);
			holmes2.setLocation(50, 250);
			holmes2.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			holmes3 = new JButton(iconOne);
			holmes3.setContentAreaFilled(false);
			holmes3.setBorderPainted(false);
			holmes3.setLocation(50, 250);
			holmes3.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			holmes4 = new JButton(iconOne);
			holmes4.setContentAreaFilled(false);
			holmes4.setBorderPainted(false);
			holmes4.setLocation(50, 250);
			holmes4.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(holmes1);
			this.add(holmes2);
			this.add(holmes3);
			this.add(holmes4);	
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
