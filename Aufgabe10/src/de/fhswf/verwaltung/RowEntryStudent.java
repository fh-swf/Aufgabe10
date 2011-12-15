package de.fhswf.verwaltung;

import java.sql.Date;


class RowEntryStudent
{
   String name;
   String fKlasse;
   String fDatum;

   @SuppressWarnings("deprecation")
public RowEntryStudent(String name, String fKlasse, Date fDatum )
   {
      this.name = name;
      this.fKlasse = fKlasse;
      this.fDatum = String.valueOf(fDatum.getDate()) + "." + String.valueOf(fDatum.getMonth()+1) + "." + String.valueOf(fDatum.getYear());
   }

   public String getName()
   {
      return name;
   }

   public String getKlasse()
   {
      return fKlasse;
   }

   public String getDatum()
   {
      return fDatum;
   }
   
}
