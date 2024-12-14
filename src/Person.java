public class Person extends Creature{
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    public Person(String firstName,String lastName,String middleName,Gender gender,double age,double health,double dryWeight,double energy){
        super(nameString(firstName,middleName,lastName)+lastName,age,health,dryWeight,energy);
        this.firstName=firstName;
        this.lastName=lastName;
        this.middleName=middleName;
        this.gender=gender;
    }
    public String name(){
        return this.name;
    }
    public void name(String name){
        this.name=name;
    }
    public String firstName() {
        return firstName;
    }
    public String lastName() {
        return lastName;
    }
    public String middleName(){
        return middleName;
    }
    public static String nameString(String firstName,String middleName,String lastName){
        return firstName+" "+((middleName.equals(""))?(""):(middleName+" "));
    }
    public void setName(String firstName,String middleName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
        this.middleName=middleName;
        this.name=nameString(firstName,middleName,lastName);
    }
    public Gender gender() {
        return gender;
    }
}
