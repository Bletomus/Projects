#include <stdio.h>
#include <stdlib.h>

int main()
{
    int c,n;
    int result[100];
    int bestResult[100];
    int finalNumber =0;
    int i,m,j;
    int goods[100][2];
    int bestValues = 0, w, v, flag=0;
    for(i=1;i<100;i++)
    {
        result[i] = 0;
        bestResult[i]  = 0;

    }

    for(i=0;i<100;i++)
        for(j=0;j<2;j++)
            goods[i][j] = 0;

    printf("Please enter the capacity of the bag\n");
    scanf("%d", &c);
    printf("Please enter the number of items in the bag\n");
    scanf("%d",&n);

    for(i=1;i<n+1;i++)
    {
        printf("pls input the weight of No. %d item:\n", i);
        scanf("%d",&goods[i][0]);
        printf("pls input the value of No. %d item:\n", i);
        scanf("%d",&goods[i][1]);
    }
    for(i=1;i<n;i++)
    {
        w = c - goods[i][0];
        v = goods[i][1];
        flag=0;
        result[flag] = i;
        for(j=i+1;j<=n;j++)
        {
            if(goods[j][0]<=w)
            {
                flag++;
                result[flag] = j;
                v = v + goods[j][1];
                w = w - goods[j][0];
            }
        }
        if(bestValues<v)
        {
            bestValues = v;
            finalNumber = flag;
            for(m=0;m<=flag;m++)
            {
                bestResult[m] = result[m];
            }
        }
    }
    printf("the optimal value of this problem is %d",bestValues);
    printf(" from the following item:");
    for(i = 0;i<=finalNumber;i++)
    {
        printf(" %d",bestResult[i]);
    }
    return 0;
}
