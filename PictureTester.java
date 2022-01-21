package Classes;


import java.awt.Color;

public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue() 
  {
    // Picture beach = new Picture("beach.jpg");
    Picture beach = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  // Method to test keepOnlyBlue (Activity 5)
  
  public static void testKeepOnlyBlue() 
  {
    // Picture beach = new Picture("beach.jpg");
    Picture beach = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  
  // Method to test negate (Activity 5)
  
  public static void testNegate() 
  {
    // Picture beach = new Picture("beach.jpg");
    Picture beach = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  
  // Method to test grayscale (Activity 5)
  
  public static void testGrayscale() 
  {
    // Picture beach = new Picture("beach.jpg");
    Picture beach = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
   public static void testFixUnderwater() 
  {
    // Picture beach = new Picture("beach.jpg");
    Picture beach = new Picture("H:/AP Computer Science/Image Processing/Images/water.jpg");
    beach.explore();
    beach.fixUnderwater();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical() 
  {
    Picture caterpillar = new Picture("H:/AP Computer Science/Image Processing/Images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorVerticalRightToLeft (Activity 6) */
  
  public static void testMirrorVerticalRightToLeft() 
  {
    Picture caterpillar = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  
  
   /** Method to test mirrorVerticalRightToLeft (Activity 6) */
  
  public static void testMirrorHorizontal() 
  {
    Picture caterpillar = new Picture("H:/AP Computer Science/Image Processing/Images/redMotorcycle.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontal();
    caterpillar.explore();
  }
  
  
  /** Method to test mirrorVerticalRightToLeft (Activity 6) */
  
  public static void testMirrorHorizontalBotToTop() 
  {
    Picture caterpillar = new Picture("H:/AP Computer Science/Image Processing/Images/redMotorcycle.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBotToTop();
    caterpillar.explore();
  }
  
  
   /** Method to test mirrorVerticalRightToLeft (Activity 6) */
  
  public static void testMirrorPart()  
  {
    Picture caterpillar = new Picture("H:/AP Computer Science/Image Processing/Images/seagull.jpg");
    caterpillar.explore();
    caterpillar.mirrorPart( 378, 230, 333, 230, 353, "L");
   // caterpillar.write("seagull2.jpg");
    caterpillar.explore();
  }
  
  
  
  public static void testEncodeAndDecode() 
  {
      Picture pic = new Picture("H:/AP Computer Science/Image Processing/Images/beach.jpg");  
      pic.explore();
      Picture picMsg = new Picture("H:/AP Computer Science/Image Processing/Images/msg.jpg");  
      pic.encode(picMsg);
      pic.write("H:/AP Computer Science/Image Processing/Images/beach2.jpg");
      pic.explore();
      Picture decoded = pic.decode();
      decoded.explore();      
  }
  
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple() 
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage() 
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection() 
  {
    Picture swan = new Picture("H:/AP Computer Science/Image Processing/Images/swan.jpg");
    swan.explore();
    swan.edgeDetection(5);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args) 
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
 
   // testZeroBlue();
   //testKeepOnlyBlue();
   // testKeepOnlyRed();
   // testKeepOnlyGreen();
   // testNegate();
    //testGrayscale();
   // testFixUnderwater();
   // testMirrorVertical();
   // testMirrorHorizontal();
   // testMirrorHorizontalBotToTop();
    //testMirrorPart();
 // testMirrorVerticalRightToLeft();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
   // testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncode();
    //testDecode();
   // testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}