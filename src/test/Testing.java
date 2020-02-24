package test;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Testing extends JFrame
{
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JSplitPane spt = new JSplitPane(JSplitPane.VERTICAL_SPLIT,panel1,null);
  JToggleButton btn = new JToggleButton("Show/Hide panel2");
  public Testing()
  {
    setLocation(400,300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    panel1.setPreferredSize(new Dimension(100,100));
    panel2.setPreferredSize(new Dimension(100,100));
    panel1.add(new JLabel("Panel 1"));
    panel2.add(new JTextField("Panel 2"));
    getContentPane().add(spt,BorderLayout.CENTER);
    getContentPane().add(btn,BorderLayout.SOUTH);
    pack();
    btn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent ae){
        if(btn.isSelected())
        {
          spt.setBottomComponent(panel2);
          pack();
        }
        else
          spt.setBottomComponent(null);
          pack();
        }});
  }
  public static void main(String[] args){new Testing().setVisible(true);}
}