
package producerconsumer;

import java.util.Random;

public class ProducerConsumer {

    public static void main(String[] args) {
        
        Buffer buffer = new Buffer();
        
        Producer producer = new Producer(buffer, 0, 10);
        producer.start();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 3; i++) {
            new Consumer(buffer, i, r.nextInt(5)).start();
        }
    }
}
