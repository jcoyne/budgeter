/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.view.models;

import budgeter.domain.Account;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import budgeter.domain.Purchase;

/**
 *
 * @author jcoyne
 */
public class PurchaseTableModel extends AbstractTableModel implements TableModel {
    private List<Purchase> purchases;

    private DateFormat date = DateFormat.getDateInstance(SimpleDateFormat.SHORT);

    
    private static HashMap<Integer, String> colNames =
            new HashMap<Integer, String>(){{
                put(0, "Date");
                put(1, "Note");
                put(2, "Account");
                put(3, "Amount");
    }};

    public static HashMap<String, Integer> cols = reverse(colNames);

    private static HashMap<String, Integer> reverse(HashMap<Integer,String> in) {
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        for (Integer k : in.keySet()) {
            out.put(in.get(k), k);
        }
        return out;
    }

    public PurchaseTableModel() { 
        purchases = Purchase.getPurchases();
    }

    // Run after an new line is created.
    @Override
    public void fireTableDataChanged() {
        System.out.println("Table data changed");
        purchases = Purchase.getPurchases();
        super.fireTableDataChanged();
    }



    @Override
    public boolean isCellEditable(int row, int col)
        { return true; }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Purchase purchase = purchases.get(row);
        if (col == cols.get("Date")) {
            try {
              purchase.setDate(DateFormat.getDateInstance(SimpleDateFormat.SHORT).parse((String)value));
            } catch (ParseException e) {}
        } else if (col == cols.get("Note"))
            purchase.setNote((String) value);
        else if (col == cols.get("Account"))
            if (value instanceof String)
                System.out.println("Value is a string but it should be an Account ("+value+")");
            else
                purchase.setAccount((Account) value);
        else if (col == cols.get("Amount")) {
            try {
                purchase.setAmount(new BigDecimal((String) value));
            } catch (NumberFormatException e) { }
        }
        Purchase.update(purchase);
        fireTableCellUpdated(row, col);
    }


    @Override
    public String getColumnName(int arg0) {
        return colNames.get(arg0);
    }

    public int getColumnCount() {
        return colNames.size();
    }

    public int getRowCount() {
        return purchases.size();
    }

    public Object getValueAt(int arg0, int arg1) {
        Purchase purch = purchases.get(arg0);
        if (arg1 == 0 ) {
            return date.format(purch.getDate());
        } else if (arg1 == 1) {
            return purch.getNote();
        } else if (arg1 == 2) {
            return purch.getAccount().getName();
        } else if (arg1 == 3) {
            return purch.getAmount();
        } else {
                throw new RuntimeException();
        }
    }

}
