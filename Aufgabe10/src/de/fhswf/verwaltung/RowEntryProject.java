package de.fhswf.verwaltung;



class RowEntryProject
{
   String driver;
   String car;

   public RowEntryProject(String Driver, String Car )
   {
      this.driver = Driver;
      this.car = Car;
   }

   public String getFahrer()
   {
      return driver;
   }

   public String getFahrzeug()
   {
      return car;
   }
   
}
