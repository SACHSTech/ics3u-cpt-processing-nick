import processing.core.PApplet;
import processing.core.PImage;

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
  float fltPlantTimer = 70;

  int intScoreCounter = 0;
  boolean blnIsCounterOn = false;

  float[] fltRainY = new float[30];
  float fltRainTimer = 250;
  boolean blnIsRaining = false;
  float fltRainStop = 80;

  int intGameCountdown = 30; //Change

  boolean blnIsMenuOn = true;

  boolean blnIsEndScreen = false;
  int intGameCounter = 0;
  boolean blnCounterGoUp = false;
  int[] intHighScores = new int[2500];

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
    if (blnIsMenuOn == true){
      background(92, 163, 204);

      image(ImgCloud, (float) dblCloudX, 40, 200, 90);
      dblCloudX = dblCloudX + 1.75;
      if (dblCloudX > 900) {
        dblCloudX = -180;
      }
      image(ImgCloud, (float) dblCloud2X, 20, 250, 90);
      dblCloud2X = dblCloud2X + 1.2;
      if (dblCloud2X > 900) {
        dblCloud2X = -270;
      }
      image(ImgCloud, (float) dblCloud3X, 60, 200, 80);
      dblCloud3X = dblCloud3X + 1.3;
      if (dblCloud3X > 900) {
        dblCloud3X = -900;
      }

      image(ImgGrass, 0, 150, 900, 600);
      image(ImgWater, 700, 240, 255, 150);

      if (blnIsCropPlanted == false) {
        image(ImgFarmPlot, 25, 175, 300, 300);
      } else if (blnIsCropPlanted == true) {
        image(ImgPlantedPlot, 25, 175, 300, 300);
      }
      if (blnIsCropGrown == true) {
        image(ImgGrownPlot, 25, 175, 300, 300);
      }

      image(ImgPlayer, fltPlayerX, fltPlayerY, 300, 300);

      if (blnIsBucketFull == false) {
        image(ImgEmptyBucket, fltPlayerX + 100, fltPlayerY + 170, 30, 30);
      } else {
        image(ImgFullBucket, fltPlayerX + 100, fltPlayerY + 170, 30, 30);
      }

      if (blnUpClicked) {
        if (fltPlayerY >= 30) {
          fltPlayerY = fltPlayerY - fltPlayerSpeed;
        }
      }
      if (blnDownClicked) {
        if (fltPlayerY <= 304) {
          fltPlayerY = fltPlayerY + fltPlayerSpeed;
        }
      }
      if (blnLeftClicked) {
        if (fltPlayerX >= -112) {
          fltPlayerX = fltPlayerX - fltPlayerSpeed;
        }
      }
      if (blnRightClicked) {
        if (fltPlayerX <= 736) {
          fltPlayerX = fltPlayerX + fltPlayerSpeed;
        }
      }

      if (fltPlayerX >= 505) {
        textSize(20);
        text("Press E to get Water", 700, 200);
        if (keyPressed) {
          if (key == 'e') {
            blnIsBucketFull = true;
          }
        }
      }

      if (fltPlayerX <= 190 && blnIsBucketFull == true && blnIsCropGrown == true) {
        fltPlantTimer = 70;
        textSize(20);
        text("Press F to Harvest Crops", 75, 170);
        if (keyPressed) {
          if (key == 'f') {
            blnIsCropGrown = false;
            blnIsCropPlanted = false;
            blnIsCounterOn = true;
          }
        }
      } else if (fltPlayerX <= 200 && blnIsBucketFull == true && blnIsRaining == true) {
        fltPlantTimer = 70;
        textSize(20);
        text("Press F to Harvest Crops", 75, 170);
        if (keyPressed) {
          if (key == 'f') {
            blnIsCropGrown = false;
            blnIsCropPlanted = false;
            blnIsCounterOn = true;
          }
        }
      } else if (fltPlayerX <= 200 && blnIsBucketFull && blnIsCropGrown){
        fltPlantTimer = 70;
        textSize(20);
        text("Press F to Harvest Crops", 75, 170);
        if (keyPressed) {
          if (key == 'f') {
            blnIsCropGrown = false;
            blnIsCropPlanted = false;
            blnIsCounterOn = true;
          }
        }
      }else if (fltPlayerX <= 200 && blnIsBucketFull == true){
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
        fltPlantTimer--;
        if (fltPlantTimer <= 0){
          blnIsCropPlanted = false;
          blnIsCropGrown = true;
        }
      }
      if (blnIsCropGrown == true && fltPlayerX <= 200){
        fltPlantTimer = 70;
        textSize(20);
        text("Press F to Harvest Crops", 75, 170);
        if (keyPressed){
          if (key == 'f'){
            blnIsCropGrown = false;
            blnIsCropPlanted = false;
            blnIsCounterOn = true;
          }
        }
      }

      if (blnIsRaining == false){
        fltRainStop = 80;
        fltRainTimer--;
      }

      if (fltRainTimer <= 0) {
        blnIsRaining = true;
      }

      if (blnIsRaining == true){
        textSize(100);
        text("RAIN FRENZY", 0, 120);

        for (int i = 0; i < fltRainY.length; i ++) {
          float fltRainX = width * i / fltRainY.length;
          image(ImgRain, fltRainX, fltRainY[i], 25, 25);
          fltRainY[i] += 7;
          if (fltRainY[i] > height) {
            fltRainY[i] = -30;
          }
        }
        blnIsCropGrown = true;
        fltRainStop--;

        if (fltRainStop <= 0){
          blnIsRaining = false;
          fltRainTimer = 250;
        }
      }

      if (blnIsCounterOn == true){
        intScoreCounter++;
        blnIsCounterOn = false;
      }

      fill(0);
      textSize(25);
      text("Score: " + intScoreCounter, 760, 485);

      intGameCountdown--;
      textSize(25);
      text("Time Left: " + intGameCountdown, 700, 40);

      if (intGameCountdown <= 0){
        blnIsEndScreen = true;
      }
    }

    if (blnIsEndScreen == true){
      background(92, 163, 204);

      System.out.println(mouseX + " / " + mouseY);

      fill(181, 169, 138);
      rect(25, 25, 850, 450);

      blnCounterGoUp = true;

      fill(0);
      textSize(100);
      text("Click To Retry", 125, 280);
      text("Good Job!", 220, 120);
      text("Score: " + intScoreCounter, 250, 430);
      noFill();
      stroke(0);
      strokeWeight(8);
      rect(121, 181, 686, 130);

      if (mouseX >= 121 && mouseX <= 807 && mouseY >= 199 && mouseY <= 318){
          if (mousePressed){
            
            blnIsEndScreen = false;
          }
      }
    }

    if (blnCounterGoUp == true){
        intGameCounter++;
        blnCounterGoUp = false;
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