package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.Client;
import Players.Player;

/**
 * 
 * @author Team Alpha-Super-Awesome-Cool-Dynamite Wolf-Squadron
 *
 */
public class LoginView extends JFrame 
{	
	private static final long serialVersionUID = 4033594531497903499L;
	private Client client; // Client to be associated with this new player
	
	private BufferedImage image;
	private JLabel error, oldy, oldUN, oldUP;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton, createButton;
	
	public LoginView(Client client, final ConcurrentHashMap<String, Player> playerAccounts, final List<String> playersOnline) 
	{ 
		this.client = client;
		
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try 
		{
			image = ImageIO.read(new File("images/scaryRoom.jpg"));
			// Set your Image Here.
			this.setContentPane(new JLabel(new ImageIcon(image)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// has to be here before try catch block
		this.setSize(image.getWidth()-10,image.getHeight());
		//this.setSize(500,300);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Returning user JLabel and text fields
		oldy = new JLabel("Player Login:");
		oldy.setFont(getFont("fonts/trajan.ttf").deriveFont(20f));
		oldy.setForeground(Color.RED);
		oldy.setSize(250,30);
		oldy.setLocation(60,50);
		
		oldUN = new JLabel("Username:");
		oldUN.setFont(getFont("fonts/trajan.ttf").deriveFont(16f));
		oldUN.setForeground(Color.GREEN);
		oldUN.setSize(250,30);
		oldUN.setLocation(100,100);
		
		usernameField = new JTextField("");
		usernameField.setFont(getFont("fonts/trajan.ttf").deriveFont(12f));
		usernameField.setSize(175,30);
		usernameField.setLocation(200,100);
		
		oldUP = new JLabel("Password:");
		oldUP.setFont(getFont("fonts/trajan.ttf").deriveFont(16f));
		oldUP.setForeground(Color.GREEN);
		oldUP.setSize(250,30);
		oldUP.setLocation(100,150);
		
		passwordField = new JPasswordField("");
		passwordField.setSize(175,30);
		passwordField.setLocation(200,150);
		
		error = new JLabel("");
		error.setFont(getFont("fonts/trajan.ttf").deriveFont(16f));
		error.setForeground(Color.YELLOW);
		error.setSize(400,30);
		error.setLocation(95,260);
				
		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				login(playerAccounts, playersOnline);
				
			}
		});		
		loginButton.setOpaque(true);
		loginButton.setBorderPainted(false);
		loginButton.setBackground(Color.RED);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(getFont("fonts/trajan.ttf").deriveFont(14f));
		loginButton.setSize(130, 30);
		loginButton.setLocation(90, 205);
		
		
		createButton = new JButton("Create Account");
		createButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				addPlayer(playerAccounts);
			}
		});
		createButton.setOpaque(true);
		createButton.setBorderPainted(false);
		createButton.setBackground(Color.RED);
		createButton.setForeground(Color.WHITE);
		createButton.setFont(getFont("fonts/trajan.ttf").deriveFont(14f));
		createButton.setSize(180, 30);
		createButton.setLocation(245, 205);
		//Adding components to Panel and JFrame
		
		//this.add(title);
		this.add(oldy);
		this.add(oldUN);
		this.add(usernameField);
		this.add(oldUP);
		this.add(passwordField);
		this.add(loginButton);
		this.add(createButton);
		this.add(error);
		
		this.setVisible(true);
	}
	
	/**
	 * This method allows a player to login to the MUD. 
	 * @param playersOnline 
	 */
	public void login(ConcurrentHashMap<String, Player> playerAccounts, List<String> playersOnline)
	{
		String username = usernameField.getText();
		
		// If the player is already online, don't let this motherfucker try to 
		// sign in again
		for (String playerName: playersOnline)
		{
			if (username.equalsIgnoreCase(playerName))
			{
				error.setText("Player already logged in. Try again." );
				error.setLocation(115,260);
				usernameField.setText("");
				passwordField.setText("");
				return;
			}
		}
		
		// Grab the player from the list of playerAccounts that matches the 
		// username that was input into the login username field
		Player person = playerAccounts.get(username);
		
		// If the username does not exist in the current map of players or 
		// the password does not match the username's stored password, 
		// return an error. 
		if(person == null || !person.matches(passwordField.getPassword()))
		{
			error.setText("Invalid username or password");
			error.setLocation(115,260);
			usernameField.setText("");
			passwordField.setText("");
			return;
		}
		
		client.setPlayer(username);
		dispose();
		//client.finishSettingUpPlayer();
	} // end of method login
	
	/**
	 * This method adds a new player to the MUD.
	 */
	public void addPlayer(ConcurrentHashMap<String, Player> playerAccounts)
	{
		String username = usernameField.getText();
		String password = passwordField.getText();
		
		// If either the username or password field are empty, return an error. 
		if(username.equals("") || passwordField.equals(""))
		{
			error.setText("Enter a valid username or password");
			error.setLocation(90,260);
			usernameField.setText("");
			passwordField.setText("");
			return;
		}
		
		// If trying to create a new account with information of an administrator, return an error. 
		/*if(username.equalsIgnoreCase("admin"))
=======
		if(username.equalsIgnoreCase("admin") && !password.equalsIgnoreCase("0000"))
>>>>>>> 31aa352fc9faa41e1e75b6f629193391588f7bd6
		{
				error.setText("An account exists with that name");
				error.setLocation(95,260);
				usernameField.setText("");
				passwordField.setText("");
				return;
		}*/
		
		Player existingPlayer = playerAccounts.get(username);
		
		// If trying to create a new account with information of a player that already exists, return an error. 
		if(existingPlayer != null)
		{
			error.setText("An account exists with that name");
			error.setLocation(95,260);
			usernameField.setText("");
			passwordField.setText("");
			return;
		}
		
		client.setPlayer(username);
		dispose();
		Player newPlayer = new Player(username, new String(passwordField.getPassword()));
		client.finishSettingUpPlayer(newPlayer);
	} // end of method addPlayer
	
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
} // end of class LoginView
