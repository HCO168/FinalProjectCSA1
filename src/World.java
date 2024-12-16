import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;

public class World extends JPanel {
    public final List<Entity> worldEntities=Collections.synchronizedList(new ArrayList<>());
    public void addEntity(Entity entity){
        entitiesToBeRemovedSet.remove(entity);
        entitiesToBeAddedSet.add(entity);
        worldEntities.add(entity);
    }
    public void removeEntity(Entity entity){
        entitiesToBeAddedSet.remove(entity);
        entitiesToBeRemovedSet.add(entity);
        worldEntities.remove(entity);
    }
    public <T extends Entity> T getEntity(Class<T> type,String name){
        for(Entity entity:worldEntities){
            if(entity.name().equals(name)&&type.isInstance(entity)){
                return type.cast(entity);
            }
        }
        return null;
    }
    public <T extends Entity> T getEntity(Class<T> type,long uid){
        for(Entity entity:worldEntities){
            if(entity.uid()==uid&&type.isInstance(entity)){
                return type.cast(entity);
            }
        }
        return null;
    }
    public Entity getEntity(long uid){
        for(Entity entity:worldEntities){
            if(entity.uid()==uid){
                return entity;
            }
        }
        return null;
    }
    public <T extends Entity> ArrayList<T> getEntities(Class<T> type){
        ArrayList<T> resultAL=new ArrayList<>();
        for(Entity entity:worldEntities){
            if(type.isInstance(entity)){
                resultAL.add(type.cast(entity));
            }
        }
        return resultAL;
    }




    private final List<Entity> allEntitiesAL=Collections.synchronizedList(new ArrayList<>());
    private final Set<Entity> entitiesToBeAddedSet = Collections.synchronizedSet(new HashSet<>());//get outside help
    private final Set<Entity> entitiesToBeRemovedSet = Collections.synchronizedSet(new HashSet<>());
    private long firstMillisecondTime=System.currentTimeMillis();
    private long lastMillisecondTime=firstMillisecondTime;
    private long deltaMilliSeconds=0;
    public long getDeltaMilliSeconds(){
        return deltaMilliSeconds;
    }
    public void updateAll() {
        long currentMillisecond=System.currentTimeMillis();
        deltaMilliSeconds=currentMillisecond-lastMillisecondTime;
        lastMillisecondTime=currentMillisecond;
        allEntitiesAL.addAll(entitiesToBeAddedSet);
        entitiesToBeAddedSet.clear();
        for(Entity entity: entitiesToBeRemovedSet){
            allEntitiesAL.remove(entity);
        }
        entitiesToBeRemovedSet.clear();
        for (Entity entity:allEntitiesAL) {
            if(entity.isUpdating()){
                entity.update();
            }
        }
    }
    private boolean isUpdatingAll=false;
    public void updateAllRepetitively(int deltaMilliSecondsTime, int n_Execute){
        isUpdatingAll=true;
        lastMillisecondTime=System.currentTimeMillis();
        try {
            Thread.sleep(deltaMilliSecondsTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread lastThread=new Thread(this::updateAll);
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
            lastThread=new Thread(this::updateAll);
            lastThread.start();
        }
        Main.output("Stop updating: "+deltaMilliSecondsTime+" ms * "+n_Execute+" complete executed.");
    }
    public void stopUpdatingAll(){
        isUpdatingAll=false;
    }
    public long getRuntime(){
        return System.currentTimeMillis()-firstMillisecondTime;
    }
}
