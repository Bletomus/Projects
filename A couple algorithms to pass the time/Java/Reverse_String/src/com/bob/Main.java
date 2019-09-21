package com.bob;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
	    System.out.println("Well hello there!Why don't you enter some text for me");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        System.out.println("The reverse is:");
        reverse(userInput);
    }

    public static void reverse(String userInput)
    {
        int length = userInput.length();
        reverse(userInput,length - 1);
    }
    public static void reverse(String userInput, int len)
    {
        System.out.print(userInput.charAt(len));
        if(len > 0)
        {
            reverse(userInput,len - 1);
        }
        System.out.println();
    }

}
