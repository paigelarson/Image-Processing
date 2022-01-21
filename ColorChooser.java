package Classes;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import java.awt.Color;
  
public class ColorChooser 
{
  
  public static Color pickAColor()
  {
    Color color = Color.white;
    
    // create a JFrame to be the parent of the color chooser open dialog
    // if you don't do this then you may not see the dialog.
    JFrame frame = new JFrame();
    frame.setAlwaysOnTop(true);
    
    
    // use the color chooser to pick the color
    color = JColorChooser.showDialog(frame,"Pick a color",color);
    
    return color;
  }
  
    public static void main(String[] args)
  {
    Color pickedColor = ColorChooser.pickAColor();
    System.out.println(pickedColor);
  }
  
}
