import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
  PImage ImgCloud;
  PImage ImgGrass;
  PImage ImgLeaf;
  PImage ImgPlayer;

  double dblCloudX = -180;
  double dblCloud2X = -270;
  double dblCloud3X = -900;

  float[] fltLeafY = new float[5];

  boolean blnUpClicked = false;
  boolean blnDownClicked = false;
  boolean blnLeftClicked = false;
  boolean blnRightClicked = false;

  float fltPlayerX = 300;
  float fltPlayerY = 200;
  float fltPlayerSpeed = 4;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(900, 600);
    for (int i = 0; i < fltLeafY.length; i++){
      fltLeafY[i] = random(height);
    }
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {

    background(210, 255, 173);
    ImgCloud = loadImage("../assets/cloud.png");
    ImgGrass = loadImage("../assets/grass.png");
    ImgLeaf = loadImage("../assets/leaf.png");
    ImgPlayer = loadImage("../assets/player.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(92, 163, 204);

    image(ImgCloud, (float) dblCloudX, 40, 200, 90);
    dblCloudX = dblCloudX + 1.75;
    if (dblCloudX > 900){
      dblCloudX = -180;
    }

    image(ImgCloud, (float) dblCloud2X, 20, 250, 90);
    dblCloud2X = dblCloud2X + 1.2;
    if (dblCloud2X > 900){
      dblCloud2X = -270;
    }

    image(ImgCloud, (float) dblCloud3X, 60, 200, 80);
    dblCloud3X = dblCloud3X + 1.3;
    if (dblCloud3X > 900){
      dblCloud3X = -900;
    }

    image(ImgGrass,0,150, 900, 600);

    image(ImgPlayer, fltPlayerX, fltPlayerY, 300, 300);

    if (blnUpClicked){
      fltPlayerY = fltPlayerY - fltPlayerSpeed;
    }
    if (blnDownClicked){
      fltPlayerY = fltPlayerY + fltPlayerSpeed;
    }
    if (blnLeftClicked){
      fltPlayerX = fltPlayerX - fltPlayerSpeed;
    }
    if (blnRightClicked){
      fltPlayerX = fltPlayerX + fltPlayerSpeed;
    }
  }

  public void keyPressed() {
    if (keyCode == UP) {
      blnUpClicked = true;
    } else if (keyCode == DOWN) {
      blnDownClicked = true;
    } else if (keyCode == LEFT) {
      blnLeftClicked = true;
    } else if (keyCode == RIGHT) {
      blnRightClicked = true;
    }
  }

  public void keyReleased() {
    if (keyCode == UP) {
      blnUpClicked = false;
    } else if (keyCode == DOWN) {
      blnDownClicked = false;
    } else if (keyCode == LEFT) {
      blnLeftClicked = false;
    } else if (keyCode == RIGHT) {
      blnRightClicked = false;
    }
  }
}