package chapter_five;
//Mini-program to convert kilogram values from 1 to 199 to their equivalent pound value
public class KilogramToPounds {

	public static void main(String[] args) 
	{
		System.out.printf("%-15s %15s\n","Kilograms","Pounds");
		for(int i = 1; i < 200;i = i +2 )
		{
			System.out.printf("%-15d %15.2f\n",i, i*2.2);
		}
	}

}
