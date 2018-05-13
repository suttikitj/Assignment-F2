import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip2 extends Sprite{

	int step = 8;
	private boolean alive = true;
	public SpaceShip2(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}

	public void move(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 400 - width)
			x = 400 - width;
    }
    public boolean isAlive(){
		return alive;
	}
	public void die()
	{
		alive = false;
	}

}
