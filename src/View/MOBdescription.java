package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

//import View.Map.MouseMoved;

public class MOBdescription extends JFrame {

	private BufferedImage backgroundImage;
	private JButton dahmerImage1, bittakerImage, norrisImage, ramirezImage1, andreChikatilo1;
	private JButton andreChikatilo2, andreChikatilo3, andreChikatilo4, andraeChikatilo5;
	private JButton chase1, chase2, chase3, chase4, chase5;
	private JButton lucus1, lucus2, lucus3, lucus4;
	private JButton gein1, gein2, gein3, gein4;
	private JButton hannibal1, hannibal2, hannibal3, hannibal4;
	private JButton holmes1, holmes2, holmes3, holmes4;
	private JButton  toolBox2, toolBox3, toolBox4;
	private JTextArea infoArea, titleArea;
	private JLabel title;
	private String person, info;
	private Float fontSize;
	
	/*public static void main(String []args){
		new MOBdescription("andre chikatilo");
	}*/

	public MOBdescription(String mobName) {

		this.setTitle("Save Yo Ass MOB Description");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			backgroundImage = ImageIO.read(new File("images/scary-wall.JPG"));
			this.setContentPane(new JLabel(new ImageIcon(backgroundImage)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		this.setSize(backgroundImage.getWidth()-550, backgroundImage.getHeight()-250);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		if (mobName.equalsIgnoreCase("jeffrey dahmer") || mobName.equalsIgnoreCase("dahmer"))
		{
			person = "Jeffrey Dahmer";
			fontSize = 50f;
			info = "\n Legal Name: Jeffrey Lionel Dahmer\n\n"
					+ " Born: May 21, 1960\n\n"
					+ " Died: November 28, 1994\n\n"
					+ " Other names:\n"
					+ "     The Milwaukee Cannibal\n"
					+ "     The Milwaukee Monster\n\n"
					+ " Height: 6' 0''\n\n"
					+ " Weight: 180 lbs\n\n"
					+ " Convicitions:\n"
					+ "     Child molestation\n"
					+ "     Disorderly conduct\n"
					+ "     Indecent exposure\n"
					+ "     Murder\n"
					+ "     Public intoxication\n\n"
					+ " Victims: 17\n\n"
					+ " Criminal penalty:\n"
					+ "     Life imprisonment (16 life terms)\n\n"
					+ " Criminal Biography:\n"
					+ "   An American serial killer and sex offender,\n"
					+ "   who committed the rape, murder and\n"
					+ "   dismemberment of 17 men and boys between\n"
					+ "   1978 and 1991, with many of his later murders\n"
					+ "   also involving necrophilia, cannibalism and\n"
					+ "   the permanent preservation of body parts.\n";
			// setting image
			ImageIcon iconOne = new ImageIcon("images/dahmer.jpg");
			dahmerImage1 = new JButton(iconOne);
			dahmerImage1.setContentAreaFilled(false);
			dahmerImage1.setBorderPainted(false);
			dahmerImage1.setLocation(100, 70);
			dahmerImage1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(dahmerImage1);
			
		} else if (mobName.equalsIgnoreCase("lawrence bittaker") || mobName.equalsIgnoreCase("bittaker")) {
			person = "Lawrence Bittaker";
			fontSize = 45f;
			info = "\n Legal Name: Lawrence Sigmund Bittaker\n\n"
					+ " Born: September 27, 1940\n\n"
					+ " Died: N/A\n\n"
					+ " Other names:\n"
					+ "     Toolbox Killer\n\n"
					+ " Height: Not on file\n\n"
					+ " Weight: not on file\n\n"
					+ " Convicitions:\n"
					+ "     First-degree murder\n"
					+ "     Kidnapping\n"
					+ "     Conspiracy\n"
					+ "     Rape\n"
					+ "     Hit and Run\n\n"
					+ " Victims: 5\n\n"
					+ " Criminal penalty:\n"
					+ "     Death\n\n"
					+ " Criminal Biography:\n"
					+ "   An American serial killer and rapist, who was\n"
					+ "   known as one of the toolbox killers.\n"
					+ "   Committed to te kidnap, rape, torture, and\n"
					+ "   murder of five, teenage girls ove ther period\n"
					+ "   of five months in Souther California 1979.\n"
					+ "   Sentenced to death and is currently in the\n"
					+ "   Quentin Sate prison on death row. Known as\n"
					+ "   the a tool box killer because the tools used\n"
					+ "   to torture and murder victims were items that\n"
					+ "   are normally stored inside a household.\n";
			ImageIcon iconOne = new ImageIcon("images/bittaker.jpg");
			bittakerImage = new JButton(iconOne);
			bittakerImage.setContentAreaFilled(false);
			bittakerImage.setBorderPainted(false);
			bittakerImage.setLocation(78, 80);
			bittakerImage.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(bittakerImage);
		}
		else if (mobName.equalsIgnoreCase("norris")|| mobName.equalsIgnoreCase("roy norris") ){
			person = "Roy Norris";
			fontSize = 50f;
			info = "\n Legal Name: Roy Lewis Norris\n\n"
					+ " Born: February 2, 1948\n\n"
					+ " Died: N/A\n\n"
					+ " Other names:\n"
					+ "     Toolbox Killer\n\n"
					+ " Height: Not on file\n\n"
					+ " Weight: not on file\n\n"
					+ " Convicitions:\n"
					+ "     First-degree murder\n"
					+ "     Second-degree murder\n"
					+ "     Assault with a deadly weapon\n"
					+ "     Rape\n"
					+ "     Robbery\n\n"
					+ " Victims: 5\n\n"
					+ " Criminal penalty:\n"
					+ "     Life imprisonment\n\n"
					+ " Criminal Biography:\n"
					+ "   An American serial killer and rapist, who was\n"
					+ "   known as one of the toolbox killers.\n"
					+ "   Committed to te kidnap, rape, torture, and\n"
					+ "   murder of five, teenage girls ove ther period\n"
					+ "   of five months in Souther California 1979.\n"
					+ "   Norris accepted a plea bargain whereby he\n"
					+ "   agreed to testify against Bittaker and was\n"
					+ "   sentenced to life imprisonment on May 7, 1980,\n"
					+ "   with possibility of parole after serving 30\n"
					+ "   years. He is currently incarcerated at Pelican\n"
					+ "   Bay State Prison.\n";
			ImageIcon iconOne = new ImageIcon("images/norris.jpg");
			norrisImage = new JButton(iconOne);
			norrisImage.setContentAreaFilled(false);
			norrisImage.setBorderPainted(false);
			norrisImage.setLocation(88, 88);
			norrisImage.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
			
			this.add(norrisImage);	
			
			
		} 
		else if (mobName.equalsIgnoreCase("richard ramirez") || mobName.equalsIgnoreCase("ramirez") || mobName.equalsIgnoreCase("night stalker")) 
		{
			person = "Richard Ramirez";
			fontSize = 50f;
			info = "\n Legal Name: Ricardo Leyva Muñoz Ramírez\n\n"
					+ " Born: February 28, 1960\n\n"
					+ " Died: June 7, 2013\n\n"
					+ " Other names:\n"
					+ "     The Night Stalker\n"
					+ "     The Walk-In Killer\n"
					+ "     The Valley Intruder\n\n"
					+ " Height: 6' 1''\n\n"
					+ " Weight: ~140lbs\n\n"
					+ " Convicitions:\n"
					+ "     13 counts of murder\n"
					+ "     5 counts of attempted murder\n"
					+ "     11 counts of sexual assault\n"
					+ "     14 counts of burglary\n\n"
					+ " Victims: 14\n\n"
					+ " Criminal penalty:\n"
					+ "     Death penalty\n\n"
					+ " Criminal Biography:\n"
					+ "   an American serial killer, rapist, and burglar.\n"
					+ "   His highly publicized home invasion crime\n"
					+ "   spree terrorized the residents of the greater\n"
					+ "   Los Angeles area, and later the residents of the\n"
					+ "   San Francisco area, from June 1984 until August\n"
					+ "   1985. The judge who upheld his thirteen death\n"
					+ "   sentences remarked that Ramirez's deeds\n"
					+ "   exhibited\"cruelty, callousness, and viciousness\n"
					+ "   beyond any human understanding.\"\n";
			
			ImageIcon iconOne = new ImageIcon("images/ramirez4.jpeg");
			
			ramirezImage1 = new JButton(iconOne);
			ramirezImage1.setContentAreaFilled(false);
			ramirezImage1.setBorderPainted(false);
			ramirezImage1.setLocation(100, 75);
			ramirezImage1.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
						
			this.add(ramirezImage1);
			
		} 
		else if (mobName.equalsIgnoreCase("andre chikatilo") || mobName.equalsIgnoreCase("chikatilo") || mobName.equalsIgnoreCase("red ripper")) 
		{
			person = "Richard Ramirez";
			fontSize = 50f;
			info = "\n Legal Name: Ricardo Leyva Muñoz Ramírez\n\n"
					+ " Born: February 28, 1960\n\n"
					+ " Died: June 7, 2013\n\n"
					+ " Other names:\n"
					+ "     The Night Stalker\n"
					+ "     The Walk-In Killer\n"
					+ "     The Valley Intruder\n\n"
					+ " Height: 6' 1''\n\n"
					+ " Weight: ~140lbs\n\n"
					+ " Convicitions:\n"
					+ "     13 counts of murder\n"
					+ "     5 counts of attempted murder\n"
					+ "     11 counts of sexual assault\n"
					+ "     14 counts of burglary\n\n"
					+ " Victims: 14\n\n"
					+ " Criminal penalty:\n"
					+ "     Death penalty\n\n"
					+ " Criminal Biography:\n"
					+ "   an American serial killer, rapist, and burglar.\n"
					+ "   His highly publicized home invasion crime\n"
					+ "   spree terrorized the residents of the greater\n"
					+ "   Los Angeles area, and later the residents of the\n"
					+ "   San Francisco area, from June 1984 until August\n"
					+ "   1985. The judge who upheld his thirteen death\n"
					+ "   sentences remarked that Ramirez's deeds\n"
					+ "   exhibited\"cruelty, callousness, and viciousness\n"
					+ "   beyond any human understanding.\"\n";
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
		} 
		else if (mobName.equals("richard trenton chase") || mobName.equals("richard chase") || mobName.equals("chase")) {
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
		else{
			infoArea = new JTextArea("why you here idiot");
		}
		
		
		title = new JLabel(person, SwingConstants.CENTER);
		title.setFont(getFont("fonts/ARMY RUST.ttf").deriveFont(fontSize));
		title.setForeground(Color.RED);
		title.setOpaque(true);
		title.setBackground(Color.BLACK);
		title.setSize(370,50);
		title.setLocation(15,10);
		// name formating black 
		titleArea = new JTextArea();
		//titleArea.setFont(getFont("fonts/ARMY RUST.ttf").deriveFont(24f));
		titleArea.setEditable(false);
		titleArea.setSize(350,100);
		titleArea.setLocation(25,25);
		
		titleArea.setBackground(Color.BLACK);
		
		// killer stats
		
		infoArea = new JTextArea(info);
		infoArea.setFont(getFont("fonts/trajan.ttf").deriveFont(12f));
		infoArea.setEditable(false);

		infoArea.setBackground(Color.BLACK);
		infoArea.setForeground(Color.WHITE);
		JScrollPane scroll = new JScrollPane(infoArea);
		scroll.setSize(372, 225);
		scroll.setLocation(15, 335);
		//this.add(titleArea);
		this.add(scroll);
		
		this.add(title);
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
