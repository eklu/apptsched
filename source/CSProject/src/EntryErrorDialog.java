import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;


public class EntryErrorDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8279696938665887125L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblErrorPleaseCheck;
	private JButton okButton;
	private final Action action = new SwingAction();
	protected MyCalendar owner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EntryErrorDialog dialog = new EntryErrorDialog(null, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EntryErrorDialog(MyCalendar parent, boolean modal) {
		super(parent, "Error", modal);
		owner = parent;
		setBounds(300, 300, 400, 149);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		{
			lblErrorPleaseCheck = new JLabel("Error. Please enter information in correct format.");
		}
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					dispose();
				}
			});
			okButton.setAction(action);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel lblAppointmentsMustBe = new JLabel("Appointments must be between "+MyCalendar.startLimit+" AM and "+MyCalendar.endLimit+" PM.");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(lblAppointmentsMustBe)
							.addComponent(lblErrorPleaseCheck)))
					.addContainerGap(151, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblErrorPleaseCheck)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAppointmentsMustBe)
					.addGap(21)
					.addComponent(okButton)
					.addContainerGap(136, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
					.addGap(83))
		);
		getContentPane().setLayout(groupLayout);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2365893109301352678L;
		public SwingAction() {
			putValue(NAME, "OK");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
