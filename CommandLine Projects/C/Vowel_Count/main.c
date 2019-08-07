#include "vowel_count.h"

int main()
{
    printf("Vowel Counter over here!\n");
    char * word = NULL;
    word = getInput();
    int * count = NULL;
    count = countVowel(word);
    dispayResult(word,count);
    return 0;
}
