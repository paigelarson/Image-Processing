package Classes;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  public Picture ()
  {
    super();  
  }
  
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  
  
  
  
  
  /** Method to set the blue to 0*/ 
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  public void keepOnlyBlue()
  {
      Pixel[][] bpixels= this.getPixels2D();
      for(Pixel[] row:bpixels)
      {
          for(Pixel pob:row)
          {
              pob.setGreen(0);
              pob.setRed(0);
          }
      }
  }
    public void negate()
  {
       Pixel[][] bpixels= this.getPixels2D();
      for(Pixel[] row:bpixels)
      {
          for(Pixel pob:row)
          {
              pob.setGreen(255-pob.getGreen());
              pob.setRed(255-pob.getRed());
              pob.setBlue(255-pob.getBlue());
          }
      }
  }
  public void grayscale()
  {
         Pixel[][] bpixels= this.getPixels2D();
      for(Pixel[] row:bpixels)
      {
          for(int i=0; i<row.length; i++)
          {
            int b= row[i].getBlue();
            int r=row[i].getRed();
            int g= row[i].getGreen();
            int color=((r+g+b)/3);
            Color asd;
              asd = new Color(color,color,color);
            row[i].setColor(asd);
          }
      }
  }
  public void fixUnderwater()
  {
         Pixel[][] bpixels= this.getPixels2D();
      for(Pixel[] row:bpixels)
      {
          for(Pixel pob:row)
          {
              pob.setRed(pob.getRed()*6);
              
          }
      }
  }
  
  
 
  
 
  
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  public void mirrorVerticalRightToLeft()
  {
      Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  
  }
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int col = 0; col < width; col++)
    {
      for (int row = 0; row < pixels.length / 2; row++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-1-row][col];
        bottomPixel.setColor(topPixel.getColor());
      }
    } 
  
  }
  public void mirrorHorizontalBotToTop()
  {
      Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel bottomPixel = null;
    int width = pixels[0].length;
    for (int col = 0; col < width; col++)
    {
      for (int row = 0; row < pixels.length / 2; row++)
      {
        topPixel = pixels[row][col];
        bottomPixel = pixels[pixels.length-1-row][col];
        topPixel.setColor(bottomPixel.getColor());
      }
    }
  }
  
  
  
  // Mirror just part of a picture of a temple 
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  public void mirrorPart(int mirrorPoint, int rowStart, int rowEnd, int colStart, int colEnd, String type)
  {
  
    Pixel leftPixel = null;
    Pixel rightPixel = null;
   // int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = rowStart; row < rowEnd; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = colStart; col < colEnd; col++)
      {
        if(type.substring(0,1).equals("L"))
        {
            leftPixel = pixels[row][col];     
            rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
            rightPixel.setColor(leftPixel.getColor());
        }
        else if(type.substring(0,1).equals("R"))
        {
            leftPixel=pixels[row][col];
            rightPixel= pixels[row][mirrorPoint - col + mirrorPoint];
            leftPixel.setColor(rightPixel.getColor());
        }
      }
    }
  }
  
  
  
  
  
  
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel topPixel= null;
    Pixel botPixel=null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color botColor=null;
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        topPixel=pixels[row][col];
        botPixel=pixels[row+1][col];
        botColor=botPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > edgeDist || topPixel.colorDistance(botColor)>edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      
      }
    }
  }
 

  
  public void encode(Picture pic)
  {
      Pixel[][] pixels = this.getPixels2D();
      Pixel[][] p2 = pic.getPixels2D();
    //for (Pixel[] rowArray : pixels)
    for(int r=0; r<pixels.length; r++)
    {
      //for (Pixel pixelObj : rowArray)
      for(int c=0; c<pixels[0].length; c++)
      {
          Pixel pixelObj=pixels[r][c];
        int red=pixelObj.getRed();
        
        if(red%2==1)
        {
            red--;
            pixelObj.setRed(red); 
        }
        Pixel current= p2[pixelObj.getRow()][pixelObj.getCol()];
        Color pixco=Color.BLACK;
        if(current.colorDistance(pixco)<100 && red%2==0)
            {
                red++;
                pixelObj.setRed(red);
            }
        
      }
    }
  }
  
  public Picture decode()
  {
      Picture msg=new Picture(480,640);
      Pixel[][] pixels = this.getPixels2D();
      Pixel[][] p2 = msg.getPixels2D();
    for(int r=0; r<pixels.length; r++)
    {
      for(int c=0; c<pixels[0].length; c++)
      {
          Pixel pixelObj=pixels[r][c];
        Pixel current= p2[pixelObj.getRow()][pixelObj.getCol()];  
        int red=pixelObj.getRed();
        if(red%2==1)
        {
            current.setColor(Color.black);
        }
      }
    }
      return msg;
  }
  public static void main(String[] args) 
  {
    Picture beach = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();



  }
  
} 
