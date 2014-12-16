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

// I had this idea of making an "interactive" map :) maybe this could be a holly smokes W to the O to the W factor
// its not pretty code but its in progress. It's flipping cool. Maybe we could add it to our main view??
// The ultimate decision is in your hands. 

public class Map extends JFrame 
{
	private BufferedImage image;
	private JButton  room1,  room2,  room3,  room4,  room5,  room6,  room7,  room8,  room9 , room10;
	private JButton room11, room12, room13, room14, room15, room16, room17, room18, room19 , room20;
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
			
		room1 = new JButton("Lawn");
		room1.setOpaque(true);
		room1.setBorderPainted(false);
		room1.setBackground(new Color(168, 7, 7));
		room1.setForeground(Color.WHITE);
		room1.setLocation(410, 553);
		room1.setSize(67, 62);
		room1.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room1.addMouseListener(new MouseMoved("room1"));
		
		room2 = new JButton("SR");
		room2.setOpaque(true);
		room2.setBorderPainted(false);
		room2.setBackground(new Color(168, 7, 7));
		room2.setForeground(Color.WHITE);
		room2.setLocation(91, 482);
		room2.setSize(67, 62);
		room2.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room2.addMouseListener(new MouseMoved("room2"));
		
		room3 = new JButton("DW");
		room3.setOpaque(true);
		room3.setBorderPainted(false);
		room3.setBackground(new Color(168, 7, 7));
		room3.setForeground(Color.WHITE);
		room3.setLocation(250, 482);
		room3.setSize(67, 62);
		room3.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room3.addMouseListener(new MouseMoved("room3"));
		
		room4 = new JButton("BT");
		room4.setOpaque(true);
		room4.setBorderPainted(false);
		room4.setBackground(new Color(168, 7, 7));
		room4.setForeground(Color.WHITE);
		room4.setLocation(330, 482);
		room4.setSize(67, 62);
		room4.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room4.addMouseListener(new MouseMoved("room4"));

		room5 = new JButton("MC");
		room5.setOpaque(true);
		room5.setBorderPainted(false);
		room5.setBackground(new Color(168, 7, 7));
		room5.setForeground(Color.WHITE);
		room5.setLocation(410, 482);
		room5.setSize(67, 62);
		room5.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room5.addMouseListener(new MouseMoved("room5"));
		
		room6 = new JButton("WFH");
		room6.setOpaque(true);
		room6.setBorderPainted(false);
		room6.setBackground(new Color(168, 7, 7));
		room6.setForeground(Color.WHITE);
		room6.setLocation(490, 482);
		room6.setSize(67, 62);
		room6.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room6.addMouseListener(new MouseMoved("room6"));
		
		room7 = new JButton("7");
		room7.setOpaque(true);
		room7.setBorderPainted(false);
		room7.setBackground(new Color(168, 7, 7));
		room7.setForeground(Color.WHITE);
		room7.setLocation(569, 124);
		room7.setSize(67, 62);
		room7.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room7.addMouseListener(new MouseMoved("room7"));
		
		room8 = new JButton("AF");
		room8.setOpaque(true);
		room8.setBorderPainted(false);
		room8.setBackground(new Color(168, 7, 7));
		room8.setForeground(Color.WHITE);
		room8.setLocation(91, 413);
		room8.setSize(67, 62);
		room8.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room8.addMouseListener(new MouseMoved("room8"));
		
		room9 = new JButton("9");
		room9.setOpaque(true);
		room9.setBorderPainted(false);
		room9.setBackground(new Color(168, 7, 7));
		room9.setForeground(Color.WHITE);
		room9.setLocation(170, 413);
		room9.setSize(67, 62);
		room9.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room9.addMouseListener(new MouseMoved("room9"));
		
		room10 = new JButton("10");
		room10.setOpaque(true);
		room10.setBorderPainted(false);
		room10.setBackground(new Color(168, 7, 7));
		room10.setForeground(Color.WHITE);
		room10.setLocation(250, 413);
		room10.setSize(67, 62);
		room10.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room10.addMouseListener(new MouseMoved("room10"));
		
		room11 = new JButton("pool");
		room11.setOpaque(true);
		room11.setBorderPainted(false);
		room11.setBackground(new Color(168, 7, 7));
		room11.setForeground(Color.WHITE);
		room11.setLocation(330, 413);
		room11.setSize(67, 62);
		room11.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room11.addMouseListener(new MouseMoved("room11"));
		
		room12 = new JButton("HK");
		room12.setOpaque(true);
		room12.setBorderPainted(false);
		room12.setBackground(new Color(168, 7, 7));
		room12.setForeground(Color.WHITE);
		room12.setLocation(410, 413);
		room12.setSize(67, 62);
		room12.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room12.addMouseListener(new MouseMoved("room12"));
		
