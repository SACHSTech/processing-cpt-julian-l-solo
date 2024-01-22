import processing.core.PApplet;
import processing.core.PImage;

public class Sketch1 extends PApplet {

  PImage catimg;
  PImage drone;
  PImage swift;
  PImage swiftAlt;
  PImage miniSwift;
  PImage swiftOrb;

  float playerX = 100f;
  float playerY = 410f;
  int lives = 4;
  double gravity = 0.5;
  float verticalVelocity = 0;
  boolean upPressed = false;
  boolean downPressed = false;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean isJumping = false;

  float droneX = 100;
  float droneY = 410;
  float droneDirectionX = 5;
  float droneDirectionY = 0;
  float droneCooldown = 0;

  int bossX = 600;
  int bossY = 200;
  boolean facingLeft = true;
  int bossMovement = 5;
  float bossHealth = 100;
  boolean recentDamage = false;

  float proj1X = 0;
  float proj1Y = 0;
  float proj2X = 0;
  float proj2Y = 0;
  boolean warning = false;

  int phase = 0;

  public void settings() {
    size(900, 500);
  }

  public void setup() {
    catimg = loadImage("catcharacter.png");
    drone = loadImage("catDrone.png");
    swift = loadImage("swiftRight.png");
    swiftAlt = loadImage("swiftLeft.png");
    miniSwift = loadImage("birdMinion.png");
    swiftOrb = loadImage("swiftOrb.png");
  }

  public void draw() {
    background(255);
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

    fill(255, 255, 0);
    rect(15, 10, 80, 30);
    fill(0);
    textAlign(LEFT, TOP);
    textSize(18);
    text("LIVES: " + lives, 20, 15);

    if (phase == 0) {
      fill(0);
      textAlign(CENTER, CENTER);
      textSize(100);
      text("READY?", 450, 250);
      if (frameCount % 120 == 0) {
        phase += 1;
      }
    }

    if (phase == 1) {
      fill(0);
      textAlign(CENTER, CENTER);
      textSize(100);
      text("GO!", 450, 250);

      if (frameCount % 180 == 0) {
        phase += 1;
      }
    }
    
    if (phase == 2) {
      
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

      if (proj1X != 0 && proj1Y != 0) {
        image(miniSwift, proj1X, proj1Y, 50, 50);
        if (facingLeft == true) {
          proj1X -= 10;
        } else {
          proj1X += 10;
        }
      }

      if (proj2X != 0 && proj2Y != 0) {
        if (facingLeft == true) {
          proj2X -= 3;
        } else {
          proj2X += 3;
        }
        image(swiftOrb, proj2X, proj2Y, 40, 40);
      }

      if (proj1X > width || proj1X < 0 || proj1Y > height || proj1Y < 0) {
        proj1X = 0;
        proj1Y = 0;
      }

      if (frameCount % 630 == 0) {
        warning = true;
      }

      if (warning == true) {
        fill(0);
        textSize(20);
        text("WARNING", 800, 0);
      }

      if (frameCount % 750 == 0) {
        if (facingLeft == true) {
          facingLeft = false;
        } else {
          facingLeft = true;
        }
        warning = false;
      }
      
      if (lives == 0) {
        background(255);
        noLoop();

        fill(255, 0, 0);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("Game Over :(", 450, 250);

        return;
      }

      if (bossHealth <= 0) {
        background(255);
        noLoop();

        fill(255, 0, 0);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("KNOCKOUT!", 450, 250);

        return;
      }

      if (checkCollision(playerX, playerY, bossX, bossY, 200) && recentDamage == false) {
        playerDamage();
      }

      if (checkCollision(playerX, playerY, proj2X, proj2Y, 200) && recentDamage == false) {
        playerDamage();
      }

      if (checkCollision(playerX, playerY, proj1X, proj1Y, 45) && recentDamage == false) {
        playerDamage();
      }

      if (checkCollision(droneX, droneY, bossX, bossY, 200)) {
        bossHealth -= 0.1;
      }
  
      // Makes it so there's a delay before user can get damaged again
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
        resetClaw();
        droneCooldown = frameCount;
      }
  
      if (frameCount - droneCooldown > 600) {
        resetClaw();
        droneCooldown = frameCount;
      }
    }
  }

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

  void resetClaw() {
    droneX = playerX;
    droneY = playerY + 30;
  }

  boolean checkCollision(float x1, float y1, float x2, float y2, float distance) {
    if (x1 > 0 && x2 < width && y2 > 0 && y2 < height) {
      return dist(x1, y1, x2 + (distance / 2), y2 + (distance / 2)) < distance;
    }
    return false;
  }

  void playerDamage() {
    background(255, 0, 0);
    lives -= 1;
    recentDamage = true;
  }
}