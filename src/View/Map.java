package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Map extends JFrame 
{
	private BufferedImage image;
	private JButton  lawn,  secretRoom,  darkWoods,  basement,  murderCastle,  farmhouse,  cleveland,  factory,  motel , hospital;
	private JButton dakota, kitchen, room13, room14, room15, room16, room17, room18, room19 , room20;
	private JButton room21, room22, room23, room24, room25, room26, room27;
	private JButton spainButton, croissantButton, dubaiButton;
	private JTextArea infoArea;
	
	
	private String defaultString = "Welcome: \n\n" + 
	                               "This is the Save yo Ass interactive\n" +
	                               "map. For basic room information\n" +
	                               "please hover over the area you would\n" +
	                               "like to read about.";
	// using for testin purposes will delete once all rooms are done.
	public static void main(String []args){
		new Map();
	}
	     
	public Map()
	{
		this.setTitle("Save Yo Ass Map");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try 
		{
			image = ImageIO.read(new File("images/MAP.jpg"));
			this.setContentPane(new JLabel(new ImageIcon(image)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// has to be here before try catch block
		this.setSize(image.getWidth(),image.getHeight());
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		infoArea = new JTextArea(defaultString);
		infoArea.setFont(getFont("fonts/trajan.ttf").deriveFont(12f));
		infoArea.setEditable(false);
		infoArea.setSize(277,420);
		infoArea.setLocation(572, 195);
		infoArea.setBackground(Color.BLACK);
		infoArea.setForeground(Color.WHITE);
		//JScrollPane sp = new JScrollPane(infoArea);
		
		// regular buttons
			
		lawn = new JButton("Lawn");
		lawn.setOpaque(true);
		lawn.setBorderPainted(false);
		lawn.setBackground(new Color(168, 7, 7));
		lawn.setForeground(Color.WHITE);
		lawn.setLocation(410, 553);
		lawn.setSize(67, 62);
		lawn.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		lawn.addMouseListener(new MouseMoved("room1"));
		
		secretRoom = new JButton("SR");
		secretRoom.setOpaque(true);
		secretRoom.setBorderPainted(false);
		secretRoom.setBackground(new Color(168, 7, 7));
		secretRoom.setForeground(Color.WHITE);
		secretRoom.setLocation(91, 482);
		secretRoom.setSize(67, 62);
		secretRoom.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		secretRoom.addMouseListener(new MouseMoved("room2"));
		
		darkWoods = new JButton("DW");
		darkWoods.setOpaque(true);
		darkWoods.setBorderPainted(false);
		darkWoods.setBackground(new Color(168, 7, 7));
		darkWoods.setForeground(Color.WHITE);
		darkWoods.setLocation(250, 482);
		darkWoods.setSize(67, 62);
		darkWoods.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		darkWoods.addMouseListener(new MouseMoved("room3"));
		
		basement = new JButton("BT");
		basement.setOpaque(true);
		basement.setBorderPainted(false);
		basement.setBackground(new Color(168, 7, 7));
		basement.setForeground(Color.WHITE);
		basement.setLocation(330, 482);
		basement.setSize(67, 62);
		basement.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		basement.addMouseListener(new MouseMoved("room4"));

		murderCastle = new JButton("MC");
		murderCastle.setOpaque(true);
		murderCastle.setBorderPainted(false);
		murderCastle.setBackground(new Color(168, 7, 7));
		murderCastle.setForeground(Color.WHITE);
		murderCastle.setLocation(410, 482);
		murderCastle.setSize(67, 62);
		murderCastle.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		murderCastle.addMouseListener(new MouseMoved("room5"));
		
		farmhouse = new JButton("WFH");
		farmhouse.setOpaque(true);
		farmhouse.setBorderPainted(false);
		farmhouse.setBackground(new Color(168, 7, 7));
		farmhouse.setForeground(Color.WHITE);
		farmhouse.setLocation(490, 482);
		farmhouse.setSize(67, 62);
		farmhouse.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		farmhouse.addMouseListener(new MouseMoved("room6"));
		
		cleveland = new JButton("CV");
		cleveland.setOpaque(true);
		cleveland.setBorderPainted(false);
		cleveland.setBackground(new Color(168, 7, 7));
		cleveland.setForeground(Color.WHITE);
		cleveland.setLocation(569, 124);
		cleveland.setSize(67, 62);
		cleveland.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		cleveland.addMouseListener(new MouseMoved("room7"));
		
		factory = new JButton("AF");
		factory.setOpaque(true);
		factory.setBorderPainted(false);
		factory.setBackground(new Color(168, 7, 7));
		factory.setForeground(Color.WHITE);
		factory.setLocation(91, 413);
		factory.setSize(67, 62);
		factory.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		factory.addMouseListener(new MouseMoved("room8"));
		
		motel = new JButton("MOT");
		motel.setOpaque(true);
		motel.setBorderPainted(false);
		motel.setBackground(new Color(168, 7, 7));
		motel.setForeground(Color.WHITE);
		motel.setLocation(170, 413);
		motel.setSize(67, 62);
		motel.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		motel.addMouseListener(new MouseMoved("room9"));
		
		hospital = new JButton("HS");
		hospital.setOpaque(true);
		hospital.setBorderPainted(false);
		hospital.setBackground(new Color(168, 7, 7));
		hospital.setForeground(Color.WHITE);
		hospital.setLocation(250, 413);
		hospital.setSize(67, 62);
		hospital.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		hospital.addMouseListener(new MouseMoved("room10"));
		
		dakota = new JButton("DKA");
		dakota.setOpaque(true);
		dakota.setBorderPainted(false);
		dakota.setBackground(new Color(168, 7, 7));
		dakota.setForeground(Color.WHITE);
		dakota.setLocation(330, 413);
		dakota.setSize(67, 62);
		dakota.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		dakota.addMouseListener(new MouseMoved("room11"));
		
		kitchen = new JButton("HK");
		kitchen.setOpaque(true);
		kitchen.setBorderPainted(false);
		kitchen.setBackground(new Color(168, 7, 7));
		kitchen.setForeground(Color.WHITE);
		kitchen.setLocation(410, 413);
		kitchen.setSize(67, 62);
		kitchen.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		kitchen.addMouseListener(new MouseMoved("room12"));
		
		room13 = new JButton("MF");
		room13.setOpaque(true);
		room13.setBorderPainted(false);
		room13.setBackground(new Color(168, 7, 7));
		room13.setForeground(Color.WHITE);
		room13.setLocation(490, 413);
		room13.setSize(67, 62);
		room13.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room13.addMouseListener(new MouseMoved("room13"));
		
		room14 = new JButton("Jail");
		room14.setOpaque(true);
		room14.setBorderPainted(false);
		room14.setBackground(new Color(168, 7, 7));
		room14.setForeground(Color.WHITE);
		room14.setLocation(170, 343);
		room14.setSize(67, 62);
		room14.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room14.addMouseListener(new MouseMoved("room14"));
		
		room15 = new JButton("PS");
		room15.setOpaque(true);
		room15.setBorderPainted(false);
		room15.setBackground(new Color(168, 7, 7));
		room15.setForeground(Color.WHITE);
		room15.setLocation(250, 343);
		room15.setSize(67, 62);
		room15.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room15.addMouseListener(new MouseMoved("room15"));
		
		room16 = new JButton("XO");
		room16.setOpaque(true);
		room16.setBorderPainted(false);
		room16.setBackground(new Color(168, 7, 7));
		room16.setForeground(Color.WHITE);
		room16.setLocation(330, 343);
		room16.setSize(67, 62);
		room16.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room16.addMouseListener(new MouseMoved("room16"));
		
		room17 = new JButton("JD");
		room17.setOpaque(true);
		room17.setBorderPainted(false);
		room17.setBackground(new Color(168, 7, 7));
		room17.setForeground(Color.WHITE);
		room17.setLocation(410, 343);
		room17.setSize(67, 62);
		room17.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room17.addMouseListener(new MouseMoved("room17"));
		
		room18 = new JButton("CM");
		room18.setOpaque(true);
		room18.setBorderPainted(false);
		room18.setBackground(new Color(168, 7, 7));
		room18.setForeground(Color.WHITE);
		room18.setLocation(490, 343);
		room18.setSize(67, 62);
		room18.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room18.addMouseListener(new MouseMoved("room18"));
		
		room19 = new JButton("BK");
		room19.setOpaque(true);
		room19.setBorderPainted(false);
		room19.setBackground(new Color(168, 7, 7));
		room19.setForeground(Color.WHITE);
		room19.setLocation(330, 270);
		room19.setSize(67, 62);
		room19.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room19.addMouseListener(new MouseMoved("room19"));
		
		room20 = new JButton("CE");
		room20.setOpaque(true);
		room20.setBorderPainted(false);
		room20.setBackground(new Color(168, 7, 7));
		room20.setForeground(Color.WHITE);
		room20.setLocation(410, 270);
		room20.setSize(67, 62);
		room20.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room20.addMouseListener(new MouseMoved("room20"));
		
		room21 = new JButton("AL");
		room21.setOpaque(true);
		room21.setBorderPainted(false);
		room21.setBackground(new Color(168, 7, 7));
		room21.setForeground(Color.WHITE);
		room21.setLocation(490, 270);
		room21.setSize(67, 62);
		room21.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room21.addMouseListener(new MouseMoved("room21"));
		
		room22 = new JButton("DAW");
		room22.setOpaque(true);
		room22.setBorderPainted(false);
		room22.setFocusable(false);
		room22.setBackground(new Color(168, 7, 7));
		room22.setForeground(Color.WHITE);
		room22.setLocation(410, 195);
		room22.setSize(67, 62);
		room22.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room22.addMouseListener(new MouseMoved("room22"));
		
		room23 = new JButton("AP");
		room23.setOpaque(true);
		room23.setBorderPainted(false);
		room23.setFocusable(false);
		room23.setBackground(new Color(168, 7, 7));
		room23.setForeground(Color.WHITE);
		room23.setLocation(330, 124);
		room23.setSize(67, 62);
		room23.setFont(getFont("fonts/trajan.ttf").deriveFont(11f));
		room23.addMouseListener(new MouseMoved("room23"));
		
		room24 = new JButton("SD");
		room24.setOpaque(true);
		room24.setBorderPainted(false);
		room24.setFocusable(false);
		room24.setBackground(new Color(168, 7, 7));
		room24.setForeground(Color.WHITE);
		room24.setLocation(410, 124);
		room24.setSize(67, 62);
		room24.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room24.addMouseListener(new MouseMoved("room24"));
		
		room25 = new JButton("FOX");
		room25.setOpaque(true);
		room25.setBorderPainted(false);
		room25.setFocusable(false);
		room25.setBackground(new Color(168, 7, 7));
		room25.setForeground(Color.WHITE);
		room25.setLocation(490, 124);
		room25.setSize(67, 62);
		room25.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room25.addMouseListener(new MouseMoved("room25"));
		
		room26 = new JButton("BR");
		room26.setOpaque(true);
		room26.setBorderPainted(false);
		room26.setFocusable(false);
		room26.setBackground(new Color(168, 7, 7));
		room26.setForeground(Color.WHITE);
		room26.setLocation(490, 50);
		room26.setSize(67, 62);
		room26.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room26.addMouseListener(new MouseMoved("room26"));
		
		room27 = new JButton("DD");
		room27.setOpaque(true);
		room27.setBorderPainted(false);
		room27.setFocusable(false);
		room27.setBackground(new Color(168, 7, 7));
		room27.setForeground(Color.WHITE);
		room27.setLocation(569, 50);
		room27.setSize(67, 62);
		room27.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room27.addMouseListener(new MouseMoved("room27"));
		
		
		// Customized buttons 
		ImageIcon iconOne = new ImageIcon("images/spainButton.png");
		spainButton = new JButton(iconOne);
		spainButton.setContentAreaFilled(false);
		spainButton.setBorderPainted(false);
		spainButton.setLocation(14, 238);
		spainButton.setSize(iconOne.getIconWidth(),iconOne.getIconHeight());
		spainButton.addMouseListener(new MouseMoved("espana"));
		
		ImageIcon iconTwo = new ImageIcon("images/parisButton.png");
		croissantButton = new JButton(iconTwo);
		croissantButton.setContentAreaFilled(false);
		croissantButton.setBorderPainted(false);
		croissantButton.setLocation(25, 73);
		croissantButton.setSize(iconTwo.getIconWidth(),iconTwo.getIconHeight());
		croissantButton.addMouseListener(new MouseMoved("francia"));
		
		ImageIcon iconThree = new ImageIcon("images/dubaiButton.png");
		dubaiButton = new JButton(iconThree);
		dubaiButton.setContentAreaFilled(false);
		dubaiButton.setBorderPainted(false);
		dubaiButton.setLocation(160, 10);
		dubaiButton.setSize(iconThree.getIconWidth(),iconThree.getIconHeight());
		dubaiButton.addMouseListener(new MouseMoved("makeItRain$"));
		
		// add buttons to jframe 
		this.add(lawn);
		this.add(secretRoom);
		this.add(darkWoods);
		this.add(basement);
		this.add(murderCastle);
		this.add(farmhouse);
		this.add(cleveland);
		this.add(factory);
		this.add(motel);
		this.add(hospital);
		this.add(dakota);
		this.add(kitchen);
		this.add(room13);
		this.add(room14);
		this.add(room15);
		this.add(room16);
		this.add(room17);
		this.add(room18);
		this.add(room19);
		this.add(room20);
		this.add(room21);
		this.add(room22);
		this.add(room23);
		this.add(room24);
		this.add(room25);
		this.add(room26);
		this.add(room27);
		this.add(infoArea);
		this.add(spainButton);
		this.add(croissantButton);
		this.add(dubaiButton);
		this.setVisible(true);
	}
	
	private static Font getFont(String filename)
	{
		Font font = null;
		
		try 
        {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(filename));
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
		
		return font;
	} // end of private method getFont
		
	private class MouseMoved implements MouseListener 
	{
		private String current;
		
		public MouseMoved(String location)
		{
			this.current = location;
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			switch(current)
			{
			case "room1":
				lawn.setBackground(Color.BLACK);
				lawn.setEnabled(false);
		        infoArea.setText("Location:\nThe Lawn\n\n"
		        				+"Description:\n"
		        				+ "There is no escaping now! The lawn\n"
		        				+ "is homebase for all players. You are\n"
		        				+ "surrounded by thousands upon\n"
		        				+ "thousands of acres of dead grass and\n"
		        				+ "trees. Daylight does not exist in this\n"
		        				+ "area and anything can happen. There\n"
		        				+ "is only one way out. Find the key and\n"
		        				+ "enter the Murder Castle. You can stay\n"
		        				+ "but your chances of survival are slim\n"
		        				+ "to none. Do yourself a favor if you�re\n"
		        				+ "on the lawn, leave at once and save yo\n"
		        				+ "ass.\n\n"
		        				+ "Adjacent Rooms: \n"
		        				+ "North � Murder Castle\n\n");
				break;
			case "room2":
				secretRoom.setBackground(Color.BLACK);
				secretRoom.setEnabled(false);
		        infoArea.setText("Location:\nSecret Room\n\n"
		        				+"Description: \n"
		        				+ "Sorry, the contents within this room\n"
		        				+ "are confidential. You have to play\n"
		        				+ "the game and explore it yourself\n\n"
		        				+ "Adjacent Rooms:\n"
		        				+ "North � Abandoned Factory\n\n");
				break;
			case "room3":
				darkWoods.setBackground(Color.BLACK);
				darkWoods.setEnabled(false);
				infoArea.setText("Location:\nDark Woods\n\n"
	        			   + "Description:\n"
	        			   + "AAAAAOOOOOOWWWWWWWW. Watch\n"
	        			   + "out for those deathly predators\n"
	        			   + "hidden in the brush. Their eyes\n"
	        			   + "glow with a lively flourish that\n"
	        			   + "juxtaposes your inevitable fate.\n"
	        			   + "There isn�t much to see here since\n"
	        			   + "it is dark. Perhaps you�ll find a\n"
	        			   + "flashlight hidden amongst the trees.\n"
	        			   + "Word of advice� Watch your back!!\n\n"
	        			   + "Adjacent Rooms:\n"
	        			   + "North - Save Yo Self Hospital\n"
	        			   + "East - Basement of the Murder Castle\n\n");
				break;
			case "room4":
				basement.setBackground(Color.BLACK);
				basement.setEnabled(false);
				infoArea.setText("Location:\nBasement\n\n"
	        			   + "Description: \n"
	        			   + "Welcome to the deepest trenches\n"
	        			   + "of the murder castle. Beware the\n "
	        			   + "piles of corpses. That stench isn't\n"
	        			   + "just your feet. Besides the eeriness\n"
	        			   + "feeling this room gives you there isn�t\n"
	        			   + "much within the space enclosed by\n"
	        			   + "these nicely painted red walls.\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - The Dakota Apartments\n"
	        			   + "East - Murder Castle\n"
	        			   + "West - Dark Woods\n\n");
				break;
			case "room5":
				murderCastle.setBackground(Color.BLACK);
				murderCastle.setEnabled(false);
		        infoArea.setText("Location:\nMurder Castle\n\n"
		        			   + "Description: \n"
		        			   + "Welcome to the cozy home of Sir\n"
		        			   + "HH Holmes. There's no need to be\n"
		        			   + "afraid. Unless HH comes home. The\n"
		        			   + "�Castle� is located 601-603 W. 63rd St.\n"
		        			   + "Chicago. It's three stories and a block\n"
		        			   + "long. The ground floor contains Dr.\n"
		        			   + "Holmes drugstore. The upper two\n"
		        			   + "floors consist of 100 windowless\n"
		        			   + "rooms with doorways opening to\n"
		        			   + "brick walls, oddly angles hallways,\n"
		        			   + "and stairways to nowhere."
		        			   + "\n\n"
		        			   + "Adjacent Rooms: \n"
		        			   + "North - Hannibal's Kitchen\n"
		        			   + "East - Wisconsin Farmhouse of Horrors\n"
		        			   + "South - The lawn\n"
		        			   + "West - Basement\n\n");
				break;
			case "room6":
				farmhouse.setBackground(Color.BLACK);
				farmhouse.setEnabled(false);
		        infoArea.setText("Location:\nWisconsin Farmhouse of Horrors\n\n"
		        		   + "Description:\n"
	        			   + "Welcome to Ed Gein's farmhouse. Pay\n"
	        			   + "no mind to the human paraphernalia,\n"
	        			   + "Ed Gein definately does not want to\n"
	        			   + "scare you away. The house is in\n"
	        			   + "pristine shape but a little out\n"
	        			   + "dated. I wouldn�t touch anything Ed\n"
	        			   + "wouldn�t like that. He should be\n"
	        			   + "arriving shortly. If I were you get\n"
	        			   + "what you need and leave."
	        			   + "\n\n"
	        			   + "Adjacent Rooms:\n"
	        			   + "North - Manson Family Murder House\n"
	        			   + "West - Murder Castle\n\n");
				break;
			case "room7":
				cleveland.setBackground(Color.BLACK);
				cleveland.setEnabled(false);
				infoArea.setText("Location:\nCleveland Strangler Murder House\n\n"
	        			   + "Description:\n"
	        			   + "You are currently standing in the\n"
	        			   + "Cleveland Strangler�s living room.\n"
	        			   + "It is here where two bodies were\n"
	        			   + "found during the time of the\n"
	        			   + "Strangler�s arrest. Don�t worry he\n"
	        			   + "won�t be coming for you but someone\n"
	        			   + "else could be. There is a couch to\n"
	        			   + "the north of the room and a\n"
	        			   + "television. The room is pretty empty\n"
	        			   + "the Strangler wasn�t too keen on\n"
	        			   + "indoor decorating.\n\n"
	        			   + "Adjacent Rooms:\n"
	        			   + "North - Manson Family Murder House\n"
	        			   + "West - Murder Castle\n\n");
				break;
			case "room8":
				factory.setBackground(Color.BLACK);
				factory.setEnabled(false);
				infoArea.setText("Location:\nAbandoned Factory\n\n"
	        			   + "Description: \n"
	        			   + "Creeeeeaak. Low-hanging pipes and\n"
	        			   + "boarded windows haunt this desolate\n"
	        			   + "place. Beware of dangerous machinery.\n"
	        			   + "Some murders can be made to look like\n"
	        			   + "accidents. Spiders aren't the only\n"
	        			   + "thing you should be afraid of."
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "South - Secret Room\n\n");
				break;
			case "room9":
				motel.setBackground(Color.BLACK);
				motel.setEnabled(false);
				infoArea.setText("Location:\nRoach Motel\n\n"
	        			   + "Description: \n"
	        			   + "You have found a safe place. This\n"
	        			   + "place is pretty dirty. The walls are\n"
	        			   + "moldy with odd colored stains. No\n"
	        			   + "one can attack you here, so go ahead.\n"
	        			   + "Grab a roach filled cot and get some\n"
	        			   + "Just keep your mouth closed."
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Jail\n"
	        			   + "East - Save Yo Self Hospital\n"
	        			   + "West - Abandoned Factory\n\n");
				break;
			case "room10":
				hospital.setBackground(Color.BLACK);
				hospital.setEnabled(false);
				infoArea.setText("Location:\nSave Yo Self Hospital\n\n"
	        			   + "Description: \n"
	        			   + "Got wounds? Need some patching up?\n"
	        			   + "You've come to the right place.\n"
	        			   + "Just make sure you check your\n"
	        			   + "doctor's credentails. Or else you'll\n"
	        			   + "have a one way ticket to the morgue."
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Police Station\n"
	        			   + "East - The Dakota Aparments\n"
	        			   + "South - Dark Woods\n"
	        			   + "West - Roach Motel\n\n");
				break;
			case "room11":
				dakota.setBackground(Color.BLACK);
				dakota.setEnabled(false);
				infoArea.setText("Location:\nThe Dakota Apartments\n\n"
	        			   + "Description: \n"
	        			   + "Welcome to northwest corner of\n"
	        			   + "72nd Street and Central Park West in\n"
	        			   + "New York City. This place is also\n"
	        			   + "known to be the famous murder place\n"
	        			   + "of John Lennon. Come here to see\n"
	        			   + "music die."
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Chi Omega Sorority House\n"
	        			   + "               of FSU\n"
	        			   + "East- Hannibal's Kitchen\n"
	        			   + "South - Basement of the Murder\n"
	        			   + "              Castle\n"
	        			   + "West - Save Yo Self Hospital\n\n");
				break;
			case "room12":
				kitchen.setBackground(Color.BLACK);
				kitchen.setEnabled(false);
				infoArea.setText("Location:\nHannibal�s Kitchen\n\n"
	        			   + "Description: \n"
	        			   + "You're in for a treat. Pull up a chair\n"
	        			   + "and prepare to be served the finest\n"
	        			   + "finest white meat you'll consume.\n"
	        			   + "Make sure you try the ribs."
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Jeffrey Dahmer's Apartment\n"
	        			   + "East - Manson Family Murder House\n"
	        			   + "South - The Murder Castle\n"
	        			   + "West - The Dakota Apartments\n\n");
				break;
			case "room13":
				room13.setBackground(Color.BLACK);
				room13.setEnabled(false);
				infoArea.setText("Location:\nManson Family Murder House\n\n"
	        			   + "Description: \n"
	        			   + "This is a really nice home but its best\n"
	        			   + "that you don�t make yourself\n"
	        			   + "comfortable. Some really messed up\n"
	        			   + "murders were planned here. "
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - cemetery\n"
	        			   + "South - Wisconsin Farmhouse of Horrors\n"
	        			   + "West - Hannibal�s Kitchen\n\n");
				break;
			case "room14":
				room14.setBackground(Color.BLACK);
				room14.setEnabled(false);
				infoArea.setText("Location:\nDead End Jail\n\n"
	        			   + "Description: \n"
	        			   + "Bars and Stripes. Welcome to\n"
	        			   + "jail.  Your backpack has been\n"
	        			   + "taken into custody. You have\n"
	        			   + "lost all your items."
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "East- The Police Station\n"
	        			   + "South - The Roach Motel\n");
				break;
			case "room15":
				room15.setBackground(Color.BLACK);
				room15.setEnabled(false);
				infoArea.setText("Location:\nPolice Station\n\n"
	        			   + "Description: \n"
	        			   + "Got a crime to report?\n"
	        			   + "Of course you do, you snitch.\n"
	        			   + "You are safe... for now.\n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "East - Chi Omega sorority house at FSU\n"
	        			   + "South - Save Yo Self Hospital\n"
	        			   + "West - Dead End Jail\n"
	        			   + "");;
				break;
			case "room16":
				room16.setBackground(Color.BLACK);
				room16.setEnabled(false);
				infoArea.setText("Location:\nChi Omega Sorority House at FSU\n\n"
	        			   + "Description: \n"
	        			   + "Wild parties, catty drama, and\n"
	        			   + "psycho murders. Come for fun,\n"
	        			   + "because this is a party you'll\n"
	        			   + "never forget."
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Kingfield Bank\n"
	        			   + "East - Jeffrey Dahmer's Apartment\n"
	        			   + "South - The Dakota Apartments\n"
	        			   + "West - Police Station\n"
	        			   + "");
				break;
			case "room17":
				room17.setBackground(Color.BLACK);
				room17.setEnabled(false);
				infoArea.setText("Location:\nJeffrey Dahmer's Apartment\n\n"
	        			   + "Description: \n"
	        			   + "How is it in Milwaukee? I hope Jeffrey\n"
	        			   + "hasn�t marked you as his next victim.\n"
	        			   + "Last time I heard none of his male\n"
	        			   + "confidants made it out in one piece.\n"
	        			   + "Do yourself a favor and leave\n"
	        			   + "apartment 213."
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Casino Especial\n"
	        			   + "East - Cemetery\n"
	        			   + "South - Hannibal's Kitchen\n"
	        			   + "West - Chi Omega Sorority House\n");
				break;
			case "room18":
				room18.setBackground(Color.BLACK);
				room18.setEnabled(false);
				infoArea.setText("Location:\nCemetery\n\n"
	        			   + "Description: \n"
	        			   + "Tombstones and coffins and dead\n"
	        			   + "people sleeping. These are a few\n"
	        			   + "of my fa-vor-ite things.\n"
	        			   + "Pick up a shovel and bury your\n"
	        			   + "victims, before someone buries\n"
	        			   + "you.\n"
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Adventure Land\n"
	        			   + "South - Manson Family Murder House\n"
	        			   + "West - Jeffrey Dahmer's Apartment\n"
	        			   + "");;
				break;
			case "room19":
				room19.setBackground(Color.BLACK);
				room19.setEnabled(false);
				infoArea.setText("Location:\nKingsfield Bank\n\n"
	        			   + "Description: \n"
	        			   + "Out of money? You've come to\n"
	        			   + "the right place. Finance your\n"
	        			   + "bribes here."
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "East - Casino Especial\n"
	        			   + "South - Chi Omega Sorority House at FSU\n"
	        			   + "West - Police Station"
	        			   + "");
				break;
			case "room20":
				room20.setBackground(Color.BLACK);
				room20.setEnabled(false);
				infoArea.setText("Location:\nCasino Especial\n\n"
	        			   + "Description: \n"
	        			   + "Gamble all your problems away.\n"
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Dark Alley\n"
	        			   + "East - Adventure Land\n"
	        			   + "South - Jeffrey Dahmer's Apartment\n"
	        			   + "West - Kingsfield Bank\n"
	        			   + "");
				break;
			case "room21":
				room21.setBackground(Color.BLACK);
				room21.setEnabled(false);
				infoArea.setText("Location:\nAdventure Land Theme Park\n\n"
	        			   + "Description: \n"
	        			   + "Step right up and claim your prize.\n"
	        			   + "I recommend you check out Devil's\n"
	        			   + "Flight before you reach your final\n"
	        			   + "destination. Keep your hands and\n"
	        			   + "feet in the ride at all times and\n"
	        			   + "don't forget to kiss yo ass\n"
	        			   + "goodbye.\n"
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "South - Cemetery\n"
	        			   + "West - Casino Especial\n"
	        			   + "");
				break;
			case "room22":
				room22.setBackground(Color.BLACK);
				room22.setEnabled(false);
				infoArea.setText("Location:\nDark Alleyway\n\n"
	        			   + "Description: \n"
	        			   + "The best place for illegal transactions.\n"
	        			   + "But watch out. Not all sharks\n"
	        			   + "live in the ocean.\n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "North - The Streets of Detroit\n"
	        			   + "South - Casino Especial\n"
	        			   + "");
				break;
			case "room23":
				room23.setBackground(Color.BLACK);
				room23.setEnabled(false);
				infoArea.setText("Location:\nInternational Airport\n\n"
	        			   + "Description: \n"
	        			   + "Need to get away or do some\n"
	        			   + "international business? Travel\n"
	        			   + "to Paris, Spain, or Dubai!\n"
	        			   + "This is a safe zone!"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Dubai\n"
	        			   + "East - The Streets of Detroit\n"
	        			   + "South - Spain\n"
	        			   + "West - Paris\n"
	        			   + "");
				break;
			case "room24":
				room24.setBackground(Color.BLACK);
				room24.setEnabled(false);
				infoArea.setText("Location:\nStreets of Detroit\n\n"
	        			   + "Description: \n"
	        			   + "Welcome to the streets of Detroit.\n"
	        			   + "Watch yo self. These streets\n"
	        			   + "are not safe.\n"
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + "East - Fox Hollow Farms\n"
	        			   + "South - Dark Alley\n"
	        			   + "West - Airport\n"
	        			   + "");
				break;
			case "room25":
				room25.setBackground(Color.BLACK);
				room25.setEnabled(false);
				infoArea.setText("Location:\nFox Hollow Farms\n\n"
	        			   + "Description: \n"
	        			   + "This elegant Tudor-style farm house\n"
	        			   + "comes four furnished bedrooms,\n"
	        			   + "indoor swimming pool, and a riding\n"
	        			   + "stable. You have plenty of privacy\n"
	        			   + "since it lies on eighteen and half\n"
	        			   + "acres of land. Not too shabby right?\n"
	        			   + "Just ignore the fact that the remains\n"
	        			   + "of 11 men lie scattered around the\n"
	        			   + "land."
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - Robert Ben Rhodes' Big Rig\n"
	        			   + "East - Cleveland Strangler Murder House\n"
	        			   + "West - Streets of Detroit\n\n"
	        			   + "");
				break;
			case "room26":
				room26.setBackground(Color.BLACK);
				room26.setEnabled(false);
				infoArea.setText("Location:\nRobert Ben Rhodes' Big Rig\n\n"
	        			   + "Description: \n"
	        			   + "This may look like a normal Big\n"
	        			   + "Rig, but look closer. You probably\n"
	        			   + "don�t want to be in this mobile\n"
	        			   + "torture chamber."
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "East - Desert\n"
	        			   + "South - Fox Hollow Farms\n"
	        			   + "");
				break;
			case "room27":
				room27.setBackground(Color.BLACK);
				room27.setEnabled(false);
				infoArea.setText("Location:\nDesert\n\n"
	        			   + "Description: \n"
	        			   + "I hope you brought plenty of water.\n"
	        			   + "And watch out for those scorpions\n"
	        			   + "too.  Stay here too long, and yo ass\n"
	        			   + "will suffer death by dehydration.\n"
	        			   + "\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "South - Cleveland Strangler Murder House\n"
	        			   + "West - Robert Ben Rhodes' Big Rig\n"
	        			   + "");
				break;
			case "espana":
				spainButton.setBackground(Color.BLACK);
				spainButton.setEnabled(false);
				infoArea.setText("Location:\nSpain\n\n"
	        			   + "Description: \n"
	        			   + "Ole ... Welcome to Spain!\n"
	        			   + "Beware of the bulls that roam the\n"
	        			   + "street. They pack a punch if you get\n"
	        			   + "hit by one. It is best not to stay\n"
	        			   + "here unless you want to die."
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "North - International Airport\n\n"
	        			   + "");
				break;
			case "francia":
				croissantButton.setBackground(Color.BLACK);
				croissantButton.setEnabled(false);
				infoArea.setText("Location:\nParis\n\n"
	        			   + "Description: \n"
	        			   + "Welcome to Paris!\n"
	        			   + "Visit the crypts under the city and\n"
	        			   + "get lost in the Louvre. You wish\n"
	        			   + "right? Too bad you are restricted to\n"
	        			   + "the catacombs which lie right\n"
	        			   + "beneath the heart of Paris. This\n"
	        			   + "historic labyrinth contains the\n"
	        			   + "remains of at least six million\n"
	        			   + "Parisians kept at a chilling 14\n"
	        			   + "degrees Celsius. Try not to lean on\n"
	        			   + "the wall made of bones."
	        			   + ""
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "East - International Airport\n\n");
				break;
			case "makeItRain$":
				dubaiButton.setBackground(Color.BLACK);
				dubaiButton.setEnabled(false);
				infoArea.setText("Location:\nDubai\n\n"
	        			   + "Description: \n"
	        			   + "Welcome to Dubai!\n"
	        			   + "Finally a place of relaxation. Nothing\n"
	        			   + "bad can happen to you here but you\n"
	        			   + "cannot stay forever. For now enjoy\n"
	        			   + "your million dollar view."
	        			   + "\n\n"
	        			   + "Adjacent Rooms: \n"
	        			   + "South - International Airport\n\n"
	        			   + "");
				break;
			default:
				break;
			}
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
			switch(current)
			{
			case "room1":
				lawn.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				lawn.setEnabled(true);
				break;
			case "room2":
				secretRoom.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				secretRoom.setEnabled(true);
				break;
			case "room3":
				darkWoods.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				darkWoods.setEnabled(true);
				break;
			case "room4":
				basement.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				basement.setEnabled(true);
				break;
			case "room5":
				murderCastle.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				murderCastle.setEnabled(true);
				break;
			case "room6":
				farmhouse.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				farmhouse.setEnabled(true);
				break;
			case "room7":
				cleveland.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				cleveland.setEnabled(true);
				break;
			case "room8":
				factory.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				factory.setEnabled(true);
				break;
			case "room9":
				motel.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				motel.setEnabled(true);
				break;
			case "room10":
				hospital.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				hospital.setEnabled(true);
				break;
			case "room11":
				dakota.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				dakota.setEnabled(true);
				break;
			case "room12":
				kitchen.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				kitchen.setEnabled(true);
				break;
			case "room13":
				room13.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room13.setEnabled(true);
				break;
			case "room14":
				room14.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room14.setEnabled(true);
				break;
			case "room15":
				room15.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room15.setEnabled(true);
				break;
			case "room16":
				room16.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room16.setEnabled(true);
				break;
			case "room17":
				room17.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room17.setEnabled(true);
				break;
			case "room18":
				room18.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room18.setEnabled(true);
				break;
			case "room19":
				room19.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room19.setEnabled(true);
				break;
			case "room20":
				room20.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room20.setEnabled(true);
				break;
			case "room21":
				room21.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room21.setEnabled(true);
				break;
			case "room22":
				room22.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room22.setEnabled(true);
				break;
			case "room23":
				room23.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room23.setEnabled(true);
				break;
			case "room24":
				room24.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room24.setEnabled(true);
				break;
			case "room25":
				room25.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room25.setEnabled(true);
				break;
			case "room26":
				room26.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room26.setEnabled(true);
				break;
			case "room27":
				room27.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room27.setEnabled(true);
				break;
			case "espana":
				spainButton.setContentAreaFilled(false);
				infoArea.setText(defaultString);
				spainButton.setEnabled(true);
				break;
			case "francia":
				croissantButton.setContentAreaFilled(false);
				infoArea.setText(defaultString);
				croissantButton.setEnabled(true);
				break;
			case "makeItRain$":
				dubaiButton.setContentAreaFilled(false);
				infoArea.setText(defaultString);
				dubaiButton.setEnabled(true);
				break;
			default:
				break;
			}// end switch 
		}

		@Override public void mouseClicked(MouseEvent arg0) {}
		
		@Override public void mousePressed(MouseEvent arg0) {}

		@Override public void mouseReleased(MouseEvent arg0) {}
	}//end private class MouseMoved
}// end map class
