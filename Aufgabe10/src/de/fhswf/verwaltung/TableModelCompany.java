package de.fhswf.verwaltung;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class TableModelCompany extends AbstractTableModel {
	   private static final long serialVersionUID = 1L;
	   private String[] headers = {"Kennzeichen", "Erstzulassung" };

	   @SuppressWarnings({ "rawtypes" })
	   Class[] columnClasses = { String.class, String.class };

	   Vector<RowEntryCompany> data = new Vector<RowEntryCompany>();

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
	       RowEntryCompany e = data.get(r);
	       switch (c) {
	       case 0:
	           return e.getKennzeichen();
	       case 1:
	           return e.getErstzulassung();
	       default:
	           return null;
	       }
	   }

	   public void setValueAt(Object value, int r, int c) {
	   }

	   public void addRow(ClassCompany classCompany, MainWindow frame) {
	       int ID = getRowCount();
	    
	       data.add(ID, new RowEntryCompany(classCompany.getKennzeichen(), classCompany.getErstzulassung()));
	       fireTableRowsInserted(ID, ID); 
	   }

	   public void removeRowAt(int r) {
	       data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
	   }

	   public void editRowAt(ClassCompany classCompany, MainWindow frame, int r) {
		   data.removeElementAt(r);
	       fireTableRowsDeleted(r, r);
		   data.insertElementAt(new RowEntryCompany(classCompany.getKennzeichen(), classCompany.getErstzulassung() ), r);
	       fireTableRowsInserted(r, r);
	   }
	   
	   
	   public void clear() {
	      int rowCount = data.size();
	      data.clear();
	      fireTableRowsDeleted(0, rowCount);
	   }
	}