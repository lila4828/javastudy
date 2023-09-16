class SharedObject {
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
    private int[] data = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private SharedObject buffer;

    public  Sender(SharedObject buffer) {
        this.buffer = buffer;
    }
    public void run() {
        for(int i=0;i<10;i++) {
            buffer.put(data[i]);
            System.out.println("생산자: "+i+"번 케익을 생산하였습니다.");
            try {
                Thread.sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {
            }
        }
    }
}
class Receiver implements Runnable {
    private SharedObject buffer;

    public  Receiver(SharedObject drop) {
        this.buffer = drop;
    }

    public void run() {
        for(int i=0;i<10;i++) {
            int data = buffer.take();
            System.out.println("소비자: "+data+"번 케익을 소비하였습니다.");
            try {
                Thread.sleep((int)(Math.random()*100));
            } catch (InterruptedException e) {
            }
        }
    }
}


public class ProducerConsumerTest {
    public static void main(String[] args) {
        SharedObject buffer = new SharedObject();
        (new Thread(new Sender(buffer))).start();
        (new Thread(new Receiver(buffer))).start();
    }
}
