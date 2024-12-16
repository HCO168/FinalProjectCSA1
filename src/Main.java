import java.util.ArrayList;

public class Main {
    public static World mainWorld=new World();
    public static void main(String[] args) throws InterruptedException {
        int deltaMillisecondTime=1;
        double runningMillisecondTime=120*1000;
        int n_Execute= (int) (runningMillisecondTime/deltaMillisecondTime);
        World mainWorld=new World();
        Entity.defaultWorld=mainWorld;
        Thread updater=new Thread(()->mainWorld.updateAllRepetitively(deltaMillisecondTime,n_Execute));//get help from outside resources for multithreading
        updater.start();
        Food hamburger=new Food("hamburger",1,0.1,1000,10,50000);
        Food fries=new Food("fries",1,0.1,2000,10,100000);
        Player player=new Player("Man","Ke","",Gender.Male,15,100,74,100);
        player.attackPower=100;//with gun
        player.attackPenetration=100;
        player.attackResistance=10;
        Creature bear=new Creature("Bear",0,200,200,600,50,20,20,null);
        Thread.sleep(100);
        player.attack(bear);
        player.attack(bear);
        bear.attack(player);
        player.attack(bear);
        player.eat(hamburger);
        player.eat(fries);

    }
    public static void output(String str){
        System.out.println(str+"  -"+mainWorld.getRuntime()+" ms");
    }
}
