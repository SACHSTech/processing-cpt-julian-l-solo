import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  int state = 0;
  int lives = 4;	
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
      drawButton("Settings", width / 2, 400, 200, 50);
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
      text("ARROW KEYS to move drone", 450, 230);
      text("Goal: Defeat the boss, avoid losing all lives.", 450, 280);
      text("Your drone is your attacker, move them to attack the boss.", 450, 330);
      text("Careful, your drone resets every once in a while.", 450, 380);
    }

    if (state == 2) {
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

    if (state == 3) {
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
    else if (label == "Settings") {
      state = 2;
    }
    else if (label == "Play") {
      state = 3;
    }
    else if (label == "Easy") {
      lives = 4;
      state = 0;
    }
    else if (label == "Normal") {
      lives = 3;
      state = 0;
    }
    else if (label == "Hard") {
      lives = 1;
      state = 0;
    }
    else if (label == "Tutorial Tabby") {
      state = 4;
    }
    else if (label == "Script Kitty") {
      state = 5;
    }
    else if (label == "Clockpurrk") {
      state = 6;
    }
    else if (label == "The Mainframe") {
      state = 7;
    }
  }

}