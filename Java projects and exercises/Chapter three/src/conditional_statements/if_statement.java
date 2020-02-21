package conditional_statements;

import java.util.Scanner;

class if_statement {

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		double a = 0;
		double b = 0;
		double c = 0;
		double discriminant = 0;
		double squarerootdiscrimant = 0;
		double result_one = 0;
		double result_two = 0;
		System.out.println("Finding root of ax^2 + bx + c = 0");
		System.out.println("Please enter in a: ");
		a = scanner.nextDouble();
		System.out.println("Please enter in b: ");
		b = scanner.nextDouble();
		System.out.println("Please enter in c: ");
		c = scanner.nextDouble();
		
		discriminant = Math.pow(b,2) - (4 * a * c);
		if(discriminant > 0)
		{
			squarerootdiscrimant = Math.pow(discriminant, 0.5);
			result_one = ((b * -1) + squarerootdiscrimant) / (2 * a);
			result_two = ((b * -1) - squarerootdiscrimant) / (2 * a);
			System.out.printf("X is either %.2f or it is %.2f", result_one, result_two);
		}
		else if(discriminant < 0)
		{
			
			System.out.println("There is no real root...");
		}
		else
		{
			
			squarerootdiscrimant = Math.pow(discriminant, 0.5);
			result_one = ((b * -1) + squarerootdiscrimant) / (2 * a);
			System.out.printf("X is %.2f",result_one );
		}
	}

}
