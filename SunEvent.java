import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.io.PrintWriter;
import java.io.File;

public class SunEvent implements ActionListener {

	Sundial gui;

	double declination_degrees;
	double fi_degrees;
	double[][] arr = new double[25][2];
	DrawingTable table = new DrawingTable(arr);
	
	public SunEvent (Sundial in){
		gui=in;
	}
	
	public void actionPerformed(ActionEvent evt){
		String command = evt.getActionCommand();

		if (command == "Draw"){
			declination_degrees = getNum(gui.declination.getText());
			fi_degrees = getNum(gui.latitude.getText());
			arr = shadow(declination_degrees,fi_degrees);

			if (allElementsZero(arr)==true){
				gui.rise.setText("The sun doesn't rise.");
				gui.setVisible(true);
			}
			
			else {	
				DrawingTable table = new DrawingTable(arr);
				gui.add(table);
				table.setBounds(310,20,250,250);
				gui.setVisible(true);
			}
		}
		if (command == "Reset"){
			gui.declination.setText("0,0");
			gui.latitude.setText("0,0");
			gui.rise.setText(" ");
			JPanel panel = new JPanel();
			panel.setBackground(Color.pink);
			gui.add(panel);
			panel.setBounds(310,20,250,250);
			gui.setVisible(true);

		}
		if (command == "proba"){
			try {
				PrintWriter print_writer = new PrintWriter("ShadowLength_Azimuth.txt");

				for(int i=0; i<arr.length; i++){
					Object x = arr[i][0];
					Object y = arr[i][1];
					print_writer.println(x + "\t" + y);
				}
System.out.println("Siker");
				print_writer.flush();

			} catch (Exception ex) {
			ex.printStackTrace();
		    	  }
		}
	}

	double getNum(String text){
		double num = Double.parseDouble(text);		
		return num;
	}

	boolean allElementsZero (double[][] arr){
		boolean allZero = true;
		outerloop:
		for(int i=0; i<arr.length; i++){ 
			for(int j=0; j<arr[i].length; j++){
				if (arr[i][j]!=0)
					allZero = false;
					break outerloop;	
			}
		}
		return allZero;
	}

	double[][] shadow(double declination_degrees, double fi_degrees) {
		
		double decl = Math.toRadians(declination_degrees);
		double fi = Math.toRadians(fi_degrees);
		
		if(decl<fi-Math.PI/2.0){
			return arr;
		}
		else{
	
			for (int t=0; t<=24; t++){
				double m = Math.asin( Math.sin(decl)*Math.sin(fi) + Math.cos(decl)*Math.cos(fi)*Math.cos(t*Math.PI/12.0) );
				if (m<0.0 || m>90.0){
					continue;			
				}
				double shadow_length;
				double A;

				if ((1.0/Math.tan(m))<2.0){
					shadow_length =  1.0/(Math.tan(m));
				}
				else{
					shadow_length = 2.0;
				}
				arr[t][0] = shadow_length;

				double A1 = Math.asin((Math.cos(decl)*Math.sin(t*Math.PI/12.0))/Math.cos(m));
				double A1_other;
				double A2 = Math.acos((Math.cos(decl)*Math.sin(fi)*Math.cos(t*Math.PI/12.0) -
					    Math.sin(decl)*Math.cos(fi))/Math.cos(m));
				double A2_other;

				if (A1<0.0){
					A1_other = Math.PI-A1;
					A1 = (2.0*Math.PI)+A1 ;
				}
				else{
					A1_other = (Math.PI)-A1;
				}
				if (A2<0.0){
					A2_other = (-1.0)*A2;
					A2 = (3.0*Math.PI/2.0)-A2;
				}
				else if(0.0 < A2 && A2 < (Math.PI/2)) {
					A2_other = (2.0*Math.PI)-A2;
				} 
				else{
					A2_other = Math.PI + (Math.PI-A2);
				}
				if(Math.abs(A1-A2)<0.0001){
					arr[t][1]=A1;
				}
				else if(Math.abs(A1-A2_other)<0.0001){
					arr[t][1]=A1;
				}
				else if(Math.abs(A1_other-A2)<0.0001){
					arr[t][1]=A1_other;
				}
				else if(Math.abs(A1_other-A2_other)<0.00001){
					arr[t][1]=A1_other;
				}	
			}
			return arr;
		}
	}
}

class DrawingTable extends JPanel{
	
		private double[][] arr;
		DrawingTable(double[][] arr){
			super();
			this.arr = arr;
			
		}
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			super.setBackground(Color.cyan);
			setSize(250,250);
				
			for(int i=0; i<25;i++){
				int a=(int)(50*this.arr[i][0]*Math.cos(this.arr[i][1]));
				int b=(int)(50*this.arr[i][0]*Math.sin(this.arr[i][1]));
				g.drawLine(125,125,125+a,125+b);
			}
		}
	}
