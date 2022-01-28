import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
  /**
   * Farming Game with Harvesting, Watering, and Player Movement
   * @author: Nicholas Khatchadourian
   */

  // Initiating all images
  PImage ImgControls;
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

  // Declares cloud initial values
  double dblCloudX = -180;
  double dblCloud2X = -270;
  double dblCloud3X = -900;

  // Declares player movement variables
  boolean blnUpClicked = false;
  boolean blnDownClicked = false;
  boolean blnLeftClicked = false;
  boolean blnRightClicked = false;
  float fltPlayerX = 380;
  float fltPlayerY = 200;
  float fltPlayerSpeed = 6;

  // Declares watering and planting variables
  boolean blnIsBucketFull = false;
  boolean blnIsCropPlanted = false;
  boolean blnIsCropGrown = false;
  float fltPlantTimer = 70;

  // Declares raining variables and timers
  float[] fltRainY = new float[30];
  float fltRainTimer = 250;
  boolean blnIsRaining = false;
  float fltRainStop = 80;

  // Declares variables for game countdown and score
  int intScoreCounter = 0;
  boolean blnIsCounterOn = false;
  int intGameCountdown = 900;

  // Checks which menu is visible
  boolean blnIsMenuOn = true;
  boolean blnIsEndScreen = false;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(900, 500);
    // Places every raindrop at a random position
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
    // Loads all the images that were initiated above
    ImgControls = loadImage("../assets/controls.jpg");
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
    // MENU SCREEN
    if (blnIsMenuOn == true){
      // Menu screen background with title text
      background(92, 163, 204);
      fill(181, 169, 138);
      stroke(0);
      strokeWeight(8);
      textSize(60);
      rect(25, 25, 850, 450);
      fill(0);
      text("Water&Grow: Farming Game", 43, 100);
      // Play button text with box outline
      text("Play", 395, 220);
      noFill();
      stroke(0);
      strokeWeight(8);
      rect(300, 140, 300, 130);
      // Image of controls for the game
      image(ImgControls, 25,290, 840, 175);

      // Declares the perimeter of the button and when pressed, makes the menu invisible
      if (mouseX >= 305 && mouseX <= 596 && mouseY >= 143 && mouseY <= 265){
        if (mousePressed){
          blnIsMenuOn = false;
        }
      }
    }
    // THE MAIN GAME
    if (blnIsMenuOn == false){
      background(92, 163, 204);

      // Creates cloud movement, resets once they go offscreen
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
      // Places the grass and water images on screen
      image(ImgGrass, 0, 150, 900, 600);
      image(ImgWater, 700, 240, 255, 150);

      // Logic for what image to display based on plant growth
      if (blnIsCropPlanted == false) {
        image(ImgFarmPlot, 25, 175, 300, 300);
      } else if (blnIsCropPlanted == true) {
        image(ImgPlantedPlot, 25, 175, 300, 300);
      }
      if (blnIsCropGrown == true) {
        image(ImgGrownPlot, 25, 175, 300, 300);
      }

      // Places player image with player location
      image(ImgPlayer, fltPlayerX, fltPlayerY, 300, 300);
      // Logic for what image to display based on how full bucket is
      if (blnIsBucketFull == false) {
        image(ImgEmptyBucket, fltPlayerX + 100, fltPlayerY + 170, 30, 30);
      } else {
        image(ImgFullBucket, fltPlayerX + 100, fltPlayerY + 170, 30, 30);
      }

      // Declaring where the player can move based on the screen dimensions
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
        if (fltPlayerX <= 536) {
          fltPlayerX = fltPlayerX + fltPlayerSpeed;
        }
      }
      // If the player is close enough to water, they can press E to fill their bucket
      if (fltPlayerX >= 505) {
        textSize(20);
        text("Press E to get Water", 700, 200);
        if (keyPressed) {
          if (key == 'e') {
            blnIsBucketFull = true;
          }
        }
      }
      // If the player's close enough to the crops and they're grown they can harvest them with F key
      // Also other conditions if bucket is full or if it's raining to display the correct text
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
      } else if (fltPlayerX <= 200 && blnIsBucketFull == true && blnIsCropGrown == true){
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
        // Condition to allow the player to plant crops when their bucket is full and press E
      } else if (fltPlayerX <= 200 && blnIsBucketFull == true){
        textSize(20);
        text("Press E to Plant Crops", 75, 170);
        if (keyPressed){
          if (key == 'e'){
            blnIsBucketFull = false;
            blnIsCropPlanted = true;
          }
        }
      }
      // Starts a countdown for how long it takes plant to grow
      if (blnIsCropPlanted == true){
        fltPlantTimer--;
        if (fltPlantTimer <= 0){
          blnIsCropPlanted = false;
          blnIsCropGrown = true;
        }
      }
      // If the player's close enough to the crops and they're grown they can harvest them with F key
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
      // When the rain stops, it resets when the rain will stop
      if (blnIsRaining == false){
        fltRainStop = 80;
      }
      // When the timer for the rain to start is at/less than 0, it will start to rain
      if (fltRainTimer <= 0) {
        blnIsRaining = true;
      }
      // When the rain starts, it displays a text on screen, starts to show the rain falling down the screen, and make it so the crops are grown
      // Starts the RainStop timer and when it ends, the rain stops
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
      // When the user harvests plots, it adds a point to their score
      if (blnIsCounterOn == true){
        intScoreCounter++;
        blnIsCounterOn = false;
      }

      // Runs down the rain timer constantly through the game
      fltRainTimer--;
      // Puts the score and the timer on screen
      fill(0);
      textSize(25);
      text("Score: " + intScoreCounter, 760, 485);
      intGameCountdown--;
      textSize(25);
      text("Time Left: " + intGameCountdown, 700, 40);

      // When the game counter is at/less than 0 it displays the end screen/retry screen
      if (intGameCountdown <= 0){
        blnIsEndScreen = true;
      }
    }

    // END SCREEN
    if (blnIsEndScreen == true){
      // Shows the background, text, the score, and the button to retry with a boxed outline
      fltRainTimer = 250;
      background(92, 163, 204);
      fill(181, 169, 138);
      rect(25, 25, 850, 450);
      fill(0);
      textSize(100);
      text("Click To Retry", 125, 280);
      text("Good Job!", 220, 120);
      text("Score: " + intScoreCounter, 250, 430);
      noFill();
      stroke(0);
      strokeWeight(8);
      rect(121, 181, 686, 130);

      // Conditions for the user's mouse position for the button to function
      if (mouseX >= 121 && mouseX <= 807 && mouseY >= 199 && mouseY <= 318){
        // The button resets all the values back to the default set at the top of the program
          if (mousePressed){
            fltRainTimer = 250;
            fltRainStop = 80;
            intGameCountdown = 900;
            fltPlayerX = 380;
            fltPlayerY = 200;
            blnIsCropGrown = false;
            blnIsCropPlanted = false;
            blnIsBucketFull = false;
            intScoreCounter = 0;
            blnIsMenuOn = false;
            blnIsEndScreen = false;
          }
      }
    }
  }
  /**
   * Sets whichever keys are pressed to true as a way for the user to move with multiple keys at once
   * @return Which key is pressed
   */
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
  /**
   * Sets whichever keys are pressed to false as a way for the user to deselect specific keys and not others
   * @return Which key stopped being pressed
   */
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