import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;
public class Start {
    public void start()
    {
        JFrame frame = new JFrame("Space War");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 650);
		frame.getContentPane().setLayout(new BorderLayout());;    
        SpaceShip v = new SpaceShip(180, 550, 20, 20);
		SpaceShip2 v2 =new SpaceShip2(180, 550, 20, 20);
		GamePanel gp = new GamePanel();
		GameEngine engine = new GameEngine(gp,v,v2,frame);
        frame.addKeyListener(engine);
		frame.getContentPane().add(gp, BorderLayout.CENTER);
		frame.setVisible(true);
        engine.start();
       
       
    }
   
    public void con()
    {   
         JLabel label;
        JFrame frame = new JFrame("Space War");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(400, 650);
		label = new JLabel("Continues??");
		label.setFont(label.getFont().deriveFont(40.0f));
        frame.add(label, BorderLayout.PAGE_START);
        JPanel facesPanel = new JPanel();
        GridLayout layout = new GridLayout(0, 2);
        layout.setHgap(5);
        facesPanel.setLayout(layout);
        JButton button = new JButton("YES!!");
        button.setPreferredSize(new Dimension(100, 50));
		button.addActionListener(new ActionListener(){
			//anonymous class
			public void actionPerformed(ActionEvent e) { 
                start();
                frame.dispose();
			}
		});
        facesPanel.add(button);
        JButton button1 = new JButton("NO!!");
        button.setPreferredSize(new Dimension(100, 50));
		button1.addActionListener(new ActionListener(){
			//anonymous class
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
			}
		});
        facesPanel.add(button1);
        frame.add(facesPanel, BorderLayout.CENTER);	
        frame.setVisible(true);
    }
   
}
