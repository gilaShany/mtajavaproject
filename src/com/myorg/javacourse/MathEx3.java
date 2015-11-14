package com.myorg.javacourse;

public class MathEx3 {
	
	public double circleArea (double radius){
		double area;
		area= Math.PI * Math.pow(radius, 2);
		return area;
	}
	
	public double lengthOfTriangleOpposite (double angleB, double hypotenuse){
		double radians= Math.toRadians(angleB);
		double opposite= Math.sin(radians) * hypotenuse;
		return opposite;
	}
	
	public double power (double base, double exp){
		double powerResult= Math.pow(base, exp);
		return powerResult;
	}
	
	public String getResults (){
		double radius= 50;
		double angleB= 30;
		double hypotenuse= 50;
		double base= 20;
		double exp= 13;
		
		String line1= new String ("calculation 1: Area of circle with radius " + radius + " is:" 
									+ circleArea (radius)+ " square-cm.");
		String line2= new String ("calculation 2: Length of opposite where angle B is " + angleB 
									+ " degrees and Hypotenuse length is " + hypotenuse + " cm is: "
									+ lengthOfTriangleOpposite (angleB, hypotenuse)+ " cm.");
		
		String line3 = new String("calculation 3: Power of " + base + " with exp of " + exp + " is "
									+ power (base, exp));
		String results= line1 + "<br>" + line2 + "<br>" + line3;
		
		return results;
	}

}

	


