import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Entity {
    private static long currentUid=0;
    public String name;
    public String type;
    private long uid;
    private boolean isUpdating=false;
    private World world;
    public static World defaultWorld=null;
    private void register(){
        this.uid=currentUid++;
        if(defaultWorld!=null){
            this.setWorld(defaultWorld);
        }
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
    public final void setWorld(World newWorld){
        if(this.world!=null){
            world.removeEntity(this);
        }
        world=newWorld;
        world.addEntity(this);
    }
    public final World world(){
        return world;
    }
    protected final void stopUpdate(){
        isUpdating=false;
    }
    protected final void startUpdate(){
        isUpdating=true;
    }
    public final boolean isUpdating(){
        return isUpdating;
    }
    public String describe(){
        return toString();
    }
}
