#include "categories.h"

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
	int iVal2;
	int iVal3;
	switch(choice)
    {
        case 0:
            printf("Maximum Finder\n");
            printf("Please enter in the first value:\n");
            scanf("%d",&iVal1);
            printf("Please enter in the second value:\n");
            scanf("%d",&iVal2);
            printf("The maximum between %d and %d is %d\n",iVal1, iVal2, iVal1 > iVal2 ? iVal1 : iVal2);
            break;
        case 1:
            printf("Minimum Finder\n");
            printf("Please enter in the first value:\n");
            scanf("%d",&iVal1);
            printf("Please enter in the second value:\n");
            scanf("%d",&iVal2);
            printf("The maximum between %d and %d is %d\n",iVal1, iVal2, iVal1 < iVal2 ? iVal1 : iVal2);
            break;
        case 2:
            printf("Maximum between three numbers\n");
            printf("Please enter in the first value:\n");
            scanf("%d",&iVal1);
            printf("Please enter in the second value:\n");
            scanf("%d",&iVal2);
            printf("Please enter in the third value:\n");
            scanf("%d",&iVal3);
            printf("The maximum between %d, %d and %d is %d\n",iVal1, iVal2,iVal3, iVal3 > (iVal1 > iVal2 ? iVal1 : iVal2) ? iVal3 : (iVal1 > iVal2 ? iVal1 : iVal2));
            break;
        default:
            break;
    }

}
