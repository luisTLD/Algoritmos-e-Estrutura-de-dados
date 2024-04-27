import java.time.format.DateTimeFormatter;
// import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;


// ------------------------------------------------------------------------------------------------------------------------------------


public class Main{
    public static void main(String[] args){
        MyLog.startTimer();

        Scanner sc = new Scanner(System.in);
        // String input = new String();

        List<Personagem> personagens = ReadCsv.readAllFile("/tmp/characters.csv");
        List<Personagem> using = new ArrayList<>();

        using = Personagem.subList(personagens, sc);
        using = SortList.quickSort(using, 10);
        Personagem.printAll(using);


        MyLog.endTimer();
        MyLog.createLog("761670");
        sc.close();
    }

}


// ------------------------------------------------------------------------------------------------------------------------------------


// Classe Personagem
class Personagem{
    private String id;
    private String name;
    private List<String> alternateNames;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff; 
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private List<String> alternateActors;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    public Personagem(String[] aux) {
        this.id = aux[0];
        this.name = aux[1];
        this.alternateNames = new ArrayList<>();
        alternateNames.addAll(Arrays.asList(stringArrayFormat(aux[2])));
        this.house = aux[3];
        this.ancestry = aux[4];
        this.species = aux[5];
        this.patronus = aux[6];
        this.hogwartsStaff = stringBooleanFormat(aux[7]);
        this.hogwartsStudent = stringBooleanFormat(aux[8]);
        this.actorName = aux[9];
        this.alive = stringBooleanFormat(aux[10]);
        this.alternateActors = new ArrayList<>();
        alternateActors.addAll(Arrays.asList(stringArrayFormat(aux[11])));
        this.dateOfBirth = stringLocalDateFormat(aux[12]);
        this.yearOfBirth = Integer.parseInt(aux[13]);
        this.eyeColour = aux[14];
        this.gender = aux[15];
        this.hairColour = aux[16];
        this.wizard = stringBooleanFormat(aux[17]);
    }

    public Personagem(){}

