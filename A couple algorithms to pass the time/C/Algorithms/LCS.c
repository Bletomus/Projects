#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#define MAX 1000
int max(int a, int b);
void lcs(char str1[], char str2[], int m, int n );


int main()
{
    char str1[MAX];

    char str2[MAX];
    printf("Please enter in the first string\n");
    gets(str1);
    printf("Please enter in the second string\n");
    gets(str2);
    int i = strlen(str1);
    int j = strlen(str2);
    lcs(str1,str2,i,j);
    return 0;
}

int max(int a, int b)
{
    if(a > b)
        return a;
    return b;
}

void lcs(char str1[], char str2[], int m, int n )
{
   int temp[MAX][MAX];
   int i, j;


   for (i=0; i<=m; i++)
   {
     for (j=0; j<=n; j++)
     {
       if (i == 0 || j == 0)
         temp[i][j] = 0;

       else if (str1[i-1] == str2[j-1])
         temp[i][j] = temp[i-1][j-1] + 1;

       else
         temp[i][j] = max(temp[i-1][j], temp[i][j-1]);
     }
   }

   int size = temp[m][n];
   char * array = (char*)malloc(sizeof(char)*(size+1));
   array[size] = '\0';

   i = m, j = n;
   while (i > 0 && j > 0)
   {
      if (str1[i-1] == str2[j-1])
      {
          array[size-1] = str1[i-1];
          i--; j--; size--;
      }


      else if (temp[i-1][j] > temp[i][j-1])
         i--;
      else
         j--;
   }
   printf("\n\n %s", array);
}
