package chapter_2;

import java.util.Scanner;

public class TemperatureConverter
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		double fahrenheit = 0;
		double degree = 0;
		System.out.println("Please enter in a the temperature in degree Celsius:");
		degree = input.nextDouble();
		fahrenheit = (9.0/5)*degree + 32;
		System.out.println(degree +  " degree celcius is " + fahrenheit + " fahrenheit.");

	}

}
