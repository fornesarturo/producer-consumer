
package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Buffer {
    
    // private char buffer;
    private Queue<Item> bufferQ;
    private int maxSize;
    javax.swing.table.DefaultTableModel toConsumeModel;
    
    Buffer(int maxSize, GUI gui) {
        // this.buffer = 0;
        this.maxSize = maxSize;
        this.bufferQ = new LinkedList<>();
        this.toConsumeModel = (DefaultTableModel) gui.getToConsumeTable().getModel();
    }
    
    synchronized Item consume() {
        Item product;
        
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
    
    synchronized void produce(Item product) {
        if(this.bufferQ.size() >= this.maxSize) {
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