		room13 = new JButton("ATC");
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
		
		room15 = new JButton("15");
		room15.setOpaque(true);
		room15.setBorderPainted(false);
		room15.setBackground(new Color(168, 7, 7));
		room15.setForeground(Color.WHITE);
		room15.setLocation(250, 343);
		room15.setSize(67, 62);
		room15.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room15.addMouseListener(new MouseMoved("room15"));
		
		room16 = new JButton("16");
		room16.setOpaque(true);
		room16.setBorderPainted(false);
		room16.setBackground(new Color(168, 7, 7));
		room16.setForeground(Color.WHITE);
		room16.setLocation(330, 343);
		room16.setSize(67, 62);
		room16.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room16.addMouseListener(new MouseMoved("room16"));
		
		room17 = new JButton("17");
		room17.setOpaque(true);
		room17.setBorderPainted(false);
		room17.setBackground(new Color(168, 7, 7));
		room17.setForeground(Color.WHITE);
		room17.setLocation(410, 343);
		room17.setSize(67, 62);
		room17.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room17.addMouseListener(new MouseMoved("room17"));
		
		room18 = new JButton("18");
		room18.setOpaque(true);
		room18.setBorderPainted(false);
		room18.setBackground(new Color(168, 7, 7));
		room18.setForeground(Color.WHITE);
		room18.setLocation(490, 343);
		room18.setSize(67, 62);
		room18.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room18.addMouseListener(new MouseMoved("room18"));
		
		room19 = new JButton("19");
		room19.setOpaque(true);
		room19.setBorderPainted(false);
		room19.setBackground(new Color(168, 7, 7));
		room19.setForeground(Color.WHITE);
		room19.setLocation(330, 270);
		room19.setSize(67, 62);
		room19.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room19.addMouseListener(new MouseMoved("room19"));
		
		room20 = new JButton("20");
		room20.setOpaque(true);
		room20.setBorderPainted(false);
		room20.setBackground(new Color(168, 7, 7));
		room20.setForeground(Color.WHITE);
		room20.setLocation(410, 270);
		room20.setSize(67, 62);
		room20.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room20.addMouseListener(new MouseMoved("room20"));
		
		room21 = new JButton("21");
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
		
		room24 = new JButton("24");
		room24.setOpaque(true);
		room24.setBorderPainted(false);
		room24.setFocusable(false);
		room24.setBackground(new Color(168, 7, 7));
		room24.setForeground(Color.WHITE);
		room24.setLocation(410, 124);
		room24.setSize(67, 62);
		room24.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room24.addMouseListener(new MouseMoved("room24"));
		
		room25 = new JButton("25");
		room25.setOpaque(true);
		room25.setBorderPainted(false);
		room25.setFocusable(false);
		room25.setBackground(new Color(168, 7, 7));
		room25.setForeground(Color.WHITE);
		room25.setLocation(490, 124);
		room25.setSize(67, 62);
		room25.setFont(getFont("fonts/trajan.ttf").deriveFont(10f));
		room25.addMouseListener(new MouseMoved("room25"));
		
