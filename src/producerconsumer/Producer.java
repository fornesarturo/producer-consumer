
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Producer extends Thread {
    Buffer buffer;
    int minValue, maxValue, id;
    long milliseconds;
    GUI gui;
    javax.swing.table.DefaultTableModel jTableModel;
    
    Producer(Buffer buffer, int minValue, int maxValue, long milliseconds, GUI gui, int id) {
        this.buffer = buffer;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.milliseconds = milliseconds;
        this.gui = gui;
        this.id = id;
        this.jTableModel = (DefaultTableModel) gui.getToConsumeTable().getModel();
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String symbols = "*/+-";
        Random r = new Random(System.currentTimeMillis());
        String product;
        char symbol;
        int a, b;
        //for(int i=0 ; i<500 ; i++) {
        while(true) {
            product = "";
            symbol = symbols.charAt(r.nextInt(4));
            
            a = r.nextInt(this.maxValue + 1 - this.minValue) + this.minValue;
            b = r.nextInt(this.maxValue + 1 - this.minValue) + this.minValue;
            
            product += "(" + symbol + " " + String.valueOf(a) + " " + String.valueOf(b) + ")";
            this.buffer.produce(new Item(this.id, product));
            //System.out.println("Producer produced: " + product);
            Buffer.print("Producer produced: " + product);
            
            this.jTableModel.addRow(new Object[]{this.id, product});
            
            try {
                Thread.sleep(this.milliseconds);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
