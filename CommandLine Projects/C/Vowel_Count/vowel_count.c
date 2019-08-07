
#include "vowel_count.h"

char * getInput()
{
    printf("Well hello there!Why don't you enter a text real quick. Keep it short okay\n");
    static char userInput[MAX];
    fgets(userInput,MAX, stdin);
    int index = strlen(userInput) - 1;
    memmove(&userInput[index], &userInput[index + 1], strlen(userInput) - index);
    return userInput;
}


int * countVowel(char * str1)
{
    int freq[26] = {0};
    static int counter[6] = {0};
    int vowelFreq[5] = {0};
    vowelFreq[0] = 'a' - 'a';
    vowelFreq[1] = 'e'- 'a';
    vowelFreq[2] = 'i'- 'a';
    vowelFreq[3] = 'o'- 'a';
    vowelFreq[4] = 'u'- 'a';
    int i = 0;
    for(; i< strlen(str1);i++)
    {
        freq[str1[i] - 'a']++;
    }

    for(i = 0; i < 6; i++)
    {
        counter[0] += freq[vowelFreq[i]];
        counter[i + 1] = freq[vowelFreq[i]];
    }
    return counter;
}

void dispayResult(char * str1,int * count)
{
    printf("The vowels in %s are: \n a -> %d\n e -> %d\n i -> %d\n o -> %d\n u -> %d\n",str1, count[1], count[2], count[3], count[4], count[5] );
    printf("The total should be %d\n", count[0]);
    getchar();
}
