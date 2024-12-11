public class Main {
    public static void main(String[] args){
        int deltaMillisecondTime=100;
        double runningMillisecondTime=120*1000;
        int n_Execute= (int) (runningMillisecondTime/deltaMillisecondTime);
        Thread updater=new Thread(()->Entity.updateAllRepetitively(deltaMillisecondTime,n_Execute));//get help from outside resources for multithreading
        updater.start();
        Food food=new Food("Hamburger",1,0.1,1000,10,10000);
    }
    public static void output(String str){
        System.out.println(str);
    }
}
