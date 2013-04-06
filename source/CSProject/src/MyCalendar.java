import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.UIManager.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyCalendar extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3372901591341838232L;
	protected static int startLimit, endLimit, maxAppts;
	MyCalendar(String uid){
		super("Calendar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		parseLimits();

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {

				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());

					break;
				}
				else{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				}
			}
		} catch (Exception e) {

		}
		setLayout(new GridLayout(1,1));
		setSize(322,223);
		setResizable(false);
		Controller d= new Controller(this,uid);

		add(d);
	}


	public static void main(String args[]){

		MyCalendar c = new MyCalendar("emm");
		c.setVisible(true);
		//c.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void parseLimits(){
		  try {
			  File file = new File("config.xml");
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  NodeList nodeLst = doc.getElementsByTagName("config");
			  nodeLst = ((Element)nodeLst.item(0)).getChildNodes();
			  Element startHour = (Element)nodeLst.item(1);
			  Element endHour = (Element)nodeLst.item(3);
			  Element maxAppt = (Element)nodeLst.item(5);
			  NodeList sHour = startHour.getChildNodes();
			  startLimit = Integer.parseInt(((Node)sHour.item(0)).getNodeValue().trim());
			  NodeList eHour = endHour.getChildNodes();
			  endLimit = Integer.parseInt(((Node)eHour.item(0)).getNodeValue().trim());
			  NodeList maxA = maxAppt.getChildNodes();
			  maxAppts = Integer.parseInt(((Node)maxA.item(0)).getNodeValue().trim());
			  
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean addAppt(Appt appt) {
		Connection connection = null;
		Statement stmt;
		ResultSet rset;
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
			String query = "SELECT count(*) FROM appointments WHERE apptdate='"+appt.getDate()+"' AND starttime='"+appt.getStart()+"' AND endtime='"+appt.getEnd()+"'";
			rset = stmt.executeQuery(query);
			
			if(rset.getInt(1)<maxAppts){
				query = "INSERT into appointments values('"+appt.getUID()+"','"+appt.getName()+"','"+appt.getDate()+"','"+appt.getStart()+"','"+appt.getEnd()+"','"+appt.getNumber()+"','"+appt.getNotes()+"')";
				stmt.execute(query);
				return true;
			}
		return false;
			
		} catch (ClassNotFoundException e) {
		    // Could not find the database driver
			System.out.println("Could not find database driver");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
		
	}


	public void deleteAppt(Appt appt) {
		Connection connection = null;
		Statement stmt;
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
			String query = "delete FROM appointments WHERE username ='"+appt.getUID()+"' AND apptdate='"+appt.getDate()+"' AND starttime='"+appt.getStart()+"' AND endtime='"+appt.getEnd()+"' AND phone='"+appt.getNumber()+"'";
			stmt.execute(query);
			
		} catch (ClassNotFoundException e) {
		    // Could not find the database driver
			System.out.println("Could not find database driver");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	

}