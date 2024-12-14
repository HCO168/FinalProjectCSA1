import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Entity {
    private static long currentUid=0;
    public String name;
    public String type;
    private long uid;
    private boolean isUpdating=false;
    private void register(){
        this.uid=currentUid++;
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
            entitiesToBeRemovedList.add(this);
        }
    }
    protected final void startUpdate(){
        if(!isUpdating){
            isUpdating=true;
            entitiesToBeAddedList.add(this);
        }
    }
    private static final ArrayList<Entity> allEntitiesAL=new ArrayList<>();
    private static final List<Entity> entitiesToBeAddedList= Collections.synchronizedList(new ArrayList<>());//get outside help
    private static final List<Entity> entitiesToBeRemovedList= Collections.synchronizedList(new ArrayList<>());
    private static long firstMillisecondTime=System.currentTimeMillis();
    private static long lastMillisecondTime=firstMillisecondTime;
    private static long deltaMilliSeconds=0;
    public static long getDeltaMilliSeconds(){
        return deltaMilliSeconds;
    }
    public static void updateAll() {
        long currentMillisecond=System.currentTimeMillis();
        deltaMilliSeconds=currentMillisecond-lastMillisecondTime;
        lastMillisecondTime=currentMillisecond;
        allEntitiesAL.addAll(entitiesToBeAddedList);
        entitiesToBeAddedList.clear();
        for(Entity entity:entitiesToBeRemovedList){
            allEntitiesAL.remove(entity);
        }
        entitiesToBeRemovedList.clear();
        for (Entity entity:allEntitiesAL) {
            entity.update();
        }
    }
    private static boolean isUpdatingAll=false;
    public static void updateAllRepetitively(int deltaMilliSecondsTime, int n_Execute){
        isUpdatingAll=true;
        lastMillisecondTime=System.currentTimeMillis();
        try {
            Thread.sleep(deltaMilliSecondsTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread lastThread=new Thread(Entity::updateAll);
        for(int i=0;i<n_Execute;i++){
            try {
                Thread.sleep(deltaMilliSecondsTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(lastThread.isAlive()){
                continue;
            }
            if(!isUpdatingAll){
                Main.output("Stop updating manually: "+deltaMilliSecondsTime+" ms * "+i+" times has executed, plan to execute "+n_Execute+" times in total.");
                return;
            }
            lastThread=new Thread(Entity::updateAll);
            lastThread.start();
        }
        Main.output("Stop updating: "+deltaMilliSecondsTime+" ms * "+n_Execute+" complete executed.");
    }
    public static void stopUpdatingAll(){
        isUpdatingAll=false;
    }
    public static long getRuntime(){
        return System.currentTimeMillis()-firstMillisecondTime;
    }
}
