#define MAX 5
#define MAXS 1000
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <math.h>
#include <ctype.h>

int arr[MAXS];
char s[MAXS][MAXS];

int * intToC(char * cArray,char c);
int partitioner(int listi[], int first, int last);
void quick_sort(int *arr,int first, int last );
int bin_search(int * arr, int start, int ende, int element);
void readCategories(FILE*);
void readEntryIndex(FILE*fPtr1);
int selectCategory(int);
int sizeOfArrayInt();
