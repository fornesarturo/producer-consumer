/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author miguel
 */
public class SyncModel extends DefaultTableModel{
    
    @Override
    synchronized public Object getValueAt(int row, int column) {
        try {
            return super.getValueAt(row, column);
        }
        catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }
    
    @Override
    synchronized public void addRow(Object[] rowData) {
        super.addRow(rowData);
    }
    
    @Override
    synchronized public void insertRow(int row, Object[] rowData) {
        super.insertRow(row, rowData);
    }
    
    @Override
    synchronized public void removeRow(int row) {
        super.removeRow(row);
    }
    
}
