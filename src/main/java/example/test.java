package example;


public class test implements Runnable{

    private static int counter = 0;
    public static void print() {
        System.out.println("Heloo!!!!");
    }

    public static void print2() {
        /*for(int i =0; i<=10; i++){
            System.out.println(i + "Hello");
        }*/
        int thread_num = test.counter;
        while(true) {
            System.out.println("Thread #" + thread_num + " says hello");
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    public void run() {
        test.counter ++;
        print();
        print2();
    }
}
