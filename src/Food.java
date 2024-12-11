public class Food extends Item{
    public long spoilMilliseconds;
    public boolean canSpoil;
    public double energy;
    public double healthRecover;
    public Food(String name,int quantity,double weight,double energy,double healthRecover,long spoilMilliseconds){
        super(name,quantity,weight);
        this.energy=energy;
        this.healthRecover=healthRecover;
        this.spoilMilliseconds=spoilMilliseconds;
        canSpoil = spoilMilliseconds > 0;
        if(!canSpoil){
            stopUpdate();
        }
    }
    public void spoil(){
        stopUpdate();
        Main.output(name+" * "+weight+" unitWeight contain "+energy+" unitEnergy is spoiled.");
        this.name="spoiled food";
        this.energy=0;
        this.healthRecover=-10;
        this.canSpoil=false;
    }
    public void update(){
        spoilMilliseconds-=getDeltaMilliSeconds();
        if(spoilMilliseconds<=0){
            spoil();
        }
    }
}
