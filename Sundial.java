import java.awt.*;
import javax.swing.*;

public class Sundial extends JFrame{

	SunEvent sun_event = new SunEvent(this);


	JLabel d = new JLabel("Declination [degrees]:",JLabel.RIGHT);
	JTextField declination = new JTextField("0.0",4);
	JLabel l = new JLabel("Latitude [degrees]:",JLabel.RIGHT);
	JTextField latitude = new JTextField("0.0",4);


	JButton draw = new JButton("Draw");

	JButton reset = new JButton("Reset");

	JTextField rise = new JTextField(13);

	Sundial(){
		super("Sundial");
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.pink);
		
		draw.addActionListener(sun_event);
		reset.addActionListener(sun_event);

		FlowLayout flo = new FlowLayout(FlowLayout.LEFT);
		setLayout(flo);
		add(d);
		add(declination);
		add(l);
		add(latitude);
		add(draw);
		add(rise);
		add(reset);
		rise.setEnabled(false);

		setVisible(true);
	}
	
	public static void main(String[] args){
		Sundial sun = new Sundial();
	}
	
}
