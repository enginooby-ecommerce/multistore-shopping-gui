package test;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class TableWithColors {
    protected static JTable createTable() {
        Object[][] rows = new Object[][] {{1.23d},{-20.5d},{5.87d},{2.23d},{-7.8d},{-8.99d},{9d},{16.25d},{4.23d},{-26.22d},{-14.14d}};
        Object[] cols = new Object[]{"Balance"};
        JTable t = new JTable(rows,cols) {
            @Override
            public Class<?> getColumnClass(int column) {
                if(convertColumnIndexToModel(column)==0) return Double.class;
                return super.getColumnClass(column);
            }
        };
        t.setDefaultRenderer(Double.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                c.setForeground(((Double) value)>0 ? Color.BLUE : Color.RED);
                return c;
            }
        });
        return t;
    }

    private static JFrame createFrame() {
        JFrame f = new JFrame("Table with colors");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.add(new JScrollPane(createTable()),BorderLayout.CENTER);
        f.setSize(new Dimension(60,255));
        return f;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createFrame().setVisible(true);
            }
        });
    }
}