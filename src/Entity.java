public class Entity {
    public String name;
    public String type;
    public long uid;
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
    public void update(){

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
}