    // Set's
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAlternateNames(List<String> alternateNames) {this.alternateNames = alternateNames;}
    public void setHouse(String house) {this.house = house;}
    public void setAncestry(String ancestry) {this.ancestry = ancestry;}
    public void setSpecies(String species) {this.species = species;}
    public void setPatronus(String patronus) {this.patronus = patronus;}
    public void setHogwartsStaff(boolean hogwartsStaff) {this.hogwartsStaff = hogwartsStaff;}
    public void setHogwartsStudent(boolean hogwartsStudent) {this.hogwartsStudent = hogwartsStudent;}
    public void setActorName(String actorName) {this.actorName = actorName;}
    public void setAlive(boolean alive) {this.alive = alive;}
    public void setAlternateActors(List<String> alternateActors) {this.alternateActors = alternateActors;}
    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}
    public void setYearOfBirth(int yearOfBirth) {this.yearOfBirth = yearOfBirth;}
    public void setEyeColour(String eyeColour) {this.eyeColour = eyeColour;}
    public void setGender(String gender) {this.gender = gender;}
    public void setHairColour(String hairColour) {this.hairColour = hairColour;}
    public void setWizard(boolean wizard) {this.wizard = wizard;}

    // Get's
    public String getId() {return id;}
    public String getName() {return name;}
    public List<String> getAlternateNames() {return alternateNames;}
    public String getHouse() {return house;}
    public String getAncestry() {return ancestry;}
    public String getSpecies() {return species;}
    public String getPatronus() {return patronus;}
    public boolean isHogwartsStaff() {return hogwartsStaff;}
    public boolean getHogwartsStudent() {return hogwartsStudent;}
    public String getActorName() {return actorName;}
    public boolean isAlive() {return alive;}
    public List<String> getAlternateActors() {return alternateActors;}
    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public int getYearOfBirth() {return yearOfBirth;}
    public String getEyeColour() {return eyeColour;}
    public String getGender() {return gender;}
    public String getHairColour() {return hairColour;}
    public boolean isWizard() {return wizard;}

    // Retornar um clone
    public static Personagem clonePersonagem(Personagem x){
        Personagem clone = new Personagem();

        clone.setId(x.getId());
        clone.setName(x.getName());
        clone.setAlternateNames(new ArrayList<>(x.getAlternateNames()));
        clone.setHouse(x.getHouse());
        clone.setAncestry(x.getAncestry());
        clone.setSpecies(x.getSpecies());
        clone.setPatronus(x.getPatronus());
        clone.setHogwartsStaff(x.isHogwartsStaff());
        clone.setHogwartsStudent(x.getHogwartsStudent());
        clone.setActorName(x.getActorName());
        clone.setAlive(x.isAlive());
        clone.setAlternateActors(new ArrayList<>(x.getAlternateActors()));
        clone.setDateOfBirth(x.getDateOfBirth());
        clone.setYearOfBirth(x.getYearOfBirth());
        clone.setEyeColour(x.getEyeColour());
        clone.setGender(x.getGender());
        clone.setHairColour(x.getHairColour());
        clone.setWizard(x.isWizard());
    
        return clone;
    }

    // Função para printar todos os atributos 
    public void printPessoa() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateOfBirthFormat = dateOfBirth.format(formatter);

        System.out.println("[" + id + " ## "  + name + " ## " + arrayToStringFormat(alternateNames) + " ## "
        + house + " ## " + ancestry + " ## " + species + " ## " + patronus + " ## " + hogwartsStaff + " ## " 
        + hogwartsStudent + " ## " + actorName + " ## " + alive + " ## " + dateOfBirthFormat + " ## " 
        + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour + " ## " + wizard + "]");
    }

    // Função auxiliar para printar todos os atributos de todas os personagens de uma lista
    static void printAll(final List<Personagem> personagens){
        for ( Personagem pessoa : personagens ) pessoa.printPessoa();
    }
    
    // Função auxiliar para o exercico, que retorna uma Lista expecifica por ID's necessarios
    static List<Personagem> subList(final List<Personagem> personagens, Scanner sc){
        String input = new String();
        List<Personagem> aux = new ArrayList<>(); 

        while (!(input = sc.nextLine()).equals("FIM")){
            int i = SearchList.linearIdSearch(personagens, input);

            if (i != -1) aux.add(personagens.get(i));
            else System.out.print("ID " + input + " não encontrado");
        }

        return aux;
    }
 
    // Função para converter um Array em uma string com dentre de { }
    private String arrayToStringFormat(List<String> aux){
        String[] array = aux.toArray(new String[aux.size()]);
        String str = Arrays.toString(array);

        str = str.replace("[", "{");
        str = str.replace("]", "}");

        return str;
    }
    
    // Função para converter uma String em um Array, delimitado por ','
    private String[] stringArrayFormat(String aux){
        aux = aux.replace("[", "");
        aux = aux.replace("]", "");
        aux = aux.replace("'","");

        String[] auxArray = aux.split(",");

        for ( int i = 0; i < auxArray.length; i++ ){
            auxArray[i] = auxArray[i].trim();
        }

        return auxArray;
    }

    // Função para converter uma String em Boolean
    private boolean stringBooleanFormat(final String aux){
        return aux.equals("VERDADEIRO") ? true : false;
    }

    // Função para converter uma String em um LocalDate
    private LocalDate stringLocalDateFormat(final String aux){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            LocalDate data = LocalDate.parse(aux, formatter);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}


// ------------------------------------------------------------------------------------------------------------------------------------


// Classe com funções para ordenação
class SortList{

    // Função para ordenar a lista por nome, utilizando metodo SelectionSort, int totalSort = N elementos ordenados
    static List<Personagem> selectionSortName(final List<Personagem> personagens, int totalSort){
        List<Personagem> auxCopy = new ArrayList<>(personagens);
        int max = auxCopy.size();

        for ( int i = 0; i < totalSort; i++ ){
            int lower = i;

            for ( int j = i + 1; j < max; j++ ){
                if (compareName(auxCopy.get(lower), auxCopy.get(j)) > 0 ) lower = j;
            }

            swap(auxCopy, i, lower);
        }

        return auxCopy.subList(0, totalSort);
    }


