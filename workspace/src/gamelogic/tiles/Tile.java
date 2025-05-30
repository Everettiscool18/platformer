package gamelogic.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gameengine.GameObject;
import gameengine.hitbox.RectHitbox;
import gameengine.maths.Vector2D;
import gamelogic.level.Level;

public class Tile{

	protected Vector2D position;
	protected int size;
	protected RectHitbox hitbox;
	protected BufferedImage image;
	protected boolean solid;
	protected Level level;
	
	public Tile(float x, float y, int size, BufferedImage image, boolean solid, Level level) {
		this.position = new Vector2D(x*size, y*size);
		this.size = size;
		this.image = image;
		this.solid = solid;
		this.level = level;
	}
	
	public void update (float tslf) {};
	
	public void draw (Graphics g) {
		if(image != null) g.drawImage(image, (int)position.x, (int)position.y, size, size, null);
		
		if(hitbox != null) hitbox.draw(g);		
	}
	
	
	
	//------------------------------------Getters
	public boolean isSolid() {
		return solid;
	}
	
	public RectHitbox getHitbox() {
		return hitbox;
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public int getRow() {
		return (int)(position.y/size);
	}
	
	public int getCol() {
		return (int)(position.x/size);
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getSize() {
		return size;
	}
}
