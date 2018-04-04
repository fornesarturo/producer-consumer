
package producerconsumer;

public class ProducerConsumer {

    public static void main(String[] args) {
        
        Buffer buffer = new Buffer();
        
        Producer producer = new Producer(buffer);
        producer.start();
        
        for (int i = 0; i < 3; i++) {
            new Consumer(buffer, i, i + 5).start();
        }
    }
}
