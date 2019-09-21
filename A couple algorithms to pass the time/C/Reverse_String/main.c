#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 100

int main()
{
    printf("Well hello there!Why don't you enter a text real quick. Keep it short okay\n");
    char userInput[MAX];
    fgets(userInput,MAX, stdin);
    printf("The string in reverse is:\t");
    reverse(userInput, strlen(userInput));
    getchar();
    return 0;
}
void reverse(char userInput[], int len)
{
    int i = len - 1;
    for(; i >= 0; i-- )
    {
        printf("%c",userInput[i]);
    }

}


