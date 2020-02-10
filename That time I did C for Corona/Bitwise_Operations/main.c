#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <math.h>
#include <ctype.h>
#define MAX 5
#define MAXS 1000

int * intToC(char * cArray,char c);
int partitioner(int list[], int first, int last);
void quick_sort(int * list,int first, int last );
int bin_search(int * arr, int start, int end, int element);


int main()
{
    char s[1000][1000];
    char c[MAX];
    int iTemp =0;
    char cTemp = '0';
    int length = 0;
    int i = 0;
    int arr[1000];
    int j = 0;
    int k = 0;
    int choice = -1;
    FILE *fPtr1,*fPtr2;
    int size = 0;
    int iVal1;
    fPtr1 = fopen("list.txt","r");
    fPtr2 = fopen("category.txt","r");
    if(fPtr1 == NULL || fPtr2==NULL)
    {
        printf("Error encountered when attempting to read the files. ");
        exit(1);
    }
    while(!feof(fPtr1)&&(j < 1000))
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

    while( !feof(fPtr2) && k < 1000)
    {
        fgets(s[k++], MAXS, fPtr2);
    }


    quick_sort(arr,0, j - 1);
    size = j;


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

    switch(choice)
    {
        case 0:
            printf("Least Significant Bit\n");
            printf("Please enter in the value that should be checked:\n");
            scanf("%d",&iVal1);
            printf("The least significant digit of %d is %d\n",iVal1, iVal1 & 1);
            break;
        case 1:
            printf("Most Significant Bit\n");
            printf("Please enter in the value that should be checked:\n");
            scanf("%d",&iVal1);
            printf("The most significant digit of %d is %d\n",iVal1, (1 << (sizeof(int) - 1)) & iVal1 ? 1: 0);
            break;
        default:
            break;
    }

    fclose(fPtr1);
    fclose(fPtr2);
    return 0;
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
