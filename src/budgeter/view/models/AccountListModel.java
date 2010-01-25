/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.view.models;

import java.util.List;
import budgeter.domain.Account;

/**
 *
 * @author jcoyne
 */
public class AccountListModel extends javax.swing.DefaultComboBoxModel{
    private List<Account> accounts = Account.getAccounts();

    public AccountListModel() {
    }
    
    public void refresh() {
        accounts = Account.getAccounts();
//        if (summary != null)
//            summary.fireTableDataChanged();
        fireContentsChanged(this, 0, accounts.size());
    }


    @Override
    public int getSize() { return accounts.size(); }

    @Override
    public Object getElementAt(int i) { return accounts.get(i); }
}
