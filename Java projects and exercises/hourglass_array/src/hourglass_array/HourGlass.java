package hourglass_array;

public class HourGlass 
{

	public static void main(String[] args) 
	{
		int array[][] = {{-1,-1,0,-9,-2,-2},{-2,-1,-6,-8,-2,-5},{-1,-1,-1,-2,-3,-4},{-1,-9,-2,-4,-4,-5},{-7,-3,-3,-2,-9,-9},{-1,-3,-1,-2,-4,-5}};
		arr_adder ar = new arr_adder(array);
		int maximum_val = ar.execute();
		System.out.println("max is: " + maximum_val);

	}
	
}


class arr_adder
{
	private int full_item[][];
	private double max_val = 0;
	private final int SECOND_ROW_OFFSET = 1; //offset for setting the i to get the middle array index for array hourglass
	private final int SECOND_COLUMN_OFFSET = 1; // offset for setting the k to the value of the middle array index for the hourglass
	private final int BASE_OFFSET = 2; //offset to set the index for the row of the base of the hourglass
	private final int HEIGHT_BREAK = 2; //the value of the maximum possible invalid height of of the hourglass
	private final int WIDTH_BREAK = 2; //the value of the maximum possible invalid width of of the hourglass
	private final int COLUMN_SUM_OFFSET = 1;
	
	arr_adder(int arr[][])
	{
		this.full_item = arr;
	}
	
	
	
	private boolean total_sum()
	{
		int row_size = full_item.length;
		int i = 0;
		double total = 0;
		int k = 0;
		int column_size = 0;
		for(; HEIGHT_BREAK < row_size - i; i++ )
		{
			column_size =full_item[i].length;
			
			for(; column_size - k > WIDTH_BREAK;k++)
			{
				total =  sum_row(i,k,0) + full_item[i + SECOND_ROW_OFFSET][k + SECOND_COLUMN_OFFSET] + sum_row(i+BASE_OFFSET,k,0);
				if(i==0 && k == 0)
				{
					max_val = total;
				}
				else
				{
					calculate_Max(total);
				}
				
			}
			
			k = 0;
			total = 0;
		}
		return true;
	}
	private boolean calculate_Max(double value)
	{
		max_val = Math.max(max_val, value);
		return true;
	}
	
	private double sum_row(int row_num, int column_num, int counter)
	{
		if(counter == 3)
		{
			return 0;
		}
		return full_item[row_num][column_num] + sum_row(row_num,column_num + COLUMN_SUM_OFFSET, counter + COLUMN_SUM_OFFSET);
	}
	
	
	int execute()
	{
		total_sum();
		
		return (int)max_val;
	}
}