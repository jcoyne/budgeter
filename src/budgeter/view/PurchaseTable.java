/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.view;

import budgeter.view.editors.CategoryEditor;
import budgeter.view.models.PurchaseTableModel;
import budgeter.view.renderers.MoneyRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author jcoyne
 */
public class PurchaseTable extends JTable {

    JPopupMenu popupMenu = new JPopupMenu();

    public PurchaseTable() {
        JMenuItem menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(new DeleteRowsActionAdapter(this));
        popupMenu.add(menuItem);

        MouseListener popupListener = new PopupListener();
        this.addMouseListener(popupListener);

        setModel(new PurchaseTableModel());

        // Align the amount right
        TableColumn purchaseAmount = this.getColumnModel().getColumn(PurchaseTableModel.cols.get("Amount"));
        purchaseAmount.setCellRenderer(new MoneyRenderer());

        TableColumn purchaseCategory = this.getColumnModel().getColumn(PurchaseTableModel.cols.get("Account"));
        purchaseCategory.setCellEditor(CategoryEditor.getCategoryEditor());



    }


    class PopupListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }
        private void showPopup(MouseEvent e) {
          if (e.isPopupTrigger()) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
          }
        }

    }

    class DeleteRowsActionAdapter implements ActionListener {
        public DeleteRowsActionAdapter(JTable t) {

        }

        public void actionPerformed(ActionEvent arg0) {
            throw new UnsupportedOperationException("Not supported yet.");
        }


    }
}
