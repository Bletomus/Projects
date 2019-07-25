#include "pig_latin.h"

int main()
{
    char * word = getInput();
    char *  newWord = getPigWord(word);
    printf("The old word is %s and the new pig in the pen is %s",word,newWord);
    getchar();
    return 0;
}
