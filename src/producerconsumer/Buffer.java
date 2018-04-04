
package producerconsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private char buffer;
    private Queue<Character> bufferQ;
    
    Buffer() {
        this.buffer = 0;
        this.bufferQ = new LinkedList<>();
    }
    
    synchronized char consume() {
        char product = 0;
        
        while(true) {
            if(this.bufferQ.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                break;
            }
        }
        product = this.bufferQ.poll();
        notifyAll();
        
        return product;
    }
    
    synchronized void produce(char product) {
        if(this.bufferQ.size() >= 4) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.bufferQ.offer(product);
        
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
