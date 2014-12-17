package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Saved extends JFrame{

	private JTextArea text;
	private BufferedImage image;
	private String message = "Yo Ass Is Saved.";
	
	public static void main(String[] args)
	{
		new Saved();
	}
	
	public Saved()
	{
		this.setTitle("Yo Ass Is Saved");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/*try
		{
			image = new ImageIO.read(new File("images/scary-wall.jpg"));
			this.setContentPane(new JLabel(new ImageIcon(image)));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		*/
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