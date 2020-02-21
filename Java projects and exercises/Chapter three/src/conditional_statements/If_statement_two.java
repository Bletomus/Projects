package conditional_statements;

import java.util.Scanner;

class If_statement_two {

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		double a = 0;
		double b = 0;
		double c = 0;
		double d = 0;
		double e = 0;
		double f = 0;
		double diff =0; 
		System.out.println("2 by two matrix x and y solver");
		System.out.println("Please enter in a");
		a = scanner.nextDouble();
		System.out.println("Please enter in b");
		b = scanner.nextDouble();
		System.out.println("Please enter in c");
		c = scanner.nextDouble();
		System.out.println("Please enter in d");
		d = scanner.nextDouble();
		System.out.println("Please enter in e");
		e = scanner.nextDouble();
		System.out.println("Please enter in f");
		f = scanner.nextDouble();
		diff = (a*d)-(b*c);
		if(diff>0)
		{
			x_y_finder(a,b,c,d,e,f);
		}
		else if(diff<0)
		{
			x_y_finder(a,b,c,d,e,f);
		}
		else
		{
			System.out.println("The equation has no solution");
		}
	}
	private static void x_y_finder(double a,double b,double c,double d,double e,double f)
	{
		double x = ((e*d)-(b*f))/((a*d)-(b*c));
		double y = ((a*f)-(e*c))/((a*d)-(b*c));
		System.out.printf("X = %.2f and Y = %.2f\n",x,y);
	}
}
