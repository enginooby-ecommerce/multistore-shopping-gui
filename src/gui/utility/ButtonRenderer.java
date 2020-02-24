package gui.utility;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import asystem.Setup;
  
/**
 * @version 1.0 11/09/98
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
  
  public ButtonRenderer() {
    setOpaque(true);

  }
   
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
    	
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());

    } else{
    //text in button
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
      //setBackground(Setup.colorPage);
      setForeground(Setup.colorPageText);
      setFont(new Font(Setup.font, Font.BOLD, 11));
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}