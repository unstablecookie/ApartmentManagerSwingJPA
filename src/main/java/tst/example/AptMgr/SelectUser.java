package tst.example.AptMgr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.List;
import java.util.ArrayList;

public class SelectUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	DefaultTableModel model;
	private MainFrame mainFrame;
	private UserManager userManager;

	/**
	 * Create the dialog.
	 */
	public SelectUser(MainFrame mF) {
		this.mainFrame = mF;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			model = new DefaultTableModel();
			table = new JTable(model);
			
			model.addColumn("id");
			model.addColumn("first name");
			model.addColumn("last name");
			model.addColumn("user name");
			
			userManager = new  UserManager();
			List<User> users = userManager.listUsers();
			populateTable(users,model);
			
			
			contentPanel.add(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Select");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						int[] rows = table.getSelectedRows();
						Long id  = Long.valueOf(String.valueOf(model.getValueAt(rows[0],0)));
						User user = userManager.getUser(id);
						mainFrame.userSelected(user);
						dispose();
					}
				});
				okButton.setActionCommand("Select");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void populateTable(List<User> list,DefaultTableModel model) {
		for(User u: list) {
			String[] arr = new String[4];
			arr[0] = String.valueOf(u.getId());
			arr[1] = String.valueOf(u.getFirstName());
			arr[2] = String.valueOf(u.getLastName());
			arr[3] = String.valueOf(u.getUserName());
			model.addRow(arr);
		}
	}
	

}
