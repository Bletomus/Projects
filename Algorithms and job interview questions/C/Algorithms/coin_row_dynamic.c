#include <stdio.h>
#include <stdlib.h>

int max (int a, int b)
{
    if ( a>b)
        return a;
    else
        return b;
}

int * CoinRowDynamic(int coin[], int f[], int n)
{
    int i;
    int * result = (int*)malloc(sizeof(int) * (n +1));
    for ( i=2; i<=n; i++)
        f[i] = max(coin[i] + f[i-2],  f[i-1]);

    for (int i=0; i<=n; i++)
        result[i] = f[i];

    return result;
}

int main()
{
    int coin[] = {0,5,1,2,10,6,2};
    int f[7];
    int * result;
    f[0] = 0;
    f[1] = coin[1];
    int i;
    result = CoinRowDynamic(coin,f,6);
    for (i=0; i<=6; i++)
        printf("%d\n",result[i]);
    return 0;
}

