package executor;


import java.util.LinkedList;

public class MyExecutorService {

    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> queue=new MyBlockingQueue<>(5);

        Thread cons1=new Thread(()->{
            while(true){
            try {

                System.out.println("consumer - 1 : "+queue.pop());
            } catch (Exception e) {
                System.out.println("cons - 1 interrupted");
                System.out.println(e.toString());
                return ;
            }}
        });
        Thread cons2=new Thread(()->{
            while(true){
                try {

                    System.out.println("consumer - 2: "+queue.pop());
                } catch (Exception e) {
                    System.out.println("cons - 2 interrupted");
                    System.out.println(e.toString());
                    return ;
                }}
        });

        Thread prod1=new Thread(()->{
            for(int i=0;i<5;i++){
                try {
                    queue.push(i);
                    System.out.println("producer th-1 :"+i);
                } catch (Exception e) {
                    throw new RuntimeException(e+"error in th- 1");
                }

            }
        });

        Thread prod2=new Thread(()->{
            for(int i=5;i<10;i++){
                try {
                    queue.push(i);
                    System.out.println("producer th-2 :"+i);
                } catch (Exception e) {
                    throw new RuntimeException(e+"error in th- 2");
                }

            }
        });

        Thread prod3=new Thread(()->{
            for(int i=10;i<15;i++){
                try {
                    queue.push(i);
                    System.out.println("producer th-3 :"+i);
                } catch (Exception e) {
                    throw new RuntimeException(e+"error in th- 3");
                }

            }
        });

        cons1.start();
        prod1.start();
        cons2.start();
        prod2.start();
        prod3.start();

        prod1.join();
        prod2.join();
        prod3.join();

        cons2.interrupt();
        cons1.interrupt();
        LinkedList<Integer> lst=queue.getList();
        System.out.println(lst);

    }

}
