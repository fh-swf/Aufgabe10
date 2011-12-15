package de.fhswf.verwaltung;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TableModelProject extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private String[] headers = { "ClassStudent", "ClassCompany" };

	@SuppressWarnings({ "rawtypes" })
	Class[] columnClasses = { String.class, String.class };

	Vector<RowEntryProject> data = new Vector<RowEntryProject>();

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return columnClasses[c];
	}

	public String getColumnName(int c) {
		return headers[c];
	}

	public boolean isCellEditable(int r, int c) {
		return false;
	}

	@Override
	public Object getValueAt(int r, int c) {
		RowEntryProject e = data.get(r);
		switch (c) {
		case 0:
			return e.getFahrer();
		case 1:
			return e.getFahrzeug();
		default:
			return null;
		}
	}

	public void setValueAt(Object value, int r, int c) {
	}

	public void addRow(ClassProject classProject, MainWindow frame) {
		int ID = getRowCount();

		data.add(ID, new RowEntryProject(classProject.getDriverId().toString(),
				classProject.getCarId().toString()));
		fireTableRowsInserted(ID, ID);
	}

	public void removeRowAt(int r) {
		data.removeElementAt(r);
		fireTableRowsDeleted(r, r);
	}

	public void clear() {
		int rowCount = data.size();
		data.clear();
		fireTableRowsDeleted(0, rowCount);
	}
}