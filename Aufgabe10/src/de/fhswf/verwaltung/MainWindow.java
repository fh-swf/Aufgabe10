package de.fhswf.verwaltung;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


/**
 * Hauptfenster der Fuhrparkverwaltung
 * 
 *
 * @author Michael Rockstein & Philipp Schaefer
 * @version 1.0
 */
public class MainWindow extends JFrame
{
   /** Version. */
   private static final long serialVersionUID = 1L;
   
   public Vector<ClassStudent> studentMap = new Vector<ClassStudent>();
   public Vector<ClassStudent> studentMapDel = new Vector<ClassStudent>();
   public Vector<ClassCompany> companyMap = new Vector<ClassCompany>();
   public Vector<ClassCompany> companyMapDel = new Vector<ClassCompany>();
   public Vector<ClassProject> projectMap = new Vector<ClassProject>();
   public Vector<ClassProject> projectMapDel = new Vector<ClassProject>();
   public TableModelStudent tableDataStudent = new TableModelStudent();
   public TableModelCompany tableDataCompany = new TableModelCompany();
   public TableModelProject tableDataProject = new TableModelProject();

   MainWindow frame;
   Database database = new Database();
   /**
    * Bastelt die GUI fuers Hauptfenster.
    */
   public MainWindow()
   {
      super("MainWindow");
      
      final int width = 1024;
      final int height = 700;
      frame = this;
      JFrame.setDefaultLookAndFeelDecorated(true);

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent we){
//            database.write(frame);
            database.shutdown();
            System.exit(0);
         }
       });
      
      
      JMenuBar menuBar;
      JMenu menuFile;
      JMenu menuHelp;
      JMenuItem actionExit;
      JMenuItem actionInfo;
      JMenuItem actionNewStudent;
      JMenuItem actionNewCompany;
      JMenuItem actionNewProject;
      JMenuItem actionLoadDb;
      JMenuItem actionWriteDb;
      
      menuBar = new JMenuBar();
      menuFile = new JMenu("Datei");
      menuFile.setMnemonic(KeyEvent.VK_D);
      menuFile.getAccessibleContext().setAccessibleDescription("Datei-Menue");
      menuBar.add(menuFile);

      menuHelp = new JMenu("Hilfe");
      menuHelp.setMnemonic(KeyEvent.VK_H);
      menuHelp.getAccessibleContext().setAccessibleDescription("Hilfe-Menue");
      menuBar.add(menuHelp);

      actionNewStudent = new JMenuItem("Neuen Student...", KeyEvent.VK_N);
      actionNewStudent.getAccessibleContext().setAccessibleDescription(
            "Neuen Student anlegen.");
      actionNewStudent.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
              new DialogStudent("Student hinzufuegen", frame, tableDataStudent).setRow(-1);
         }
      });
      menuFile.add(actionNewStudent);

      
      actionNewCompany = new JMenuItem("Neue Company...", KeyEvent.VK_L);
      actionNewCompany.getAccessibleContext().setAccessibleDescription(
            "Neue Company anlegen.");
      actionNewCompany.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent e)
          {
               new DialogCompany("Company hinzufuegen", frame, tableDataCompany).setRow(-1);
          }
       });
      menuFile.add(actionNewCompany);
      
      
      actionNewProject = new JMenuItem("Neues Projekt...", KeyEvent.VK_L);
      actionNewProject.getAccessibleContext().setAccessibleDescription(
            "Neues Projekt anlegen.");
      actionNewProject.addActionListener(new ActionListener()
      {
          @Override
          public void actionPerformed(ActionEvent e)
          {
               new DialogProject("Projekt hinzufuegen", frame, tableDataProject).setRow(-1);
          }
       });
      menuFile.add(actionNewProject);
      
      
      actionLoadDb = new JMenuItem("Laden...", KeyEvent.VK_L);
      actionLoadDb.getAccessibleContext().setAccessibleDescription(
            "Aus Datenbank Laden.");
      actionLoadDb.addActionListener(new ActionListener()
      {
    	  @Override
          public void actionPerformed(ActionEvent e)
          {
    		   studentMap.clear();
    		   studentMapDel.clear();
    		   companyMap.clear();
    		   companyMapDel.clear();
    		   projectMap.clear();
    		   projectMapDel.clear();
    		   tableDataStudent.clear();
    		   tableDataCompany.clear();
    		   tableDataProject.clear();
         	database.load(frame);
          }
       });
      menuFile.add(actionLoadDb);
      
      
      actionWriteDb = new JMenuItem("Schreiben...", KeyEvent.VK_L);
      actionWriteDb.getAccessibleContext().setAccessibleDescription(
            "In Datenbank schreiben.");
      actionWriteDb.addActionListener(new ActionListener()
      {
    	  @Override
          public void actionPerformed(ActionEvent e)
          {
//         	database.write(frame);
          }
       });
      menuFile.add(actionWriteDb);
      
      
      actionExit = new JMenuItem("Beenden", KeyEvent.VK_E);
      actionExit.getAccessibleContext().setAccessibleDescription(
            "Beendet die Anwendung");
      actionExit.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