		room26 = new JButton("26");
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
		this.add(room1);
		this.add(room2);
		this.add(room3);
		this.add(room4);
		this.add(room5);
		this.add(room6);
		this.add(room7);
		this.add(room8);
		this.add(room9);
		this.add(room10);
		this.add(room11);
		this.add(room12);
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
				room1.setBackground(Color.BLACK);
				room1.setEnabled(false);
		        infoArea.setText("Location:\nThe Lawn\n\n"
		        				+"Description:\n"
		        				+ "There is no escaping now! The lawn\n"
		        				+ "is homebase for all players. You are\n"
		        				+ "surrounded by thousands upon\n"
		        				+ "thousands of acres of dead grass and\n"
		        				+ "trees. Daylight does not exist in this\n"
		        				+ "area and anything  can happen. There\n"
		        				+ "is only one way out. Find the key and\n"
		        				+ "enter the Murder Castle. You can stay\n"
		        				+ "but your chances of survival are slim\n"
		        				+ "to none. Do yourself a favor if you�re\n"
		        				+ "on the lawn, leave at once and save yo\n"
		        				+ "ass.\n\n"
		        				+ "Adjacent Rooms: \n"
		        				+ "North � Murder Castle\n\n"
		        				+ "Initial Items:\n"
		        				+ "key to murder castle");
				break;
			case "room2":
				room2.setBackground(Color.BLACK);
				room2.setEnabled(false);
		        infoArea.setText("Location:\nSecret Room\n\n"
		        				+"Description: \n"
		        				+ "Sorry, the contents within this room\n"
		        				+ "are confidential. You have to play\n"
		        				+ "the game and explore it yourself\n\n."
		        				+ "Adjacent Rooms: \n"
		        				+ "North � Abandoned Factory\n\n"
		        				+ "Initial Items:\n"
		        				+ "Confidential!");
				break;
			case "room3":
				room3.setBackground(Color.BLACK);
				room3.setEnabled(false);
				infoArea.setText("Location:\nDark Woods\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room4":
				room4.setBackground(Color.BLACK);
				room4.setEnabled(false);
				infoArea.setText("Location:\nBasement\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room5":
				room5.setBackground(Color.BLACK);
				room5.setEnabled(false);
		        infoArea.setText("Location:\nMurder Castle\n\n"
		        			   + "Description: \n"
		        			   + ""
		        			   + ""
		        			   + "\n\n."
		        			   + "Adjacent Rooms: \n"
		        			   + ""
		        			   + "Initial Items:\n"
		        			   + "");
				break;
			case "room6":
				room6.setBackground(Color.BLACK);
				room6.setEnabled(false);
		        infoArea.setText("Wisconsin Farmhouse of Horrors\ndescription");
				break;
			case "room7":
				room7.setBackground(Color.BLACK);
				room7.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room8":
				room8.setBackground(Color.BLACK);
				room8.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room9":
				room9.setBackground(Color.BLACK);
				room9.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room10":
				room10.setBackground(Color.BLACK);
				room10.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room11":
				room11.setBackground(Color.BLACK);
				room11.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room12":
				room12.setBackground(Color.BLACK);
				room12.setEnabled(false);
				infoArea.setText("Location:\n8.	Hannibal�s Kitchen\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room13":
				room13.setBackground(Color.BLACK);
				room13.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room14":
				room14.setBackground(Color.BLACK);
				room14.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room15":
				room15.setBackground(Color.BLACK);
				room15.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");;
				break;
			case "room16":
				room16.setBackground(Color.BLACK);
				room16.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room17":
				room17.setBackground(Color.BLACK);
				room17.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room18":
				room18.setBackground(Color.BLACK);
				room18.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");;
				break;
			case "room19":
				room19.setBackground(Color.BLACK);
				room19.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room20":
				room20.setBackground(Color.BLACK);
				room20.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room21":
				room21.setBackground(Color.BLACK);
				room21.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room22":
				room22.setBackground(Color.BLACK);
				room22.setEnabled(false);
				infoArea.setText("Location:\nDark Alleyway\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room23":
				room23.setBackground(Color.BLACK);
				room23.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");;
				break;
			case "room24":
				room24.setBackground(Color.BLACK);
				room24.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room25":
				room25.setBackground(Color.BLACK);
				room25.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room26":
				room26.setBackground(Color.BLACK);
				room26.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "room27":
				room27.setBackground(Color.BLACK);
				room27.setEnabled(false);
				infoArea.setText("Location:\nMurder Castle\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "espana":
				spainButton.setBackground(Color.BLACK);
				spainButton.setEnabled(false);
				infoArea.setText("Location:\nSpain\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "francia":
				croissantButton.setBackground(Color.BLACK);
				croissantButton.setEnabled(false);
				infoArea.setText("Location:\nParis\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
	        			   + "");
				break;
			case "makeItRain$":
				dubaiButton.setBackground(Color.BLACK);
				dubaiButton.setEnabled(false);
				infoArea.setText("Location:\nDubai\n\n"
	        			   + "Description: \n"
	        			   + ""
	        			   + ""
	        			   + "\n\n."
	        			   + "Adjacent Rooms: \n"
	        			   + ""
	        			   + "Initial Items:\n"
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
				room1.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room1.setEnabled(true);
				break;
			case "room2":
				room2.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room2.setEnabled(true);
				break;
			case "room3":
				room3.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room3.setEnabled(true);
				break;
			case "room4":
				room4.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room4.setEnabled(true);
				break;
			case "room5":
				room5.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room5.setEnabled(true);
				break;
			case "room6":
				room6.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room6.setEnabled(true);
				break;
			case "room7":
				room7.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room7.setEnabled(true);
				break;
			case "room8":
				room8.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room8.setEnabled(true);
				break;
			case "room9":
				room9.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room9.setEnabled(true);
				break;
			case "room10":
				room10.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room10.setEnabled(true);
				break;
			case "room11":
				room11.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room11.setEnabled(true);
				break;
			case "room12":
				room12.setBackground(new Color(168, 7, 7));
				infoArea.setText(defaultString);
				room12.setEnabled(true);
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
