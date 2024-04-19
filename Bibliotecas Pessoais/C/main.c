#include "MySort.h"

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#define test 8


int randNum(int max);
void fillArray(int array[], int length);

int main(){
    srand(time(NULL));

    int array[test];
    fillArray(array, test);

    //printArray(array, test);

    quickSort(array, test);

    printArray(array, test);

    //reverseArray(array, test);

    //printArray(array, test);

    return 0;
}

// função para sortear um numero
int randNum(int max){
    return (rand() % max);
}

// preencher o array com numero aleatorio
void fillArray(int array[], int length){
    for ( int i = 0; i < length; i++ ){
        array[i] = randNum(100);
    }
}