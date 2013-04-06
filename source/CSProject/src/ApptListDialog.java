import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class ApptListDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6286624147331443457L;
	private final JPanel contentPanel = new JPanel();
	protected MyCalendar owner;
	protected String date;
	protected String apptTime;
	protected JButton okButton;
	protected JButton btnAddEditAppointment;
	protected JButton btnDelete;
	protected Appt selectAppt;
	protected String uid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			@SuppressWarnings("unused")
			ApptListDialog dialog = new ApptListDialog(null,"Appointments","user",true);
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ApptListDialog(MyCalendar parent, String title, String uid1, boolean modal) {
		super(parent, ("Your Appointments for "+ title), modal);
		owner=parent;
		date = title;
		uid=uid1;
		String[] columnNames = {"Name", "Date", "Start Time", "Stop Time",  "Phone", "Notes"};
		//System.out.println(date);
		Connection connection = null;
		Statement stmt;
		ResultSet rset;
		int count=0;
		Object[][] data = {{"Cannot", "connect", "to", "database.", "Try", "again."}};
		try {
		    // Load the JDBC driver
		    Class.forName("oracle.jdbc.driver.OracleDriver");

		    // Create a connection to the database
		    String serverName = "fourier.cs.iit.edu";
		    String portNumber = "1521";
		    String sid = "orcl";
		    String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
		    String username = "eklu";
		    String password = "kweON123.bs";
		    connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
			
			stmt = connection.createStatement();
			String query = "SELECT * FROM appointments WHERE username ='"+uid+"' AND apptdate='"+title+"'";
			rset = stmt.executeQuery(query);
			count=rset.getFetchSize();
			data= new Object[count][columnNames.length];
			int x=0;
			while (rset.next()){
				for(int i=0;i<columnNames.length;i++){
					data[x][i]=rset.getString(i);
				}
				x++;
			}
				
			
		} catch (ClassNotFoundException e) {
		    // Could not find the database driver
			System.out.println("Could not find database driver");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		/*Object[][] data = {
				{"12-12-12", "Kathy", "312-556-9567", "01:30 AM", "02:00 PM", "N/A"},
				{"12-12-12", "Emmanuel", "312-556-9567", "01:30 AM", "02:00 PM", "N/A"},
				{"12-12-12", "Dave", "312-556-9567", "01:30 AM", "02:00 PM", "N/A"},
				{"12-12-12", "Bella", "312-556-9567", "01:30 AM", "02:00 PM", "N/A"},
				{"12-12-12", "John", "312-556-9567", "01:30 AM", "02:00 PM", "N/A"},
		};*/

		final JTable table = new JTable(data, columnNames);

		setBounds(100, 100, 450, 300);

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5647073409011301718L;

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};

		table.setModel(tableModel);
		
		table.setRowSelectionAllowed(true);
		
		
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	int selected = table.getSelectedRow(); 
	    	String date = (String) table.getValueAt(selected, 0);
	        String name = (String) table.getValueAt(selected, 1);
	        String number=(String) table.getValueAt(selected, 2);
	        String start =(String) table.getValueAt(selected, 3);
	        String end=(String) table.getValueAt(selected, 4);
	        String notes=(String) table.getValueAt(selected, 5);
	        selectAppt = new Appt(date,name,number,start,end,notes,uid);
	      }
	    });
	    
		
		getContentPane().setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(table);		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		table.setFillsViewportHeight(true);


		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.add(contentPanel);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		btnAddEditAppointment = new JButton("Add/Edit Appointment");
		btnAddEditAppointment.addActionListener(new MyAction());
		contentPanel.add(btnAddEditAppointment);
		
		JButton btnDelete = new JButton("Delete Appointment");
		contentPanel.add(btnDelete);


		okButton = new JButton("Cancel");
		okButton.addActionListener(new MyAction());
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);


	}
	
	 public class MyAction implements ActionListener{
		  public void actionPerformed(ActionEvent event){
			  Object o=event.getSource();
			  ApptDialog dialog;
			  
			  if (o==okButton){
				  setVisible(false);
				  dispose();
			  }
			  if (o==btnAddEditAppointment){

				  if(selectAppt==null){
					  dialog=new ApptDialog(owner,date,uid,true);
				  		dialog.setVisible(true);
				  }else{
					  dialog = new ApptDialog(owner,date,uid,true,selectAppt);
					  dialog.setVisible(true);
				  }

				  
			  }
			  if (o==btnDelete){
				  if(selectAppt!=null){
					  owner.deleteAppt(selectAppt);
					  repaint(); 
				  }
			  }
			  

					  
					  
		  }
	 }

}
