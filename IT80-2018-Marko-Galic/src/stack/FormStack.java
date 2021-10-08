package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
//import stack.DlgStack;
//import stack.FormStack;

public class FormStack extends JFrame {
	
	DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormStack frame = new FormStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FormStack() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblstack = new JLabel("Stack");
		pnlNorth.add(lblstack);
		
		JPanel pnlCentar = new JPanel();
		contentPane.add(pnlCentar, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		pnlCentar.add(scrollPane);
		
		JList<Rectangle> lstRectangle = new JList();
		scrollPane.setViewportView(lstRectangle);
		lstRectangle.setModel(dlm);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true); 
				if(dlgStack.confirmation)
				{
					try {
						int x = Integer.parseInt(dlgStack.getTxtX().getText()); 
						int y = Integer.parseInt(dlgStack.getTxtY().getText());
						int width = Integer.parseInt(dlgStack.getTxtWidth().getText());
						int height = Integer.parseInt(dlgStack.getTxtHeight().getText());
						
						Rectangle rct = new Rectangle(new Point(x,y), width, height); 
						
						dlm.add(0, rct);
						
					}
					 catch(Exception NumberFormatException) {
						 
						 JOptionPane.showMessageDialog(null,"Please, insert values!");
						 
					 }
					
				}
				
			}
		});
		pnlSouth.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dlm.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "List is empty!");
				}
				else {
					DlgStack dlg = new DlgStack();
					Point p= dlm.getElementAt(0).getUpperLeftPoint(); 
					int width = dlm.getElementAt(0).getSirina();
					int height = dlm.getElementAt(0).getVisina();
					
					dlg.getTxtX().setText("" + Integer.toString(p.getX()));
					dlg.getTxtY().setText("" + Integer.toString(p.getY()));
					dlg.getTxtWidth().setText("" + Integer.toString(width));
					dlg.getTxtHeight().setText("" + Integer.toString(height));
					
					dlg.setVisible(true);
					
					if(dlg.confirmation)
					{
						dlm.remove(0); 
					}
						
					
				}
			}
		});
		pnlSouth.add(btnDelete);
	}

}
