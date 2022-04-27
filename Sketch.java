import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	int x = 400;
  int y = 400;

  double x_speed = 0;
  double y_speed = 0;

  double x_friction = 0;
  double y_friction = 0;

  int xRemember = 400;
  int yRemember = 400;

	boolean upPressed = false;
  boolean downPressed = false;
  boolean rightPressed = false;
  boolean leftPressed = false;

  int width = 800;
  int height = 800;

  int i = 0;

  boolean makeWave = false;

  PImage character;
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(width, height);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(60, 200, 190);
    character = loadImage("Sans Dogeron.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(60, 200, 190);
    image(character, x - 97, y - 72);  
    
	  println(upPressed + " " + downPressed + " " + leftPressed + " " +  rightPressed + " " +  x_speed + " " + y_speed + " " + makeWave);

    if (upPressed) {
      y_speed -= 3.5;
    }
    if (downPressed) {
      y_speed += 3.5;
    }
    if (leftPressed) {
      x_speed += 3.5;
    }
    if (rightPressed) {
      x_speed -= 3.5;
    }

    if ((x > width) || (x < 0)) {
      x_speed = x_speed * -1.2;
    }
    if ((y > height) || (y < 0)) {
      y_speed = y_speed * -1.2;
    }

    // friction
    if (x_speed > 0) {
      x_friction =  x_speed*0.2;
    }
    if (x_speed < 0) {
      x_friction =  x_speed*0.2;
    }

    if (Math.round(x_speed) == 0) {
      x_friction = 0;
    }


    if (y_speed > 0) {
      y_friction =  y_speed*0.2;
    }
    if (y_speed < 0) {
      y_friction = y_speed*0.2;
    }

    if (Math.round(y_speed)== 0) {
      y_friction = 0;
    }

    x_speed = x_speed - x_friction;
    y_speed = y_speed - y_friction;

    x = (int) (x + (int) x_speed);
    y = (int) (y + (int) y_speed);
    
    wavePos(xRemember, yRemember);
    
    

	// sample code, delete this stuff
  }
  public void keyPressed(){
    if (key == 119){
      upPressed = true;
    }
    if (key == 115){
      downPressed = true;
    }
    if (key == 100){
      leftPressed = true;
    }
    if (key == 97){
      rightPressed = true;
    }
    if (key == 101){
      //makeWave = true;
      xRemember = x;
      yRemember = y;
      i = 0;
    }
    if (key == CODED){
      if (keyCode == SHIFT){
        x = 400;
        y = 400;
      }
    }
  }
  
  public void keyReleased() {
    if (key == 119){
      upPressed = false;
    }
    if (key == 115){
      downPressed = false;
    }
    if (key == 100){
      leftPressed = false;
    }
    if (key == 97){
      rightPressed = false;
    }
    if (key == 101){
      //makeWave = false;
    }
  }
  
  
  // define other methods down here.
  public void surfaceWave(int xWave, int yWave, int waveEnd, int wavelength){
    stroke(0);
    noFill();
    ellipse(xWave, yWave, 0, 0);
    for (int wavePropogate = 1; wavePropogate < waveEnd; wavePropogate += wavelength){
        stroke(0);
        noFill();
        ellipse(xWave, yWave, wavePropogate, wavePropogate);
      }
    }
  public void wavePos(int xPos, int yPos){

  
    if (makeWave = true){
      surfaceWave(xPos, yPos, i*i, i);
      i += 1;
    }
/*
    if (frameCount > 180){
      frameCount = 0;
      makeWave = false;
      i = 0;
    }*/
  }  
}