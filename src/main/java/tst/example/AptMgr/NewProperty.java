package tst.example.AptMgr;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JComboBox;

import java.util.List;
import java.util.ArrayList;
import java.awt.CardLayout;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.TextArea;

import javax.swing.JFileChooser;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

public class NewProperty extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtArea;
	private JTextField txtBuildyear;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private UserManager userManager;
	
	private static final Logger logger = LoggerFactory.getLogger(NewProperty.class);
	private JButton btnNewButton;
	private JFileChooser fc;
	private byte[] photo;

	/**
	 * Create the dialog.
	 */
	public NewProperty() {
		
			
		setBounds(100, 100, 550, 144);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.EAST);
		{
			txtArea = new JTextField();
			txtArea.setHorizontalAlignment(SwingConstants.CENTER);
			txtArea.setText("area");
			txtArea.setColumns(10);
		}
		{
			txtBuildyear = new JTextField();
			txtBuildyear.setText("build(year)");
			txtBuildyear.setColumns(10);
		}
		{
			comboBox = new JComboBox(AptType.values());
		}
		{
			userManager = new UserManager();
			List<User> listUsers = userManager.listUsers();
			List<Long> list = new ArrayList<Long>();
			for(User u: listUsers) {
				list.add(u.getId());
			}
			comboBox_1 = new JComboBox(list.toArray());
		}
		fc = new JFileChooser();
		{
			btnNewButton = new JButton("Set image");
			btnNewButton.addActionListener(new ActionListener() {//TODO
				public void actionPerformed(ActionEvent e) {
					int returnVal = fc.showSaveDialog(NewProperty.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						try {
							photo = Files.readAllBytes(file.toPath());
						}catch(IOException ex) {
							logger.error("cannot load the file : "+ex);
						}
					}
				}
			});
		}
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPanel.add(txtArea);
		contentPanel.add(txtBuildyear);
		contentPanel.add(comboBox);
		contentPanel.add(comboBox_1);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
							PropertyManager propertyManager = new PropertyManager();
							UserManager userManager = new UserManager();
							User usr = userManager.getUser((long)comboBox_1.getSelectedItem());
							propertyManager.addProperty((AptType)comboBox.getSelectedItem(),Integer.valueOf(txtArea.getText()),Integer.valueOf(txtBuildyear.getText()),usr,photo);
							dispose();
						}catch(Exception ex) {
							logger.error("cannot add property : "+ex);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
