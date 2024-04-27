#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

// Variavel global para armazenar o tempo de execução do codigo
clock_t startTime;
clock_t endTime;
int totalComp = 0;


// Define auxiliares para strings, arrays, structs e atributos
#define lineMax 1000
#define auxStringTam 200
#define auxArrayTam 200
#define structList 500
#define attributes 18


// Struct para fazer um "split" enquanto lee o CSV
typedef struct{
    char stringSplited[attributes][auxStringTam];
} Split;


// Struct para o LocalDate
typedef struct{
    int day;
    int month;
    int year;
} LocalDate;


// Struct principal
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
    LocalDate dateOfBirth;
    int yearOfBirth;
    char eyeColour[auxStringTam];
    char gender[auxStringTam];
    char hairColour[auxStringTam];
    char wizard[auxStringTam];
} Personagem;

// -------------------------------------------------------------------------------------------------------------------------

                            // Declaração das funções criadas

// Ordenação
void swap(Personagem *x, Personagem *y);

// Busca
Personagem linearIdSearch(const Personagem personagem[], char* id);

// Expecificas de Personagem
void printPersonagem(const Personagem* personagem);
LocalDate stringDateFormat(char* aux);

// Leitura de arquivo
void readCsv(Personagem personagens[], char* fileToRead);

// Funções para tempo de execução e comparações
void startTimer();
void endTimer();
void createLog(char* matricula);

// Auxiliares
void stringBooleanFormat(char* aux);
int stringIntFormat(const char* aux);
void removeStringChar(char* aux, char c);

// -------------------------------------------------------------------------------------------------------------------------

                            // Main

int main(){
    startTimer();

    Personagem personagens[structList];

    readCsv(personagens, "/tmp/characters.csv");

    endTimer();
    createLog("761670");

    return 0;
}

// -------------------------------------------------------------------------------------------------------------------------

                            // Funções para busca de personagem

// Função para busca linear por ID
Personagem linearIdSearch(const Personagem personagens[], char* id){
    Personagem resp;
    strcpy(resp.id, "-1");

    for ( int i = 0; i < structList; i++){
        if ( strcmp(personagens[i].id, id) == 0 ){
            countComp();

            resp = personagens[i];
            break;
        }
    }

    return resp;
}

// -------------------------------------------------------------------------------------------------------------------------

                             // Funções para ordenação

// Função auxiliar para trocar 2 personagens de lugar
void swap(Personagem *x, Personagem *y){
    Personagem aux = *x;
    *x = *y;
    *y = aux;
}

// -------------------------------------------------------------------------------------------------------------------------

                            // Funções auxiliares expecifica para Personagem

// Função para printar todos os atributos de um personagem expecifico
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
            personagem->dateOfBirth.day,
            personagem->dateOfBirth.month,
            personagem->dateOfBirth.year,
            personagem->yearOfBirth,
            personagem->eyeColour,
            personagem->gender,
            personagem->hairColour,
            personagem->wizard);
}

// -------------------------------------------------------------------------------------------------------------------------

                        // Função para ler o CSV e armazenar na struct principal

void readCsv(Personagem personagens[], char* fileToRead){
    char aux[lineMax];
    Split split;

    FILE* arq; 
    arq = fopen(fileToRead, "r");
    if ( arq == NULL ) {
        printf("Erro ao abrir arquivo");
        return;
    }

    fgets(aux, sizeof(aux), arq);

    // Ler uma linha do CSV separando os atributos em cada posição do split, e depois ir setando na struct pricipal, formatando quando necessário
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

        stringBooleanFormat(split.stringSplited[8]);
        strcpy(personagens[k].hogwartsStudent, split.stringSplited[8]);

        strcpy(personagens[k].actorName, split.stringSplited[9]);

        stringBooleanFormat(split.stringSplited[10]);
        strcpy(personagens[k].alive, split.stringSplited[10]);

        strcpy(personagens[k].alternateActors, split.stringSplited[11]);

        personagens[k].dateOfBirth = stringDateFormat(split.stringSplited[12]);

        personagens[k].yearOfBirth = atoi(split.stringSplited[13]);

        strcpy(personagens[k].eyeColour, split.stringSplited[14]);

        strcpy(personagens[k].gender, split.stringSplited[15]);

        strcpy(personagens[k].hairColour, split.stringSplited[16]);

        stringBooleanFormat(split.stringSplited[17]);
        strcpy(personagens[k].wizard, split.stringSplited[17]);

        for ( int i = 0; i < attributes; i++ ) split.stringSplited[i][0] = 0;

        k++;
    }

    fclose(arq);
}

// -------------------------------------------------------------------------------------------------------------------------

                        // Funções auxiliares para formatar uma string expecifica

// Mudar de VERDADEIRO ou FALSO para true or false
void stringBooleanFormat(char* aux){
    aux[0] == 'V' ? strcpy(aux, "true") : strcpy(aux, "false");
}

// Passar uma string para int
int stringIntFormat(const char* aux){
    return atoi(aux);
}

// Passar uma string para LocalDate
LocalDate stringDateFormat(char* aux){
    LocalDate formated;

    sscanf(aux, "%d-%d-%d", &formated.day, &formated.month, &formated.year);
    
    return formated;
}

// Remover o char 'c' da string
void removeStringChar(char* aux, char c){
    int i = 0;
    int j = 0;
    while (aux[i] != '\0') {
        if (aux[i] != c) aux[j++] = aux[i];
        i++;
    }
    aux[j] = '\0';
}

// -------------------------------------------------------------------------------------------------------------------------

            // Funções para contar o tempo de execução do codigo, comparações, e criar um TXT de saida com as informações

// Iniciar cronometro
void startTimer() {
    startTime = clock();
}

// Encerrar cronometro
void endTimer() {
    endTime = clock();
}

// Somar 1 no comparador
void countComp() {
    totalComp++;
}

// Criar o TXT de saida
void createLog(char* matricula) {
    FILE* logArq;
    logArq = fopen(strcat(matricula, "_teste.txt"), "w");

    if (logArq == NULL) {
        printf("Erro ao criar o arquivo de log\n");
        return;
    }

    int totalTime = ((double)(endTime - startTime)) / CLOCKS_PER_SEC;

    fprintf(logArq, "%s\t%.2f\t%d\n", matricula, totalTime, totalComp);

    fclose(logArq);
}