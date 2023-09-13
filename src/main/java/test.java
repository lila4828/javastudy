public class test {
        class MyThread extends Thread {
            private int[] arr = new int[50];
            private int sum = 0;
            private int start, end;

            public MyThread(int[] arr, int start, int end) {
                this.arr = arr;
                this.start = start;
                this.end = end;
            }

            public void run() {
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
            }

            public int getResult() {
                return sum;
            }
        }

    public void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        int sum1, sum2;

        MyThread T1 = new MyThread(array, 0, 50);
        MyThread T2 = new MyThread(array, 50, 100);

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
