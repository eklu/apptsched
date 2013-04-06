
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Controller extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2400459212609533151L;
	//lables use for showing weekday names
	private JLabel sun;
	private JLabel mon;
	private JLabel tue;
	private JLabel wed;
	private JLabel thu;
	private JLabel fri;
	private JLabel sat;
	
	private MyCalendar owner;
	
	private String selectDate;


	//need to images blackArrowUp.gif and upArrowUp.gif  to put on up and down button 
	//private Icon pic[]= {new ImageIcon(getClass().getResource("up.png")),new ImageIcon(getClass().getResource("down.png"))};
	private Icon pic[]={new ImageIcon(),new ImageIcon()};
	private JPanel mainpanel;   //for add days and weekday
	private JPanel p[]= new JPanel[42]; //Array of Panels for Adding Days Buttons
	private JButton b[]= new JButton[31]; //Array of 31 Buttons For 31 Days


	//for year , month and date (all will be added to Pannel )


	private JPanel mainp; //main Panel on which date , year and month will add
	private JTextField date; //for showing date in text fields
	@SuppressWarnings("rawtypes")
	public JComboBox month; //for months
	public JTextField year; //for year
	private JButton sb[]= new JButton[2]; // for increasing year by 1 on every click
	protected String uid;
	
	


	//string array has name of months for adding to combobox

	private String strmon[]= {"January","Febuary","March","April","May","June","July","Augest","September","October","November","December"};

	//Constructor Start
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Controller(MyCalendar owner1, String uid1){
		owner=owner1;
		uid=uid1;

		setLayout(null); //setting layout
		setSize(350,228);
		setVisible(true);



		//first Frame Build

		mainp = new JPanel();
		mainp.setLayout(null);
		mainp.setSize(325,31);
		mainp.setLocation(1,0);



		date = new JTextField(); //date textField will strore the date
		date.setSize(100,30);
		date.setLocation(5,0);
		date.setBackground(Color.WHITE);
		date.setFont(new Font("Arial",Font.PLAIN,14));
		date.setEditable(false);

		month = new JComboBox(strmon); //month combo box will have all months
		month.setSize(90,30);
		month.setLocation(110,0);


		year = new JTextField("2008"); //year will have year part of date
		year.setSize(70,30);
		year.setLocation(205,0);
		year.setBackground(Color.WHITE);
		year.setEditable(false);

		sb[0] = new JButton(""); //sb scroll bar increments the year by 1
		sb[0].setSize(30,15);
		sb[0].setLocation(275,0);
		sb[0].setIcon(pic[0]);

		sb[1] = new JButton(""); //sb scroll bar increments the year by 1
		sb[1].setSize(30,15);
		sb[1].setLocation(275,16);
		sb[1].setIcon(pic[1]);

		mainp.add(date);
		mainp.add(month);
		mainp.add(year);
		mainp.add(sb[0]);
		mainp.add(sb[1]);
		add(mainp);


		//Days Panel

		mainpanel = new JPanel();
		mainpanel.setLayout(new GridLayout(7,7,1,1));
		mainpanel.setSize(315,160);
		mainpanel.setLocation(1,31);

		sun = new JLabel("Sun");
		sun.setForeground(Color.RED);
		mon = new JLabel("Mon");
		tue = new JLabel("Tue");
		wed = new JLabel("Wed");
		thu = new JLabel("Thu");
		fri = new JLabel("Fri");
		sat = new JLabel("Sat");

		//add labels to panel
		mainpanel.add(sun);
		mainpanel.add(mon);
		mainpanel.add(tue);
		mainpanel.add(wed);
		mainpanel.add(thu);
		mainpanel.add(fri);
		mainpanel.add(sat);

		//Initializing memory to Jpanels and add it to mainpanel
		for (int x=0;x<42;x++){
			p[x]= new JPanel();
			p[x].setLayout(new GridLayout(1,1));
			p[x].setBackground(Color.WHITE);
			mainpanel.add(p[x]);
			validate();
		}

		final HandlerClass handler= new HandlerClass(); // object of handlerclass

		for (int i=0;i<31;i++){ //only Initializing memory to buttons not adding them
			b[i]= new JButton();
			b[i].addActionListener(handler);
			b[i].setFont(new Font("Times New Roman",Font.PLAIN,11));
			b[i].setText(Integer.toString(i+1));

		}


		final Calendar now = Calendar.getInstance(); //create a Calendar object
		year.setText(Integer.toString(now.get(Calendar.YEAR))); //get year and month from Calendar object
		month.setSelectedIndex(now.get(Calendar.MONTH)); //add values to year and month

		validate();

		// DAY_OF_WEEK GIVES THE DAY OF THE FROM WIHCH THE MONTH STARTS
		int ye=Integer.parseInt(year.getText());
		Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		for (int i=0;i<getDaysNo();i++){
			p[dayOfWeek-1].add(b[i]);               //adding the days button on panels according to the day of week
			
			if(i<now.get(Calendar.DATE)){
				b[i].setEnabled(false);
			}
			
			dayOfWeek++;
		}

		//int bd=now.get(Calendar.DATE); //getting the current date
		//b[bd-1].setEnabled(false); // sets the current date button enabled false


		add(mainpanel); // add main pannel to

		validate();

		month.addItemListener(
				new ItemListener(){

					@Override
					public void itemStateChanged(ItemEvent event){
						if (event.getStateChange()==ItemEvent.SELECTED){
							mainpanel.removeAll();
							validate();

							mainpanel.add(sun);
							mainpanel.add(mon);
							mainpanel.add(tue);
							mainpanel.add(wed);
							mainpanel.add(thu);
							mainpanel.add(fri);
							mainpanel.add(sat);

							for (int x=0;x<42;x++){

								p[x].removeAll();
								mainpanel.add(p[x]);
								validate();
							}


							int ye=Integer.parseInt(year.getText());
							Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);

							int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
							boolean disable =false;
							Calendar rightnow=Calendar.getInstance();
							for (int i=0;i<getDaysNo();i++){

								disable=false;
								p[dayOfWeek-1].add(b[i]);
								dayOfWeek++;
								if(ye<rightnow.get(Calendar.YEAR)) 
									disable=true;
								else if (ye==rightnow.get(Calendar.YEAR)){
									if(month.getSelectedIndex()<rightnow.get(Calendar.MONTH))
										disable=true;
									else if(month.getSelectedIndex()==rightnow.get(Calendar.MONTH)){
										if(i<rightnow.get(Calendar.DATE))
											disable=true;
									}
								}
								b[i].setEnabled(!disable);
								
								
								validate();
							}
							validate();
						}
						mainp.validate();
						validate();
						

						date.setText(Integer.toString(handler.db +1).concat("-").concat(Integer.toString(month.getSelectedIndex() + 1)).concat("-").concat(year.getText()));
					}
				}
				);


		sb[0].addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						int y = Integer.parseInt(year.getText());
						y++;
						year.setText(Integer.toString(y));



						mainpanel.removeAll();
						validate();

						mainpanel.add(sun);
						mainpanel.add(mon);
						mainpanel.add(tue);
						mainpanel.add(wed);
						mainpanel.add(thu);
						mainpanel.add(fri);
						mainpanel.add(sat);

						for (int x=0;x<42;x++){


							p[x].removeAll();
							mainpanel.add(p[x]);
							validate();
						}


						int ye=Integer.parseInt(year.getText());
						Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);
						Calendar rightnow = Calendar.getInstance();
						int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
						boolean disable=false;
						for (int i=0;i<getDaysNo();i++){

							disable=false;
							p[dayOfWeek-1].add(b[i]);
							dayOfWeek++;
							if(ye<rightnow.get(Calendar.YEAR)) 
								disable=true;
							else if (ye==rightnow.get(Calendar.YEAR)){
								if(month.getSelectedIndex()<rightnow.get(Calendar.MONTH))
									disable=true;
								else if(month.getSelectedIndex()==rightnow.get(Calendar.MONTH)){
									if(i<rightnow.get(Calendar.DATE))
										disable=true;
								}
							}
							b[i].setEnabled(!disable);
							
							validate();
						}


						validate();
						//sets the date on Click of year Button
						date.setText(Integer.toString(handler.db +1).concat("-").concat(Integer.toString(month.getSelectedIndex() + 1)).concat("-").concat(year.getText()));

					}

				}
				);



		sb[1].addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						int y = Integer.parseInt(year.getText());
						y--;
						year.setText(Integer.toString(y));



						mainpanel.removeAll();
						validate();

						mainpanel.add(sun);
						mainpanel.add(mon);
						mainpanel.add(tue);
						mainpanel.add(wed);
						mainpanel.add(thu);
						mainpanel.add(fri);
						mainpanel.add(sat);

						for (int x=0;x<42;x++){


							p[x].removeAll();
							mainpanel.add(p[x]);
							validate();
						}


						int ye=Integer.parseInt(year.getText());
						Calendar cal = new GregorianCalendar(ye, month.getSelectedIndex(), 1);

						int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
						Calendar rightnow = Calendar.getInstance();
						boolean disable=false;
						for (int i=0;i<getDaysNo();i++){
							disable=false;
							p[dayOfWeek-1].add(b[i]);
							dayOfWeek++;
							if(ye<rightnow.get(Calendar.YEAR)) 
								disable=true;
							else if (ye==rightnow.get(Calendar.YEAR)){
								if(month.getSelectedIndex()<rightnow.get(Calendar.MONTH))
									disable=true;
								else if(month.getSelectedIndex()==rightnow.get(Calendar.MONTH)){
									if(i<rightnow.get(Calendar.DATE))
										disable=true;
								}
							}
							b[i].setEnabled(!disable);
							validate();
						}

						
						validate();
						//sets the date on Click of year Button
						date.setText(Integer.toString(handler.db +1).concat("-").concat(Integer.toString(month.getSelectedIndex() + 1)).concat("-").concat(year.getText()));

					}

				}
				);



		//sets the date on form load
		date.setText(Integer.toString(handler.db +1).concat("-").concat(Integer.toString(month.getSelectedIndex() + 1)).concat("-").concat(year.getText()));
	}





	//returns no. of days in a week
	public int getDaysNo(){
		int no = 31;
		if (month.getSelectedIndex()==1){
			no=28;
			if (Integer.parseInt(year.getText())%4==0){
				no=29;

			}
			month.validate();
		}

		if (month.getSelectedIndex()==3 || month.getSelectedIndex()==5 || month.getSelectedIndex()==8 || month.getSelectedIndex()==10)
		{
			no=30;
		}
		return no;

	}
	
	public String getDate(){
		return selectDate;
	}
	
	
	
	// handler class set which date is selected

	class HandlerClass implements ActionListener{


		Calendar now = Calendar.getInstance();
		public int db=(now.get(Calendar.DATE))-1;

		@Override
		public void actionPerformed(ActionEvent e){

			for (int k=0;k<31;k++){

				if (e.getSource()==b[k])
				{
					//b[k].setEnabled(false);
					validate();
					db=k;

				}
				else{
					b[k].setEnabled(true);
					validate();
				}
				month.validate();

			}
			selectDate = Integer.toString(db +1).concat("-").concat(Integer.toString(month.getSelectedIndex()+1)).concat("-").concat(year.getText());
			date.setText(selectDate);
			new ApptListDialog(owner,selectDate,uid,true);
			
		}
		


	}

}


