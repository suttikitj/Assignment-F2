//package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
		
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private ArrayList<BigEnemy> Benemies = new ArrayList<BigEnemy>();	
	private SpaceShip v;	
	private SpaceShip2 v2;
	private JFrame frame;
	private Timer timer;
	private int timer2;
	private long score = 0;
	private long score2 = 0;
	private double difficulty = 0.1;
	
	public GameEngine(GamePanel gp, SpaceShip v,SpaceShip2 v2, JFrame frame) {
		this.gp = gp;
		this.v = v;		
		this.v2 =v2;
		this.frame =frame;
		gp.sprites.add(v);
		gp.sprites.add(v2);
		timer = new Timer(50, new ActionListener() {
			
			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				process();
				timer2 +=1;
			}
			
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*390), 30);
		
		//System.out.println(timer2);
		if(timer2 % 4 == 0)
		{
			System.out.println("Level up!!");
			BigEnemy ex = new BigEnemy((int)(Math.random()*390), 30);
			difficulty = difficulty +0.002;
			e.speedup();
			ex.speedup();
			
			gp.sprites.add(ex);
			Benemies.add(ex);
			
		
		}
		
		gp.sprites.add(e);
		enemies.add(e);
		
	}
	
	private void process(){
		
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				if(v.isAlive())
				{
					score += 100;
				}
				if(v2.isAlive())
				{
					score2 += 100;
				}
				
			}
		}
		Iterator<BigEnemy> ex_iter = Benemies.iterator();
		while(ex_iter.hasNext()){
			BigEnemy ex = ex_iter.next();
			ex.proceed();
			if(!ex.isAlive()){
				ex_iter.remove();
				gp.sprites.remove(ex);
				//System.out.println("Extra");
			}
		}
		
		gp.updateGameUI(this,v,v2);
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double vr2 = v2.getRectangle();
		Rectangle2D.Double er;
		Rectangle2D.Double er2;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				v.die();
				gp.sprites.remove(v);
				return;
			}
			if(er.intersects(vr2)){
				die();
				v2.die();
				gp.sprites.remove(v2);
				return;
			}
	
		}
		for(BigEnemy ex : Benemies){
			er2 = ex.getRectangle();
			if(er2.intersects(vr)){
				score += 200;
				
				return;
			}
			if(er2.intersects(vr2)){
				score2+=200;
				
				return;
			}
	
		}
	
	}
	
	public void die(){
		long scoreend =  getScore();
		long scoreend2 = getScorep2();
		Start continues = new Start();
		
		if(!((v.isAlive())||(v2.isAlive())))
		{
			
			if(scoreend > scoreend2)
			{
				JOptionPane.showMessageDialog(null, "Player1 WON!!\n score:"+scoreend, "Result!!", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(scoreend == scoreend2)
			{
				JOptionPane.showMessageDialog(null, "DRAW!!\n score:"+scoreend, "Result!!", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Player2 WON!!\n score:"+scoreend2, "Result!!", JOptionPane.INFORMATION_MESSAGE);
			}
			continues.con();
			frame.dispose();
			timer.stop();
			
			
		}
		
		
	}
	
	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.move(-1);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(1);
			break;
		case KeyEvent.VK_A:
			v2.move(-1);
			break;
		case KeyEvent.VK_D:
			v2.move(1);
			break;
		}
	}

	public long getScore(){
		return score;
	}
	public long getScorep2(){
		return score2;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
