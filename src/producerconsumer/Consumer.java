
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int id;
    int seconds;
    
    Consumer(Buffer buffer, int id, int seconds) {
        this.buffer = buffer;
        this.id = id;
        this.seconds = seconds;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        char product;
        
        for(int i=0 ; i<5 ; i++) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            Buffer.print("Consumer"+ this.id +"consumed: " + product );
            try {
                Thread.sleep(this.seconds * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
