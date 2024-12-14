public class Main {
    public static void main(String[] args){
        int deltaMillisecondTime=1;
        double runningMillisecondTime=120*1000;
        int n_Execute= (int) (runningMillisecondTime/deltaMillisecondTime);
        Thread updater=new Thread(()->Entity.updateAllRepetitively(deltaMillisecondTime,n_Execute));//get help from outside resources for multithreading
        updater.start();
        Food hamburger=new Food("hamburger",1,0.1,1000,10,5000);
        Food fries=new Food("fries",1,0.1,2000,10,10000);
        System.out.println(Creature.calculateDamage(100,100,50));

    }
    public static void output(String str){
        System.out.println(str+"  -"+Entity.getRuntime()+" ms");
    }
}
