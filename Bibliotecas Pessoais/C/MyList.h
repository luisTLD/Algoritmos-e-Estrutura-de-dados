#ifndef MyList_h
#define MyList_h

#include <stdlib.h>
#include <stdio.h>


typedef struct Celula{
    int valor;
    struct Celula* prox;
    struct Celula* ant;
} Celula;

Celula *firstList = NULL;
Celula *lastList = NULL;

void startList(){
    Celula *aux = (Celula*)malloc(sizeof(Celula));

    aux->valor = 0;

    firstList = aux;
    lastList = aux;
}

Celula* newCelula(int valor){
    Celula *aux = (Celula*)malloc(sizeof(Celula));

    aux->valor = valor;
    aux->prox = NULL;
    aux->ant = NULL;

    return aux;
}

void addBegin(int valor){
    Celula *aux = newCelula(valor);

    aux->prox = firstList->prox;
    aux->ant = firstList;
    firstList->prox = aux;

    if ( firstList == lastList ) lastList = aux;
    else aux->prox->ant = aux;
   
}

void addEnd(int valor){
    Celula *aux = newCelula(valor);

    lastList->prox = aux;
    aux->prox = NULL;
    aux->ant = lastList;
    lastList = lastList->prox;
}

int removeEnd(){
    if ( lastList = firstList ){
        printf("Lista vazia");
        return 0;
    }

    int aux = lastList->valor;

    lastList = lastList->ant;
    free(lastList->prox);
    lastList->prox = NULL;

    return aux;
}

void printList(){
    Celula *index = firstList->prox;

    while ( index != NULL ){
        printf("%d ", index->valor);
        index = index->prox;
    } 
}


#endif