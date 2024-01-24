import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Main Menu/Cutscene Image Variables
  PImage catimg;
  PImage drone;
  PImage clock;
  PImage clockDiamond;
  PImage spiral;
  PImage evil;
  PImage explosion;
  PImage laptop;
  PImage backgroundImage;

  // C Sharp++ Images
  PImage Cboss;
  PImage codeUmbrella;

  // Swift Images
  PImage swift;
  PImage swiftAlt;
  PImage miniSwift;
  PImage swiftOrb;

  // Go Images
  PImage cop;
  PImage goBoss;
  PImage geyser;
  PImage laserOrb;
  PImage goCar;

  // General Variables
  int state = 0;
  int lives = 4;
  float playerX = 100f;
  float playerY = 410f;
  double gravity = 0.5;
  float verticalVelocity = 0;
  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean isJumping = false;	
  boolean buttonPressed = false;
  boolean cutsceneWatched = false;

  // Drone Variables
  float droneX = 100;
  float droneY = 410;
  float droneDirectionX = 5;
  float droneDirectionY = 0;
  float droneCooldown = 0;

  // General Boss Variables
  int bossX = 0;
  int bossY = 0;
  float bossHealth = 100;
  int bossMovement = 5;
  boolean recentDamage = false;
  int phase = 0;

  // C Sharp++ Variables
  int umbrellaMovement = -5;
  float umbrellaX = 800;
  float umbrellaY = 415;

  // Swift Variables
  boolean facingLeft = true;
  float proj1X = 0;
  float proj1Y = 0;
  float proj2X = 0;
  float proj2Y = 0;
  boolean warning = false;

  // Go Variables
  Random myRandom = new Random();
  int trafficLight = -1;
  float currentPlayerLocation = playerX;
  float carX = 900;
  int[] copPosition = new int[5];
  int[] discPosition = new int[5];
  int currentPosition = -1;
  int geyserLength = 100;
	
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
    catimg = loadImage("catcharacter.png");
    drone = loadImage("catDrone.png");
    clock = loadImage("clock.png");
    clockDiamond = loadImage("clock_diamond.png");
    spiral = loadImage("spiral.png");
    evil = loadImage("redeyes.png");
    explosion = loadImage("clocksplosion.jpg");
    laptop = loadImage("laptop.png");

    Cboss = loadImage("C.png");
    codeUmbrella = loadImage("binary_umbrella.png");

    swift = loadImage("swiftRight.png");
    swiftAlt = loadImage("swiftLeft.png");
    miniSwift = loadImage("birdMinion.png");
    swiftOrb = loadImage("swiftOrb.png");

    cop = loadImage("birdCop.png");
    goBoss = loadImage("goBoss.png");
    geyser = loadImage("carWash.png");
    laserOrb = loadImage("discAttack.png");
    goCar = loadImage("carAttack.png");
  }

  public void draw() {
    background(210, 255, 173);
    
    // Main Menu
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

    // Controls Menu
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

    // Settings Menu
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

    // Play Menu
    if (state == 3) {
      background(0);
      drawButton("Back", 70, 50, 100, 50);
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(255);
      text("Choose your Bossfight", 450, 80);
      textSize(20);
      drawButton("C Sharp++", 450, 180, 250, 50);
      drawButton("Swift", 450, 250, 250, 50);
      drawButton("Go", 450, 320, 250, 50);
    }

    // Cutscene
    if (state == 4 && cutsceneWatched == false) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(catimg, 350, 150, 60, 90);
      image(drone, 500, 130, 40, 60);
      text("Meowtrix is a mischievous cat programmer and with his buddy Catdrone, they discover", width/2, 410);
      text("the secret coding languages of the world.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }
    
    // Cutscene
    if (state == 5 && cutsceneWatched == false) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(catimg, 300, 150, 60, 90);
      image(clock, 400, 20, 300, 300);
      text("One day, Meowtrix comes across a mysterious-looking clock. It promises to provide", width/2, 410);
      text("Meowtrix knowledge of a special code controlled by time.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Cutscene
    if (state == 6 && cutsceneWatched == false) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(catimg, 300, 150, 60, 90);
      image(clock, 400, 20, 300, 300);
      image(swiftOrb, 300, 90, 50, 50);
      text("However, there was a catch: Meowtrix had to pay his soul", width/2, 410);
      text("to the clock for the knowledge.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Cutscene
    if (state == 7 && cutsceneWatched == false) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(catimg, 550, 150, 60, 90);
      image(clock, 200, 20, 300, 300);
      image(clockDiamond, 500, 120, 50, 50);
      text("Meowtrix obviously doesn’t like that, so he tampers with the back of the clock", width/2, 410);
      text("and finds the tiny diamond that stores all the knowledge inside.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Cutscene
    if (state == 8 && cutsceneWatched == false) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(spiral, 300, 50, 300, 300);
      image(catimg, 400, 150, 60, 90);
      image(clockDiamond, 300, 140, 50, 50);
      text("There’s only one problem; Meowtrix forgets to put back the batteries, and", width/2, 410);
      text("now time is in an infinite continuum.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Cutscene
    if (state == 9 && cutsceneWatched == false) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(evil, 250, 10, 350, 250);
      image(catimg, 390, 170, 60, 90);
      text("In order to restore balance, Meowtrix must face the deepest secrets of the mysterious", width/2, 410);
      text("coding language, get to 12:00am, and defeat whoever’s controlling the clock.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Ending Cutscene
    if (state == 13) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(explosion, 300, 50, 300, 300);
      text("The clock shatters into a million pieces, each piece", width/2, 410);
      text("representing a different coding language.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Ending Cutscene
    if (state == 14) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(clock, 300, 50, 300, 300);
      image(evil, 250, 10, 375, 300);      
      text("Turns out, the clock was holding all these languages hostage! In", width/2, 410);
      text("reality, coding languages are just literal copies of each other. Not a surprise.", width/2, 440);
      drawButton("NEXT", 830, 30, 100, 50);
    }

    // Ending Cutscene
    if (state == 15) {
      background(0);
      textSize(20);
      fill(255);
      textAlign(CENTER, CENTER);
      image(catimg, 400, 150, 60, 90);
      image(laptop, 350, 150, 100, 100);
      text("Oh well, at least the languages are saved. Meowtrix goes home and forgets all", width/2, 410);
      text(" about finding new coding languages. Java is torturous enough.", width/2, 440);
      drawButton("Home", 830, 30, 100, 50);
    }
    
    // Game States
    if (state == 10 || state == 11 || state == 12) {

      // C Sharp++ Bossfight
      if (state == 10 && lives > 0 && bossHealth > 0) {
        background(255);
        image(catimg, playerX, playerY, 60, 90);
        image(Cboss, bossX, bossY, 125, 125);
        image(codeUmbrella, umbrellaX, umbrellaY, 100, 100);

        // Boss Movement
        if (phase == 2) {
          bossX += bossMovement;
          if (bossX >= 800) {
            bossMovement = -5;
          }
          if (bossX <= 0) {
            bossMovement = 5;
          }
          
          // Umbrella Movement
          umbrellaX += umbrellaMovement;
          if (umbrellaX >= 800) {
            umbrellaMovement = -5;
          }
          if (umbrellaX <= 0) {
            umbrellaMovement = 5;
          }
          
          // Damage
          if (checkCollision(playerX, playerY, umbrellaX, umbrellaY, 50) && recentDamage == false) {
            playerDamage();
          }
        }
      }

      // Swift Bossfight
      if (state == 11 && lives > 0 && bossHealth > 0) {
        // Changed variables
        if (phase == 0) {
          bossHealth = 200;
          bossX = 600;
          bossY = 200;
        }

        // Changes Image of Swift
        background(173, 216, 230);
        image(catimg, playerX, playerY, 60, 90);
        if (facingLeft == true) {
          bossX = 600;
          bossY = 200;
          image(swift, bossX, bossY, 300, 300);
        } else {
          bossX = 0;
          bossY = 200;
          image(swiftAlt, bossX, bossY, 300, 300);
        }

        if (phase == 2) {
          // Projectile's Starting Coordinates
          if (frameCount % 240 == 0) {
            if (facingLeft) {
              proj1X = bossX;
              proj1Y = bossY + 200;
              proj2X = bossX + 50;
              proj2Y = bossY + 250;
            } else {
              proj1X = bossX + 50;
              proj1Y = bossY + 200;
              proj2X = bossX + 100;
              proj2Y = bossY + 250;
            }
          }
          
          // Bird Projectile Movement
          if (proj1X != 0 && proj1Y != 0) {
            image(miniSwift, proj1X, proj1Y, 50, 50);
            if (facingLeft == true) {
              proj1X -= 11;
            } else {
              proj1X += 11;
            }
          }
          
          // Soul Projectile Movement
          if (proj2X != 0 && proj2Y != 0) {
            if (facingLeft == true) {
              proj2X -= 3;
            } else {
              proj2X += 3;
            }
            image(swiftOrb, proj2X, proj2Y, 40, 40);
          }
          
          // Text Warning When Swift Switches Positions
          if (frameCount % 540 == 0) {
            warning = true;
          }
          if (warning == true) {
            fill(0);
            textSize(20);
            text("WARNING, SWIFT WILL MOVE", 600, 0);
          }
          
          // Swift Switching Positions
          if (frameCount % 750 == 0) {
            if (facingLeft == true) {
              facingLeft = false;
            } else {
              facingLeft = true;
            }
            warning = false;
          }

          // Collision Detections
          if (checkCollision(playerX, playerY, bossX, bossY, 200) && recentDamage == false) {
            playerDamage();
          }
          if (checkCollision(playerX, playerY, proj1X, proj1Y, 50) && recentDamage == false) {
            playerDamage();
          }
          if (checkCollision(playerX, playerY, proj2X, proj2Y, 100) && recentDamage == false) {
            playerDamage();
          }
          if (checkCollision(droneX, droneY, bossX, bossY, 200)) {
            bossHealth -= 0.1;
          }
        }
      }

      // Go Bossfight
      if (state == 12 && lives > 0 && bossHealth > 0) {
        // Changed Variables
        if (phase == 0) {
          bossX = 100;
          bossY = 0;
          bossMovement = 2;
          bossHealth = 250;
        }

        background(0, 128, 128);
        image(catimg, playerX, playerY, 60, 90);
        image(goBoss, bossX, bossY, 125, 125);

        // Go's Movement
        if (phase == 2) {
          bossX += bossMovement;
          if (bossX >= 800) {
            bossMovement = -2;
          }
          if (bossX <= 0) {
            bossMovement = 2;
          }

          // Randomizes Traffic Light
          if (frameCount % 180 == 0) {
            geyserLength = 100;
            trafficLight = myRandom.nextInt(3);
            if (trafficLight == 0) {
              currentPosition += 1;
              if (currentPosition == 4) {
                currentPosition = 0;
              }
            }
            if (trafficLight == 1) {
              currentPlayerLocation = playerX;
            }
          }

          // Summons Projectiles for Lights
          if (trafficLight == 0) {
            copPosition[currentPosition] = bossX;
          }
          else if (trafficLight == 1) {
            image(geyser, currentPlayerLocation, 0, 100, geyserLength);
          }
          else if (trafficLight == 2) {
            image(goCar, carX, 400, 200, 100);
          }

          // Summons Cop Birds by Looping Through Array
          for (int i = 0; i < copPosition.length; i++) {
            if (copPosition[i] != 0) {
              image(cop, copPosition[i], 100, 50, 50);
            }
          }
          
          // Loops Through Array to Shoot Discs
          if (frameCount % 80 == 0) {
            for (int i = 0; i < copPosition.length; i++) {
              if (copPosition[i] != 0) {
                discPosition[i] = 100;
              }
            }
          }

          // Moves the Discs
          for (int i = 0; i < discPosition[i]; i++) {
            if (discPosition[i] != 0) {
              image(laserOrb, copPosition[i], discPosition[i], 30, 30);
              discPosition[i] += 5;
              if (discPosition[i] > 500) {
                discPosition[i] = 0;
              }
            }
          }

          // Moves the Car and Geyser
          carX -= 12;
          if (geyserLength <= 720) {
            geyserLength += 5;
          }
          if (carX < -100 || trafficLight != 2) {
            carX = 900;
          }

          // Displays the Current Light
          if (trafficLight == -1) {
            fill(0);
            textSize(20);
            text("CURRENT TRAFFIC LIGHT: N/A", 550, 0);
          }
          else if (trafficLight == 0) {
            fill(0);
            textSize(20);
            text("CURRENT TRAFFIC LIGHT: RED", 550, 0);
          }
          else if (trafficLight == 1) {
            fill(0);
            textSize(20);
            text("CURRENT TRAFFIC LIGHT: YELLOW", 550, 0);
          }
          else if (trafficLight == 2) {
            fill(0);
            textSize(20);
            text("CURRENT TRAFFIC LIGHT: GREEN", 550, 0);
          }

          // Collision Checks
          if (checkCollision(droneX, droneY, bossX, bossY, 125)) {
            bossHealth -= 0.1;
          }
          if (checkCollision(playerX, playerY, carX, 500, 150) && recentDamage == false) {
            playerDamage();
          }
          for (int i = 0; i < discPosition[i]; i++) {
            if (checkCollision(playerX, playerY, copPosition[i], discPosition[i], 35) && recentDamage == false) {
              playerDamage();
            }
          }
          if (trafficLight == 1) {
            if (checkCollision(playerX, playerY, currentPlayerLocation, 500, 105) && recentDamage == false) {
              if (geyserLength >= 500) {
                playerDamage();
              }
            }
          }
        }
      }

      // Lives Display
      fill(255, 255, 0);
      rect(55, 25, 80, 30);
      fill(0);
      textAlign(LEFT, TOP);
      textSize(18);
      text("LIVES: " + lives, 20, 15);

      // Starting Screen to Give Players a Chance to Get Ready
      if (phase == 0) {
        fill(0);
        textAlign(CENTER, CENTER);
        textSize(100);
        text("READY?", 450, 250);
        if (frameCount % 120 == 0) {
          phase += 1;
        }
      }

      // Game Start
      if (phase == 1) {
        fill(0);
        textAlign(CENTER, CENTER);
        textSize(100);
        text("GO!", 450, 250);
  
        if (frameCount % 180 == 0) {
          phase += 1;
        }
      }

      // General Code For Game Start
      if (phase == 2 && lives > 0) {
        // Boss Defeat Screen
        if (bossHealth <= 0) {
          background(255);
  
          fill(255, 0, 0);
          textSize(32);
          textAlign(CENTER, CENTER);
          text("KNOCKOUT!", 450, 200);
          if (state == 12) {
            drawButton("NEXT", 450, 300, 100, 50);
          } else {
            drawButton("Home", 450, 300, 100, 50);
          }
        }
    
        // Delay before Being Damaged Again
        if (recentDamage) {
          if (frameCount % 120 == 0) {
            recentDamage = false;
          }
        }
    
        if (keyPressed) {
          if (upPressed && isJumping == false) {
            verticalVelocity = -12;
            isJumping = true;
          }
          if (leftPressed) {
            if (playerX >= 0) {
              playerX -= 4;
            }
          }
          if (rightPressed) {
            if (playerX <= 840) {
              playerX += 4;
            }
          }
        }
        
        playerY += verticalVelocity;
        verticalVelocity += gravity;
    
        if (playerY >= 410) {
          playerY = 410;
          isJumping = false;
          verticalVelocity = 0;
        }
    
        droneX += droneDirectionX;
        droneY += droneDirectionY;
    
        image(drone, droneX, droneY, 40, 60);
    
        if (droneX > width || droneX < 0 || droneY > height || droneY < 0) {
          resetDrone();
          droneCooldown = frameCount;
        }
    
        if (frameCount - droneCooldown > 600) {
          resetDrone();
          droneCooldown = frameCount;
        }
      }

      if (lives == 0) {
        background(255);
        fill(255, 0, 0);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("Game Over :(", 450, 200);
        drawButton("Home", 450, 300, 100, 50);
        }
      }

    if (buttonPressed == true) {
      if (frameCount % 65 == 0) {
        buttonPressed = false;
      }
    }
  }



  // Functions

  /*
   * Draws a Button Given Parameters
   * @author JL2500
   * @param label The button's text
   * @param x The x position of the button
   * @param y The y position of the button
   * @param buttonWidth How wide the button is
   * @param buttonHeight How long the button is
   */
  public void drawButton(String label, float x, float y, float buttonWidth, float buttonHeight) {
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

  /*
   * Does Different Actions to State Depending on Button
   * @author JL2500
   * @param label The button's text 
   */
  public void handleButtonClick(String label) {
    if (buttonPressed == false) {
      if (label == "Back") {
        state = 0;
      }
      else if (label == "Home") {
        state = 0;
        gameReset();
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
      else if (label == "C Sharp++") {
        if (cutsceneWatched == true) {
          state = 10;
        } else {
          state = 4;
        }
      }
      else if (label == "Swift") {
        state = 11;
      }
      else if (label == "Go") {
        state = 12;
      }
      else if (label == "NEXT") {
        if (state == 9) {
          cutsceneWatched = true;
        }
        state += 1;
      }
      buttonPressed = true;
    }
  }

  /*
   * Handles key pressing
   * @author JL2500
   */
  public void keyPressed() {
    if (key == 'w') {
      upPressed = true;
    }
    else if (key == 's') {
      downPressed = true;
    }
    else if (key == 'a') {
      leftPressed = true;
    }
    else if (key == 'd') {
      rightPressed = true;
    }
    
    if (keyCode == UP) {
      droneDirectionX = 0;
      droneDirectionY = -4;
    } else if (keyCode == DOWN) {
      droneDirectionX = 0;
      droneDirectionY = 4;
    } else if (keyCode == LEFT) {
      droneDirectionX = -4;
      droneDirectionY = 0;
    } else if (keyCode == RIGHT) {
      droneDirectionX = 4;
      droneDirectionY = 0;
    }
  }

  /*
   * Handles key releasing for multi-movement
   * @author JL2500
   */
  public void keyReleased() {
    if (key == 'w') {
      upPressed = false;
    }
    else if (key == 's') {
      downPressed = false;
    }
    else if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }
  }

  /*
   * Resets the Drone Back to its Original Place
   * @author JL2500
   */
  public void resetDrone() {
    droneX = playerX;
    droneY = playerY + 30;
  }

  /*
   * Checks Collision of Two Objects Using Distance
   * @author JL2500
   * @param x1 The x of the first object
   * @param y1 The y of the first object
   * @param x2 The x of the second object
   * @param y2 The y of the second object
   * @param distance The hitbox
   */
  public boolean checkCollision(float x1, float y1, float x2, float y2, float distance) {
    return dist(x1, y1, x2, y2) < distance;
  }

  /*
   * Handles Player Damage to Remove Lives and Give recentDamage
   */
  public void playerDamage() {
    background(255, 0, 0);
    lives -= 1;
    recentDamage = true;
  }

  /*
   * Resets All Variables for a New Game
   */
  public void gameReset() {
    lives = 4;
    playerX = 100f;
    playerY = 410f;
    gravity = 0.5;
    verticalVelocity = 0;
  
    droneX = 100;
    droneY = 410;
    droneDirectionX = 5;
    droneDirectionY = 0;
    droneCooldown = 0;
  
    bossX = 0;
    bossY = 0;
    bossHealth = 100;

    umbrellaX = 800;
    umbrellaY = 415;

    facingLeft = true;
    proj1X = 0;
    proj1Y = 0;
    proj2X = 0;
    proj2Y = 0;
    warning = false;

    myRandom = new Random();
    trafficLight = -1;
    currentPlayerLocation = playerX;
    carX = 900;
    copPosition = new int[5];
    discPosition = new int[5];
    currentPosition = -1;
    geyserLength = 100;
  
    phase = 0;
  }
}