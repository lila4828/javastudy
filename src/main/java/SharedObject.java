public class SharedObject {
    private int data;
    private boolean empty = true;
    private int i = 0;

    public synchronized int take() {
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e){
            }
        }
        empty = true;
        notifyAll();
        return data;
    }

    public synchronized void put(int data) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = false;
        this.data = data;
        notifyAll();
    }
}

class Sender implements Runnable {
    private int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private SharedObject buffer;

    public  Sender(SharedObject buffer) {
        this.buffer = buffer;
    }
    public void run() {
        for(int i=0;i<10;i++) {
            buffer.put(data[i]);
            try {
                Thread.sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {
            }
        }
    }
}
class Receiver implements Runnable {
    private SharedObject buffer;
    private int sum = 0;

    public  Receiver(SharedObject drop) {
        this.buffer = drop;
    }

    public void run() {
        for(int i=0;i<10;i++) {
            int data = buffer.take();
            System.out.println("MESSAGE RECEIVED: "+data);
            sum+=data;
            try {
                Thread.sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Sum: "+sum);
    }

    public static void main(String[] args) {
        SharedObject buffer = new SharedObject();
        (new Thread(new Sender(buffer))).start();
        (new Thread(new Receiver(buffer))).start();
    }
}
