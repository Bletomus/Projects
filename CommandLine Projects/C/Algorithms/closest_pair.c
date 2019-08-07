
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

//this structure simulates how points seem with an x for x axis and y for y axis both being restricted to the integer type for simplicities sake
struct points
{
    int x;
    int y;
};

typedef struct points pointss; //gives the structure the name points for easier identification

pointss * innitializeD(pointss * lists, int iSize)
{
    int i = 0;
    srand(time(0));;
    lists = (pointss*)malloc(sizeof(pointss) * iSize);
    for(; i < iSize ;i++)
    {
        lists[i].x = rand() % 50;
        lists[i].y = rand() % 50;
    }
    return lists;
}

void pointes(pointss * lists, int iSize)
{
    int i = 0;
    for(;i < iSize; i++)
    {
        printf("\nPoint %d : (%d,%d)\n", i, lists[i].x,lists[i].y);
    }
}

double minD(pointss * lists,pointss results[2],int iSize)
{
    int i = 0, j = 0;
    double mini = 100000;
    double squr;
    double min1;
    int x, y, x1, y1;
    //this nested for loop structure will test to see how far the distance is for each point
    for(; i < iSize; i++)
    {
        x = lists[i].x;
        y = lists[i].y;
        for(j = i + 1;j < iSize; j++)
        {
            x1 = lists[j].x;
            y1 = lists[j].y;
            squr = pow((x-x1),2) + pow((y-y1),2);
            min1 = sqrt(squr);
            if(mini > min1)
            {
                results[0].x = x;
                results[0].y = y;
                results[1].x = x1;
                results[1].y = y1;
                mini = min1;
            }
        }
    }
    return mini;
}


pointss * innitializeD(pointss * lists, int iSize); //This function creates a random number of points to test
void pointes(pointss * lists, int iSize); //This function prints out the points
double minD(pointss * lists,pointss results[2],int iSize); //this function calculates the distance between two points

int main()
{
    pointss * lists = NULL;
    //pointss test[15]
    //pointss  test[15] = {{27,36},{30,10},{47,5},{17,17},{15,1},{22,15},{49,12},{1,30},{2,45},{45,0},{42,3},{43,14},{48,17},{47,37},{34,20}};
    pointss results[2];
    int iSize = 0;
    printf("Please enter in the number of points: ");
    scanf("%d", &iSize);
    lists = innitializeD(lists, iSize);
    printf("%d", lists[0].x);
    pointes(lists,iSize);
    double distance =  minD(lists,results, iSize);
    printf("The two closest points are points (%d,%d) and (%d,%d) with a distance of %f.2\n", results[0].x, results[0].y, results[1].x, results[1].y, distance);
    return 0;
}
