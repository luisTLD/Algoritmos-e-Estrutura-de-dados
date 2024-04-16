import java.time.format.DateTimeFormatter;
// import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        List<Personagem> personagens = ReadCsv.readAllFile("/tmp/characters.csv");

        questao01(personagens, sc);
        
        sc.close();
    }

    
    static Personagem searchPessoaId(final List<Personagem> personagens, Scanner sc, String id){
        Personagem search = new Personagem();
        search = null;
        
        for ( Personagem pessoa : personagens){
            if ( pessoa.getId().equals(id)){
                search = pessoa;
                break;
            }
        }
        
        return search;
    }
    
    static void questao01(final List<Personagem> personagens, Scanner sc){
        String input = new String();
        Personagem resp = new Personagem();

        while(!(input = sc.nextLine()).equals("FIM")){
            resp = searchPessoaId(personagens, sc, input);

            if ( resp != null ) resp.printPessoa();
            else System.out.println("Id nao encontrado");
        }
    }
}

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
        //this.hogwartsStaff = false;
        //this.hogwartsStudent = false;
        this.actorName = aux[9];
        this.alive = stringBooleanFormat(aux[10]);
        //this.alive = false;
        this.alternateActors = new ArrayList<>();
        alternateActors.addAll(Arrays.asList(stringArrayFormat(aux[11])));
        this.dateOfBirth = stringLocalDateFormat(aux[12]);
        this.yearOfBirth = Integer.parseInt(aux[13]);
        this.eyeColour = aux[14];
        this.gender = aux[15];
        this.hairColour = aux[16];
        this.wizard = stringBooleanFormat(aux[17]);
        //this.wizard = false;
    }

    public Personagem(){}

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

    public void printPessoa() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateOfBirthFormat = dateOfBirth.format(formatter);

        System.out.println("[" + id + " ## "  + name + " ## " + arrayToStringFormat(alternateNames) + " ## " + house + " ## "
        + ancestry + " ## " + species + " ## " + patronus + " ## " + hogwartsStaff + " ## " + hogwartsStudent + " ## " 
        + actorName + " ## " + alive + " ## " + dateOfBirthFormat + " ## "
        + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour + " ## " + wizard + "]");
    }

    static String arrayToStringFormat(List<String> aux){
        String[] array = aux.toArray(new String[aux.size()]);
        String str = Arrays.toString(array);

        str = str.replace("[", "{");
        str = str.replace("]", "}");

        return str;
    }
    
    static String[] stringArrayFormat(String aux){
        aux = aux.replace("[", "");
        aux = aux.replace("]", "");
        aux = aux.replace("'","");

        String[] auxArray = aux.split(",");

        for ( int i = 0; i < auxArray.length; i++ ){
            auxArray[i] = auxArray[i].trim();
        }

        return auxArray;
    }

    static boolean stringBooleanFormat(final String aux){
        return aux.equals("VERDADEIRO") ? true : false;
    }

    static LocalDate stringLocalDateFormat(final String aux){
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


    static String[] lineArrayFormat(String aux, final String c){
        String[] auxArray = aux.split(c);
        return auxArray;
    }

}