/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budgeter.view.models;

import budgeter.domain.Account;
import budgeter.domain.Purchase;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author jcoyne
 */
public class SummaryTableModel extends AbstractTableModel implements TableModel {

    private List<Account> accounts;
    private static HashMap<Integer, String> colNames =
            new HashMap<Integer, String>() {

                {
                    put(0, "Account");
                    put(1, "Amount");
                }
            };
    public static HashMap<String, Integer> cols = reverse(colNames);

    private static HashMap<String, Integer> reverse(HashMap<Integer,String> in) {
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        for (Integer k : in.keySet()) {
            out.put(in.get(k), k);
        }
        return out;
    }

    public SummaryTableModel() {
        accounts = Account.getAccounts();
    }

    @Override
    public void fireTableDataChanged() {
        accounts = Account.getAccounts();
        super.fireTableDataChanged();
    }

    public int getColumnCount() {
        return colNames.keySet().size();
    }

    public int getRowCount() {
        return accounts.size();
    }

    @Override
    public String getColumnName(int arg0) {
        return colNames.get(arg0);
    }

    public Object getValueAt(int arg0, int arg1) {
        Account account = accounts.get(arg0);
        if (arg1 == 0) {
            return account.getName();
        } else if (arg1 == 1) {
            return Purchase.sum(account.getPurchases());
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
