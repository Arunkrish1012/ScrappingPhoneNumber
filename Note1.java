/*
    Developed by Arun
	Modified on :05-11-2019
	
	code for scrapping clipboard  Data

*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Note1  {
		public static void main(String args[]) {
	          /*try catch to handle unreported exception UnsupportedFlavorException  */
			   try{
				    /*Creating a frame for our Application and specifying its size*/
					JFrame frame = new JFrame("Scrap Phone number");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(400, 400);
					/*Using Clipboard class to get data which is copied on the clipboard 
					  and using regular expression to extract the needed pattern from data in the clipboard*/
					Clipboard c=Toolkit.getDefaultToolkit().getSystemClipboard();
					String data =(String)c.getData(DataFlavor.stringFlavor);
					String regexStr = "\\w*\\s{1,}\\d{10}|\\w*\\s{1,}+-+\\s{1,}\\d{10}|\\w*\\s{1,}(\\d{3}-){2}\\d{4}|\\w*\\s{1,}+-+\\s{1,}(\\d{3}-){2}\\d{4}|\\w*\\s{1,}\\(\\d{3}\\)\\d{3}-?\\d{4}|\\w*\\s{1,}+-+\\s{1,}\\(\\d{3}\\)\\d{3}-?\\d{4}|\\w*\\s{1,}+\\-\\d{10}|\\w*\\s{1,}+\\-\\d{3}|\\w*+\\-\\d{10}|\\w*+\\-\\d{3}";   
					Pattern p = Pattern.compile(regexStr);
					Matcher m = p.matcher(data);
					String Copied_Values="";
				    while(m.find()) {
					  
					    Copied_Values=Copied_Values+m.group()+"\n";

					}
					final String temp=Copied_Values;
					/*Creating a JPanel and creating the necessary Components our application*/
				    JPanel panel = new JPanel(); // the panel is not visible in output
					JTextField tf = new JTextField(10); // accepts upto 10 characters
					JTextArea ta = new JTextArea(Copied_Values);
					JButton search = new JButton("search");
					search.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){  
															      String Filter_values="";
																  String Filter_regx="";
																  String Filtered_values="";
																  /*Getting search value  from the textfield and using that search value to form regular expression  */
																  Filter_values=tf.getText();
						                                          Filter_regx= Filter_values+"(?:.*)";
						                                          Pattern p1 = Pattern.compile(Filter_regx, Pattern.CASE_INSENSITIVE);
																  Matcher m1 = p1.matcher(temp);
																  while(m1.find())
																  {
																	  Filtered_values=Filtered_values+m1.group()+"\n";
																  }
																  /*Replacing the textarea with filtered values*/
						                                          ta.selectAll();
						                                          ta.replaceSelection(Filtered_values);
															   }
															});  
					/*Adding components to Jpanel*/
					panel.add(tf);
					panel.add(search);
					Font font = new Font("", Font.BOLD, 20);
                    ta.setFont(font);
					frame.getContentPane().add(BorderLayout.SOUTH, panel);
					frame.getContentPane().add(BorderLayout.NORTH, tf);
					frame.getContentPane().add(BorderLayout.CENTER, ta);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
			   }
			   catch(Exception e)
			   {
			   }
		}
}
