package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Death extends JFrame{

	private JTextArea text;
	private BufferedImage image;
	private String message = "Yo Ass Is Dead.";
	
	public static void main(String[] args)
	{
		new Death();
	}
	
	public Death()
	{
		this.setTitle("Yo Ass Is Dead");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.setSize(300, 200);
		this.setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		text = new JTextArea(message);
		text.setEditable(false);
		text.setSize(200, 200);
		text.setLocation(45, 60);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		
		text.setFont(getFont("fonts/trajan.ttf").deriveFont(25f));
		
		this.add(text);
		this.setBackground(Color.BLACK);
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
	
}
