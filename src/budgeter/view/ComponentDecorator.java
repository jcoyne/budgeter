/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package budgeter.view;

import java.awt.Component;
import javax.swing.UIManager;

/**
 *
 * @author jcoyne
 */
public class ComponentDecorator {

    public static void setColor(Component comp, boolean isSelected) {
        if (isSelected) {
            comp.setBackground(UIManager.getColor("Table.selectionBackground"));
            comp.setForeground(UIManager.getColor("Table.selectionForeground"));
        } else {
            comp.setBackground(UIManager.getColor("Table.focusCellBackground"));
            comp.setForeground(UIManager.getColor("Table.foreground"));

        }
    }

}
