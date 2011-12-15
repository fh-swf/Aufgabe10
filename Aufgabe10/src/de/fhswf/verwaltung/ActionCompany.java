package de.fhswf.verwaltung;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import javax.swing.Action;

public class ActionCompany implements Action
{

   DialogCompany source;
   MainWindow parent;
   TableModelCompany model;
   String methode;
   
   public ActionCompany(String string, DialogCompany dialogCompany, MainWindow parent, TableModelCompany model)
   {
	  this.methode = string;
      this.source = dialogCompany;
      this.parent = parent;
      this.model = model;
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public Object getValue(String key)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean isEnabled()
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void putValue(String key, Object value)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener listener)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void setEnabled(boolean b)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
	   if ( methode.contentEquals("add") )
	   {
		   @SuppressWarnings("deprecation")
		   ClassCompany classCompany = new ClassCompany(source.kennzeichen.getText(),
				   							new Date (source.erstzulassung.getSelectedDate().getYear()+1900, source.erstzulassung.getSelectedDate().getMonth(), source.erstzulassung.getSelectedDate().getDate())
						);
		   classCompany.setEdited(2);
		   
		   if (source.getRow() != -1)
		   {
			   if(parent.getClassCompany(source.getRow()).getEdited() == 3)
				   classCompany.setEdited(3);
			   classCompany.setFahrzeug_ID(parent.getClassCompany(source.getRow()).getFahrzeug_ID());
			   model.editRowAt(classCompany, parent, source.getRow());	// Tabelle
			   parent.editClassCompany(classCompany, source.getRow());     // fachMap
		   }
		   else
		   {
			   classCompany.setEdited(3);
			   model.addRow(classCompany, parent);          // Tabelle
			   parent.addClassCompany(classCompany);                // fachMap
		   }



	   }
	   else if ( methode.contentEquals("del") )
	   {
			   model.removeRowAt(source.getRow());	// Tabelle
			   parent.delFahrzeug(source.getRow());     // fachMap
	   }
            source.dispose();
   }
}