//        	database.write(frame);
            database.shutdown();
            System.exit(0);
         }
      });
      menuFile.add(actionExit);

      actionInfo = new JMenuItem("Info", KeyEvent.VK_I);
      actionInfo.getAccessibleContext().setAccessibleDescription(
            "About Dialog");
      actionInfo.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            JDialog aboutDialog = new JDialog();
            JLabel aboutDialogLabel = new JLabel(
                  "Fahrzeugverwaltung - Version 0.8b", JLabel.CENTER);
            aboutDialog.add(aboutDialogLabel);
            aboutDialog.setSize(320, 200);
            aboutDialog.setVisible(true);
         }
      });
      menuHelp.add(actionInfo);

      setJMenuBar(menuBar);
      
      Container c = getContentPane();

      JTable tableStudent = new JTable();
      tableStudent.setModel(tableDataStudent);
      tableStudent.addMouseListener(new TableClickListenerStudent(tableStudent, frame, tableDataStudent));
      JScrollPane tableScrollPaneStudent = new JScrollPane(tableStudent);
      tableScrollPaneStudent
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//      tableScrollPaneStudent.setPreferredSize(new Dimension(400, 400));
      tableScrollPaneStudent.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Studentenuebersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneStudent
                  .getBorder()));
      
      JTable tableCompany = new JTable();
      tableCompany.setModel(tableDataCompany);
      tableCompany.addMouseListener(new TableClickListenerCompany(tableCompany, frame, tableDataCompany));
      JScrollPane tableScrollPaneCompany = new JScrollPane(tableCompany);
      tableScrollPaneCompany
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//      tableScrollPaneCompany.setPreferredSize(new Dimension(240, 400));
      tableScrollPaneCompany.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Firmenuebersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneCompany
                  .getBorder()));
      
      JTable tableProject = new JTable();
      tableProject.setModel(tableDataProject);
      tableProject.addMouseListener(new TableClickListenerProject(tableProject, frame, tableDataProject));
      JScrollPane tableScrollPaneProject = new JScrollPane(tableProject);
      tableScrollPaneProject
            .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//      tableScrollPaneProject.setPreferredSize(new Dimension(240, 400));
      tableScrollPaneProject.setBorder(BorderFactory
            .createCompoundBorder(BorderFactory.createCompoundBorder(
                  BorderFactory.createTitledBorder("Projektuebersicht"), BorderFactory
                        .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneProject
                  .getBorder()));
    
          
      JPanel hBox = new JPanel();
      hBox.setLayout(new BoxLayout(hBox, BoxLayout.LINE_AXIS));
      JPanel vBox = new JPanel();
      vBox.setLayout(new BoxLayout(vBox, BoxLayout.PAGE_AXIS));
      
      hBox.add(tableScrollPaneStudent);
      hBox.add(tableScrollPaneCompany);
      vBox.add(hBox);
      vBox.add(tableScrollPaneProject);
      
      c.add(vBox);
            
      setSize(width, height);
      setVisible(true);
   }

   /**
    * Hauptfunktion des Programms.
    * 
    * @param args
    *           Argumente - derzeit ungenutzt.
    */
   public static void main(String[] args)
   {
//       Display the window in a thread safe way.
	   MainWindow frame = new MainWindow();
	   Database database = new Database();
	   database.load(frame);
       new JFrameShower(frame);

   }
   
   public void addClassStudent(ClassStudent classStudent)
   {
      studentMap.add(classStudent);
   }
   
   public void editClassStudent(ClassStudent classStudent, int row)
   {
 	  studentMap.remove(row);
 	  studentMap.insertElementAt(classStudent, row);
   }
   public void delFahrer(int row)
   {
	   studentMapDel.add(studentMap.get(row));
	   studentMap.remove(row);
   }
   
   public ClassStudent getClassStudent(int index)
   {
      return studentMap.get(index);
   }
   
   public Vector<ClassStudent> getClassStudentMap()
   {
 	  return studentMap;
   }
   
   
   public void addClassCompany(ClassCompany classCompany)
   {
	   companyMap.add(classCompany);
   }
   
   public void editClassCompany(ClassCompany classCompany, int row)
   {
	  companyMap.remove(row);
	  companyMap.insertElementAt(classCompany, row);
   }
   public void delFahrzeug(int row)
   {
	   companyMapDel.add(companyMap.get(row));
	   companyMap.remove(row);
   }
   
   public ClassCompany getClassCompany(int index)
   {
      return companyMap.get(index);
   }
   
   public Vector<ClassCompany> getClassCompanyMap()
   {
 	  return companyMap;
   }
   
   
   public void addClassProject(ClassProject classProject)
   {
	   projectMap.add(classProject);
   }
   
   public void delDriverCar(int row)
   {
	   projectMapDel.add(projectMap.get(row));
	   projectMap.remove(row);
   }
   
   public ClassProject getClassProject(int index)
   {
      return projectMap.get(index);
   }
   
   public Vector<ClassProject> getClassProjectMap()
   {
 	  return projectMap;
   }

}
