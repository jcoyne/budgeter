/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.view.renderers;

import budgeter.view.ComponentDecorator;
import java.awt.Component;
import java.text.NumberFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jcoyne
 */
public class MoneyRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rowIndex, int vColIndex) {
        NumberFormat right = NumberFormat.getCurrencyInstance();
        setText(right.format(value));
        setHorizontalAlignment(RIGHT);

        ComponentDecorator.setColor(this, isSelected);

        return this;
    }

}
