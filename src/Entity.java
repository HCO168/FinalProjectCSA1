import java.util.ArrayList;

public class Entity {
    public String name;
    public String type;
    private final long uid;
    private static long currentUid=0;
    public Entity(){
        this.name="unnamed";
        this.type="Entity";
        this.uid=currentUid++;
    }
    public Entity(String name,String type){
        this.name=name;
        this.type=type;
        this.uid=currentUid++;
    }
    public void name(String name){
        this.name=name;
    }
    public String name(){
        return name;
    }
    public String type(){
        return type;
    }
    public long uid(){
        return uid;
    }
    public String toString(){
        return "Entity{name="+name+",type="+type+",uid="+uid+"}";
    }
    public void update(){

    }


    private static ArrayList<Entity> allEntitiesAL;
    private static long lastMillisecondTime=System.currentTimeMillis();
    private static long deltaMilliSeconds=0;
    public static long getDeltaMilliSeconds(){
        return deltaMilliSeconds;
    }
    public static void updateAll() {
        long currentMillisecond=System.currentTimeMillis();
        deltaMilliSeconds=currentMillisecond-lastMillisecondTime;
        lastMillisecondTime=currentMillisecond;
        for (Entity entity:allEntitiesAL) {
            entity.update();
        }
    }
}
