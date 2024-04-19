#ifndef MySort_h
#define MySort_h

#include <stdio.h>


void reverseArray(int array[], int length);
void printArray(int array[], int length);
void swap(int array[], int i, int j);
void bubbleSort(int array[], int length);
void selectionSort(int array[], int length);
void quickSort(int array[], int length);
void quickSortCall(int array[], int left, int right);
void heapSort(int array[], int length);
void makeHeap(int array[], int max);
void redoHeap(int array[], int endIndex);
int getBiggerSon(int array[], int i, int max);
int hasSon(int i, int max);


// trocar posição i com a j
void swap(int array[], int i, int j){
    int aux = array[i];
    array[i] = array[j];
    array[j] = aux;
}

// printar todos elementos de um array
void printArray(int array[], int length){
    for ( int i = 0; i < length; i++ ){
        printf("%d ", array[i]);
    }
    
    printf("\n");
}

// retorna 1 caso array esteja ordenado
int sortedArray(int array[], int length){
    int ver = 1;

    for ( int i = 0; i < length - 1; i++ ){
        if (array[i] > array[i+1]){
            ver = 0;
            i = length;
        }
    }

    return ver;
}

// inverte o array
void reverseArray(int array[], int length){
    int i = 0;
    length--;
    
    while (i < length) swap(array, i++, length--);
}

// ordenação bubbleSort -> n^2
void bubbleSort(int array[], int length){
    int tmp = 0;

    for ( int i = 0; i < length - 1; i++ ){
        for ( int j = 0; j < length - i - 1; j++ ){
            if ( array[j] > array[j+1]){
                swap(array, j, j + 1);
            }
        }
    }
}

// ordenação selectionSort -> n^2
void selectionSort(int array[], int length){
    int lowest = 0;
    
    for ( int i = 0; i < length - 1; i++ ){
        lowest = i;
        
        for ( int j = i + 1; j < length; j++ ){
            if ( array[lowest] > array[j] ){
                lowest = j;
            }
        }

        swap(array, i, lowest);
    }
}

// ordenação quickSort -> n.log(n) // n^2 pior caso
void quickSort(int array[], int length){
    quickSortCall(array, 0, length);
}
// chamada recursiva para quickSort
void quickSortCall(int array[], int left, int right){
    int i = left;
    int j = right;
    int piv = array[(left + right)/2];

    while ( j >= i ){
        while ( array[i] < piv ) i++;
        while ( array[j] > piv ) j--;

        if ( j >= i ) {
            swap(array, i, j);
            i++;
            j--;
        }

        if ( left < j ) quickSortCall(array, left, j);
        if ( right > i ) quickSortCall(array, i, right);
    }
}

// ordenação heapSort -> n.log(n)
void heapSort(int array[], int length){
    int endIndex = length - 1;

    for ( int max = 1; max < length; max++ ){
        makeHeap(array, max);
    }

    while (endIndex > 0){
        swap(array, 0, endIndex);
        endIndex--;
        redoHeap(array, endIndex);
    }

}
// montar o array em formato de heap
void makeHeap(int array[], int max){
    for ( int i = max; max > 0 && array[i] > array[(i-1)/2]; i = (i-1)/2 ){
        swap(array, i, (i-1)/2);
    }
}
// remontar o heap
void redoHeap(int array[], int endIndex){
    int aux = 0;

    while (hasSon(aux, endIndex)){
        int son = getBiggerSon(array, aux, endIndex);
        if (array[aux] < array[son]){
            swap(array, aux, son);
            aux = son;
        }
        else break;
    }
}
// retorna 1 caso tenha filho
int hasSon(int aux, int endIndex){
    int ver = 0;

    if (((aux*2)+1) <= endIndex) ver = 1;

    return ver;
}
// obter maior filho
int getBiggerSon(int array[], int aux, int endIndex){
    int son = 0;
    
    if ( ((2*aux)+1) == endIndex ) son = endIndex;
    else if (array[(2*aux)+1] > array[(2*aux)+2]) son = ((2*aux)+1);
    else son = ((2*aux)+2);

    return son;
}

#endif