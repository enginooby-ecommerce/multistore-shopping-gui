package gui.utility;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import asystem.Database;
import asystem.Setup;

public class Clock extends JPanel {

  public Clock() {
    //super("Timer Demo");
    setSize(100, 45);
    
    setBackground(Setup.colorTaskbar);
    


    this.add(new ClockLabel(), BorderLayout.NORTH);
  }

 
}
class ClockLabel extends JLabel implements ActionListener {
	DateFormat format = new SimpleDateFormat("hh:mm:ss");
  public ClockLabel() {
	  
	
	  //super("" + stringTime);
   // super("" + new Date());
	  
    Timer t = new Timer(1000, this);
    setForeground(Color.BLACK); 
    setFont(new Font(Setup.font, Font.BOLD, 15));
    t.start();
  }

  public void actionPerformed(ActionEvent ae) {
	  setText(format.format(new Date()));
	 
  }
}