package Classes;

 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
public class PictureExplorer implements MouseMotionListener, ActionListener, MouseListener
{
 
  // current indicies
  /** row index */
  private int rowIndex = 0; 
  /** column index */
  private int colIndex = 0;
  
  // main GUI
  /** window to hold GUI */
  private JFrame pictureFrame;
  /** window that allows the user to scroll to see a large picture */
  private JScrollPane scrollPane;
  
  // GUI components
  /** column label */
  private JLabel colLabel;
  /** column previous button */
  private JButton colPrevButton;
  /** row previous button */
  private JButton rowPrevButton;
  /** column next button */
  private JButton colNextButton;
  /** row next button */
  private JButton rowNextButton;
  /** row label */
  private JLabel rowLabel;
  /** text field to show column index */
  private JTextField colValue;
  /** text field to show row index */
  private JTextField rowValue;
  /** red value label */
  private JLabel rValue;
  /** green value label */
  private JLabel gValue;
  /** blue value label */
  private JLabel bValue;
  /** color swatch label */
  private JLabel colorLabel;
  /** panel to show the color swatch */
  private JPanel colorPanel;
  
  // menu components
  /** menu bar */
  private JMenuBar menuBar;
  /** zoom menu */
  private JMenu zoomMenu;
  /** 25% zoom level */
  private JMenuItem twentyFive;
  /** 50% zoom level */
  private JMenuItem fifty;
  /** 75% zoom level */
  private JMenuItem seventyFive;
  /** 100% zoom level */
  private JMenuItem hundred;
  /** 150% zoom level */
  private JMenuItem hundredFifty;
  /** 200% zoom level */
  private JMenuItem twoHundred;
  /** 500% zoom level */
  private JMenuItem fiveHundred;
  
  /** The picture being explored */
  private DigitalPicture picture;
  
  /** The image icon used to display the picture */
  private ImageIcon scrollImageIcon;
  
  /** The image display */
  private ImageDisplay imageDisplay;
  
  /** the zoom factor (amount to zoom) */
  private double zoomFactor;
  
  /** the number system to use, 0 means starting at 0, 1 means starting at 1 */
  private int numberBase=0;
  
  public PictureExplorer(DigitalPicture picture)
  {
    // set the fields
    this.picture=picture;
    zoomFactor=1;
    
    // create the window and set things up
    createWindow();
  }
  public void changeToBaseOne()
  {
    numberBase=1;
  }
  public void setTitle(String title)
  {
    pictureFrame.setTitle(title);
  }
  
  private void createAndInitPictureFrame()
  {
    pictureFrame = new JFrame(); // create the JFrame
    pictureFrame.setResizable(true);  // allow the user to resize it
    pictureFrame.getContentPane().setLayout(new BorderLayout()); 
    pictureFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
    pictureFrame.setTitle(picture.getTitle());
    PictureExplorerFocusTraversalPolicy newPolicy = new PictureExplorerFocusTraversalPolicy();
    pictureFrame.setFocusTraversalPolicy(newPolicy);
    
  }
  
  private void setUpMenuBar()
  {
    //create menu
    menuBar = new JMenuBar();
    zoomMenu = new JMenu("Zoom");
    twentyFive = new JMenuItem("25%");
    fifty = new JMenuItem("50%");
    seventyFive = new JMenuItem("75%");
    hundred = new JMenuItem("100%");
    hundred.setEnabled(false);
    hundredFifty = new JMenuItem("150%");
    twoHundred = new JMenuItem("200%");
    fiveHundred = new JMenuItem("500%");
    
    // add the action listeners
    twentyFive.addActionListener(this);
    fifty.addActionListener(this);
    seventyFive.addActionListener(this);
    hundred.addActionListener(this);
    hundredFifty.addActionListener(this);
    twoHundred.addActionListener(this);
    fiveHundred.addActionListener(this);
    
    // add the menu items to the menus
    zoomMenu.add(twentyFive);
    zoomMenu.add(fifty);
    zoomMenu.add(seventyFive);
    zoomMenu.add(hundred);
    zoomMenu.add(hundredFifty);
    zoomMenu.add(twoHundred);
    zoomMenu.add(fiveHundred);
    menuBar.add(zoomMenu);
    
    // set the menu bar to this menu
    pictureFrame.setJMenuBar(menuBar);
  }
  
