#ifndef MyList_h
#define MyList_h

#include <stdlib.h>
#include <stdio.h>

// Struct Celula
typedef struct Celula{
    int valor;
    struct Celula* prox;
    struct Celula* ant;
} Celula;

// Ponteiros para inicio e fim da lista
Celula *firstList = NULL;
Celula *lastList = NULL;

// Função para Iniciar a lista / Celula cabeça
void startList(){
    Celula *aux = (Celula*)malloc(sizeof(Celula));

    aux->valor = 0;

    firstList = aux;
    lastList = aux;

    aux = NULL;
}

// Função para criar uma celula nova
Celula* newCelula(int valor){
    Celula *aux = (Celula*)malloc(sizeof(Celula));

    aux->valor = valor;
    aux->prox = NULL;
    aux->ant = NULL;

    return aux;
}

// Adicionar celula no inicio
void addBegin(int valor){
    Celula *aux = newCelula(valor);

    aux->prox = firstList->prox;
    aux->ant = firstList;
    firstList->prox = aux;

    if ( firstList == lastList ) lastList = aux;
    else aux->prox->ant = aux;

    aux = NULL;
}

// Adicionar celula no final
void addEnd(int valor){
    Celula *aux = newCelula(valor);

    lastList->prox = aux;
    aux->ant = lastList;
    lastList = lastList->prox;

    aux = NULL;
}

// Remover celula do final
int removeEnd(){
    if ( lastList == firstList ){
        printf("Lista vazia\n");
        return 0;
    }

    int aux = lastList->valor;

    lastList = lastList->ant;
    free(lastList->prox);
    lastList->prox = NULL;

    return aux;
}

// Remover celula do inicio
int removeBegin(){
    if ( lastList == firstList ){
        printf("Lista vazia\n");
        return 0;
    }

    int aux = firstList->prox->valor;

    firstList = firstList->prox;
    firstList->valor = 0;
    free(firstList->ant);
    firstList->ant = NULL;

    return aux;
}

// Printar toos os elementos da lista 
void printList(){
    if ( firstList == lastList ) printf("Lista vazia");
    else {
        Celula *index = firstList->prox;

        while ( index != NULL ){
            printf("%d ", index->valor);
            index = index->prox;
        } 
    }
    printf("\n");
}

// Zerar toda a lista
void resetList(){
    while(lastList != firstList){
        lastList = lastList->ant;
        free(lastList->prox);
    }
    firstList->prox = NULL;
}

// Encerrar a lista e dar free na celula cabeça
void endList(){
    if ( lastList != firstList ) resetList();
    free(firstList);
    firstList = NULL;
    lastList = NULL;
}



#endif