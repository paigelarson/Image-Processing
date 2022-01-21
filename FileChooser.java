package Classes;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.util.Properties;
import java.io.*;
import java.net.*;
  
public class FileChooser 
{
  
  /////////////////////// methods /////////////////////////////
  public static String getMediaPath(String fileName) 
  {
    String path = null;
    String directory = getMediaDirectory();
    boolean done = true;
    
    // get the full path
    path = directory + fileName;
    return path;
  }
  
  public static String pickPath(JFileChooser fileChooser)
  {
    String path = null;
    
    JFrame frame = new JFrame();
    frame.setAlwaysOnTop(true);
    
    // get the return value from choosing a file
    int returnVal = fileChooser.showOpenDialog(frame);
    
    // if the return value says the user picked a file 
    if (returnVal == JFileChooser.APPROVE_OPTION)
      path = fileChooser.getSelectedFile().getPath();
    return path;
  }
  
  public static String pickAFile()
  {
    JFileChooser fileChooser = null;
    
    // start off the file name as null
    String fileName = null;
    
    // get the current media directory
    String mediaDir = getMediaDirectory();
    
    try {
      File file = new File(mediaDir);
      if (file.exists())
        fileChooser = new JFileChooser(file);
    } catch (Exception ex) {
    }
    
    // if no file chooser yet create one
    if (fileChooser == null)
      fileChooser = new JFileChooser();
    
    // pick the file
    fileName = pickPath(fileChooser);
    
    return fileName;
  }
  
  public static String getMediaDirectory() 
  {
    String directory = null;
    boolean done = false;
    File dirFile = null;
    
    // try to find the images directory
      try {
        // get the URL for where we loaded this class 
        Class currClass = Class.forName("FileChooser");
        URL classURL = currClass.getResource("FileChooser.class");
        URL fileURL = new URL(classURL,"../images/");
        directory = fileURL.getPath();
        directory = URLDecoder.decode(directory, "UTF-8");
        dirFile = new File(directory);
        if (dirFile.exists()) {
          //setMediaPath(directory);
          return directory;
        }
      } catch (Exception ex) {
      }
      
      return directory;
  }
  
}