    // Função para ordenar a lista por DateOfBirth, utilizando InsertionSort
    static List<Personagem> insertionSortDateOfBirth(final List<Personagem> personagens){
        List<Personagem> auxCopy = new ArrayList<>(personagens);
        Personagem temp = new Personagem();
        int max = auxCopy.size();

        for ( int i = 1; i < max; i++ ){
            temp = auxCopy.get(i);
            int j = i - 1;

            while ( (j >= 0) && (compareDateOfBirth(auxCopy.get(j), temp) > 0)){
                auxCopy.set(j + 1, auxCopy.get(j));
                j--;
            }

            auxCopy.set(j + 1, temp);
        }

        return auxCopy;
    }

    // Função para ordenar a lista por House, utilizando QuickSort
    static List<Personagem> quickSort(List<Personagem> personagens, int totalSort) {
        List<Personagem> auxCopy = new ArrayList<>(personagens);

        quickSortCall(auxCopy, 0, personagens.size() - 1, totalSort);

        return auxCopy.subList(0, totalSort);
    }

    static void quickSortCall(List<Personagem> personagens, int left, int right, int totalSort) {
        int i = left;
        int j = right;
        Personagem piv = personagens.get((left + right) / 2);

        while (j >= i) {
            while (compareHouse(personagens.get(i), piv) < 0) i++;
            while (compareHouse(piv, personagens.get(j)) < 0) j--; 

            if (j >= i) {
                swap(personagens, i, j);
                i++;
                j--;
            }

            if (left < j) quickSortCall(personagens, left, j, totalSort);
            if (totalSort > i) quickSortCall(personagens, i, right, totalSort);
        }
    }


    // Função para ordenar a listar por YearOfBirth, utilizandou CountingSort
    static List<Personagem> countingSortYearOfBirth(final List<Personagem> personagens){
        int length = personagens.size();

        // Maior elemento da List
        int maxYear = 0;
        for ( Personagem pessoa : personagens ){
            if ( pessoa.getYearOfBirth() > maxYear ) maxYear = pessoa.getYearOfBirth();
        }
        
        // Inicializar o array de contagem
        int[] count = new int[maxYear + 1];

        // Contar as ocorrências de cada ano de nascimento
        for (Personagem pessoa : personagens) count[pessoa.getYearOfBirth()]++;
      
        // Atualizar o array de contagem para conter o número de elementos menores ou iguais a i
        for (int i = 1; i <= maxYear; i++) count[i] += count[i - 1];

        // Construir a lista ordenada
        List<Personagem> auxCopy = new ArrayList<>(personagens);
        for (int i = length - 1; i >= 0; i--) {
            int yearOfBirth = personagens.get(i).getYearOfBirth();
            auxCopy.set(count[yearOfBirth] - 1, personagens.get(i));
            count[yearOfBirth]--;
        }

        // Passar pela Lista novamente para desempatar por nome onde o Year estava igual
        List<Personagem> newSortSubList = new ArrayList<>();
        int index = 0;
        int atualYear = auxCopy.get(0).getYearOfBirth();
        boolean reSort = false;

        // Procurar intervalos com Year repetido, reordendar esses intervalos e voltar para Lista Principal
        for (int i = 1; i < length; i++) {
            if ( auxCopy.get(i).getYearOfBirth() == atualYear ) {
                if ( !reSort ) {
                    index = i - 1; 
                    reSort = true;
                }
            }
            
            // Pegar o intervalo, reordenar e adicionar
            if ( reSort && (auxCopy.get(i).getYearOfBirth() != atualYear)) {
                newSortSubList = auxCopy.subList(index, i);
                newSortSubList = selectionSortName(newSortSubList, newSortSubList.size());
        
                for (int j = index; j < i; j++) {
                     auxCopy.set(j, newSortSubList.get(j - index));
                }

                reSort = false;
            }

            atualYear = auxCopy.get(i).getYearOfBirth();
        }

        // Tratar caso tenha uma sequencia repetida ate o ultimo elemento
        if ( reSort ){
            newSortSubList = auxCopy.subList(index, length);
            newSortSubList = selectionSortName(newSortSubList, newSortSubList.size());
        
            for (int j = index; j < length; j++) {
                auxCopy.set(j, newSortSubList.get(j - index));
            }
        }

        return auxCopy;
    }


