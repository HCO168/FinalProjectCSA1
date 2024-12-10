public class Creature extends Entity{
    double age;
    double health;
    double weight;
    double attackResistance;
    double energy;
    double attackPower;
    double attackPenetration;
    public Creature(String name,double age,double health,double weight,double energy,double attackResistance,double attackPower,double attackPenetration){
        super(name,"Creature");
        this.age=age;
        this.health=health;
        this.weight=weight;
        this.energy=energy;
        this.attackResistance=attackResistance;
        this.attackPower=attackPower;
        this.attackPenetration=attackPenetration;
    }
    public Creature(String name,double age,double health,double weight,double energy){
        super(name,"Creature");
        this.age=age;
        this.health=health;
        this.weight=weight;
        this.energy=energy;
    }
    void beAttacked(Creature creature){

    }
    void attack(Creature creature){

    }
    void eat(Food food){

    }
    void die(){

    }
    Item[] drop(){
        return null;
    }
    Item excrete(){
        return null;
    }
}
