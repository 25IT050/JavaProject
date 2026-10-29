class MyThread extends Thread{
    static int counter;
    @Override 
    public void run(){
        for(int i=0;i<10;i++){
            counter++;
        }
    }
}
public class Main91{
    public static void main(String[] args)throws Exception{
        MyThread T1 = new MyThread();
        MyThread T2 = new MyThread();
        MyThread T3 = new MyThread();

        T1.start();
        T2.start();
        T3.start();

        T1.join();
        T2.join();
        T3.join();

        System.out.println(MyThread.counter);
    }
}