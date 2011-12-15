package de.fhswf.verwaltung;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

/*
 * $RCSFile$
 *
 * Created on 01.11.2011
 * for ClassProject: Notenverwaltung
 * by Michael Rockstein & Philipp Schäfer
 *
 * (C) 2005-2006 by 
 */

public class TableClickListenerStudent implements MouseListener
{
   JTable table;
   MainWindow parent;
   TableModelStudent dataModel;
   
   public TableClickListenerStudent(JTable table, MainWindow parent, TableModelStudent dataModel)
   {
      this.table = table;
      this.parent = parent;
      this.dataModel = dataModel;
   }
   
   @Override
   public void mouseClicked(MouseEvent e)
   {
      if(e.getClickCount()==2)
      {
         int row = table.rowAtPoint(e.getPoint());
         DialogStudent dialog = new DialogStudent("Note bearbeiten - Zeile " + row, parent, dataModel);
         dialog.setRow(row);
         dialog.loadClassStudent(parent.getClassStudent(row));
         dialog.setDeleteEnabled(true);
         dialog.setSaveEnabled(true);
         dialog.setRelationEnabled(false);
         dialog.setExitEnabled(true);
         System.out.println(row);
         System.out.println(dialog.getRow());
      }
   }

   @Override
   public void mouseEntered(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent e)
   {
      // TODO Auto-generated method stub
      
   }
}
