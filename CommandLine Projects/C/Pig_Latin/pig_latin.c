#include "pig_latin.h"

char * getInput()
{
    printf("Well hello there!Why don't you enter a text real quick. Keep it short okay\n");
    static char userInput[MAX];
    fgets(userInput,MAX, stdin);
    int index = strlen(userInput) - 1;
    memmove(&userInput[index], &userInput[index + 1], strlen(userInput) - index);
    return userInput;
}

char * getPigWord(char * normalWord)
{
    int count = getConsonantCount(normalWord);
    static char * newWord;
    if(count == 0)
    {
        newWord = vowelMe(normalWord);
    }
    if(count == 1)
    {
        newWord =  oneTimeConsonant(normalWord,count);
    }
    if(count > 1)
    {
        newWord =  oneTimeConsonant(normalWord,count);
    }


    return newWord;
}

int getConsonantCount(char * normalWord)
{
    //int wordSize = strlen(normalWord);
    char item = normalWord[0];
    int count = 0;
    //could have used c type to manage the cases but nah

    while(item != 'a' && item != 'e' && item != 'i' && item != 'o' && item != 'u' && item != 'A' && item != 'E' && item != 'I' && item != 'O' && item != 'U' && item != '\0')
    {
        item = normalWord[count++];
    }
    return count > 1?count-1:count ;
}

char * vowelMe(char * normal)
{
    static char newWord[MAXPLUS];
    strcpy(newWord, normal);
    strcat(newWord, "way");
    return newWord;
}
char * oneTimeConsonant(char * normal, int counter)
{
    char * temp = NULL;
    temp = normal + counter;
    char consonant[MAXPLUS];
    memset( consonant, '\0', sizeof(char)*MAXPLUS );
    int i = 0;
    for(; i< counter; i++)
    {
        consonant[i] = normal[i];
    }
   /* if(counter == 1)
    {
        consonant[i] = normal[i];
    }

    if(counter > 1)
    {

        for(; i< counter; i++)
        {
            consonant[i] = normal[i];
        }
    }

*/

    strcat(consonant, "ay");
    static char newWord[MAXPLUS];
    strcpy(newWord, temp);
    strcat(newWord, consonant);
    return newWord;
}

