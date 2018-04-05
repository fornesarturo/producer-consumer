
package producerconsumer;

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
        long consumerMillis = gui.getConsumerMillis();
        long producerMillis = gui.getProducerMillis();
        
        Buffer buffer = new Buffer();
        
        Producer producer = new Producer(buffer);
        producer.start();
        
        for (int i = 0; i < 3; i++) {
            new Consumer(buffer, i, i + 5).start();
        }
    }
}
