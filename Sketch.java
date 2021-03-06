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

  int makeWave = 0;

  PImage character;
  PImage worm;

  int[] xArray = new int[100];
  int[] yArray = new int[100];

  int[] lengthArray = new int[100];
  

  int[] xItemArray = new int[100];
  int[] yItemArray = new int[100];

  int objectArray = 0;
  int mouseArray = 0;

  
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
    worm = loadImage("Truffle_Worm_%28NPC%29.png");
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(60, 200, 190);
    image(character, x - 97, y - 72);  
    
	  //println(upPressed + " " + downPressed + " " + leftPressed + " " +  rightPressed + " " +  x_speed + " " + y_speed + " " + makeWave);
    //println(xArray[1] + " " + objectArray + " " + yArray[1]);

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
    
    //wavePos(xRemember, yRemember);
    for (int waveFor = 0; waveFor < objectArray; waveFor ++){
      wavePos(xArray[waveFor], yArray[waveFor]);
    }
    for (int waveFor2 = 0; waveFor2 < mouseArray; waveFor2 ++){
      fill(0, 255, 255);
      image(worm, xItemArray[waveFor2], yItemArray[waveFor2]);
    }

  }
/** The keyPressed method taken from https://happycoding.io/tutorials/processing/input#handling-multiple-keys.
 * 
 * 
 * 
 * 
 */
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
      makeWave = 1;
      xRemember = x;
      yRemember = y;
      //i = 0;

      
      
      xArray[objectArray] = x;
      yArray[objectArray] = y;
      objectArray += 1;
      
    }
    if (key == CODED){
      if (keyCode == SHIFT){
        x = 400;
        y = 400;
        i = 0;

      }
    }
  }

/** The keyPressed method taken from https://happycoding.io/tutorials/processing/input#handling-multiple-keys.
 * 
 * 
 * 
 * 
 */
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
    }
  }
  
  // define other methods down here.
/**
 * makes waves at points specified at xWave and yWave. The waveEnd and the wavelength specify the frequency and the wavelength of each wave. 
 * @param xWave specifies the x position of the wave
 * @param yWave specifies the x position of the wave
 * @param waveEnd specifies when the wave ends in the for loop; in other words, how many times the wave repeats.
 * @param wavelength specifies how many steps the for loop takes, and the wavelength of the wave.
 */
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
/** The inbetween method that checks if makeWave == 1, which starts to make the surfacewave.
 * @param xPos Checks for the x position at that time.
 * @param yPos Checks for the y position at that time. 
 */
  public void wavePos(int xPos, int yPos){
    if (makeWave == 1){
      //surfaceWave(xPos, yPos, i*i, i);
      surfaceWave(xPos, yPos, lengthArray[objectArray]*lengthArray[objectArray], lengthArray[objectArray]);
      //i += 1;
      lengthArray[objectArray] = lengthArray[objectArray] + 1;
    }
  } 
  public void mousePressed(){
    
    xItemArray[mouseArray] = mouseX;
    yItemArray[mouseArray] = mouseY;
    mouseArray += 1;
  }
  
}