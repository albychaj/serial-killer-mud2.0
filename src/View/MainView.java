package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Commands.CommandErrorCommand;
import Commands.ForServerCommand;
import Enums.Commands;

public class MainView extends JPanel
{
	private static final long serialVersionUID = 818711182821925316L;
	private JTextArea chatArea, commandArea; 
	private JTextField textField; // field where user enters text
	private JButton enterButton;
	
	private ObjectOutputStream output; // output stream to server
	private String clientName;
	
	public MainView(String clientName, ObjectOutputStream output)
	{
		this.output = output;
		this.clientName = clientName;
		
		this.setLayout(new BorderLayout());
		//background.setLayout(new BorderLayout());
		
		// create and add top panel of MudGUI
		this.add(createTopPanel(), BorderLayout.NORTH);
		
		// create and add center panel of MudGUI
		this.add(createCenterPanel(), BorderLayout.CENTER);
		
		// create and add bottom panel of MudGUI
		this.add(createBottomPanel(), BorderLayout.SOUTH);
	}
	
	public JPanel createTopPanel()
	{
		JPanel top = new JPanel();
		
		JLabel banner = new JLabel("SAVE YO ASS", JLabel.CENTER);
		banner.setPreferredSize(new Dimension(1100, 60));
		banner.setForeground(Color.RED);
        banner.setOpaque(true);
        banner.setFont(getFont("fonts/cenobyte.ttf"));
        
        top.add(banner);
        
		return top;
	} // end of method createTopPanel
	
	/**
	 * Sets the fucking font
	 * 
	 * @param filename
	 * @return
	 */
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
		
		return font.deriveFont(50f);
	} // end of private method getFont
	
	public JPanel createCenterPanel()
	{	
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 2, 10, 0));
		center.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// create chat panel
		JPanel chatPanel = new JPanel();
		chatPanel.setLayout(new BorderLayout());
		chatPanel.setPreferredSize(new Dimension(550, 550));
		
		// initialize the text area and add it to the chat panel
		chatArea = new JTextArea();
		chatArea.setLineWrap(true);
		chatArea.setBackground(Color.BLACK);
		chatArea.setForeground(Color.WHITE);
		chatArea.setEditable(false);
		chatArea.setFont(getFont("fonts/trajan.ttf").deriveFont(14f));
		chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
		
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BorderLayout());
		commandPanel.setPreferredSize(new Dimension(550, 550));

		// initialize the text area and add it to the command panel
		commandArea = new JTextArea();
		commandArea.setLineWrap(true);
		commandArea.setBackground(Color.BLACK);
		commandArea.setForeground(Color.GRAY);
		commandArea.setEditable(false);
		commandArea.setFont(getFont("fonts/trajan.ttf").deriveFont(Font.BOLD, 12f));
		commandPanel.add(new JScrollPane(commandArea), BorderLayout.CENTER);
		
		center.add(chatPanel);
		center.add(commandPanel);
		
		return center;
	} // end of method createCenterPanel
	
	public JPanel createBottomPanel()
	{
		JPanel bottomPanel = new JPanel();
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(500, 40));
		textField.setBackground(Color.BLACK);
		textField.setForeground(Color.WHITE);
		textField.setFont(getFont("fonts/trajan.ttf").deriveFont(12f));
		
		enterButton = new JButton("Enter");
		enterButton.setOpaque(true);
		enterButton.setBorderPainted(false);
		//enterButton.setContentAreaFilled(false);
		enterButton.setBackground(Color.RED);
		enterButton.setForeground(Color.WHITE);
		enterButton.setFont(getFont("fonts/cenobyte.ttf").deriveFont(14f));
		enterButton.setPreferredSize(new Dimension(100, 40));
		
		// add button and field to the bottom panel
		bottomPanel.add(textField);
		bottomPanel.add(enterButton);
		
		// create a listener for writing messages to server
		ActionListener listener = new EnterListener();
		
		// attach listener to the text field and button
		textField.addActionListener(listener);
		enterButton.addActionListener(listener);
		
		return bottomPanel;
	} // end of method createBottomPanel
	
	private class EnterListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{	
			String s = textField.getText();
			String argument = new String();
			String c = new String();
			
			if (s.indexOf(" ") > 0)
			{
				String[] splitS = s.split(" ", 2);
				c = splitS[0].toUpperCase();
				argument = splitS[1];
			}
			
			else
				c = s.toUpperCase();
			
			
				
			
			if(c.equalsIgnoreCase("COMMANDS") ||c.equalsIgnoreCase("OOC") || c.equalsIgnoreCase("WHO") || c.equalsIgnoreCase("SAY") ||
					c.equalsIgnoreCase("TELL") || c.equalsIgnoreCase("SCORE") || c.equalsIgnoreCase("GIVE") || c.equalsIgnoreCase("GET") ||
					c.equalsIgnoreCase("INVENTORY") || c.equalsIgnoreCase("DROP") || c.equalsIgnoreCase("USE") || c.equalsIgnoreCase("QUIT") ||
					c.equalsIgnoreCase("SHUTDOWN") || c.equalsIgnoreCase("MOVE") || c.equalsIgnoreCase("LOOK") || c.equalsIgnoreCase("MAP") ||
					c.equalsIgnoreCase("ACCEPT") || c.equalsIgnoreCase("DENY") || c.equalsIgnoreCase("MOBSAY") || c.equalsIgnoreCase("FIGHT")    ){
				
				Commands command = Commands.valueOf(c);
				
				try
				{
					output.writeObject(new ForServerCommand(clientName, command, argument));	
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else{
				try {
					output.writeObject(new ForServerCommand(clientName, Commands.ERROR, argument));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			textField.setText("");
		}
	} // end of private class EnterListener
	
	/**
	 * Updates the chat log and command log. Called by UpdateClientCommands
	 * 
	 * @param chatMessages The current chat log
	 */
	public void updateChatLog(List<String> chatMessages, List<String> commandMessages) 
	{
		String chat = "";
		for (String message: chatMessages)
			chat = chat + message + "\n";
		
		chatArea.setText(chat);
		chatArea.setCaretPosition(chat.length());
		
		String command = "";
		for (String message: commandMessages)
			command = command + message + "\n";
		
		commandArea.setText(command);
		commandArea.setCaretPosition(command.length());
		
		repaint();
	} // end of method update
		
	public void updateCommandLog(List<String> commandMessages)
	{
		String command = "";
		for (String message: commandMessages)
			command = command + message + "\n";
		
		commandArea.setText(command);
		commandArea.setCaretPosition(command.length());
		
		repaint();
	} // end of method updateCommands
} // end of class MainPanel
