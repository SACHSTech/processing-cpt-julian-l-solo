import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  int state = 0;	
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
    image(backgroundImage, 0, 0, width, height);

    textAlign(CENTER, CENTER);
    textSize(80);
    rect(450, 90, 500, 100);
    fill(255);
    text("CATQUEST", width / 2, 80);

    textSize(30);
    drawButton("Play", width / 2, 200);
    drawButton("Controls", width / 2, 300);
    drawButton("Attacks", width / 2, 400);
    
  }

  void drawButton(String label, float x, float y) {
    float buttonWidth = 200;
    float buttonHeight = 50;
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
    text(label, x, y);
  }

  void handleButtonClick(String label) {
    if (label == "Controls") {
      state = 1;
    }
    if (label == "Attacks") {
      state = 2;
    }
    if (label == "Play") {
      state = 3;
    }
  }

}