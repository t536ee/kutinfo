import java.awt.*;
import javax.swing.*;

public class Sundial extends JFrame{

	JLabel d = new JLabel("Declination [degrees]:",JLabel.RIGHT);
	JTextField declination = new JTextField(4);
	JLabel l = new JLabel("Latitude [degrees]:",JLabel.RIGHT);
	JTextField latitude = new JTextField(4);

	JButton draw = new JButton("Draw");


	JTextField rise = new JTextField("The sun doesn't rise.",13);

	Sundial(){
		super("Sundial");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.pink);

		FlowLayout flo = new FlowLayout(FlowLayout.LEFT);
		setLayout(flo);
		add(d);
		add(declination);
		add(l);
		add(latitude);
		add(draw);
		add(rise);
		rise.setEnabled(false);

		setVisible(true);
	}

	public static void main(String[] args){
		Sundial sun = new Sundial();
	}
	
}
