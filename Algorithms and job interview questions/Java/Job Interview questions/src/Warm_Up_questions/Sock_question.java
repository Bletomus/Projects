package Warm_Up_questions;
import java.util.*;
public class Sock_question 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}
	static int sockMerchant(int n, int[] ar)
    {
        HashMap<Integer, Integer> colors = new HashMap<>();
        int temp = 0;
        for(int i =0; i<ar.length;i++)
        {
            if(colors.containsKey(ar[i]))
            {
            	temp = colors.get(ar[i]) + 1;
            	colors.replace(ar[i],temp);
            }
            else
            {
            	colors.put(ar[i],1);
            }
        }
        int pairs = getPairs(colors);
        return pairs;
        
    }
	private static int getPairs(HashMap<Integer,Integer> hm)
    {
		Iterator iterate = hm.entrySet().iterator();
		int value = 0;
		int pairs = 0;
		while(iterate.hasNext())
		{
			Map.Entry<Integer, Integer> mapElement = (Map.Entry<Integer, Integer>)iterate.next();
			value = (int)mapElement.getValue();
			if(value >= 2)
			{
				pairs += value/2;
			}
		}
    	
    	return pairs;
    }
}
