#include "categories.h"

void selectOption(int choice);

int main()
{
    int choice = -1;
    FILE *fPtr1,*fPtr2;
    int size = 0;
    int j = 0;
    fPtr1 = fopen("list.txt","r");
    fPtr2 = fopen("category.txt","r");

    if(fPtr1 == NULL || fPtr2==NULL)
    {
        printf("Error encountered when attempting to read the files. ");
        exit(1);
    }
    readEntryIndex(fPtr1);

    readCategories(fPtr2);

	j = sizeOfArrayInt(arr);

    quick_sort(arr,0, j - 1);
    size = j;


    choice = selectCategory(size);
	selectOption(choice);
    getchar();
    getchar();

    fclose(fPtr1);
    fclose(fPtr2);
    return 0;
}

void selectOption(int choice)
{
	int iVal1;
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

}




