package de.fhswf.verwaltung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.standbysoft.component.date.swing.JDatePicker;

/**
 * Hauptfenster der Fahrerverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schaefer
 * @version 1.0
 */

public class DialogStudent extends JDialog
							implements ActionListener{
	
	/** Version. */
	   private static final long serialVersionUID = 1L;
	   
	   private Vector<JCheckBox> checkBoxMap = new Vector<JCheckBox>();

	   protected JTextField nameStudent;
	   private JTextField vornameStudent;
	   private JTextField emailStudent;
	   private JTextField martStudent;
		
	   protected JCheckBox A1, A, B, C1, C, D1, D, BE, C1E, CE, D1E, DE, M, L, TS;
	   protected JDatePicker fDatum;
	   
	   private JButton deleteButton;
	   private JButton relationButton;
	   private JButton saveButton;
	   private JButton exitButton;
	   
	   private  TableModelCompany tableDataFahrzeug = new TableModelCompany();
	   
	   private int row = -1;
	   
	   private JDialog dialog;


	   
	   
	   /**
	    * Bastelt die GUI fürs Hauptfenster.
	    */
	   public DialogStudent (String title, MainWindow parent, TableModelStudent model)
	   {
	      super();
	      if (title == null)
	         throw new IllegalArgumentException("Title must not be null!");
	      
//	      fKlasseStrings = parent.noteStrings;

	      setTitle(title);
	      final int width = 540;
	      final int height = 420;
	      dialog = this;
	      
	      JFrame.setDefaultLookAndFeelDecorated(true);

	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      Container c = getContentPane();

	      JPanel vertBox = new JPanel();
	      vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.PAGE_AXIS));

	      JPanel vertBox1 = new JPanel();
	      vertBox1.setLayout(new BorderLayout());
	      JPanel vertBox2 = new JPanel();
	      vertBox2.setLayout(new BorderLayout());
	      
	      JPanel horiBox = new JPanel();
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel matrLabel = new JLabel(" Matrikelnummer: ");
	      matrLabel.setPreferredSize(new Dimension(140, 30));
	      matrLabel.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(matrLabel);
	      martStudent = new JTextField();
	      horiBox.add(martStudent);
	      vertBox.add(horiBox);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel nameLabel = new JLabel(" Name: ");
	      nameLabel.setPreferredSize(new Dimension(140, 30));
	      nameLabel.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(nameLabel);
	      nameStudent = new JTextField();
	      horiBox.add(nameStudent);
	      vertBox.add(horiBox);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel vornameLabel = new JLabel(" Vorname: ");
	      vornameLabel.setPreferredSize(new Dimension(140, 30));
	      vornameLabel.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(vornameLabel);
	      vornameStudent = new JTextField();
	      horiBox.add(vornameStudent);
	      vertBox.add(horiBox);
	      
	      horiBox = new JPanel();
	      horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
	      JLabel emailLabel = new JLabel(" E-Mail: ");
	      emailLabel.setPreferredSize(new Dimension(140, 30));
	      emailLabel.setMaximumSize(new Dimension(140, 30));
	      horiBox.add(emailLabel);
	      emailStudent = new JTextField();
	      horiBox.add(emailStudent);
	      vertBox.add(horiBox);
	      
	      JTable tableCar = new JTable();
	      tableCar.setModel(tableDataFahrzeug);
//	      tableCar.addMouseListener(new TableClickListener(tableCar, frame, tableDataCompany));
	      JScrollPane tableScrollPaneCar = new JScrollPane(tableCar);
	      tableScrollPaneCar
	            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	      tableScrollPaneCar.setPreferredSize(new Dimension(400, 150));
	      tableScrollPaneCar.setMaximumSize(new Dimension(440, 150));
	      tableScrollPaneCar.setBorder(BorderFactory
	            .createCompoundBorder(BorderFactory.createCompoundBorder(
	                  BorderFactory.createTitledBorder("Fahrzeuguebersicht"), BorderFactory
	                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneCar
	                  .getBorder()));
	      
	      vertBox.add(tableScrollPaneCar);
	       
	      deleteButton = new JButton("Löschen");
	      deleteButton.addActionListener(new ActionStudent("del", this, parent, model));
	      deleteButton.setEnabled(false);

	      saveButton = new JButton("Speichern");
	      saveButton.addActionListener(new ActionStudent("add", this, parent, model));
	      saveButton.setEnabled(true);

	      exitButton = new JButton("Abbrechen");
	      exitButton.addActionListener(new ActionListener()
	      {
	         @Override
	         public void actionPerformed(ActionEvent e)
	         {
	            dialog.dispose();
	         }
	      });
	      exitButton.setEnabled(true);

	      JPanel buttonBox = new JPanel();
	      buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.LINE_AXIS));
	      buttonBox.add(deleteButton);
	      buttonBox.add(saveButton);
	      buttonBox.add(exitButton);

	      vertBox.add(buttonBox, BorderLayout.SOUTH);
	      
	      // ClassStudent, Tabelle und Buttons
	      c.add(vertBox, BorderLayout.NORTH);
	      
	      setSize(width, height);
	      setVisible(true);
	   }

	   public void setDeleteEnabled(boolean state)
	   {
	      deleteButton.setEnabled(state);
	   }
	   
	   public void setRelationEnabled(boolean state)
	   {
	      relationButton.setEnabled(state);
	   }
	   
	   public void setSaveEnabled(boolean state)
	   {
	      saveButton.setEnabled(state);
	   }
	   
	   public void setExitEnabled(boolean state)
	   {
	      exitButton.setEnabled(state);
	   }  
	 
	   @SuppressWarnings("deprecation")
	public void loadClassStudent(ClassStudent classStudent)
	   {
//	      nameStudent.setText(classStudent.getName());
//	      for(JCheckBox checkBox : checkBoxMap)
//	      {
//	    	  if(classStudent.getFueKlasse().contains(checkBox.getLabel().trim() + ","))
//	    		  checkBox.setSelected(true);
//	    	  if(classStudent.getFueKlasse().endsWith(checkBox.getLabel().trim()))
//	    		  checkBox.setSelected(true);
//	      }
//	      fDatum.setSelectedDate(new Date(classStudent.getFueSeit().getYear()-1900, classStudent.getFueSeit().getMonth(), classStudent.getFueSeit().getDate()));
	   }

	public Integer getRow() {
		return row;
	}

	public void setRow(int row) {
//		System.out.println(row.toString());
		this.row = row;
	}

	public Vector<JCheckBox> getCheckBoxMap() {
		return checkBoxMap;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
