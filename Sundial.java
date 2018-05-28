import java.awt.*;
import javax.swing.*;

public class Sundial extends JFrame{

	SunEvent sun_event = new SunEvent(this);

	JLabel d = new JLabel("Declination [degrees]:",JLabel.LEFT);
	JTextField declination = new JTextField("0.0",4);
	JLabel l = new JLabel("Latitude [degrees]:",JLabel.LEFT);
	JTextField latitude = new JTextField("0.0",4);

	JButton draw = new JButton("Draw");
	JButton reset = new JButton("Reset");

	JLabel rise = new JLabel(" ",JLabel.CENTER);

	JButton write_file = new JButton("proba");

	Sundial(){
		super("Sundial");
		setSize(580,310);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.pink);
		
		draw.addActionListener(sun_event);
		reset.addActionListener(sun_event);
		write_file.addActionListener(sun_event);

		setLayout(null);

		d.setBounds(40,20,200,30);
		declination.setBounds(240,20,50,30);
		l.setBounds(40,70,200,40);
		latitude.setBounds(240,70,50,30);

		draw.setBounds(40,140,100,70);
		reset.setBounds(190,140,100,70);

		rise.setBounds(40,230,250,40);

		write_file.setBounds(400,280,100,20);

		draw.setBackground(Color.green);
		reset.setBackground(Color.red);
	
		rise.setFont(new Font("Serif",Font.ITALIC,20));		
		rise.setOpaque(false);

		add(d);
		add(declination);
		add(l);
		add(latitude);
		add(draw);
		add(reset);
		add(rise);
		add(write_file);
		setVisible(true);
	}

	public static void main(String[] args){
		Sundial sun = new Sundial();
	}
	
}
