package producerconsumer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author miguel
 */
public class Item {
    public int producedBy;
    public String operation;
    
    public Item(int producedBy, String operation) {
        this.producedBy = producedBy;
        this.operation = operation;
    }
}
