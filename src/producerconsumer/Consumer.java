
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
        String product, result;
        String[] tokens;
        
        for(int i=0 ; i<5 ; i++) {
            product = this.buffer.consume();
            //System.out.println("Consumer consumed: " + product);
            product = product.substring(1, product.length() - 1);
            tokens = product.split(" ");
            //for (String token:tokens) {
            //    System.out.print(token + " ");
            //}
            //System.out.println();
            try {
                switch(tokens[0]) {
                    case "*":
                        result = String.valueOf(Integer.valueOf(tokens[1]) * Integer.valueOf(tokens[2]));
                        break;
                    case "/":
                        result = String.valueOf(Integer.valueOf(tokens[1]) / Integer.valueOf(tokens[2]));
                        break;
                    case "+":
                        result = String.valueOf(Integer.valueOf(tokens[1]) + Integer.valueOf(tokens[2]));
                        break;
                    case "-":
                        result = String.valueOf(Integer.valueOf(tokens[1]) - Integer.valueOf(tokens[2]));
                        break;
                    default:
                        result = "Undefined";
                        break; 
                }
            } catch(ArithmeticException ae) {
                result = "Undefined";
            }
            Buffer.print("Consumer["+ this.id +"] consumed: (" + product + ") resulting in = " + result);
            try {
                Thread.sleep(this.seconds * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