  private void createAndInitScrollingImage()
  {
    scrollPane = new JScrollPane();
    
    BufferedImage bimg = picture.getBufferedImage();
    imageDisplay = new ImageDisplay(bimg);
    imageDisplay.addMouseMotionListener(this);
    imageDisplay.addMouseListener(this);
    imageDisplay.setToolTipText("Click a mouse button on a pixel to see the pixel information");
    scrollPane.setViewportView(imageDisplay);
    pictureFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
  }
  
  private void createWindow()
  {
    // create the picture frame and initialize it
    createAndInitPictureFrame();
    
    // set up the menu bar
    setUpMenuBar();
    
    //create the information panel
    createInfoPanel();
    
    //creates the scrollpane for the picture
    createAndInitScrollingImage();
    
    // show the picture in the frame at the size it needs to be
    pictureFrame.pack();
    pictureFrame.setVisible(true);
  }
  
  private void setUpNextAndPreviousButtons()
  {
    // create the image icons for the buttons
    Icon prevIcon = new ImageIcon(DigitalPicture.class.getResource("leftArrow.gif"), 
                                  "previous index");
    Icon nextIcon = new ImageIcon(DigitalPicture.class.getResource("rightArrow.gif"), 
                                  "next index");
    // create the arrow buttons
    colPrevButton = new JButton(prevIcon);
    colNextButton = new JButton(nextIcon);
    rowPrevButton = new JButton(prevIcon);
    rowNextButton = new JButton(nextIcon);
    
    // set the tool tip text
    colNextButton.setToolTipText("Click to go to the next column value");
    colPrevButton.setToolTipText("Click to go to the previous column value");
    rowNextButton.setToolTipText("Click to go to the next row value");
    rowPrevButton.setToolTipText("Click to go to the previous row value");
    
    // set the sizes of the buttons
    int prevWidth = prevIcon.getIconWidth() + 2;
    int nextWidth = nextIcon.getIconWidth() + 2;
    int prevHeight = prevIcon.getIconHeight() + 2;
    int nextHeight = nextIcon.getIconHeight() + 2;
    Dimension prevDimension = new Dimension(prevWidth,prevHeight);
    Dimension nextDimension = new Dimension(nextWidth, nextHeight);
    colPrevButton.setPreferredSize(prevDimension);
    rowPrevButton.setPreferredSize(prevDimension);
    colNextButton.setPreferredSize(nextDimension);
    rowNextButton.setPreferredSize(nextDimension);
    
    // handle previous column button press
    colPrevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        colIndex--;
        if (colIndex < 0)
          colIndex = 0;
        displayPixelInformation(colIndex,rowIndex);
      }
    });
    
    // handle previous row button press
    rowPrevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        rowIndex--;
        if (rowIndex < 0)
          rowIndex = 0;
        displayPixelInformation(colIndex,rowIndex);
      }
    });
    
    // handle next column button press
    colNextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        colIndex++;
        if (colIndex >= picture.getWidth())
          colIndex = picture.getWidth() - 1;
        displayPixelInformation(colIndex,rowIndex);
      }
    });
    
    // handle next row button press
    rowNextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        rowIndex++;
        if (rowIndex >= picture.getHeight())
          rowIndex = picture.getHeight() - 1;
        displayPixelInformation(colIndex,rowIndex);
      }
    });
  }
  
  public JPanel createLocationPanel(Font labelFont) {
    
    // create a location panel
    JPanel locationPanel = new JPanel();
    locationPanel.setLayout(new FlowLayout());
    Box hBox = Box.createHorizontalBox();
    
    // create the labels
    rowLabel = new JLabel("Row:");
    colLabel = new JLabel("Column:");
    
    // create the text fields
    colValue = new JTextField(Integer.toString(colIndex + numberBase),6);
    colValue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayPixelInformation(colValue.getText(),rowValue.getText());
      }
    });
    rowValue = new JTextField(Integer.toString(rowIndex + numberBase),6);
    rowValue.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayPixelInformation(colValue.getText(),rowValue.getText());
      }
    });
    
    // set up the next and previous buttons
    setUpNextAndPreviousButtons();
    
    // set up the font for the labels
    colLabel.setFont(labelFont);
    rowLabel.setFont(labelFont);
    colValue.setFont(labelFont);
    rowValue.setFont(labelFont);
    
    // add the items to the vertical box and the box to the panel
    hBox.add(Box.createHorizontalGlue());
    hBox.add(rowLabel);
    hBox.add(rowPrevButton);
    hBox.add(rowValue);
    hBox.add(rowNextButton);
    hBox.add(Box.createHorizontalStrut(10));
    hBox.add(colLabel);
    hBox.add(colPrevButton);
    hBox.add(colValue);
    hBox.add(colNextButton);
    locationPanel.add(hBox);
    hBox.add(Box.createHorizontalGlue());
    
    return locationPanel;
  }
  
  private JPanel createColorInfoPanel(Font labelFont)
  {
    // create a color info panel
    JPanel colorInfoPanel = new JPanel();
    colorInfoPanel.setLayout(new FlowLayout());
    
    // get the pixel at the x and y
    Pixel pixel = new Pixel(picture,colIndex,rowIndex);
    
    // create the labels
    rValue = new JLabel("R: " + pixel.getRed());
    gValue = new JLabel("G: " + pixel.getGreen());
    bValue = new JLabel("B: " + pixel.getBlue());
    
    // create the sample color panel and label
    colorLabel = new JLabel("Color at location: ");
    colorPanel = new JPanel();
    colorPanel.setBorder(new LineBorder(Color.black,1));
    
    // set the color sample to the pixel color
    colorPanel.setBackground(pixel.getColor());
    
    // set the font
    rValue.setFont(labelFont);
    gValue.setFont(labelFont);
    bValue.setFont(labelFont);
    colorLabel.setFont(labelFont);
    colorPanel.setPreferredSize(new Dimension(25,25));
    
    // add items to the color information panel
    colorInfoPanel.add(rValue);
    colorInfoPanel.add(gValue);
    colorInfoPanel.add(bValue);
    colorInfoPanel.add(colorLabel);
    colorInfoPanel.add(colorPanel);
    
    return colorInfoPanel; 
  }
  
  private void createInfoPanel()
  {
    // create the info panel and set the layout
    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BorderLayout());
    
    // create the font
    Font largerFont = new Font(infoPanel.getFont().getName(),
                               infoPanel.getFont().getStyle(),14);
    
    // create the pixel location panel
    JPanel locationPanel = createLocationPanel(largerFont);
    
    // create the color information panel
    JPanel colorInfoPanel = createColorInfoPanel(largerFont);
    
    // add the panels to the info panel
    infoPanel.add(BorderLayout.NORTH,locationPanel);
    infoPanel.add(BorderLayout.SOUTH,colorInfoPanel); 
    
    // add the info panel
    pictureFrame.getContentPane().add(BorderLayout.NORTH,infoPanel);
  } 
  
  public void checkScroll()
  {
    // get the x and y position in pixels
    int xPos = (int) (colIndex * zoomFactor); 
    int yPos = (int) (rowIndex * zoomFactor); 
    
    // only do this if the image is larger than normal
    if (zoomFactor > 1) {
      
      // get the rectangle that defines the current view
      JViewport viewport = scrollPane.getViewport();
      Rectangle rect = viewport.getViewRect();
      int rectMinX = (int) rect.getX();
      int rectWidth = (int) rect.getWidth();
      int rectMaxX = rectMinX + rectWidth - 1;
      int rectMinY = (int) rect.getY();
      int rectHeight = (int) rect.getHeight();
      int rectMaxY = rectMinY + rectHeight - 1;
      
      // get the maximum possible x and y index
      int macolIndexX = (int) (picture.getWidth() * zoomFactor) - rectWidth - 1;
      int macolIndexY = (int) (picture.getHeight() * zoomFactor) - rectHeight - 1;
      
      // calculate how to position the current position in the middle of the viewing
      // area
      int viewX = xPos - (int) (rectWidth / 2);
      int viewY = yPos - (int) (rectHeight / 2);
      
      // reposition the viewX and viewY if outside allowed values
      if (viewX < 0)
        viewX = 0;
      else if (viewX > macolIndexX)
        viewX = macolIndexX;
      if (viewY < 0)
        viewY = 0;
      else if (viewY > macolIndexY)
        viewY = macolIndexY;
      
      // move the viewport upper left point
      viewport.scrollRectToVisible(new Rectangle(viewX,viewY,rectWidth,rectHeight));
    }
  }
  
  public void zoom(double factor)
  {
    // save the current zoom factor
    zoomFactor = factor;
    
    // calculate the new width and height and get an image that size
    int width = (int) (picture.getWidth()*zoomFactor);
    int height = (int) (picture.getHeight()*zoomFactor);
    BufferedImage bimg = picture.getBufferedImage();
    
    // set the scroll image icon to the new image
    imageDisplay.setImage(bimg.getScaledInstance(width, height, Image.SCALE_DEFAULT));
    imageDisplay.setCurrentX((int) (colIndex * zoomFactor));
    imageDisplay.setCurrentY((int) (rowIndex * zoomFactor));
    imageDisplay.revalidate();
    checkScroll();  // check if need to reposition scroll
  }
  
  public void repaint()
  {
    pictureFrame.repaint();
  }
  
  public void mouseDragged(MouseEvent e)
  {
    displayPixelInformation(e);
  }
  
  private boolean isLocationInPicture(int column, int row)
  {
    boolean result = false; // the default is false
    if (column >= 0 && column < picture.getWidth() &&
        row >= 0 && row < picture.getHeight())
      result = true;
    
    return result;
  }
  
  public void displayPixelInformation(String xString, String yString)
  {
    int x = -1;
    int y = -1;
    try {
      x = Integer.parseInt(xString);
      x = x - numberBase;
      y = Integer.parseInt(yString);
      y = y - numberBase;
    } catch (Exception ex) {
    }
    
    if (x >= 0 && y >= 0) {
      displayPixelInformation(x,y);
    }
  }
  
  private void displayPixelInformation(int pictureX, int pictureY)
  {
    // check that this x and y are in range
    if (isLocationInPicture(pictureX, pictureY))
    {
      // save the current x and y index
      colIndex = pictureX;
      rowIndex = pictureY;
      
      // get the pixel at the x and y
      Pixel pixel = new Pixel(picture,colIndex,rowIndex);
      
      // set the values based on the pixel
      colValue.setText(Integer.toString(colIndex  + numberBase));
      rowValue.setText(Integer.toString(rowIndex + numberBase));
      rValue.setText("R: " + pixel.getRed());
      gValue.setText("G: " + pixel.getGreen());
      bValue.setText("B: " + pixel.getBlue());
      colorPanel.setBackground(new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
      
    } 
    else
    {
      clearInformation();
    }
    
    // notify the image display of the current x and y
    imageDisplay.setCurrentX((int) (colIndex * zoomFactor));
    imageDisplay.setCurrentY((int) (rowIndex * zoomFactor));
  }
  
  private void displayPixelInformation(MouseEvent e)
  {
    
    // get the cursor x and y
    int cursorX = e.getX();
    int cursorY = e.getY();
    
    // get the x and y in the original (not scaled image)
    int pictureX = (int) (cursorX / zoomFactor + numberBase);
    int pictureY = (int) (cursorY / zoomFactor + numberBase);
    
    // display the information for this x and y
    displayPixelInformation(pictureX,pictureY);
    
  }
  
  private void clearInformation()
  {
    colValue.setText("N/A");
    rowValue.setText("N/A");
    rValue.setText("R: N/A");
    gValue.setText("G: N/A");
    bValue.setText("B: N/A");
    colorPanel.setBackground(Color.black);
    colIndex = -1;
    rowIndex = -1;
  }
  
  public void mouseMoved(MouseEvent e)
  {}
  
  public void mouseClicked(MouseEvent e)
  {
    displayPixelInformation(e);
  }
  public void mousePressed(MouseEvent e)
  {
    displayPixelInformation(e);
  }
  public void mouseReleased(MouseEvent e)
  {
  }
  
  public void mouseEntered(MouseEvent e)
  {
  }
  
  public void mouseExited(MouseEvent e)
  {
  }
  
  private void enableZoomItems()
  {
    twentyFive.setEnabled(true);
    fifty.setEnabled(true);
    seventyFive.setEnabled(true);
    hundred.setEnabled(true);
    hundredFifty.setEnabled(true);
    twoHundred.setEnabled(true);
    fiveHundred.setEnabled(true);
  }
  
  public void actionPerformed(ActionEvent a)
  {
    
    if(a.getActionCommand().equals("Update"))
    {
      this.repaint();
    }
    
    if(a.getActionCommand().equals("25%"))
    {
      this.zoom(.25);
      enableZoomItems();
      twentyFive.setEnabled(false);
    }
    
    if(a.getActionCommand().equals("50%"))
    {
      this.zoom(.50);
      enableZoomItems();
      fifty.setEnabled(false);
    }
    
    if(a.getActionCommand().equals("75%"))
    {
      this.zoom(.75);
      enableZoomItems();
      seventyFive.setEnabled(false);
    }
    
    if(a.getActionCommand().equals("100%"))
    {
      this.zoom(1.0);
      enableZoomItems();
      hundred.setEnabled(false);
    }
    
    if(a.getActionCommand().equals("150%"))
    {
      this.zoom(1.5);
      enableZoomItems();
      hundredFifty.setEnabled(false);
    }
    
    if(a.getActionCommand().equals("200%"))
    {
      this.zoom(2.0);
      enableZoomItems();
      twoHundred.setEnabled(false);
    }
    
    if(a.getActionCommand().equals("500%"))
    {
      this.zoom(5.0);
      enableZoomItems();
      fiveHundred.setEnabled(false);
    }
  }
  
  
  
  /**
   * Class for establishing the focus for the textfields
   */
  private class PictureExplorerFocusTraversalPolicy
    extends FocusTraversalPolicy {
    
    /**
     * Method to get the next component for focus
     */
    public Component getComponentAfter(Container focusCycleRoot,
                                       Component aComponent) {
      if (aComponent.equals(colValue))
        return rowValue;
      else 
        return colValue;
    }
    
    /**
     * Method to get the previous component for focus
     */
    public Component getComponentBefore(Container focusCycleRoot,
                                        Component aComponent) {
      if (aComponent.equals(colValue))
        return rowValue;
      else 
        return colValue;
    }
    
    public Component getDefaultComponent(Container focusCycleRoot) {
      return colValue;
    }
    
    public Component getLastComponent(Container focusCycleRoot) {
      return rowValue;
    }
    
    public Component getFirstComponent(Container focusCycleRoot) {
      return colValue;
    }
  }
  
  public static void main( String args[])
  {
    Picture pix = new Picture("H:/AP Computer Science/Image Processing/Images/msg.jpg");
    pix.explore();
  }
  
}