    // Função para ordenar a lista por HairColour, utilizando HeapSort
    static List<Personagem> heapSortHairColour(final List<Personagem> personagens){
        List<Personagem> auxCopy = new ArrayList<>(personagens);
        int length = auxCopy.size();

        for ( int max = 1; max < length; max++ ){

            // Construir Heap, se o filho for maior que o pai, troca e continua verificando com o proximo pai
            for ( int i = max; max > 0 && compareHairColour(auxCopy.get(i), auxCopy.get((i - 1) / 2)) > 0; i = (i - 1) / 2){
                swap(auxCopy, i, (i - 1) / 2);
            }
        }

        // Ordenação, taca o primiero pro final, e constroi o Heap novamente, ate ordenar
        int endIndex = length - 1;
        while ( endIndex > 0 ){
            swap(auxCopy, 0, endIndex);
            endIndex--;

            // Reconstruir o Heap
            int aux = 0; 

            // Entrar em Loop enquando aux tiver filho
            while((aux * 2) + 1 <= endIndex){
                // Obter o maior filho de aux
                int son = 0;

                // Ver se so tem um filho, ou, comparar os 2 para retornar o maior
                if ( (2 * aux) + 1 == endIndex ) son = endIndex;
                else if ( compareHairColour(auxCopy.get((2 * aux) + 1), auxCopy.get((2 * aux) + 2)) > 0 ) son = ((2 * aux) + 1);
                else son = ((2 * aux) + 2);

                // Verificar se o filho é maior que o novo pai, para fazer a troca
                if ( compareHairColour(auxCopy.get(son), auxCopy.get(aux)) > 0 ){
                    swap(auxCopy, son, aux);

                    // aux ser igual a son, para que verifique se agora ele é filho de algum novo pai menor que ele
                    aux = son;
                } else aux = endIndex; // Sair do loop
            }
        }

        return auxCopy;
    }


    // Função para chamar o MergeSort
    static List<Personagem> mergeSortActorName(List<Personagem> personagens){
        List<Personagem> auxCopy = new ArrayList<>(personagens);
    
        mergeSortActorNameCall(auxCopy, 0, auxCopy.size() - 1);
    
        return auxCopy;
    }

    // Função para ordenar ActorName utilizando MergeSort
    static void mergeSortActorNameCall(final List<Personagem> personagens, int left, int right){
        if ( left < right ){
            int middle = (left + right) / 2;

            mergeSortActorNameCall(personagens, left, middle);
            mergeSortActorNameCall(personagens, middle + 1, right);
            mergeSortActorNameAction(personagens, left, middle, right);
        }
    }

    // Fazer o merge entre os arrays
        static private void mergeSortActorNameAction(List<Personagem> personagens, int left, int middle, int right) {
        int nLeft = (middle - left) + 1;
        int nRight = right - middle;

        Personagem[] arrayLeft = new Personagem[nLeft + 1];
        Personagem[] arrayRight = new Personagem[nRight + 1];

        for (int i = 0; i < nLeft; i++) {
            arrayLeft[i] = personagens.get(left + i);
        }
        for (int j = 0; j < nRight; j++) {
            arrayRight[j] = personagens.get(middle + j + 1);
        }

        // Sentinela
        arrayLeft[nLeft] = new Personagem();
        arrayRight[nRight] = new Personagem();

        int i = 0;
        int j = 0;

        for (int k = left; k <= right; k++) {
            if ((arrayRight[j].getActorName() == null || (arrayLeft[i].getActorName() != null) && compareActorName(arrayRight[j], arrayLeft[i]) >= 0)) {
                personagens.set(k, arrayLeft[i]);
                i++;
            } else {
                personagens.set(k, arrayRight[j]);
                j++;
            }
        }
    }



    // Função para inverter 2 posições
    static private void swap(List<Personagem> list, int i, int j) {
        Personagem aux = list.get(i);
        list.set(i, list.get(j));
        list.set(j, aux);
    }

