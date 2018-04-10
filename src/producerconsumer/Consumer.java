
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Consumer extends Thread {
    Buffer buffer;
    int id;
    long milliseconds;
    GUI gui;
    SyncModel consumedModel;
    SyncModel toConsumeModel;
    
    Consumer(Buffer buffer, int id, long milliseconds, GUI gui) {
        this.buffer = buffer;
        this.id = id;
        this.milliseconds = milliseconds;
        this.gui = gui;
        this.consumedModel = (SyncModel) gui.getConsumedTable().getModel();
        this.toConsumeModel = (SyncModel) gui.getToConsumeTable().getModel();
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        Item productItem;
        String result;
        String[] tokens;
        
        while(true) {
            productItem = this.buffer.consume();
            String product = productItem.operation;
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
            this.consumedModel.insertRow(0, new Object[]{productItem.producedBy, this.id, "(" + product + ")", result});
            
            try {
                Thread.sleep(this.milliseconds);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
