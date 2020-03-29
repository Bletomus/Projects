#include <stdio.h>
#include <stdlib.h>

int main()
{
    int c[] = {0,1,2,3};
    int i,j, m=3;
    int f[] = {0,1,2,3,4,5,6,7,8,9,10};
    int index[11];
    int tmp = 32000;
    int t, k;
    f[0] = 0;
    for (i=1; i<=10; i++)
    {
        tmp = 32000;
        j =1;
        k = -1;
        while (j<=m && i>= c[j])
        {
            t= f[i-c[j]];
            if ( t < tmp)
            {
                tmp = t;
                k = j;
            }
            j++;
        }
        f[i] = tmp +1;
        if ( k!= -1 )
        index[i] = c[k];

    }
    j=7;
    for (i=1; i<=f[7]; i++)
    {
        printf("%d\n",index[j]);
        j = j-index[j];
    }
    printf("at least we should use %d coins", f[7]);
    return 0;
}
