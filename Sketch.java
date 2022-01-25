import processing.core.PApplet;
import processing.core.PImage;
import java.util.Timer;

public class Sketch extends PApplet {
  PImage ImgCloud;
  PImage ImgGrass;
  PImage ImgRain;
  PImage ImgPlayer;
  PImage ImgEmptyBucket;
  PImage ImgFullBucket;
  PImage ImgWater;
  PImage ImgFarmPlot;
  PImage ImgPlantedPlot;
  PImage ImgGrownPlot;

  double dblCloudX = -180;
  double dblCloud2X = -270;
  double dblCloud3X = -900;

  float[] fltRainY = new float[10];

  boolean blnUpClicked = false;
  boolean blnDownClicked = false;
  boolean blnLeftClicked = false;
  boolean blnRightClicked = false;

  float fltPlayerX = 380;
  float fltPlayerY = 200;
  float fltPlayerSpeed = 5;

  boolean blnIsBucketFull = false;

  boolean blnIsCropPlanted = false;
  boolean blnIsCropGrown = false;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(900, 500);

    for (int i = 0; i < fltRainY.length; i++){
      fltRainY[i] = random(height);
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
    ImgRain = loadImage("../assets/rain.png");
    ImgPlayer = loadImage("../assets/player.png");
    ImgEmptyBucket = loadImage("../assets/emptybucket.png");
    ImgFullBucket = loadImage("../assets/fullbucket.png");
    ImgWater = loadImage("../assets/water.jpg");
    ImgFarmPlot = loadImage("../assets/farmplot.jpg");
    ImgPlantedPlot = loadImage("../assets/plantedplot.jpg");
    ImgGrownPlot = loadImage("../assets/grownplot.jpg");
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
    image(ImgWater, 700, 240, 255, 150);

    if (blnIsCropPlanted == false){
      image(ImgFarmPlot, 25, 175, 300, 300);
    } else if (blnIsCropPlanted == true){
      image(ImgPlantedPlot, 25, 175, 300, 300);
    } else if (blnIsCropGrown == true){
      image(ImgGrownPlot, 25, 175, 300, 300);
    }


    image(ImgPlayer, fltPlayerX, fltPlayerY, 300, 300);

    if (blnIsBucketFull == false) {
      image(ImgEmptyBucket, fltPlayerX + 100, fltPlayerY + 170, 30,30);
    } else {
      image(ImgFullBucket, fltPlayerX + 100, fltPlayerY + 170, 30, 30);
    }

    System.out.println(fltPlayerX + " / " + fltPlayerY);

    if (blnUpClicked){
      if (fltPlayerY >= 30) {
        fltPlayerY = fltPlayerY - fltPlayerSpeed;
      }
    }
    if (blnDownClicked){
      if (fltPlayerY <= 304) {
        fltPlayerY = fltPlayerY + fltPlayerSpeed;
      }
    }
    if (blnLeftClicked){
      if (fltPlayerX >= -112) {
        fltPlayerX = fltPlayerX - fltPlayerSpeed;
      }
    }
    if (blnRightClicked){
      if (fltPlayerX <= 736){
        fltPlayerX = fltPlayerX + fltPlayerSpeed;
      }
    }

    if (fltPlayerX >= 505){
      textSize(20);
      text("Press E to get Water", 700, 200);
      if (keyPressed){
        if (key == 'e'){
          blnIsBucketFull = true;
        }
      }
    }
    if (fltPlayerX <= 190 && blnIsBucketFull == true){
      textSize(20);
      text("Press E to Plant Crops", 75, 170);
      if (keyPressed){
        if (key == 'e'){
          blnIsBucketFull = false;
          blnIsCropPlanted = true;
        }
      }
    }

    if (blnIsCropPlanted == true){

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