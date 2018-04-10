
package producerconsumer;

import java.util.Random;

public class ProducerConsumer {
    

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        
        GUI gui = new GUI();
        gui.setVisible(true);
        javax.swing.JButton startButton = gui.getStartButton();
        
        startButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            startProgram(gui);
        });
        
        
    }
    
    public static void startProgram(GUI gui) {
        int minRange = gui.getMinRange();
        int maxRange = gui.getMaxRange();
        int consumerQty = gui.getConsumerQty();
        int producerQty = gui.getProducerQty();
        int bufferSize = gui.getBufferSize();
        long consumerMillis = gui.getConsumerMillis();
        long producerMillis = gui.getProducerMillis();
        
        SyncModel consumedModel = new SyncModel();
        consumedModel.addColumn("Produced By");
        consumedModel.addColumn("Consumed By");
        consumedModel.addColumn("Operation");
        consumedModel.addColumn("Result");
        gui.getConsumedTable().setModel(consumedModel);
        
        SyncModel toConsumeModel = new SyncModel();
        toConsumeModel.addColumn("Produced By");
        toConsumeModel.addColumn("Operation");
        gui.getToConsumeTable().setModel(toConsumeModel);
        
        
        Buffer buffer = new Buffer(bufferSize, gui);
        
        for(int i = 0; i < producerQty; i++) {
            new Producer(buffer, minRange, maxRange, producerMillis, gui, i).start();
        }
        
        for (int i = 0; i < consumerQty; i++) {
            new Consumer(buffer, i, consumerMillis, gui).start();
        }
    }
}
