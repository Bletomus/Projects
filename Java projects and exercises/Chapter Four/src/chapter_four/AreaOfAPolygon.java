package chapter_four;

import java.util.Scanner;

public class AreaOfAPolygon {

	public static void main(String[] args) 
	{
		int sides = 0;
		double length = 0;
		double area = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter in the sides count!");
		sides = scanner.nextInt();
		System.out.println("Please enter in the length of each side!");
		length = scanner.nextDouble();
		area = (sides * Math.pow(length, 2))/(4 * (Math.tan((Math.PI/sides)))) ;
		
		System.out.printf("The area is %.2f",area);

	}

}
