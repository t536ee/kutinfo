import javax.swing.*;
import java.awt.*;
import java.lang.Math;
public class Ma{
	public static void main(String[] args){

		double decl = Math.toRadians(Double.parseDouble(args[0]));
		double fi = Math.toRadians(Double.parseDouble(args[1]));
		double[][] arr = new double[25][2];
		if(decl<fi-Math.PI/2.0){
			System.out.println("A Nap nem kel fel");
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

		}
		Sundial sun = new Sundial(arr);
	}
}

class Sundial extends JFrame{
	double[][] arr;
	Sundial(double[][] arr){
		super("Sundial");
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.arr=arr;
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(int i=0; i<25;i++){
			int a=(int)(60*arr[i][0]*Math.cos(arr[i][1]));
			int b=(int)(60*arr[i][0]*Math.sin(arr[i][1]));
			g2.drawLine(200,200,200+a,200+b);
		}
		
	}
	
}

