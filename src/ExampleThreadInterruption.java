import java.util.Random;

public class ExampleThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 1_000_000_000; i++) {
                    // если другой поток хочет прервать данный поток, то поток прерывается
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("Thread was interrupted");
                        break;
                    }
                    System.out.println(Math.sin(random.nextDouble()));
                }
            }
        });
        System.out.println("Starting thread");
        thread.start();

        // прерывание потока назначено через 1 секунду после старта работающего потока
        Thread.sleep(1000);
        thread.interrupt();

        thread.join();
        System.out.println("Thread has finished");
    }
}
