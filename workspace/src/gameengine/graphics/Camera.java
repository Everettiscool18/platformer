package gameengine.graphics;

import java.awt.Color;
import java.awt.Graphics;

import gameengine.maths.Vector2D;
import gamelogic.Main;
import gamelogic.player.Player;

public class Camera {

	public static final boolean SHOW_CAMERA = false;
	
	private Vector2D position;
	private Player player;
	
	private int width;
	private int height;
	
	private float velocityX = 1.6f; 
	private float velocityY = 1.6f;
	
	private float setValue = 0.5f;
	
	private float offsetX = 450;
	private float offsetY = 100; //to rather keep the camera down
	
	private int size = 10; //used to draw the oval
	
	private float borderLeft;
	private float borderRight;
	private float borderBot;
	private boolean smokeCamera = false;
	private Vector2D goalPosition;
	
	public Camera(int width, int height, int borderLeft, int borderRight, int borderBot) {
		this.position = new Vector2D();
		this.goalPosition = new Vector2D();
		this.width = width;
		this.height = height;
		this.borderLeft = borderLeft;
		this.borderRight = borderRight;
		this.borderBot = borderBot;
	}
	
	public void update(float tslf) {
		if(player != null) {
			
			calulateGoalPositon();
			checkBorders();
			
			//Calculating the differences between the goal position and the current position of the camera
			float diffX = goalPosition.x - position.x;
			float amountX = diffX * velocityX;
			position.x += amountX * tslf;

			float diffY = goalPosition.y - position.y;
			float amountY = diffY * velocityY;
			position.y += amountY * tslf;
			
			//At this point the difference is too small so the value gets set to avoid shaking of the camera
			if(-setValue < diffX && diffX < setValue) position.x = goalPosition.x;
			if(-setValue < diffY && diffY < setValue) position.y = goalPosition.y;
		}
	}

	public boolean getSmokeValue() {
		return smokeCamera;
	}


public void toggleSmokeCamera() {
    smokeCamera = !smokeCamera;

    if(smokeCamera) {

        width = 10;
        height = 10;

        if (player != null) {
            calulateGoalPositon();
            checkBorders();
            position.x = goalPosition.x;
            position.y = goalPosition.y;
        }
    } else {
        width = Main.SCREEN_WIDTH;
        height = Main.SCREEN_HEIGHT;

        if (player != null) {
            calulateGoalPositon();
            checkBorders();
            position.x = goalPosition.x;
            position.y = goalPosition.y;
        }
    }
}

	private void checkBorders() {
		if(borderLeft != -1 && goalPosition.x < borderLeft) goalPosition.x = borderLeft;
		if(borderRight != -1 && borderRight < goalPosition.x + width) goalPosition.x = borderRight - width; 
		
		if(borderBot != -1 && borderBot < goalPosition.y + height) goalPosition.y = borderBot - height;
	}
	
	private void calulateGoalPositon() {
		float offsetX = Math.copySign(this.offsetX, player.getMovementX());
		if(player.getMovementX() == 0) offsetX = 0;
		
		goalPosition.x = player.getX() + player.getWidth()/2 - Main.SCREEN_WIDTH/2 + offsetX;
		goalPosition.y = player.getY() + player.getHeight()/2 - Main.SCREEN_HEIGHT/2 + offsetY;
	}
	
	//used for debugging
	public void draw (Graphics g) {
		g.setColor(Color.RED);
		g.drawOval((int)(position.x + width/2) - size/2, (int)(position.y + height/2) - size/2, size, size);
	}
	
	public void setFocusedObject(Player object) {
		this.player = object;
		
		calulateGoalPositon();
		checkBorders();
		
		position.x = goalPosition.x;
		position.y = goalPosition.y;
	}
	
	public boolean isVisibleOnCamera(float x, float y, int width, int height) {
		if(x + width > position.x && x < position.x + this.width && y + height > position.y && y < position.y + this.height) return true;
		return false;
	}
	
	//--------------------------------Getters
	public void setX(float x) {
		position.x = x;
	}
	
	public void setY(float y) {
		position.y = y;
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
}
