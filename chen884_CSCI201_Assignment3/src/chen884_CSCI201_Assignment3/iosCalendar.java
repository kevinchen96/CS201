package chen884_CSCI201_Assignment3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class iosCalendar extends JFrame {
	int eventLocation;
	int eventeventLocation;
	int currentLocation = 0;
	String []calendar = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	Calendar c, temp, temp2;
	ArrayList<JButton[]> events;
	ArrayList<ArrayList<ArrayList<JButton>>> actual;
	JPanel J4;
	JPanel J5;
	JLabel l;
	JPanel Outerpanel;
	JPanel Home;
	JPanel createEvent;
	JPanel EditEvent;
	JPanel EventManager;
	TextField title, title1;
	TextField location, location1;
	JComboBox startHour, startMinute, startTime, endHour, endMinute, endTime;
	JComboBox startHour1, startMinute1, startTime1, endHour1, endMinute1, endTime1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		iosCalendar C = new iosCalendar();
	}
	
	public iosCalendar(){
		super("Calendar");
		setSize(500,500);
		setLocation(200,200);
		c =new GregorianCalendar();
		c.set(Calendar.DAY_OF_MONTH, 1);
		events = new ArrayList<JButton[]>();
		events.add(new JButton[c.getActualMaximum(Calendar.DAY_OF_MONTH)]);
		actual = new ArrayList<ArrayList<ArrayList<JButton>>>();
		actual.add(new ArrayList<ArrayList<JButton>>());
		for(int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
			events.get(0)[i] = new JButton(""+(i+1));
			actual.get(0).add(new ArrayList<JButton>());
			final int y = i;
			events.get(0)[i].addMouseListener(new MouseListener(){
				final int x = y;
				ArrayList<JButton> message = actual.get(0).get(x);
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getClickCount() == 1 && !e.isConsumed()){
						e.consume();
						J5.removeAll();
						Home.validate();
						for(int j = 0; j < events.size(); j++){
							for(int i = 0; i < events.get(j).length; i++){
								events.get(j)[i].setBackground(null);
							}
						}
						if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
							events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
						}
						events.get(currentLocation)[x].setBackground(Color.BLUE);
						
						J5.setLayout(new BoxLayout(J5, BoxLayout.PAGE_AXIS));
						if(message.size() == 0){
							J5.add(new JButton("No Events"));
						}
						else{
							for(int i = 0; i < message.size(); i++){
								J5.add(message.get(i));
							}
						}
						
						Home.validate();
					}
					else if (e.getClickCount() == 2 && !e.isConsumed()) {
					     e.consume();
					     eventLocation = x;
					     iosCalendar.super.setTitle("Create Event");
					     title.setText("");
					     location.setText("");
					     startHour.setSelectedIndex(0);
					     startMinute.setSelectedIndex(0);
					     startTime.setSelectedIndex(0);
					     ((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "create");
					}
					
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}
		if(c.get(Calendar.MONTH)==0){
			temp = new GregorianCalendar(c.get(Calendar.YEAR)-1, 11, 1);
		}
		else{
			temp = new GregorianCalendar(c.get(Calendar.YEAR),c.get(Calendar.MONTH)-1,1);
		}
		if(c.get(Calendar.MONTH)==11){
			temp2 = new GregorianCalendar(c.get(Calendar.YEAR)+1, 0, 1);
		}
		else{
			temp2 = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, 1);
		}
		events.add(0, new JButton[temp.getActualMaximum(temp.DAY_OF_MONTH)]);
		actual.add(0, new ArrayList<ArrayList<JButton>>());
		for(int i = 0; i < temp.getActualMaximum(temp.DAY_OF_MONTH); i++){
			events.get(0)[i] = new JButton(""+(i+1));
			actual.get(0).add(new ArrayList<JButton>());
			final int y = i;
			events.get(0)[i].addMouseListener(new MouseListener(){
				final int x = y;
				ArrayList<JButton> message = actual.get(0).get(x);
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getClickCount() == 1 && !e.isConsumed()){
						e.consume();
						J5.removeAll();
						Home.validate();
						for(int j = 0; j < events.size(); j++){
							for(int i = 0; i < events.get(j).length; i++){
								events.get(j)[i].setBackground(null);
							}
						}
						if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
							events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
						}
						events.get(currentLocation)[x].setBackground(Color.BLUE);
						J5.setLayout(new BoxLayout(J5, BoxLayout.PAGE_AXIS));
						if(message.size() == 0){
							J5.add(new JButton("No Events"));
						}
						else{
							for(int i = 0; i < message.size(); i++){
								J5.add(message.get(i));
							}
						}
						Home.validate();
					}
					else if (e.getClickCount() == 2 && !e.isConsumed()) {
					     e.consume();
					     eventLocation = x;
					     iosCalendar.super.setTitle("Create Event");
					     title.setText("");
					     location.setText("");
					     startHour.setSelectedIndex(0);
					     startMinute.setSelectedIndex(0);
					     startTime.setSelectedIndex(0);
					     ((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "create");
					}
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});

		}
		events.add(new JButton[temp2.getActualMaximum(temp2.DAY_OF_MONTH)]);
		actual.add(new ArrayList<ArrayList<JButton>>());
		for(int i = 0; i < temp2.getActualMaximum(temp2.DAY_OF_MONTH); i++){
			events.get(2)[i] = new JButton(""+(i+1));
			actual.get(2).add(new ArrayList<JButton>());
			final int y = i;
			events.get(2)[i].addMouseListener(new MouseListener(){
				final int x = y;
				ArrayList<JButton> message = actual.get(2).get(x);
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					if(e.getClickCount() == 1 && !e.isConsumed()){
						e.consume();
						J5.removeAll();
						Home.validate();
						for(int j = 0; j < events.size(); j++){
							for(int i = 0; i < events.get(j).length; i++){
								events.get(j)[i].setBackground(null);
							}
						}
						if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
							events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
						}
						events.get(currentLocation)[x].setBackground(Color.BLUE);
						J5.setLayout(new BoxLayout(J5, BoxLayout.PAGE_AXIS));
						if(message.size() == 0){
							J5.add(new JButton("No Events"));
						}
						else{
							for(int i = 0; i < message.size(); i++){
								J5.add(message.get(i));
							}
						}
						
						Home.validate();
					}
					else if (e.getClickCount() == 2 && !e.isConsumed()) {
					     e.consume();
					     eventLocation = x;
					     iosCalendar.super.setTitle("Create Event");
					     title.setText("");
					     location.setText("");
					     startHour.setSelectedIndex(0);
					     startMinute.setSelectedIndex(0);
					     startTime.setSelectedIndex(0);
					     ((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "create");
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});

		}
		currentLocation = 1;
		l = new JLabel(calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR), JLabel.CENTER);
		JPanel J1 = new JPanel();
		J1.setLayout(new BorderLayout());
		JButton left = new JButton("◀");
		left.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				prevMonth();
				l.setText(calendar[c.get(c.MONTH)] + " " + c.get(c.YEAR));
				if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
					events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
				}
			}
			
		});
		
		JButton right = new JButton("▶");
		right.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextMonth();
				l.setText(calendar[c.get(c.MONTH)] + " " + c.get(c.YEAR));
				if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
					events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
				}
			}
			
		});
		Outerpanel = new JPanel();
		Outerpanel.setLayout(new CardLayout());
		Home = new JPanel();
		Home.setLayout(new BorderLayout());
		createEvent = new JPanel();
		EditEvent = new JPanel();
		EventManager = new JPanel();
		
		JToolBar menu = new JToolBar();
		JButton eventM = new JButton("Event Manager");
		JButton export = new JButton("Export");
		JButton about = new JButton("About");
		menu.add(eventM);
		menu.add(export);
		menu.add(about);
		add(menu, BorderLayout.NORTH);
		J1.add(left, BorderLayout.WEST);
		J1.add(l, BorderLayout.CENTER);
		J1.add(right, BorderLayout.EAST);
		JPanel J2 = new JPanel();
		J2.setLayout(new GridLayout(1, 7));
		JLabel day = new JLabel("Sun", JLabel.CENTER);
		JLabel day1 = new JLabel("Mon", JLabel.CENTER);
		JLabel day2 = new JLabel("Tues", JLabel.CENTER);
		JLabel day3 = new JLabel("Wed", JLabel.CENTER);
		JLabel day4 = new JLabel("Thu", JLabel.CENTER);
		JLabel day5 = new JLabel("Fri", JLabel.CENTER);
		JLabel day6 = new JLabel("Sat", JLabel.CENTER);
		J2.add(day);
		J2.add(day1);
		J2.add(day2);
		J2.add(day3);
		J2.add(day4);
		J2.add(day5);
		J2.add(day6);
		JPanel J3 = new JPanel();
		J3.setLayout(new BorderLayout());
		J3.add(J1, BorderLayout.NORTH);
		J3.add(J2, BorderLayout.SOUTH);
		J4 = new JPanel();
		J4.setLayout(new GridLayout(0, 7));
		Home.add(J3, BorderLayout.NORTH);
		Home.add(J4, BorderLayout.CENTER);
		J5 = new JPanel();
		J5.setLayout(new BoxLayout(J5,  BoxLayout.Y_AXIS));
		Home.add(J5, BorderLayout.SOUTH);
		Outerpanel.add(Home, "Home");
		Outerpanel.add(createEvent, "create");
		Outerpanel.add(EditEvent, "edit");
		Outerpanel.add(EventManager, "event manager");
		title = new TextField("", 20);
		location = new TextField("", 20);
		JPanel row = new JPanel();
		row.add(new JLabel("Title: "));
		row.add(title);
		createEvent.setLayout(new BoxLayout(createEvent, BoxLayout.PAGE_AXIS));
		createEvent.add(row);
		row = new JPanel();
		row.add(new JLabel("Location: "));
		row.add(location);
		createEvent.add(row);
		row = new JPanel();
		row.add(new JLabel("Start: "));
		Object options[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		startHour = new JComboBox(options);
		Object options1[] = {"00", "15", "30", "45"};
		startMinute = new JComboBox(options1);
		Object options2[] = {"AM", "PM"};
		startTime = new JComboBox(options2);
		row.add(startHour);
		row.add(startMinute);
		row.add(startTime);
		createEvent.add(row);
		row = new JPanel();
		row.add(new JLabel("End: "));
		
		endHour = new JComboBox(options);
		
		endMinute = new JComboBox(options1);
		
		endTime = new JComboBox(options2);
		row.add(endHour);
		row.add(endMinute);
		row.add(endTime);
		createEvent.add(row);
		JButton CREATE = new JButton("Create Event");
		CREATE.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				events.get(currentLocation)[eventLocation].setText((eventLocation+1) + "  E");
				actual.get(currentLocation).get(eventLocation).add(new JButton(title.getText() + "-" + location.getText() + " From " + startHour.getSelectedItem() + ":" + startMinute.getSelectedItem() + startTime.getSelectedItem() + "-" + endHour.getSelectedItem() + ":" + endMinute.getSelectedItem() + endTime.getSelectedItem()));
				actual.get(currentLocation).get(eventLocation).get(actual.get(currentLocation).get(eventLocation).size()-1).addActionListener(new ActionListener(){
					final int x = actual.get(currentLocation).get(eventLocation).size()-1;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Object [] options = {"Delete", "Edit", "Cancel"};
						int value = JOptionPane.showOptionDialog(iosCalendar.this, 
						"Would you like to edit or delete this event?", "Option Dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
						if(value == 0){
							J5.remove(actual.get(currentLocation).get(eventLocation).get(x));
							actual.get(currentLocation).get(eventLocation).remove(x);
							Home.validate();
						}
						else if(value == 1){
							eventeventLocation = x;
							String info = actual.get(currentLocation).get(eventLocation).get(eventeventLocation).getText();
							String[]y = info.split("-", 2);
							String[]y2 = y[1].split(" From ", 2);
							String[]y3 = y2[1].split(":", 2);
							String[]y4 = y3[1].split("M-",2);
							String[]y5 = y4[1].split(":", 2);
							String[]y6 = y5[1].split("M-",2);
							if(y[0]!= null){
								title1.setText(y[0]);
							}
							if(y2[0] != null){
								location1.setText(y2[0]);
							}
							Object optionss[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
							int index = 0;
							for(int i = 0; i < 12; i++){
								if(Integer.parseInt((String) optionss[i]) == Integer.parseInt(y3[0])){
									index = i;
									break;
								}
							}
							
							String minute = y4[0].charAt(0) + "" + y4[0].charAt(1);
							int index1 = 0;
							for(int i = 0; i < 4; i++){
								if(Integer.parseInt((String) options1[i]) == Integer.parseInt(minute)){
									index1 = i;
									break;
								}
							}
							String daytime = y4[0].charAt(2)+"M";
							int index2 = 0;
							for(int i = 0; i < 2; i++){
								if(options2[i].equals(daytime)){
									index2 = i;
									break;
								}
							}
							startHour1.setSelectedIndex(index);
							startMinute1.setSelectedIndex(index1);
							startTime1.setSelectedIndex(index2);
							
							index = 0;
							for(int i = 0; i < 12; i++){
								if(Integer.parseInt((String) optionss[i])==Integer.parseInt(y5[0])){
									index = i;
									break;
								}
							}
						
							minute = y6[0].charAt(0) + "" + y6[0].charAt(1);
							index1 = 0;
							for(int i = 0; i < 4; i++){
								if(Integer.parseInt((String) options1[i])==Integer.parseInt(minute)){
									index1 = i;
									break;
								}
							}
						
							String daytime1 = y6[0].charAt(2)+"M";
							index2 = 0;
							for(int i = 0; i < 2; i++){
								if(options2[i].equals(daytime1)){
									index2 = i;
									break;
								}
							}
							endHour1.setSelectedIndex(index);
							endMinute1.setSelectedIndex(index1);
							endTime1.setSelectedIndex(index2);
							 iosCalendar.super.setTitle("Edit Event");
							((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "edit");
							
						}

					}
					
				});
				J5.add(actual.get(currentLocation).get(eventLocation).get(actual.get(currentLocation).get(eventLocation).size()-1));
				((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "Home");
				 iosCalendar.super.setTitle("Calendar");
			}
			
		});
		createEvent.add(CREATE);
		JButton CANCEL = new JButton("Cancel");
		CANCEL.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "Home");
				 iosCalendar.super.setTitle("Calendar");
			}
			
		});
		createEvent.add(CANCEL);
	
		title1 = new TextField("", 20);
		
		location1 = new TextField("", 20);

		JPanel row1 = new JPanel();
		row1.add(new JLabel("Title: "));
		row1.add(title1);
		EditEvent.setLayout(new BoxLayout(EditEvent, BoxLayout.PAGE_AXIS));
		EditEvent.add(row1);
		row1 = new JPanel();
		row1.add(new JLabel("Location: "));
		row1.add(location1);
		EditEvent.add(row1);
		row1 = new JPanel();
		row1.add(new JLabel("Start: "));
		startHour1 = new JComboBox(options);
		
		startMinute1 = new JComboBox(options1);
		startTime1 = new JComboBox(options2);
		

		row1.add(startHour1);
		row1.add(startMinute1);
		row1.add(startTime1);
		EditEvent.add(row1);
		row1 = new JPanel();
		row1.add(new JLabel("End: "));
		
		endHour1 = new JComboBox(options);
		
		endMinute1 = new JComboBox(options1);
		
		endTime1 = new JComboBox(options2);
		
		row1.add(endHour1);
		row1.add(endMinute1);
		row1.add(endTime1);
		EditEvent.add(row1);
		JButton SAVE = new JButton("Save Event");
		SAVE.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actual.get(currentLocation).get(eventLocation).get(eventeventLocation).setText(title1.getText() + "-" + location1.getText() + " From " + startHour1.getSelectedItem() + ":" + startMinute1.getSelectedItem() + startTime1.getSelectedItem() + "-" + endHour1.getSelectedItem() + ":" + endMinute1.getSelectedItem() + endTime1.getSelectedItem());
				((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "Home");
				 iosCalendar.super.setTitle("Calendar");
			}
			
		});
		EditEvent.add(SAVE);
		JButton CANCEL2 = new JButton("Cancel");
		CANCEL2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "Home");
				 iosCalendar.super.setTitle("Calendar");
			}
			
		});
		EditEvent.add(CANCEL);
		add(Outerpanel);
		int start = c.get(Calendar.DAY_OF_WEEK);
		
		for(int i = start - 1 ; i > 0; i -- ){
			J4.add(events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i]);
			if(actual.get(currentLocation-1).get(temp.getActualMaximum(temp.DAY_OF_MONTH)-i).size() > 0){
				events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setText((temp.getActualMaximum(temp.DAY_OF_MONTH)-i+1 )+ "  E");
			}
			events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setEnabled(false);
		}
		for(int i = 0; i < Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH); i++){
			J4.add(events.get(currentLocation)[i]);
			if(actual.get(currentLocation).get(i).size() > 0){
				events.get(currentLocation)[i].setText((i+1) + "  E");
			}
			events.get(currentLocation)[i].setEnabled(true);
		}
		events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.BLUE);
	    c.set(c.DATE, c.getActualMaximum(Calendar.DATE));
	    int end = c.get(Calendar.DAY_OF_WEEK);
	    for(int i = end; i < 7; i++){
	    	J4.add(events.get(currentLocation+1)[i-end]);
	    	if(actual.get(currentLocation+1).get(i-end).size()>0){
	    		events.get(currentLocation+1)[i-end].setText((i-end+1) + "  E");
	    	}
	    	events.get(currentLocation+1)[i-end].setEnabled(false);
	    }
		setVisible(true);
		
	}
	public void nextMonth(){
		currentLocation++;
		if(events.size()-1 < currentLocation + 1){
			temp = c;
			c = temp2;
			c.set(Calendar.DAY_OF_MONTH, 1);
			if(temp2.get(temp2.MONTH)==11){
				temp2 = new GregorianCalendar(temp2.get(temp2.YEAR)+1, 0, 1);
			}
			else{
				temp2 = new GregorianCalendar(temp2.get(temp2.YEAR), temp2.get(temp2.MONTH)+1, 1);
			}
			events.add(new JButton[temp2.getActualMaximum(temp2.DAY_OF_MONTH)]);
			actual.add(new ArrayList<ArrayList<JButton>>());
			for(int i = 0; i < temp2.getActualMaximum(temp2.DAY_OF_MONTH); i++){
				events.get(currentLocation+1)[i] = new JButton(""+(i+1));
				actual.get(currentLocation+1).add(new ArrayList<JButton>());
				final int y = i;
		
				events.get(currentLocation+1)[i].addMouseListener(new MouseListener(){
					final int x = y;
					ArrayList<JButton> message = actual.get(currentLocation+1).get(x);
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						if(e.getClickCount() == 1 && !e.isConsumed()){
							e.consume();
							J5.removeAll();
							Home.validate();
							for(int j = 0; j < events.size(); j++){
								for(int i = 0; i < events.get(j).length; i++){
									events.get(j)[i].setBackground(null);
								}
							}
							if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
								events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
							}
							events.get(currentLocation)[x].setBackground(Color.BLUE);
							J5.setLayout(new BoxLayout(J5, BoxLayout.PAGE_AXIS));
							if(message.size() == 0){
								J5.add(new JButton("No Events"));
							}
							else{
								for(int i = 0; i < message.size(); i++){
									J5.add(message.get(i));
								}
							}
							
							Home.validate();
						}
						else if (e.getClickCount() == 2 && !e.isConsumed()) {
						     e.consume();
						     eventLocation = x;
						     iosCalendar.super.setTitle("Create Event");
						     title.setText("");
						     location.setText("");
						     startHour.setSelectedIndex(0);
						     startMinute.setSelectedIndex(0);
						     startTime.setSelectedIndex(0);
						     ((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "create");
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});

			}
			int start = c.get(c.DAY_OF_WEEK);
			J4.removeAll();
			J4.setLayout(new GridLayout(0, 7));
			for(int i = start - 1 ; i > 0; i -- ){
				J4.add(events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i]);
				if(actual.get(currentLocation-1).get(temp.getActualMaximum(temp.DAY_OF_MONTH)-i).size() > 0){
					events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setText((temp.getActualMaximum(temp.DAY_OF_MONTH)-i+1 )+ "  E");
				}
				events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setEnabled(false);
			}
			for(int i = 0; i < c.getActualMaximum(c.DAY_OF_MONTH); i++){
				J4.add(events.get(currentLocation)[i]);
				if(actual.get(currentLocation).get(i).size() > 0){
					events.get(currentLocation)[i].setText((i+1) + "  E");
				}
				events.get(currentLocation)[i].setEnabled(true);
			}
		    c.set(c.DATE, c.getActualMaximum(c.DATE));
		    int end = c.get(c.DAY_OF_WEEK);
		    for(int i = end; i < 7; i++){
		    	J4.add(events.get(currentLocation+1)[i-end]);
		    	if(actual.get(currentLocation+1).get(i-end).size()>0){
		    		events.get(currentLocation+1)[i-end].setText((i-end+1) + "  E");
		    	}
		    	events.get(currentLocation+1)[i-end].setEnabled(false);
		    }
			
		    J4.revalidate();
		}
		else{
			temp = c;
			c = temp2;
			c.set(Calendar.DAY_OF_MONTH, 1);
			if(temp2.get(temp2.MONTH)==11){
				temp2 = new GregorianCalendar(temp2.get(temp2.YEAR)+1, 0, 1);
			}
			else{
				temp2 = new GregorianCalendar(temp2.get(temp2.YEAR), temp2.get(temp2.MONTH)+1, 1);
			}
			int start = c.get(c.DAY_OF_WEEK);
			J4.removeAll();
			J4.setLayout(new GridLayout(0, 7));
			for(int i = start - 1 ; i > 0; i -- ){
				J4.add(events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i]);
				if(actual.get(currentLocation-1).get(temp.getActualMaximum(temp.DAY_OF_MONTH)-i).size() > 0){
					events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setText((temp.getActualMaximum(temp.DAY_OF_MONTH)-i+1 )+ "  E");
				}
				events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setEnabled(false);
			}
			for(int i = 0; i < c.getActualMaximum(c.DAY_OF_MONTH); i++){
				J4.add(events.get(currentLocation)[i]);
				if(actual.get(currentLocation).get(i).size() > 0){
					events.get(currentLocation)[i].setText((i+1) + "  E");
				}
				events.get(currentLocation)[i].setEnabled(true);
			}
		    c.set(c.DATE, c.getActualMaximum(c.DATE));
		    int end = c.get(c.DAY_OF_WEEK);
		    for(int i = end; i < 7; i++){
		    	J4.add(events.get(currentLocation+1)[i-end]);
		    	if(actual.get(currentLocation+1).get(i-end).size()>0){
		    		events.get(currentLocation+1)[i-end].setText((i-end+1) + "  E");
		    	}
		    	events.get(currentLocation+1)[i-end].setEnabled(false);
		    }
			
		    J4.revalidate();
		}
	}
	public void prevMonth(){
		currentLocation--;
		if(currentLocation == 0){
			temp2 = c;
			c = temp;
			c.set(Calendar.DAY_OF_MONTH, 1);
			if(temp.get(temp.MONTH)==0){
				temp = new GregorianCalendar(temp.get(temp.YEAR)-1, 11, 1);
			}
			else{
				temp = new GregorianCalendar(temp.get(temp.YEAR), temp.get(temp.MONTH)-1, 1);
			}
			events.add(0, new JButton[temp.getActualMaximum(temp.DAY_OF_MONTH)]);
			actual.add(0, new ArrayList<ArrayList<JButton>>());
			currentLocation = 1;
			for(int i = 0; i < temp.getActualMaximum(temp.DAY_OF_MONTH); i++){
				events.get(currentLocation-1)[i] = new JButton(""+(i+1));
				actual.get(currentLocation-1).add(new ArrayList<JButton>());
				final int y = i;
				events.get(currentLocation-1)[i].addMouseListener(new MouseListener(){
					final int x = y;
					ArrayList<JButton> message = actual.get(currentLocation-1).get(x);
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						if(e.getClickCount() == 1 && !e.isConsumed()){
							e.consume();
							J5.removeAll();
							Home.validate();
							for(int j = 0; j < events.size(); j++){
								for(int i = 0; i < events.get(j).length; i++){
									events.get(j)[i].setBackground(null);
								}
							}
							if((calendar[Calendar.getInstance().get(Calendar.MONTH)] + " " + Calendar.getInstance().get(Calendar.YEAR)).equals(l.getText())){
								events.get(currentLocation)[Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1].setBackground(Color.YELLOW);
							}
							events.get(currentLocation)[x].setBackground(Color.BLUE);
							J5.setLayout(new BoxLayout(J5, BoxLayout.PAGE_AXIS));
							if(message.size() == 0){
								J5.add(new JButton("No Events"));
							}
							else{
								for(int i = 0; i < message.size(); i++){
									J5.add(message.get(i));
								}
							}
							
							Home.validate();
						}
						else if (e.getClickCount() == 2 && !e.isConsumed()) {
						     e.consume();
						     eventLocation = x;
						     iosCalendar.super.setTitle("Create Event");
						     title.setText("");
						     location.setText("");
						     startHour.setSelectedIndex(0);
						     startMinute.setSelectedIndex(0);
						     startTime.setSelectedIndex(0);
						     ((CardLayout) Outerpanel.getLayout()).show(Outerpanel, "create");
						}
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});

			}
			int start = c.get(c.DAY_OF_WEEK);
			J4.removeAll();
			J4.setLayout(new GridLayout(0, 7));
			for(int i = start - 1 ; i > 0; i -- ){
				J4.add(events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i]);
				if(actual.get(currentLocation-1).get(temp.getActualMaximum(temp.DAY_OF_MONTH)-i).size() > 0){
					events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setText((temp.getActualMaximum(temp.DAY_OF_MONTH)-i+1 )+ "  E");
				}
				events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setEnabled(false);
			}
			for(int i = 0; i < c.getActualMaximum(c.DAY_OF_MONTH); i++){
				J4.add(events.get(currentLocation)[i]);
				if(actual.get(currentLocation).get(i).size() > 0){
					events.get(currentLocation)[i].setText((i+1) + "  E");
				}
				events.get(currentLocation)[i].setEnabled(true);
			}
		    c.set(c.DATE, c.getActualMaximum(c.DATE));
		    int end = c.get(c.DAY_OF_WEEK);
		    for(int i = end; i < 7; i++){
		    	J4.add(events.get(currentLocation+1)[i-end]);
		    	if(actual.get(currentLocation+1).get(i-end).size()>0){
		    		events.get(currentLocation+1)[i-end].setText((i-end+1) + "  E");
		    	}
		    	events.get(currentLocation+1)[i-end].setEnabled(false);
		    }
			
		    J4.revalidate();
		}
		else{
			temp2 = c;
			c = temp;
			c.set(Calendar.DAY_OF_MONTH, 1);
			if(temp.get(temp.MONTH)==0){
				temp = new GregorianCalendar(temp.get(temp.YEAR)-1, 11, 1);
			}
			else{
				temp = new GregorianCalendar(temp.get(temp.YEAR), temp.get(temp.MONTH)-1, 1);
			}
			
			int start = c.get(c.DAY_OF_WEEK);
			J4.removeAll();
			J4.setLayout(new GridLayout(0, 7));
			for(int i = start - 1 ; i > 0; i -- ){
				J4.add(events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i]);
				if(actual.get(currentLocation-1).get(temp.getActualMaximum(temp.DAY_OF_MONTH)-i).size() > 0){
					events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setText((temp.getActualMaximum(temp.DAY_OF_MONTH)-i+1 )+ "  E");
				}
				events.get(currentLocation-1)[temp.getActualMaximum(temp.DAY_OF_MONTH)-i].setEnabled(false);
			}
			for(int i = 0; i < c.getActualMaximum(c.DAY_OF_MONTH); i++){
				J4.add(events.get(currentLocation)[i]);
				if(actual.get(currentLocation).get(i).size() > 0){
					events.get(currentLocation)[i].setText((i+1) + "  E");
				}
				events.get(currentLocation)[i].setEnabled(true);
			}
		    c.set(c.DATE, c.getActualMaximum(c.DATE));
		    int end = c.get(c.DAY_OF_WEEK);
		    for(int i = end; i < 7; i++){
		    	J4.add(events.get(currentLocation+1)[i-end]);
		    	if(actual.get(currentLocation+1).get(i-end).size()>0){
		    		events.get(currentLocation+1)[i-end].setText((i-end+1) + "  E");
		    	}
		    	events.get(currentLocation+1)[i-end].setEnabled(false);
		    }
			
		    J4.revalidate();
		}
	}

}
