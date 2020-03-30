#include <stdio.h>
#include <stdlib.h>

int main()
{
    char* datam = NULL;
    char temp = '\0';
    datam = (char *)malloc(sizeof(char) * 2);
    int length =0;
    printf("Please enter some text here for something cool to happen:\n");
    do
    {

        temp = getchar();
        if(temp == '\n')
        {
            break;
        }
        datam[length]=temp;
        datam[length+1]='\0';
        length++;
        datam = (char *)realloc(datam,sizeof(char) * (length + 2));
    }while(1);
    printf("I am sorry we can't do anything with your string. Here, \"%s\"; Take it back", datam);
    free(datam);
    return 0;
}
