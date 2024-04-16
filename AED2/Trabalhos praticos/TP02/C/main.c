#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define lineMax 1000
#define auxStringTam 200
#define auxArrayTam 200
#define structList 500
#define attributes 18

typedef struct{
    char stringSplited[attributes][auxStringTam];
} Split;

typedef struct{
    int day;
    int month;
    int year;
} LocalDate;

typedef struct{
    char id[auxStringTam];
    char name[auxStringTam];
    char alternateNames[auxArrayTam];
    char house[auxStringTam];
    char ancestry[auxStringTam];
    char species[auxStringTam];
    char patronus[auxStringTam];
    char hogwartsStaff[auxStringTam];
    char hogwartsStudent[auxStringTam];
    char actorName[auxStringTam];
    char alive[auxStringTam];
    char alternateActors[auxStringTam];
    //char dateOfBirth[auxStringTam];
    // int day;
    // int month;
    // int year;
    LocalDate dateOfBirth;
    int yearOfBirth;
    char eyeColour[auxStringTam];
    char gender[auxStringTam];
    char hairColour[auxStringTam];
    char wizard[auxStringTam];
} Personagem;

void questao01(const Personagem personagens[]);

LocalDate stringDateFormat(char* aux);
void stringBooleanFormat(char* aux);
int stringIntFormat(const char* aux);
void removeStringChar(char* aux, char c);

void readCsv(Personagem personagens[], char* fileToRead);
Personagem searchPessoaId(const Personagem personagem[], char* id);

void printPersonagem(const Personagem* personagem);

int main(){
    Personagem personagens[structList];

    readCsv(personagens, "/tmp/characters.csv");

    questao01(personagens);

    return 0;
}

Personagem searchPessoaId(const Personagem personagens[], char* id){
    Personagem resp;
    strcpy(resp.id, "-1");

    for ( int i = 0; i < structList; i++){
        if ( strcmp(personagens[i].id, id) == 0 ){
            resp = personagens[i];
            break;
        }
    }

    return resp;
}

void questao01(const Personagem personagens[]) {
    char input[auxStringTam];
    Personagem aux;

    scanf(" %s", input);
    while ( (strcmp(input, "FIM") != 0) ){

        aux = searchPessoaId(personagens, input);

        if ( strcmp(aux.id, "-1") != 0 ) printPersonagem(&aux);
        else printf("Id nao encontrado\n");

        scanf(" %s", input);
    }

}

void readCsv(Personagem personagens[], char* fileToRead){
    char aux[lineMax];
    Split split;

    FILE* arq;
    arq = fopen(fileToRead, "r");
    if ( arq == NULL ) printf("Erro ao abrir arquivo");

    fgets(aux, sizeof(aux), arq);

    int k = 0;
    while ( !(feof(arq)) ){
        for ( int i = 0; i < attributes; i++ ) {
            fscanf(arq, "%[^;\n]", split.stringSplited[i]);
            fgetc(arq);
        }

        strcpy(personagens[k].id, split.stringSplited[0]);
        strcpy(personagens[k].name, split.stringSplited[1]);
            removeStringChar(split.stringSplited[2], '[');
            removeStringChar(split.stringSplited[2], ']');
            removeStringChar(split.stringSplited[2], '\'');
        strcpy(personagens[k].alternateNames, split.stringSplited[2]);
        strcpy(personagens[k].house, split.stringSplited[3]);
        strcpy(personagens[k].ancestry, split.stringSplited[4]);
        strcpy(personagens[k].species, split.stringSplited[5]);
        strcpy(personagens[k].patronus, split.stringSplited[6]);
        stringBooleanFormat(split.stringSplited[7]);
        strcpy(personagens[k].hogwartsStaff, split.stringSplited[7]);
            // strcpy(personagens[k].hogwartsStaff, "false");
        stringBooleanFormat(split.stringSplited[8]);
        strcpy(personagens[k].hogwartsStudent, split.stringSplited[8]);
            // strcpy(personagens[k].hogwartsStudent, "false");
        strcpy(personagens[k].actorName, split.stringSplited[9]);
        stringBooleanFormat(split.stringSplited[10]);
        strcpy(personagens[k].alive, split.stringSplited[10]);
            // strcpy(personagens[k].alive, "false");
        strcpy(personagens[k].alternateActors, split.stringSplited[11]);
            // strcpy(personagens[k].dateOfBirth, split.stringSplited[12]);
            // sscanf(split.stringSplited[12], "%d-%d-%d", &personagens[k].day, &personagens[k].month, &personagens[k].year);
        personagens[k].dateOfBirth = stringDateFormat(split.stringSplited[12]);
        personagens[k].yearOfBirth = atoi(split.stringSplited[13]);
        strcpy(personagens[k].eyeColour, split.stringSplited[14]);
        strcpy(personagens[k].gender, split.stringSplited[15]);
        strcpy(personagens[k].hairColour, split.stringSplited[16]);
        stringBooleanFormat(split.stringSplited[17]);
        strcpy(personagens[k].wizard, split.stringSplited[17]);
            // strcpy(personagens[k].wizard, "false");

        for ( int i = 0; i < attributes; i++ ) {
            split.stringSplited[i][0] = 0;
        }

        k++;
    }

    fclose(arq);
}

void stringBooleanFormat(char* aux){
    aux[0] == 'V' ? strcpy(aux, "true") : strcpy(aux, "false");
}

int stringIntFormat(const char* aux){
    return atoi(aux);
}

LocalDate stringDateFormat(char* aux){
    LocalDate formated;

    sscanf(aux, "%d-%d-%d", &formated.day, &formated.month, &formated.year);
    
    return formated;
}

void removeStringChar(char* aux, char c){
    int i = 0;
    int j = 0;
    while (aux[i] != '\0') {
        if (aux[i] != c) aux[j++] = aux[i];
        i++;
    }
    aux[j] = '\0';
}

void printPersonagem(const Personagem* personagem) { //                             LocalDate
    printf("[%s ## %s ## {%s} ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %s ## %02d-%02d-%02d ## %d ## %s ## %s ## %s ## %s]\n",
            personagem->id,
            personagem->name,
            personagem->alternateNames,
            personagem->house,
            personagem->ancestry,
            personagem->species,
            personagem->patronus,
            personagem->hogwartsStaff,
            personagem->hogwartsStudent,
            personagem->actorName,
            personagem->alive,
            // personagem->day,
            // personagem->month,
            // personagem->year,
            // personagem->dateOfBirth,
            personagem->dateOfBirth.day,
            personagem->dateOfBirth.month,
            personagem->dateOfBirth.year,
            personagem->yearOfBirth,
            personagem->eyeColour,
            personagem->gender,
            personagem->hairColour,
            personagem->wizard);
}
