import java.util.ArrayList;

public class Entity {
    private static long currentUid=0;
    public String name;
    public String type;
    private long uid;
    private boolean isUpdating=true;
    private void register(){
        this.uid=currentUid++;
        allEntitiesAL.add(this);
    }
    public Entity(){
        this.name="unnamed";
        this.type="Entity";
        register();
    }
    public Entity(String name,String type){
        this.name=name;
        this.type=type;
        register();
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

    protected final void stopUpdate(){
        if(isUpdating){
            isUpdating=false;
            allEntitiesAL.remove(this);
        }
    }
    protected final void startUpdate(){
        if(!isUpdating){
            isUpdating=true;
            allEntitiesAL.add(this);
        }
    }
    private static ArrayList<Entity> allEntitiesAL=new ArrayList<>();
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
    public static void updateAllRepetitively(int deltaMilliSecondsTime, int n_Execute){
        lastMillisecondTime=System.currentTimeMillis();
        try {
            Thread.sleep(deltaMilliSecondsTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i=0;i<n_Execute;i++){
            try {
                Thread.sleep(deltaMilliSecondsTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateAll();
        }
    }
}
