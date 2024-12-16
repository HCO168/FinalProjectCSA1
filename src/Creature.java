import java.util.ArrayList;

public class Creature extends Entity{
    public double age;
    public double health;
    public double dryWeight;
    public double wetWeight=0;
    public double energy;
    public double attackResistance;
    public double energyConsumeRate;
    public double attackPower;
    public double attackPenetration;
    protected ArrayList<Item> inventory=new ArrayList<>();
    public Creature(String name,double age,double health,double dryWeight,double energy,double attackResistance,double attackPower,double attackPenetration,ArrayList<Item> inventory){
        super(name,"Creature");
        this.startUpdate();
        this.age=age;
        this.health=health;
        this.dryWeight =dryWeight;
        this.energy=energy;
        this.attackResistance=attackResistance;
        this.attackPower=attackPower;
        this.attackPenetration=attackPenetration;
        if(energy==0){
            this.energyConsumeRate=0;
        }
        if(inventory!=null){
            this.inventory.addAll(inventory);
        }
    }
    public Creature(String name,double age,double health,double dryWeight,double energy){
        super(name,"Creature");
        this.startUpdate();
        this.age=age;
        this.health=health;
        this.dryWeight =dryWeight;
        this.energy=energy;
        this.attackResistance=0;
        this.attackPower=10;
        this.attackPenetration=0;
        if(energy==0){
            this.energyConsumeRate=0;
        }else{
            this.energyConsumeRate=0.001;
        }
    }
    public void beAttacked(Creature creature){
        double damage=calculateDamage(creature.attackPower,creature.attackResistance,creature.attackPenetration);
        health-=damage;
        if(health<=0){
            health=0;
        }
        Main.output(creature.name()+" hurt "+this.name()+" "+damage+"*hp, now have "+this.health+" hp");
        if(this.health<=0){
            Main.output(creature.name()+" killed "+this.name());
            this.die();
        }
    }
    public void attack(Creature creature){
        creature.beAttacked(this);
    }
    public void eat(Food food){
        energy+=food.energy;
        health+=food.healthRecover;
        wetWeight+=food.weight;
        Main.output(name+" eat some "+food.name+", gain "+food.energy+" uE, now have "+energy+" uE, gain "+food.healthRecover+" hp, now have "+health+" hp");
        food.remove();
    }
    public Item[] die(){
        inventory.add(new Item("corpse",1,dryWeight));
        inventory.add(new Item("excretion",1,wetWeight));
        this.stopUpdate();
        Item[] result=inventory.toArray(new Item[0]);
        inventory.clear();
        return result;
    }
    public Item[] drop(){
        Item[] result=inventory.toArray(new Item[0]);
        inventory.clear();
        return result;
    }
    public Item excrete(){
        Item item=new Item("excretion",1,wetWeight);
        wetWeight=0;
        return item;
    }
    public static double energyConsumePerMS=0.01;
    public void update(){
        energy-=energyConsumeRate*world().getDeltaMilliSeconds();
        if(energy<0){
            this.die();
        }
    }
    private static final double C_zeroBehavior=1;
    private static final double C_resistanceFacter=1;
    public static double calculateDamage(double attackPower,double attackResistance,double attackPenetration){
        return attackPower*(attackPenetration+C_zeroBehavior)/
                (attackPenetration+C_zeroBehavior+C_resistanceFacter*attackResistance);//restrictive decrease when attackResistence increase.
    }
}
