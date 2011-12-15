package de.fhswf.verwaltung;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DialogProject extends JDialog implements ActionListener {
	/** Version. */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	public JComboBox driver;
	@SuppressWarnings("rawtypes")
	public JComboBox car;

	private JButton deleteButton;
	private JButton saveButton;
	private JButton exitButton;

	private int row = -1;
	private JDialog dialog;

	/**
	 * Bastelt die GUI fuers Hauptfenster.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DialogProject(String title, MainWindow parent,
			TableModelProject model) {
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
		JLabel driverLabel = new JLabel(" ClassStudent: ");
		driverLabel.setPreferredSize(new Dimension(100, 10));
		horiBox.add(driverLabel);
		driver = new JComboBox();
		for (ClassStudent classStudent : parent.classStudentMap) {
			driver.addItem(classStudent.getFahrer_ID() + "  : " + classStudent.getName());
		}
		driver.setEditable(false);
		driver.setSelectedIndex(0);
		driver.addActionListener(this);
		horiBox.add(driver);
		JLabel carLabel = new JLabel(" ClassCompany: ");
		carLabel.setPreferredSize(new Dimension(100, 10));
		horiBox.add(carLabel);
		car = new JComboBox();
		for (ClassCompany classCompany : parent.classCompanyMap) {
			car.addItem(classCompany.getFahrzeug_ID() + "  : "
					+ classCompany.getKennzeichen());
		}
		car.setEditable(false);
		car.setSelectedIndex(0);
		car.addActionListener(this);
		horiBox.add(car);
		vertBox.add(horiBox);

		JTable tableDriverCar = new JTable();
		tableDriverCar.setModel(parent.tableDataProject);
		// tableDriver.addMouseListener(new TableClickListener(tableDriver,
		// frame, tableDataStudent));
		JScrollPane tableScrollPaneDriverCar = new JScrollPane(tableDriverCar);
		tableScrollPaneDriverCar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tableScrollPaneDriverCar.setPreferredSize(new Dimension(400, 300));
		tableScrollPaneDriverCar.setMaximumSize(new Dimension(440, 300));
		tableScrollPaneDriverCar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Fahreruebersicht"),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)),
				tableScrollPaneDriverCar.getBorder()));

		vertBox.add(tableScrollPaneDriverCar);

		deleteButton = new JButton("Loeschen");
		deleteButton.addActionListener(new ActionProject("del", this, parent,
				model));
		deleteButton.setEnabled(false);

		saveButton = new JButton("Speichern");
		saveButton.addActionListener(new ActionProject("add", this, parent,
				model));
		saveButton.setEnabled(true);

		exitButton = new JButton("Abbrechen");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

	public void setDeleteEnabled(boolean state) {
		deleteButton.setEnabled(state);
	}

	public void setSaveEnabled(boolean state) {
		saveButton.setEnabled(state);
	}

	public void setExitEnabled(boolean state) {
		exitButton.setEnabled(state);
	}

	public void loadFahrzeug(ClassProject classProject) {
		// kennzeichen.setText(fahrzeug.getKennzeichen());
		// erstzulassung.setSelectedDate(fahrzeug.getErstzulassung());
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(int row) {
		// System.out.println(row.toString());
		this.row = row;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void loadClassProject(ClassProject classProject) {
		// TODO Auto-generated method stub
		
	}
}
