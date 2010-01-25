/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.view.editors;

import budgeter.view.models.AccountListModel;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author jcoyne
 */
public class CategoryEditor extends DefaultCellEditor implements TableCellEditor {
    // This is the component that will handle the editing of the cell value

    // This method is called when a cell value is edited by the user.

    public static CategoryEditor getCategoryEditor() {
        AccountListModel acctListModel = new AccountListModel();
        return new CategoryEditor(new JComboBox(acctListModel));
    }

    public CategoryEditor(JComboBox arg0) {
        super(arg0);
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
        // 'value' is value contained in the cell located at (rowIndex, vColIndex)
        if (isSelected) { // cell (and perhaps other cells) are selected
        }
        // Configure the component with the specified value
        //acctListModel.setSelectedItem(value);
        ((JComboBox) editorComponent).getModel().setSelectedItem(value);
        // Return the configured component
        return editorComponent;
    }
/*
    // This method is called when editing is completed.
    // It must return the new value to be stored in the cell.
    public Object getCellEditorValue() {
        return ((JComboBox)component).getSelectedItem();
    }
 */

}
