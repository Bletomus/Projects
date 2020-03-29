package chapter_four;

import java.util.Scanner;

public class AreaOfAPentagon {

	public static void main(String[] args)
	{
		double radius = 0;
		double length = 0;
		double area = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter in the radius from the center to a given vertex!");
		radius = scanner.nextDouble();
		length = 2 * radius * Math.sin((Math.PI / 5));
		area = (5 * Math.pow(length, 2))/(4 * (Math.tan((Math.PI/5)))) ;
		
		System.out.printf("The area is %.2f",area);
		
	}

}
