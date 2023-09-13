public class test {
    static class MyThread extends Thread {
        private int[] array = new int[50];
        private int sum = 0;
        private int start, end;

        public MyThread(int start, int end) {
            this.start = start;
            this.end = end;

            for (int i = 0; i < 50; i++) {
                array[i] = i+start;
            }
        }

        public void run() {
            for (int i = 0; i < 50; i++) {
                sum += array[i];
            }
        }

        public int getResult() {
            return sum;
        }
    }

    public static void main(String[] args) {
        int sum1, sum2;

        test.MyThread T1 = new MyThread(0, 50);
        test.MyThread T2 = new MyThread(50, 100);

        T1.start();
        try {
            T1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T2.start();
        try {
            T2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sum1 = T1.getResult();
        sum2 = T2.getResult();
        System.out.print("Thread 1: "+sum1);
        System.out.print("Thread 2: "+sum2);
        System.out.print("Thread result: "+(sum1+sum2));
    }
}
