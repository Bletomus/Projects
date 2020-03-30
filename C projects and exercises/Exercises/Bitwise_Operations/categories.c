#include "categories.h"

int bin_search(int * arr, int start, int end, int element)
{
    int temp = 0;
    while (start <= end)
    {
        int temp = start + (end - start) / 2;


        if (arr[temp] == element)
            return temp;


        if (arr[temp] < element)
            start = temp + 1;
        else
            end = temp - 1;
    }

    return -1;
}

int partitioner(int list[], int first, int last)
{
    int pivot = list[first];
    int low = first + 1;
    int high = last;
    int temp;
    while(high > low)
    {
        while(low <= high && list[low] <= pivot)
        {
            low++;
        }

        while(low <= high && list[high] > pivot)
        {
            high--;
        }

        if(high > low)
        {
            temp = list[low];
            list[low] = list[high];
            list[high] = temp;
        }
    }
    while(high > first && list[high] >= pivot)
        high--;
    if(pivot > list[high])
    {
        list[first] = list[high];
        list[high] = pivot;
        return high;
    }
    else
        return first;

}
void quick_sort(int * list,int first, int last )
{
    if(last > first)
    {
        int pivot = partitioner(list,first, last);
        quick_sort(list,first,pivot -1);
        quick_sort(list,pivot + 1, last);
    }
}


void readEntryIndex(FILE*fPtr1)
{
	int iTemp = 0;
	char cTemp = '\0';
	char c[MAX];
	int j =0;
	int length = 0;
	int i = 0;
	while(!feof(fPtr1)&&(j < MAXS))
    {
        fgets(c, MAX, fPtr1);
        length = strlen(c);
        if( c[length - 1] == '\n' )
        {
            c[--length] = '\0';

        }
        for(i = 0;i < length;i++)
        {
            if(isdigit(c[i]))
            {
                cTemp =c[i];
                iTemp += (cTemp - '0') * ceil(pow(10,length - i - 1));
            }


        }
        arr[j++] = iTemp;
        iTemp = 0;
    }
	arr[j] = -1;
}

int sizeOfArrayInt(int *arr)
{
	int j = 0;
	while(arr[j] != -1)
	{
		j++;
	}
	return j;
}

void readCategories(FILE*fPtr2)
{
	int k=0;
	while( !feof(fPtr2) && k < MAXS)
    {
        fgets(s[k++], MAXS, fPtr2);
    }

}

int selectCategory(int size)
{
	int j = 0;
	int choice = -1;
	int iTemp = 0;
	printf("Please select one of the below numbers in the bitwise operations:\n");
    do
    {
        for(j = 0; j< size;j++)
        {
            printf("%d - %s",arr[j], s[j]);
        }
        printf("\n");
        scanf("%d",&iTemp);
        choice = bin_search(arr,0,j-1,iTemp);
        if( choice != -1)
        {
            break;
        }

        printf("\nPlease select one of the below numbers in the bitwise operations:\n");
    }while(1);

	return choice;
}
