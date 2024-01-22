import processing.core.PApplet;
import processing.core.PImage;

public class Sketch2 extends PApplet {

  PImage catimg;
  PImage drone;
  PImage Cboss;
  PImage codeUmbrella;
  PImage cBackground;

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

  int bossX = 0;
  int bossY = 0;
  int bossMovement = 5;
  int umbrellaMovement = -5;
  float umbrellaX = 800;
  float umbrellaY = 415;
  float bossHealth = 100;
  boolean recentDamage = false;


  int phase = 0;

  public void settings() {
    size(900, 500);
  }

  public void setup() {
    catimg = loadImage("catcharacter.png");
    drone = loadImage("catDrone.png");
    Cboss = loadImage("C.png");
    codeUmbrella = loadImage("binary_umbrella.png");
    cBackground = loadImage("cVector.jpg");
  }

  public void draw() {
    background(255);
    image(cBackground, 0, 0, width, height);
    image(catimg, playerX, playerY, 60, 90);
    image(Cboss, bossX, bossY, 125, 125);
    image(codeUmbrella, umbrellaX, umbrellaY, 100, 100);

    fill(255, 255, 0);
    rect(15, 10, 80, 30);
    fill(0);
    textAlign(LEFT, TOP);
    textSize(18);
    text("LIVES: " + bossHealth, 20, 15);

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
      bossX += bossMovement;
      if (bossX >= 800) {
        bossMovement = -5;
      }
      if (bossX <= 0) {
        bossMovement = 5;
      }
  
      umbrellaX += umbrellaMovement;
      if (umbrellaX >= 800) {
        umbrellaMovement = -5;
      }
      if (umbrellaX <= 0) {
        umbrellaMovement = 5;
      }
  
      if (checkCollision(playerX, playerY, umbrellaX, umbrellaY, 50) && recentDamage == false) {
        playerDamage();
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

      if (checkCollision(droneX, droneY, bossX, bossY, 125)) {
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
    return dist(x1, y1, x2, y2) < distance;
  }

  void playerDamage() {
    background(255, 0, 0);
    lives -= 1;
    recentDamage = true;
  }
}