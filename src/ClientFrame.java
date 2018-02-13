import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class ClientFrame extends JFrame
{
	public JTextArea area;
	public JTextField field;
	JButton send;
	JPanel areaPanel, fieldPanel;
	JScrollPane pane;
	DefaultCaret caret;
	String randomName = "";
	
	public ClientFrame()
	{
		CreateStuff();
		add(fieldPanel, BorderLayout.SOUTH);
		add(areaPanel, BorderLayout.CENTER);
	}
	
	public void CreateStuff()
	{
		area = new JTextArea();
		field = new JTextField();
		send = new JButton("Send");
		areaPanel = new JPanel();
		fieldPanel = new JPanel(new FlowLayout());
		pane = new JScrollPane(area);
		caret = (DefaultCaret)area.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		area.setLineWrap(true);
		area.setPreferredSize(new Dimension(370, 250));
		area.setMaximumSize(new Dimension(370,250));
		area.setRows(15);
		area.setEditable(false);
		area.setWrapStyleWord(true);
		field.setPreferredSize(new Dimension(300, 50));
		send.setPreferredSize(new Dimension(70,50));
		send.addActionListener(new ButtonListener());
		areaPanel.add(pane);
		fieldPanel.add(field);		
		fieldPanel.add(send);
	}
	
	public class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			randomName = field.getText();
			ChatClient2.getMessage = true;
		}
	}
	
	public String GetMessage()
	{
		return randomName;
	}
}
