/*
 * WeatherUI.java
 * Author: Priyanka Bhula
 * 
 * */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class WeatherUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	//fetch all pane
	private JButton fetchAllButton;
	private JButton coldestButton;
	private JButton warmestButton;
	private JTextArea foutput;
	
	//fetch one record pane
	private JTextArea routput;
	private JButton fetchRecordButton;
	private JTextField recToFetch;

	//add pane
	private JButton addButton;
	private JLabel aoutput;
	private JTextField newCity;
	private JTextField newTemp;
	private JTextField newWind;
	private JTextField newRain;	
	
	//delete pane
	private JButton delButton;
	private JLabel doutput;
	private JTextField delCity;

	//modify pane
	private JButton modButton;
	private JLabel moutput;
	private JTextField modCity;
	private JTextField modTemp;
	private JTextField modWind;
	private JTextField modRain;	

	private JTabbedPane tabbedPane;
	private WeatherModel model;


	public WeatherUI() {
		super ("JDBC library");
		
		//set window title
		setTitle("Weather API");
		//closes on X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setSize(600,300);	
		//initialises all the tabs
		initUI();
		model = new WeatherModel(this);
		setVisible(true);
	}

	private void initUI() {
		tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane,BorderLayout.NORTH);

		//add all the panes
		tabbedPane.addTab("Fetch Records", createFetchAllPane());
		tabbedPane.addTab("Fetch Single Record", createFetchRecordPane());
		tabbedPane.addTab("Add Record", createAddPane());
		tabbedPane.addTab("Delete Record", createDeletePane());
		tabbedPane.addTab("Modify Record", createModPane());

		//initialise all the action listeners
		initListener();
	}

	private Container createFetchAllPane() {
		fetchAllButton = new JButton("Fetch All Records");
		coldestButton = new JButton("Coldest City");
		warmestButton = new JButton("Warmest City");
		
		//text area to display results
		foutput = new JTextArea();
		foutput.setEditable(false);
		foutput.setPreferredSize(new Dimension(350, 100));
		foutput.setLineWrap(true);
		foutput.setWrapStyleWord(true);
		
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(1, 5, 1, 5);
		
		c.anchor = GridBagConstraints.WEST;
		addToGridBag(gb, c, pane, fetchAllButton, 0, 0, 20, 1);
		addToGridBag(gb, c, pane, coldestButton, 0, 2, 10, 1);
		addToGridBag(gb, c, pane, warmestButton, 10, 2, 10, 1);
		addToGridBag(gb, c, pane, foutput, 4, 4, 10, 1);

		return pane;
	}

	private Container createFetchRecordPane() {
		fetchRecordButton = new JButton ("Fetch City Record");
		recToFetch = new JTextField(10);
		
		//output area for results
		routput = new JTextArea();
		routput.setEditable(false);
		routput.setPreferredSize(new Dimension(350, 100));
		routput.setLineWrap(true);
		routput.setWrapStyleWord(true);
		
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(1, 5, 1, 5);
		
		c.anchor = GridBagConstraints.WEST;
		addToGridBag(gb, c, pane, recToFetch, 0, 1, 10, 1);
		addToGridBag(gb, c, pane, fetchRecordButton, 10, 1, 10, 1);
		addToGridBag(gb, c, pane, routput, 4, 4, 10, 1);

		return pane;
	}
	
	private Container createAddPane() {

		addButton = new JButton("Add New Record");
		aoutput = new JLabel("Enter today's details");
		newCity = new JTextField(10);
		newTemp = new JTextField(10);
		newWind = new JTextField(10);
		newRain = new JTextField(10);	

		JPanel pane = new JPanel();
		pane.setOpaque(false);
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(1, 5, 1, 5);
		

		c.anchor = GridBagConstraints.WEST;
		addToGridBag(gb, c, pane, new JLabel("City:"), 0, 0, 1, 1);
		addToGridBag(gb, c, pane, new JLabel("Temperature:"), 0, 1, 1, 1);
		addToGridBag(gb, c, pane, new JLabel("Wind:"), 0, 2, 1, 1);
		addToGridBag(gb, c, pane, new JLabel("Rain:"), 0, 3, 1, 1);
		
		addToGridBag(gb, c, pane, newCity,  10, 0, 10, 1);
		addToGridBag(gb, c, pane, newTemp, 10, 1, 3, 1);
		addToGridBag(gb, c, pane, newWind,  10, 2, 3, 1);
		addToGridBag(gb, c, pane, newRain, 10, 3, 3, 1);

		addToGridBag(gb, c, pane, addButton, 20, 0, 1, 3);
		addToGridBag(gb, c, pane, aoutput, 20, 1, 1, 3);

		return pane;
	}
	
	private Container createDeletePane() {

		delButton = new JButton("Delete Record");
		doutput = new JLabel("Choose city to delete");
		delCity = new JTextField(10);

		JPanel pane = new JPanel();
		pane.setOpaque(false);
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(1, 5, 1, 5);
		

		c.anchor = GridBagConstraints.WEST;
		addToGridBag(gb, c, pane, delCity,  0, 0, 10, 3);

		addToGridBag(gb, c, pane, delButton, 10, 0, 1, 3);
		addToGridBag(gb, c, pane, doutput, 0, 4, 1, 3);

		return pane;
	}
	
	private Container createModPane() {

		modButton = new JButton("Modify City Record");
		moutput = new JLabel("Enter city to change details");
		modCity = new JTextField(10);
		modTemp = new JTextField(10);
		modWind = new JTextField(10);
		modRain = new JTextField(10);	

		JPanel pane = new JPanel();
		pane.setOpaque(false);
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(1, 5, 1, 5);
		

		c.anchor = GridBagConstraints.WEST;
		addToGridBag(gb, c, pane, new JLabel("City:"), 0, 0, 1, 1);
		addToGridBag(gb, c, pane, new JLabel("Temperature:"), 0, 1, 1, 1);
		addToGridBag(gb, c, pane, new JLabel("Wind:"), 0, 2, 1, 1);
		addToGridBag(gb, c, pane, new JLabel("Rain:"), 0, 3, 1, 1);
		
		addToGridBag(gb, c, pane, modCity,  10, 0, 10, 1);
		addToGridBag(gb, c, pane, modTemp, 10, 1, 3, 1);
		addToGridBag(gb, c, pane, modWind,  10, 2, 3, 1);
		addToGridBag(gb, c, pane, modRain, 10, 3, 3, 1);

		addToGridBag(gb, c, pane, modButton, 20, 0, 1, 3);
		addToGridBag(gb, c, pane, moutput, 20, 1, 1, 3);

		return pane;
	}
	
	/*method for aligning elements in a gridbaglayout
	 * */
	private void addToGridBag(GridBagLayout gb, GridBagConstraints c,
			Container cont, JComponent item,
			int x, int y, int w, int h) {
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		gb.setConstraints(item, c);
		cont.add(item);
	}

	/*method for initialising all the listeners to connect each
	 * button to the respective method in the model
	 * */
	private void initListener() {		
		fetchAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = model.fetchAllRecords();
				foutput.setText(result);				
			}
		});

		fetchRecordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = recToFetch.getText();//make it ignore cases
				String result = model.fetchRecord(input);
				routput.setText(result);
			}
		});

		warmestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = model.getWarmest();
				foutput.setText(result);				
			}
		});

		coldestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = model.getColdest();
				foutput.setText(result);				
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newCity.getText().equals("")) {
					aoutput.setText("Please enter city name");
				}
				else if(newTemp.getText().equals("")){
					aoutput.setText("Please enter temperature");
				}
				else if(newWind.getText().equals("")){
					aoutput.setText("Please enter wind");
				}
				else if(newRain.getText().equals("")){
					aoutput.setText("Please enter rain");
				}
				else {
					String result = model.addRecord(newCity.getText(), Double.parseDouble(newTemp.getText()), Integer.parseInt(newWind.getText()), Integer.parseInt(newRain.getText()));
					aoutput.setText(result);
				}
			}
		});
		
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (delCity.getText().equals("")) {
					doutput.setText("Please enter city name");
				}
				else {
					String result = model.deleteRecord(delCity.getText());
					doutput.setText(result);
			
				}
			}
		});
		
		modButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(modCity.getText().equals("")) {
					moutput.setText("Please enter city name");
				}
				else if(modTemp.getText().equals("")){
					moutput.setText("Please enter temperature");
				}
				else if(modWind.getText().equals("")){
					moutput.setText("Please enter wind");
				}
				else if(modRain.getText().equals("")){
					moutput.setText("Please enter rain");
				}
				else {
					String result = model.modRecord(modCity.getText(), Double.parseDouble(modTemp.getText()), Integer.parseInt(modWind.getText()), Integer.parseInt(modRain.getText()));
					moutput.setText(result);
				}
			}
		});

	}	

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new WeatherUI();				
			}
		});
	}	


}
