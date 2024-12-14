import javax.swing.*;
import java.util.ArrayList;

public class World extends JPanel {
    public final ArrayList<Entity> worldEntities=new ArrayList<>();
    public boolean addEntity(Entity entity){
        return worldEntities.add(entity);
    }
    public boolean removeEntity(Entity entity){
        return worldEntities.remove(entity);
    }
    public <T extends Entity> T getEntity(Class<T> type,String name){
        for(Entity entity:worldEntities){
            if(entity.name().equals(name)&&entity.getClass().equals(type)){
                return (T) entity;
            }
        }
        return null;
    }
    public <T extends Entity> T getEntity(long uid){
        for(Entity entity:worldEntities){
            if(entity.uid()==uid){
                return (T) entity;
            }
        }
        return null;
    }
    public <T extends Entity> T[] getEntities(Class<T> type){
        ArrayList<T> resultAL=
        for(Entity entity:worldEntities){
            if(entity.getClass().equals(type)){
                return (T) entity;
            }
        }
        return null;
    }
}
