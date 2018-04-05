
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    int minValue, maxValue;
    long milliseconds;
    
    Producer(Buffer buffer, int minValue, int maxValue, long milliseconds) {
        this.buffer = buffer;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.milliseconds = milliseconds;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String symbols = "*/+-";
        Random r = new Random(System.currentTimeMillis());
        String product;
        char symbol;
        int a, b;
        for(int i=0 ; i<500 ; i++) {
            product = "";
            symbol = symbols.charAt(r.nextInt(4));
            
            a = r.nextInt(this.maxValue + 1 - this.minValue) + this.minValue;
            b = r.nextInt(this.maxValue + 1 - this.minValue) + this.minValue;
            
            product += "(" + symbol + " " + String.valueOf(a) + " " + String.valueOf(b) + ")";
            this.buffer.produce(product);
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
