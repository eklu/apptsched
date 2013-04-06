
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8324071780582685535L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField uidField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginDialog dialog = new LoginDialog();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 267, 159);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String uid = uidField.getText();
					if(!exists(uid)){
						LoginErrorDialog error = new LoginErrorDialog(true);
						error.setVisible(true);
						setVisible(false);
						dispose();
					}else{
						MyCalendar c = new MyCalendar(uid);
						c.setVisible(true);
					}
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Cancel");
			cancelButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblPleaseEnterYour = new JLabel("Please enter your username");
		
		uidField = new JTextField();
		uidField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(47)
							.addComponent(lblPleaseEnterYour))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(uidField)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblPleaseEnterYour)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(uidField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(cancelButton))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);
	}
	
	public boolean exists(String uid){
		//check database if uid exists
		Connection connection = null;
		Statement stmt;
		ResultSet rset;
		//int count=0;
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
			String query = "SELECT count(*) FROM users WHERE username ='"+uid+"'";
			rset = stmt.executeQuery(query);
			
			
			if(rset.getInt(0)==0)
				return false;


			return true;
			
		} catch (ClassNotFoundException e) {
		    // Could not find the database driver
			System.out.println("Could not find database driver");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
}
