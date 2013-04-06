/**
*	
*	The dialog box used for adding or editing appointments
*
**/



import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



public class ApptDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5723636852623353759L;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			new ApptDialog(null,"Appointments", "user",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** used for the current system date */
	protected Calendar rightnow;
	
	/** the owner frame that invokes the instance of ApptDialog */
	protected MyCalendar owner;
	
	/** the email and url entered into the dialog box */
	protected String email, url;
	
	/** integer value of the month chosen */
	protected int month;
	
	/** an appointment used when dialog is used for editing an exisiting appointment */
	protected Appt appointment;
	
	/** whether an appointment was opened for editing or not */
	public boolean EDIT;
	
	/** whether an appointment opened had a specified time or not */
	public boolean TIME;
	
	protected String date;
	protected int startLimit,endLimit;
	
	protected String uid;

	
	/**
	*	Used when a new blank appointment being added via the "Add" button
	*	
	*	@param parent the owner frame that invokes the dialog box
	*	@param title the title of the dialog box
	*	@param model whether the box is modal or not
	*	@wbp.parser.constructor
	*	
	**/
	
	public ApptDialog(MyCalendar parent, String title, String uid1, boolean modal) {
		super(parent, "Appointment for "+title, modal);
		initComponents();
		uid=uid1;
		date=title;
		owner = parent;
		
		//init the month and year fields to current month, year, and day
		rightnow = Calendar.getInstance();
		month = Calendar.MONTH;

	}
	


	/**
	*	Used when an existing appointment is opened for editing...
	*	Need to init the fields in the dialog box 
	*	
	*	@param parent the owner frame that invokes the dialog box
	*	@param title the title of the dialog box
	*	@param model whether the box is modal or not
	*	@param a used to hold the info of the appointment being edited
	*
	**/

	public ApptDialog(MyCalendar parent, String title, String uid1, boolean modal, Appt a) {
		
		super(parent,"Appointment for "+title, modal);
		initComponents();
		date=title;
		uid=uid1;
		
		jTextFieldName.setText(a.getName());
		jTextFieldPhone.setText(a.getNumber());

		jComboBoxStartMinutes.setSelectedIndex(a.getStartMinute());
		jComboBoxStartHours.setSelectedIndex(a.getStartHour());
		if(a.isStartPM()){
			jRadioButtonAM.setSelected(false);
			jRadioButtonPM.setSelected(true);
		}else{
			jRadioButtonAM.setSelected(true);
			jRadioButtonPM.setSelected(false);
		}	
		
		jTextFieldNotes.setText(a.getNotes());
		owner=parent;
		appointment = a;
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//setVisible(true);
	}


	/** 
	*	Private method used to initialize everything in the dialog box
	*	This method was generated by Forte, which was used to create
	*	the initial layout of the dialog box
	**/
   @SuppressWarnings({ "rawtypes", "unchecked" })
private void initComponents() {
		buttonGroup1 = new ButtonGroup();
		jPanel1 = new JPanel();
		jPanel1.setPreferredSize(new Dimension(350,270));
		jButtonAdd = new JButton();
		jButtonCancel = new JButton();
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel4 = new JLabel();
		jLabel8 = new JLabel();
		jButtonClear = new JButton();
		jTextFieldName = new JTextField();
		jTextFieldName.setToolTipText("Enter full name here");
		jTextFieldPhone = new JTextField();
		jTextFieldPhone.setToolTipText("xxx-xxx-xxxx");
		jTextFieldNotes = new JTextField();
		jComboBoxStartHours = new JComboBox();
		jSeparator1 = new JSeparator();
		jRadioButtonAM = new JRadioButton();
		jRadioButtonPM = new JRadioButton();
		jComboBoxStartMinutes = new JComboBox();
		jLabel11 = new JLabel();
		
		
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeDialog(evt);
			}
		});
		
		jButtonAdd.setText("Add");
		jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonAddMouseClicked(evt);
			}
		});
		
		jButtonCancel.setText("Cancel");
		jButtonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonCancelMouseClicked(evt);
			}
		});
		
		jLabel1.setText("Name");
		
		jLabel2.setText("Phone Number");
		
		jLabel4.setText("Appointment Time");
		
		jLabel8.setText("Notes");
		
		jButtonClear.setText("Clear");
		jButtonClear.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButtonClearMouseClicked(evt);
			}
		});
		
		jComboBoxStartHours.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "9", "10", "11", "12" }));
		
		jRadioButtonAM.setText("AM");
		buttonGroup1.add(jRadioButtonAM);
		
		jRadioButtonPM.setText("PM");
		buttonGroup1.add(jRadioButtonPM);
		jRadioButtonPM.setSelected(true);
		
		jComboBoxStartMinutes.setModel(new DefaultComboBoxModel(new String[] { "00" }));
		
		jLabel11.setText("Duration: 1 hour");
		
		getContentPane().add(jPanel1, BorderLayout.CENTER);
		
		JLabel lblXxxxxxxxxx = new JLabel("xxx-xxx-xxxx");
		GroupLayout gl_jPanel1 = new GroupLayout(jPanel1);
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addGap(40)
									.addComponent(jTextFieldName, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addComponent(jLabel2)
									.addGap(50)
									.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblXxxxxxxxxx)
										.addComponent(jTextFieldPhone)))))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addComponent(jButtonAdd, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(jButtonClear, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(jButtonCancel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addGap(30)
									.addComponent(jTextFieldNotes, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(jLabel11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
							.addGap(18)
							.addComponent(jComboBoxStartHours, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addComponent(jComboBoxStartMinutes, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(jRadioButtonAM, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addComponent(jRadioButtonPM, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel1)
						.addComponent(jTextFieldName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel2)
						.addComponent(jTextFieldPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblXxxxxxxxxx)
					.addGap(11)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel4)
						.addComponent(jComboBoxStartHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jComboBoxStartMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jRadioButtonAM, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(jRadioButtonPM, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jLabel11)
					.addGap(16)
					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(jLabel8)
						.addComponent(jTextFieldNotes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButtonAdd)
						.addComponent(jButtonCancel)
						.addComponent(jButtonClear))
					.addGap(265))
		);
		gl_jPanel1.setAutoCreateGaps(true);
		jPanel1.setLayout(gl_jPanel1);
		
		pack();
	}


	/**
	*	Private method used when the "Add button is clicked on the dialog box.
	*	Either adds the appointment or spawns an error message if invalid info entered
	**/
	private void jButtonAddMouseClicked(java.awt.event.MouseEvent evt) {

				int startHours = Integer.parseInt((String)jComboBoxStartHours.getSelectedItem());
				int startMinutes = Integer.parseInt((String)jComboBoxStartMinutes.getSelectedItem());
				int endHours = startHours==12 ? 1 : startHours+1;
				int endMinutes = 0;
				boolean startPM=false, endPM=false;
				
				if(jRadioButtonPM.isSelected()) 
					startPM=true;
				
				if(startHours==11)
					endPM=true;
				else
					endPM=startPM;
				
				String startTime = startHours+":"+String.format("%02d", startMinutes)+" "+(startPM ? "PM" : "AM");
				String endTime = endHours+":"+String.format("%02d", endMinutes)+" "+(endPM ? "PM" : "AM");
				int x =startHours + (startPM ? 12 : 0);
				int start = Integer.parseInt( x + "" + String.format("%02d", startMinutes));
				 x = endHours + (endPM ? 12 : 0);
				int end = Integer.parseInt( x + "" + String.format("%02d", endMinutes));
				
				//hours = 9-5
				boolean outHours = (startPM && startHours>MyCalendar.endLimit-1) || (!startPM && startHours<MyCalendar.startLimit);
				
				
				if(outHours || end<=start || !isValidInput(jTextFieldName.getText()) || !isValidInput(jTextFieldPhone.getText())){
					//invalid input throw popup
					EntryErrorDialog error = new EntryErrorDialog(owner, true);
					error.setVisible(true);

				}else{
					Appt newAppt=new Appt(date, jTextFieldName.getText(), jTextFieldPhone.getText(), startTime, endTime, jTextFieldNotes.getText(), uid);
					if(appointment!=null){
						if(!newAppt.equals(appointment)){
							//delete and create new if edited version is not the same
							owner.deleteAppt(appointment);
							if(owner.addAppt(newAppt)){
								setVisible(false);
							}else{
								ApptFullErrorDialog error = new ApptFullErrorDialog(owner,true);
								error.setVisible(true);
								owner.addAppt(appointment);
							}
							
							
						}
					}else{
						if(owner.addAppt(newAppt)){
							setVisible(false);
						}else{
							ApptFullErrorDialog error = new ApptFullErrorDialog(owner,true);
							error.setVisible(true);
						}
					}


				}

	}

	/**
	*	Private method used when the "Clear" button is clicked
	*	Resets all fields in the dialog box
	**/
	private void jButtonClearMouseClicked(java.awt.event.MouseEvent evt) {
		// Add your handling code here:
		jTextFieldName.setText("");
		jTextFieldPhone.setText("");
		jTextFieldNotes.setText("");
	}

	/**
	*	Private method used when the "Cancel" button is clicked
	*	Closes the dialog box, discarding anything entered
	**/
	private void jButtonCancelMouseClicked(java.awt.event.MouseEvent evt) {
		// Add your handling code here:
		setVisible(false);
		dispose();
	}

	/**
	*	Private method to close the dialog bix
	**/
	private void closeDialog(java.awt.event.WindowEvent evt) {
		setVisible(false);
		dispose();
	}

	
	private ButtonGroup buttonGroup1;
	private JPanel jPanel1;
	private JButton jButtonAdd;
	private JButton jButtonCancel;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel4;
	private JLabel jLabel8;
	private JButton jButtonClear;
	private JTextField jTextFieldName;
	private JTextField jTextFieldPhone;
	private JTextField jTextFieldNotes;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxStartHours;
	private JSeparator jSeparator1;
	private JRadioButton jRadioButtonAM;
	private JRadioButton jRadioButtonPM;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxStartMinutes;
	private JLabel jLabel11;
	
	//helper function to check if String is either a number or a string (first and last name)
	public static boolean isValidInput(String x){
		boolean hasNumber=false;
		
		for(int i=0;i<x.trim().length();i++){
			if(Character.isDigit(x.trim().charAt(i))){
				hasNumber=true;
				break;
			}
		}
		//if it has a number, check that it is all numbers
		if(hasNumber){
			for(int i=0;i<x.trim().length();i++){
				if(!Character.isDigit(x.trim().charAt(i)) && !(x.trim().charAt(i)=='-')){
					return false;
				}
			}
			return isValidNumber(x);
		}
		//else check that there are  2 inputs for first and last name;
		else
			return (x.split("\\s+")).length >= 2; //delimiting using any kind of white space
	}
	
	//check valid phone number
	public static boolean isValidNumber(String number){
		return (number.charAt(3)=='-' && number.charAt(7)=='-' && number.length()==12);
	}
}
