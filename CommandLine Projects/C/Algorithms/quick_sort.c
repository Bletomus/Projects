#include <stdio.h>
#include <stdlib.h>
int size = 0;
int * inniD();//function for initializing the array that shall be used
void quick_sort(int * list,int first, int last );//function for sorting the array using quick sort
int partitioner(int list[], int first, int last);//will produce a new pivot when quick sort is run to divide the array into two separate portions
void printarray(int array[], int iSize);//prints the size of the array

//The partitioner shuffles the array splitting the array by a pivot. Smaller items are sent to the left side of the pivot and larger items sent to the right side of the pivot
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

//Function will continue taking input until the user presses enter and returns the size of the array
int * inniD()
{
    int i = 0;
    char userValue = '\0';
    int * array = (int*)malloc(sizeof(int) * 1);//sets the initial size of the array to 1;
    for(; (userValue = getchar()) != '\n'; )
    {
        i++;
        array = (int*)realloc(array,sizeof(int) * i);//constantly increases the size of the array by one for as long as the input is not 'enter'
        array[i - 1] = userValue - '0';
    }
    size = i;
    return array;
}
void printarray(int array[], int iSize)
{
    int i = 0;
    printf("The array is \n");
    for(; i < iSize; i++ )
    {
        printf("%d\n", array[i]);
    }
    printf("\n");
}

void quick_sort(int * list,int first, int last )
{
    if(last > first)
    {
        int pivot = partitioner(list,first, last);
        quick_sort(list,first,pivot -1); //runs quick sort on the first half of the array that has been smaller value than the pivot
        quick_sort(list,pivot + 1, last);//runs the quick sort on the second half that has values larger than the pivot
    }
}



int main()
{
    size = 0;

    int * array = NULL;
    printf("Please enter the values then press enter to continue: \n");

    array = inniD();
    printf("The array: \n");
    printarray(array, size);

    quick_sort(array,0, size);

    printf("Becomes: \n");
    printarray(array, size );

    return 0;
}
