package o4;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] data;
    
    public TableModel (String[] columnNames_par, Object[][] data_par) {
    	this.columnNames = columnNames_par;
    	this.data = data_par;
    	
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

}