    // Função que compara nome de Personagem x e y, retorna positivo se a x for "maior"...
    static private int compareName(Personagem x, Personagem y){
        MyLog.countComp();

        return x.getName().compareTo(y.getName());
    }

    // Função que compara DateOfBirth de Personagem x e y, retorna positivo se a x for "maior"... e desempatar pelo nome
    static private int compareDateOfBirth(Personagem x, Personagem y){
        MyLog.countComp();

        int aux = x.getDateOfBirth().compareTo(y.getDateOfBirth());

        if ( aux == 0 ) aux = compareName(x, y);

        return aux;
    }

    // Função que compara HairColour, retorna positivo se x for "maior", e desempata em name
    static private int compareHairColour(Personagem x, Personagem y){
        MyLog.countComp();

        int aux = x.getHairColour().compareTo(y.getHairColour());

        if ( aux == 0 ) aux = compareName(x, y);

        return aux;
    }

    // Função que compara ActorName, desempata em Name
    static private int compareActorName(Personagem x, Personagem y){
        MyLog.countComp();

        int aux = x.getActorName().compareTo(y.getActorName());

        if ( aux == 0 ) aux = compareName(x, y);

        return aux;
    }

    // Função que compara House e desempata em Name
    static int compareHouse(Personagem x, Personagem y) {
        MyLog.countComp();

        int aux = x.getHouse().compareTo(y.getHouse());

        if ( aux == 0 ) aux = compareName(x, y);

        return aux;
    }

}


// ------------------------------------------------------------------------------------------------------------------------------------


// Classe com funções para busca
class SearchList{

    // Função para retornar o index do personagem com ID, ou -1 caso não exista
    static int linearIdSearch(final List<Personagem> personagens, String id){
        int index = -1;
        int length = personagens.size();

        for ( int i = 0; i < length; i++ ){
            if ((personagens.get(i).getId()).equals(id)){
                index = i;
                i = length;

                MyLog.countComp();
            }
        }

        return index;
    }
    
    // Função para retornar o index do personagem com NAME, ou -1 caso não exista
    static int linearNameSearch(final List<Personagem> personagens, String name){
        int index = -1;
        int length = personagens.size();

        for ( int i = 0; i < length; i++ ){
            if ((personagens.get(i).getName()).equals(name)){
                index = i;
                i = length;

                MyLog.countComp();
            }
        }

        return index;
    }
}


// ------------------------------------------------------------------------------------------------------------------------------------


// Classe para ler linha por linha do CSV, instanciar um personagem, mandando a linha lida para ser formatada
class ReadCsv{
    static List<Personagem> readAllFile(final String fileToRead){
        List<Personagem> personagens = new ArrayList<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToRead));
            br.readLine();

            String csvLine = new String();

            while( (csvLine = br.readLine()) != null ){
                String[] csvArray = lineArrayFormat(csvLine, ";");
                Personagem pessoa = new Personagem(csvArray);
                personagens.add(pessoa);
            }

            br.close();
        }
        catch (Exception e){
            e.printStackTrace();
            personagens = null;
        }

        return personagens;
    }

    // Função para splitar uma String pelo char 'c'
    static String[] lineArrayFormat(String aux, final String c){
        String[] auxArray = aux.split(c);
        return auxArray;
    }

}


// ------------------------------------------------------------------------------------------------------------------------------------


// Classe para lidar com o tempo de execução do codigo e o numero de comparações
class MyLog{

    // Variaveis "globais"
    private static long startTime = 0;
    private static long endTime = 0;
    private static int totalComp = 0;

    // Função para regular comparações
    static void countComp(){
        totalComp++;
    }

    // Função para começar o cronometro
    static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    // Função para encerrar o cronometro
    static void endTimer() {
        endTime = System.currentTimeMillis();
    }

    // Função para calcular o tempo gasto
    static long getTime() {
        return endTime - startTime;
    }

    // Função para criar o txt contendo as informações de comparações e tempo
    static void createLog(final String matricula) {
        try {
            FileWriter logArq = new FileWriter(matricula + "_quicksortparcial.txt");
            logArq.write(matricula + "\t" + (endTime - startTime) + "\t" + totalComp);
            logArq.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar txt");
        }
    }
}
