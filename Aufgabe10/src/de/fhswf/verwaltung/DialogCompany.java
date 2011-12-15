package de.fhswf.verwaltung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.standbysoft.component.date.swing.JDatePicker;

public class DialogCompany extends JDialog
        implements ActionListener {
    /** Version. */
    private static final long serialVersionUID = 1L;

    protected JTextField      kennzeichen;

    private  TableModelStudent tableDataFahrer = new TableModelStudent();

    protected JDatePicker     erstzulassung;

    private JButton           deleteButton;
    private JButton           saveButton;
    private JButton           exitButton;

    private int               row              = -1;

    private JDialog           dialog;

    /**
     * Bastelt die GUI fuers Hauptfenster.
     */
    public DialogCompany(String title, MainWindow parent, TableModelCompany model)
    {
        super();
        if (title == null)
            throw new IllegalArgumentException("Title must not be null!");

        setTitle(title);
	    final int width = 480;
	    final int height = 420;
	    dialog = this;

        JFrame.setDefaultLookAndFeelDecorated(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = getContentPane();

        JPanel vertBox = new JPanel();
        vertBox.setLayout(new BoxLayout(vertBox, BoxLayout.PAGE_AXIS));

        JPanel horiBox = new JPanel();

        horiBox = new JPanel();
        horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
        JLabel kennzLabel = new JLabel(" Kennzeichen: ");
        kennzLabel.setPreferredSize(new Dimension(100, 10));
        horiBox.add(kennzLabel);
        kennzeichen = new JTextField();
        horiBox.add(kennzeichen);
        vertBox.add(horiBox);

        horiBox = new JPanel();
        horiBox.setLayout(new BoxLayout(horiBox, BoxLayout.LINE_AXIS));
        JLabel erstzLabel = new JLabel(" Erstzulassung: ");
        erstzLabel.setPreferredSize(new Dimension(100, 10));
        horiBox.add(erstzLabel);
        erstzulassung = new JDatePicker();
        horiBox.add(erstzulassung);
        vertBox.add(horiBox);
        
        JTable tableDriver = new JTable();
        tableDriver.setModel(tableDataFahrer);
//      tableDriver.addMouseListener(new TableClickListener(tableDriver, frame, tableDataStudent));
        JScrollPane tableScrollPaneDriver = new JScrollPane(tableDriver);
        tableScrollPaneDriver
              .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    tableScrollPaneDriver.setPreferredSize(new Dimension(400, 300));
	    tableScrollPaneDriver.setMaximumSize(new Dimension(440, 300));
        tableScrollPaneDriver.setBorder(BorderFactory
              .createCompoundBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Fahreruebersicht"), BorderFactory
                          .createEmptyBorder(10, 10, 10, 10)), tableScrollPaneDriver
                    .getBorder()));
      
        vertBox.add(tableScrollPaneDriver);
        
        deleteButton = new JButton("Loeschen");
        deleteButton.addActionListener(new ActionCompany("del", this, parent, model));
        deleteButton.setEnabled(false);

        saveButton = new JButton("Speichern");
        saveButton.addActionListener(new ActionCompany("add", this, parent, model));
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

        // Noten und Buttons
        c.add(vertBox, BorderLayout.NORTH);

        setSize(width, height);
        setVisible(true);
    }

    public void setDeleteEnabled(boolean state)
    {
        deleteButton.setEnabled(state);
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
	public void loadClassCompany(ClassCompany classCompany)
    {
        kennzeichen.setText(classCompany.getKennzeichen());
        erstzulassung.setSelectedDate(new Date(classCompany.getErstzulassung().getYear()-1900, classCompany.getErstzulassung().getMonth(), classCompany.getErstzulassung().getDate()));
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(int row) {
//         System.out.println(row.toString());
        this.row = row;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
