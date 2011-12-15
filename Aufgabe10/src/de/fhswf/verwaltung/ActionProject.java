package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

public class ActionProject implements Action {

	DialogProject source;
	MainWindow parent;
	TableModelProject model;
	String methode;

	public ActionProject(String string, DialogProject dialogProject,
			MainWindow parent, TableModelProject model) {
		this.methode = string;
		this.source = dialogProject;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (methode.contentEquals("add")) {
			ClassProject classProject = new ClassProject(Integer.valueOf(String
					.valueOf(source.driver.getSelectedItem()).substring(0, 2)
					.trim()), Integer.valueOf(String
					.valueOf(source.car.getSelectedItem()).substring(0, 2)
					.trim()));
			classProject.setNeu(true);
			model.addRow(classProject, parent); // Tabelle
			parent.addClassProject(classProject); // fachMap

		} else if (methode.contentEquals("del")) {
			model.removeRowAt(source.getRow()); // Tabelle
			parent.delDriverCar(source.getRow()); // fachMap
			
		}
		source.dispose();
	}
}
