
package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    // private char buffer;
    private final Queue<Item> bufferQ;
    private final int maxSize;
    SyncModel toConsumeModel;
    javax.swing.JProgressBar progressBar;
    javax.swing.JLabel counter;
    
    
    Buffer(int maxSize, GUI gui) {
        // this.buffer = 0;
        this.maxSize = maxSize;
        this.bufferQ = new LinkedList<>();
        this.toConsumeModel = (SyncModel) gui.getToConsumeTable().getModel();
        this.progressBar = gui.getProgressBar();
        this.counter = gui.getCounter();
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
        double percentage = ((double) this.bufferQ.size()/this.maxSize)*100;
        this.progressBar.setValue((int) percentage);
        this.counter.setText(String.valueOf(Integer.parseInt(this.counter.getText()) +1));
        this.toConsumeModel.removeRow(0);
        return product;
    }
    
    synchronized void produce(Item product) {
        while(true) {
            if(this.bufferQ.size() >= this.maxSize) {
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
        this.bufferQ.offer(product);
        this.toConsumeModel.addRow(new Object[]{product.producedBy, product.operation});
        double percentage = ((double) this.bufferQ.size()/this.maxSize)*100;
        this.progressBar.setValue((int) percentage);
        notifyAll();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
