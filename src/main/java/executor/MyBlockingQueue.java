package executor;

import java.util.LinkedList;

public class MyBlockingQueue<T> {
    private final LinkedList<T> queue;
    private final int capacity;

    public MyBlockingQueue(int capacity){
        this.queue=new LinkedList<>();
        this.capacity=capacity;
    }

    synchronized public T pop() throws Exception {
        if(capacity<=0){throw new Exception("queue capacity - "+capacity+"; pop error !!!");}
        while(queue.isEmpty()){
            this.wait();
        }
        if(queue.size()==capacity){
            this.notifyAll();
        }
        return queue.poll();
    }

    synchronized public void push(T obj) throws Exception {
        if(capacity<=0){throw new Exception("queue capacity - "+capacity+"; push error !!!");}
        if(queue.isEmpty()){
            queue.add(obj);
            this.notifyAll();
        }
        else{
            while(queue.size()==capacity){
                this.wait();
            }
            queue.add(obj);
        }
    }

    synchronized  public LinkedList<T> getList(){
        return this.queue;
    }
}
