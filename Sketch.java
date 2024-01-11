import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  int state = 0;
  int lives = 0;	
  PImage backgroundImage;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(900, 500);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    backgroundImage = loadImage("catquest.jpg");
  }

  public void draw() {
    background(210, 255, 173);
    if (state == 0) {
      image(backgroundImage, 0, 0, width, height);

      textAlign(CENTER, CENTER);
      textSize(80);
      rect(450, 90, 500, 100);
      fill(255);
      text("CATQUEST", width / 2, 80);

      drawButton("Play", width / 2, 200, 200, 50);
      drawButton("Controls", width / 2, 300, 200, 50);
      drawButton("Attacks", width / 2, 400, 200, 50);
    }

    if (state == 1) {
      background(0);
      drawButton("Back", 70, 50, 100, 50);
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(255);
      text("CONTROLS", 450, 80);
      textSize(20);
      text("WASD to move", 450, 180);
      text("DRAG MOUSE to aim", 450, 230);
      text("RIGHT CLICK to cycle attacks", 450, 280);
      text("E for special attack [Costs 1 mouse]", 450, 330);
      text("R to activate nine lives [Costs 5 mice]", 450, 380);
      text("Goal: Defeat the boss, avoid losing all lives.", 450, 430);
    }

    if (state == 2) {
      background(0);
      drawButton("Back", 70, 50, 100, 50);
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(255);
      text("ATTACKS", 450, 80);
      textSize(20);
      text("CLAW LAUNCHER - A default, fast shooting weapon suitable for general use.", 450, 150);
      text("\tSPECIAL - Gives a rotating claw shield that removes projectiles.", 450, 180);
      text("KIBBLE BURST - A quick but small shotgun that can do wonders up close.", 450, 250);
      text("\tSPECIAL - Releases powerful kibble in all directions.", 450, 280);
      text("CATNIP CANNON - A devastating, piercing shot. Slow, but deadly.", 450, 350);
      text("\tSPECIAL - Sends an even more devastating blast, though it doesn’t pierce.", 450, 380);
      text("NINE LIVES - Can heal 1 hp by spending 5 mice.", 450, 450);
    }

    if (state == 3) {
      background(0);
      drawButton("Back", 70, 50, 100, 50);
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(255);
      text("Choose a Difficulty", 450, 80);
      text("This will determine your life count", 450, 150);
      textSize(20);
      drawButton("Easy", 450, 250, 200, 50);
      drawButton("Normal", 450, 340, 200, 50);
      drawButton("Hard", 450, 430, 200, 50);
    }

    if (state == 4) {
      background(0);
      drawButton("Back", 70, 50, 100, 50);
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(255);
      text("Choose your Bossfight", 450, 80);
      textSize(20);
      drawButton("Tutorial Tabby", 450, 180, 250, 50);
      drawButton("Script Kitty", 450, 250, 250, 50);
      drawButton("Clockpurrk", 450, 320, 250, 50);
      drawButton("The Mainframe", 450, 390, 250, 50);
    }
  }

  void drawButton(String label, float x, float y, float buttonWidth, float buttonHeight) {
    float cornerRadius = 10;

    if (mouseX > x - buttonWidth / 2 && mouseX < x + buttonWidth / 2 && mouseY > y - buttonHeight / 2 && mouseY < y + buttonHeight / 2) {
      fill(150);
      if (mousePressed) {
        handleButtonClick(label);
      }
    } else {
        fill(200);
    }

    rectMode(CENTER);
    rect(x, y, buttonWidth, buttonHeight, cornerRadius);
    fill(0);
    textSize(30);
    text(label, x, y);
  }

  void handleButtonClick(String label) {
    if (label == "Back") {
      state = 0;
    }
    else if (label == "Controls") {
      state = 1;
    }
    else if (label == "Attacks") {
      state = 2;
    }
    else if (label == "Play") {
      state = 3;
    }
    else if (label == "Easy") {
      lives = 10;
      state = 4;
    }
    else if (label == "Normal") {
      lives = 6;
      state = 4;
    }
    else if (label == "Hard") {
      lives = 3;
      state = 4;
    }
    else if (label == "Tutorial Tabby") {
      state = 5;
    }
    else if (label == "Script Kitty") {
      state = 6;
    }
    else if (label == "Clockpurrk") {
      state = 7;
    }
    else if (label == "The Mainframe") {
      state = 8;
    }
  }

}