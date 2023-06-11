package tst.example.AptMgr;

import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.SwingConstants;
import java.awt.image.BufferedImage;
import java.awt.Image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JComboBox;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.FlowLayout;

public class MainFrame {

	public JFrame frame;
	
	public User user;
	
	private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
	private JTextField txtPleaseSelectThe;
	private PropertyManager propertyManager = new PropertyManager();
	private JLabel lblNewLabel_3;
	JComboBox comboBox;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 656, 397);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{500, 248, 0};
		gridBagLayout.rowHeights = new int[]{105, 120, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("username:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblNewLabel_1);
		
		txtPleaseSelectThe = new JTextField();
		txtPleaseSelectThe.setText(" Please, select the user ");
		txtPleaseSelectThe.setEditable(false);
		
		panel_1.add(txtPleaseSelectThe);
		
		JLabel lblNewLabel_2 = new JLabel("property:");
		panel_1.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ae) {
				String propertyString = String.valueOf(comboBox.getSelectedItem());
				if(propertyString == null) return;
				String[] arr = propertyString.split(" ");
				String propertyId = arr[0].substring(3,arr[0].length());
				Long id ;
				try {
					id = Long.parseLong(propertyId,10);
				}catch(NumberFormatException e) {
					logger.error("wrong number format : "+e);
					return;
				}
				Property property = propertyManager.getPropertyById(id);
				updateMainPropertyFrame(property);
			}
		});
		panel_1.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		frame.getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("id:");
		panel_3.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		panel_3.add(textField);
		textField.setColumns(3);
		
		JLabel lblNewLabel_4 = new JLabel("type");
		panel_3.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(8);
		
		JLabel lblNewLabel_5 = new JLabel("area");
		panel_3.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(3);
		
		JLabel lblNewLabel_6 = new JLabel("Built in");
		panel_3.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		panel_3.add(textField_3);
		textField_3.setColumns(3);
		
		
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		
		lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Select User");
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				selectUserDialog();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("New");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Property");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newPropertyDialog();
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("User");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				newUserDialog();
			}
		});
	}
	
	public void newUserDialog() {
		try {
			NewUser dialog = new NewUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}catch (Exception e) {
			logger.error("cannot draw the NewUser frame : "+e);
		}
	}
	
	public void newPropertyDialog() {
		try {
			NewProperty dialog = new NewProperty();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}catch (Exception e) {
			logger.error("cannot draw the NewProperty frame : "+e);
		}
	}
	
	public void selectUserDialog() {
		try {
			SelectUser dialog = new SelectUser(this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}catch (Exception e) {
			logger.error("cannot draw the selectUser frame : "+e);
		}
	}
	
	public void userSelected(User usr) {
		this.user = usr;
		txtPleaseSelectThe.setText(user.getUserName());
		List<Property> listProperty = propertyManager.listPropertyByUser(usr);
		comboBox.removeAllItems();
		for(Property p: listProperty) {
			comboBox.addItem(String.valueOf("id:"+p.getId() + " " + p.getType().name() + " area:"+ p.getArea() + " year:" + p.getBuild()));
		}

		//loading the image
		updateMainPropertyFrame(listProperty.get(0));
	}
	public void updateMainPropertyFrame(Property property){//TODO
		lblNewLabel_3.setIcon(null);
		lblNewLabel_3.revalidate();

		BufferedImage propertyPicture;
		InputStream is = new ByteArrayInputStream(property.getPhoto());
		try {
			propertyPicture = ImageIO.read(is);
			ImageIcon imgIcn = new ImageIcon(propertyPicture);
			Image img = imgIcn.getImage();
			Image imgScaled = img.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
			lblNewLabel_3.setIcon(new ImageIcon(imgScaled));
		}catch(IOException e) {
			logger.error("cannot draw the picture : "+e);
		}
		
		//filling other forms
		textField.setText(String.valueOf(property.getId()));
		textField_1.setText(property.getType().toString());
		textField_2.setText(String.valueOf(property.getArea()));
		textField_3.setText(String.valueOf(property.getBuild()));

	}

}
