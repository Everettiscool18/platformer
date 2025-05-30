package gamelogic.tiles;


import java.awt.image.BufferedImage;


import gameengine.hitbox.RectHitbox;
import gamelogic.level.Level;


public class Gas extends Tile{
    private int intensity;
    public Gas(float x, float y, int size, BufferedImage image, Level level, int intensity) {
   	 super(x, y, size, image, false, level);
   	 this.intensity = intensity;
   	 this.hitbox = new RectHitbox(x*size , y*size, 0, 10, size, size);
    }
    public int getIntensity() {
   	 return intensity;
    }
    public void setIntensity(int intensity) {
   	 this.intensity = intensity;
    }
    public void update(float tslf, BufferedImage image) {
   	 super.update(tslf);
   	 super.setImage(image);
    }
} 
