import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Player extends Person{
    public Scanner inputor=new Scanner(System.in);
    public Player(String firstName, String lastName, String middleName, Gender gender, double age, double health, double dryWeight, double energy) {
        super(firstName, lastName, middleName, gender, age, health, dryWeight, energy);
    }
    public static Class getClassType(String className){
        className=className.toLowerCase(Locale.ROOT);
        switch (className){
            case "creature":return Creature.class;
            case "entity":return Entity.class;
            case "food":return Food.class;
            case "item":return Item.class;
            case "npc":return NPC.class;
            case "person":return Person.class;
            case "player":return Player.class;
            default:return null;
        }
    }
    public void control(){
        String command="";
        String[] commandParts;
        while(!command.equalsIgnoreCase("stop")){
            command=inputor.nextLine();
            commandParts=command.split("\\s+");
            if(commandParts.length==0){
                continue;
            }
            if(commandParts[0].equals("/get")){
                if(commandParts.length<=1){
                    continue;
                }
                Class type=getClassType(commandParts[1]);
                if(type!=null){
                    ArrayList<? extends Entity> entitiesAL=Main.mainWorld.getEntities(type);
                    System.out.println(Main.mainWorld.getEntities(Entity.class));
                    if(entitiesAL.isEmpty()){
                        Main.output("there is no "+type.getSimpleName());
                    }
                    Main.output(entitiesToString(entitiesAL));
                }else{
                    Main.output(commandParts[1]+"is not an valid class!");
                }
            }
        }
    }
    public static <T extends Entity> String entitiesToString(ArrayList<T> entitiesAL){
        String result="";
        for(Entity entity:entitiesAL){
            result+=entity.describe()+",";
        }
        return result;
    }
    
}
