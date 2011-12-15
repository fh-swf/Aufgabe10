package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.Action;
import javax.swing.JCheckBox;

public class ActionStudent implements Action {

	DialogStudent source;
	MainWindow parent;
	TableModelStudent model;
	String methode;

	public ActionStudent(String string, DialogStudent dialogStudent,
			MainWindow parent, TableModelStudent model) {
		this.methode = string;
		this.source = dialogStudent;
		this.parent = parent;
		this.model = model;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (methode.contentEquals("add")) {
			StringBuilder stringBuilder = new StringBuilder();

			for (JCheckBox checkBox : source.getCheckBoxMap()) {
				if (checkBox.isSelected()) {
					if (stringBuilder.length() == 0)
						stringBuilder.append(checkBox.getLabel().trim());
					else {
						stringBuilder.append(", ");
						stringBuilder.append(checkBox.getLabel().trim());
					}
				}
			}

//			ClassStudent classStudent = new ClassStudent(source.nameStudent.getText(),
//					stringBuilder.toString(), new Date(source.fDatum
//							.getSelectedDate().getYear() + 1900, source.fDatum
//							.getSelectedDate().getMonth(), source.fDatum
//							.getSelectedDate().getDate()));
//
//			classStudent.setEdited(2);
//
//			if (source.getRow() != -1) {
//				if (parent.getClassStudent(source.getRow()).getEdited() == 3)
//					classStudent.setEdited(3);
//				classStudent.setFahrer_ID(parent.getClassStudent(source.getRow())
//						.getFahrer_ID());
//				model.editRowAt(classStudent, parent, source.getRow()); // Tabelle
//				parent.editClassStudent(classStudent, source.getRow()); // fachMap
//			} else {
//				classStudent.setEdited(3);
//				model.addRow(classStudent, parent); // Tabelle
//				parent.addClassStudent(classStudent); // fachMap
//			}
//
//		} else if (methode.contentEquals("del")) {
//			model.removeRowAt(source.getRow()); // Tabelle
//			parent.delFahrer(source.getRow()); // fachMap
//			
//		} else if (methode.contentEquals("rel")) {
//			StringBuilder stringBuilder = new StringBuilder();
//
//			for (JCheckBox checkBox : source.getCheckBoxMap()) {
//				if (checkBox.isSelected()) {
//					if (stringBuilder.length() == 0)
//						stringBuilder.append(checkBox.getLabel().trim());
//					else {
//						stringBuilder.append(", ");
//						stringBuilder.append(checkBox.getLabel().trim());
//					}
//				}
//			}
//
//			ClassStudent classStudent = new ClassStudent(source.nameStudent.getText(),
//					stringBuilder.toString(), new Date(source.fDatum
//							.getSelectedDate().getYear() + 1900, source.fDatum
//							.getSelectedDate().getMonth(), source.fDatum
//							.getSelectedDate().getDate()));
//
//			classStudent.setEdited(2);
//
//			if (source.getRow() != -1) {
//				if (parent.getClassStudent(source.getRow()).getEdited() == 3)
//					classStudent.setEdited(3);
//				classStudent.setFahrer_ID(parent.getClassStudent(source.getRow())
//						.getFahrer_ID());
//				model.editRowAt(classStudent, parent, source.getRow()); // Tabelle
//				parent.editClassStudent(classStudent, source.getRow()); // fachMap
//			} else {
//				classStudent.setEdited(3);
//				model.addRow(classStudent, parent); // Tabelle
//				parent.addClassStudent(classStudent); // fachMap
//			}
//
//			parent.database.write(parent);
			parent.studentMap.clear();
			parent.studentMapDel.clear();
			parent.companyMap.clear();
			parent.companyMapDel.clear();
			parent.projectMap.clear();
			parent.projectMapDel.clear();
			parent.tableDataStudent.clear();
			parent.tableDataCompany.clear();
			parent.tableDataProject.clear();
			parent.database.load(parent);

			DialogProject dialog = new DialogProject("ClassCompany hinzufuegen", parent, parent.tableDataProject);
			dialog.setRow(-1);
			dialog.driver.setSelectedIndex(dialog.driver.getItemCount()-1);

		}
		source.dispose();
	}
}
