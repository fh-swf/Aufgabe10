package de.fhswf.verwaltung;

import java.sql.Date;


class RowEntryCompany
{
   String kennzeichen;
   String erstzulassung;

   @SuppressWarnings("deprecation")
public RowEntryCompany(String kennzeichen, Date erstzulassung )
   {
      this.kennzeichen = kennzeichen;
      this.erstzulassung = String.valueOf(erstzulassung.getDate()) + "." + String.valueOf(erstzulassung.getMonth()+1) + "." + String.valueOf(erstzulassung.getYear());
   }

   public String getKennzeichen()
   {
      return kennzeichen;
   }

   public String getErstzulassung()
   {
      return erstzulassung;
   }
   
}
