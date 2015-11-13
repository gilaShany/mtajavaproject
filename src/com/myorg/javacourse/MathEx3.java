package com.myorg.javacourse;

public class MathEx3 {
	
	public static double circleArea(double radius) {
	
	double area = Math.PI * (radius * radius) ; 
	return area;
		
	}
	
	public static double triangleOpposite(int angleB, int hypotenuse){
		double opposite;
		
		opposite = Math.sin(angleB)* hypotenuse;
		return opposite;
		
	}

	public static double power(double x,double y){

		double res;
		res = Math.pow(x, y);

		return res;
	
}


	public static String getResults() {
		int radius = 50;
		int angleB = 30;
		int hypotenuse = 50;
		double x = 20;
		double y = 13;
		
		double area = circleArea(radius);
		double opposite = triangleOpposite(angleB,hypotenuse);
		double pow = power(x,y);
		
	
		String line1 = new String("calculation 1: Area of circle with radius " + radius + " is " + area);
		String line2 = new String("calculation 2: Length of opposite where angle B is " + angleB + " is " + opposite);
		String line3 = new String("calculation 3: Power of base " + x + " and exp " + y + " is " + pow);
		
		String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
		
		return resultStr;
	}


}

	


