package de.fhswf.verwaltung;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.fhswf.db.Testdb;

public class Database {

	private static final String DataBaseFileName = "fuhrpark_hsql_db";
	private static final File DataBaseFile = new File(DataBaseFileName+".script");
	private Testdb dataBase;
	private Integer id;
	/**
	 * Initialisiert eine HSQL-Datenbank. Beim erstmaligem Starten der Datenbank wird eine neue 
	 * Struktur angelegt. Andernfalls wird die bestehende Struktur geladen.
	 */
	private void initDataBase() {
		// Abfangen der moeglichen SQL-Exceptions
		boolean DB_exists = DataBaseFile.isFile();
		System.out.println(String.valueOf(DB_exists) + ", Datenbank vorhanden! ");
		try {
			// Starten der Datenbank
			dataBase = new Testdb(DataBaseFileName);
			
			// Prüfen ob DataBaseFile noch nicht angelegt wurde
			if(!DB_exists){
				// Anlegen einer neuen Tabellenstruktur, falls die Datenbank noch nicht besteht.
				update(
						"CREATE TABLE fahrer ( " +
						"id INTEGER IDENTITY, " +
						"name VARCHAR(30), " +
						"fklasse VARCHAR(55), " +
						"fseit DATE" +
						")");
				
				update(
						"CREATE TABLE fahrzeug ( " +
						"id INTEGER IDENTITY, " +
						"kennzeichen VARCHAR(30), " +
						"erstzulassung DATE" +
						")");
				
				update(
						"CREATE TABLE fahrer_fahrzeug ( " +
						"id INTEGER IDENTITY, " +
						"fahrer_id INTEGER, " +
						"fahrzeug_id INTEGER" +
						")");
				
			// Füllen der Tabelle mit Einträgen
				@SuppressWarnings("deprecation")
				Date date1 = new Date(2000, 1, 15);
				System.out.println(date1.toString());
				@SuppressWarnings("deprecation")
				Date date2 = new Date(2001, 3, 15);
				@SuppressWarnings("deprecation")
				Date date3 = new Date(2011, 5, 15);
				@SuppressWarnings("deprecation")
				Date date4 = new Date(2010, 7, 1);
				update("INSERT INTO fahrer(name, fklasse, fseit) VALUES('Schmidt', 'A', '" + date1 + "')");
				update("INSERT INTO fahrer(name, fklasse, fseit) VALUES('Heinzen', 'B', '" + date2 + "')");
				update("INSERT INTO fahrzeug(kennzeichen, erstzulassung) VALUES('HAM-HY 6', '" + date3 + "')");
				update("INSERT INTO fahrzeug(kennzeichen, erstzulassung) VALUES('HAM-EY 5', '" + date4 + "')");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Sendet einen SQL-Ausdruck ab. Bitte für CREATE, DROP, INSERT und UPDATE benutzen.
	 * 
	 * @param expression
	 * SQL-Ausdruck
	 */
	public void update(String expression){
		if(dataBase == null)
			throw new IllegalAccessError("Bitte erst initDataBase() ausführen");
		
		try {
			this.dataBase.update(expression);
		} catch (SQLException e) {
			System.out.println("Fehler in folgendem Statement:");
			System.out.println(expression);
			e.printStackTrace();
		}
	}
	
	/**
	 * Schließt alle Verbindungen und beendet die Datenbank.
	 */
	public void shutdown(){
		try {
			dataBase.shutdown();
		} catch (SQLException e) {
			System.out.println("Fehler beim Beenden der Datenbank:");
			e.printStackTrace();
		}
	}
	
	/**
	 * Default Constructor.
	 */
	public Database(){
		initDataBase();
	}
	
	@SuppressWarnings("deprecation")
	public void load(MainWindow parent)
	{
		try {
			System.out.println("--- TABELLE 1 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs1 = dataBase.query("SELECT * FROM fahrer");
			
			while (rs1.next()) {
				
				Integer id = rs1.getInt("id");
		        String name = rs1.getString("name");
		        String fKlasse = rs1.getString("fklasse");
		        Date fSeit = rs1.getDate("fseit");
		        
		        
		        ClassStudent classStudent = new ClassStudent(name, fKlasse, fSeit);
		        classStudent.setFahrer_ID(id);
		        classStudent.setEdited(0);
		        parent.addClassStudent(classStudent);
		        parent.tableDataStudent.addRow(classStudent, parent);
		        
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(name);
				stringBuilder.append(", ");
				stringBuilder.append(fKlasse);
				stringBuilder.append(", ");
				stringBuilder.append(fSeit.getDay());
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getMonth()+1);
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getYear());
				System.out.println(stringBuilder.toString());
			}
			
			System.out.println("--- TABELLE 2 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs2 = dataBase.query("SELECT * FROM fahrzeug");
			
			while (rs2.next()) {
				
				id = rs2.getInt("id");
		        String kennzeichen = rs2.getString("kennzeichen");
		        Date erstzulassung = rs2.getDate("erstzulassung");
		        
		        ClassCompany classCompany = new ClassCompany(kennzeichen, erstzulassung);
		        classCompany.setFahrzeug_ID(id);
		        classCompany.setEdited(0);
		        parent.addClassCompany(classCompany);
		        parent.tableDataCompany.addRow(classCompany, parent);
		        
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(kennzeichen);
				stringBuilder.append(", ");
				stringBuilder.append(erstzulassung.getDay());
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getMonth()+1);
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getYear());
				System.out.println(stringBuilder.toString());
			}
			
			System.out.println("--- TABELLE 3 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs3 = dataBase.query("SELECT * FROM fahrer_fahrzeug");
			
			while (rs3.next()) {
				
				id = rs3.getInt("id");
		        Integer fahrerId = rs3.getInt("fahrer_id");
		        Integer fahrzeugId = rs3.getInt("fahrzeug_id");
		        
		        ClassProject classProject = new ClassProject(fahrerId, fahrzeugId);
		        classProject.setBez_ID(id);
		        parent.addClassProject(classProject);
		        parent.tableDataProject.addRow(classProject, parent);
		        
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(id);
				stringBuilder.append(", ");
				stringBuilder.append(fahrerId);
				stringBuilder.append(" : ");
				stringBuilder.append(fahrzeugId);
				
				System.out.println(stringBuilder.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void write(MainWindow parent)
	{
	      System.out.println("delete Drivers...");
	      for ( ClassStudent classStudent : parent.classStudentMapDel )
	      {
	    	 System.out.println(classStudent.toString());
	    	 if(classStudent.getFahrer_ID() != null)
	        	 update("DELETE FROM fahrer WHERE id = " + classStudent.getFahrer_ID()
	        			 			);
	      }
	      System.out.println(parent.classStudentMapDel.size() + " deleted Drivers...");
	      
				      System.out.println("writing Driver...");
				      for ( ClassStudent classStudent : parent.classStudentMap )
				      {
				    	 Date date = new Date(classStudent.getFueSeit().getYear(), classStudent.getFueSeit().getMonth(), classStudent.getFueSeit().getDate());
				         System.out.println(classStudent.toString());
				         if (classStudent.getEdited() == 1)
				        	 update("UPDATE fahrer " +
		        			 			"SET (name, fKlasse, fseit) = ('" +
				        			 classStudent.getName() + "', '" +
		        			 		 classStudent.getFueKlasse() + "', '" +
				        			 date.toString() + "') " + 
		        			 		 "WHERE id = " + classStudent.getFahrer_ID()
				        			 			);
				         
				         if (classStudent.getEdited() == 3)
				        	 update("INSERT INTO fahrer(name, fklasse, fseit) " +
				        			 	"VALUES('" +
				        			 classStudent.getName() + "', '" +
		        			 		 classStudent.getFueKlasse() + "', '" +
				        			 date.toString() + "')"
				        			 			);
				      }
				      System.out.println(parent.classCompanyMap.size() + " written Driver...");
		
				      
				      System.out.println("delete Cars...");
				      for ( ClassCompany classCompany : parent.classCompanyMapDel )
				      {
				    	 System.out.println(classCompany.toString());
				    	 if(classCompany.getFahrzeug_ID() != null)
				        	 update("DELETE FROM fahrzeug WHERE id = " + classCompany.getFahrzeug_ID()
				        			 			);
				      }
				      System.out.println(parent.classCompanyMapDel.size() + " deleted Cars...");
				      
				      System.out.println("writing Cars...");
				      for ( ClassCompany classCompany : parent.classCompanyMap )
				      {
				    	  Date date = new Date(classCompany.getErstzulassung().getYear(), classCompany.getErstzulassung().getMonth(), classCompany.getErstzulassung().getDate());
					         System.out.println(classCompany.toString());
					         if (classCompany.getEdited() == 1)
					        	 update("UPDATE fahrzeug " +
			        			 			"SET (kennzeichen, erstzulassung) = ('" +
					        			 classCompany.getKennzeichen() + "', '" +
					        			 date.toString() + "') " + 
			        			 		 "WHERE id = " + classCompany.getFahrzeug_ID()
					        			 			);
					         
					         if (classCompany.getEdited() == 3)
					        	 update("INSERT INTO fahrzeug(kennzeichen, erstzulassung) " +
					        			 	"VALUES('" +
					        			 classCompany.getKennzeichen() + "', '" +
					        			 date.toString() + "')"
					        			 			);
				      }
				      System.out.println(parent.classCompanyMap.size() + " written Cars...");
				      
				      System.out.println("delete Relations...");
				      for ( ClassProject classProject : parent.classProjectMapDel )
				      {
				    	 System.out.println(classProject.toString());
				    	 if(classProject.getBez_ID() != null)
				        	 update("DELETE FROM fahrer_fahrzeug WHERE id = " + classProject.getBez_ID()
				        			 			);
				      }
				      System.out.println(parent.classProjectMapDel.size() + " deleted Cars...");
				       
  
				      System.out.println("writing Relations...");
				      for ( ClassProject classProject : parent.classProjectMap )
				      {
				         System.out.println(classProject.toString());
				         if (classProject.getNeu())
				        	 update("INSERT INTO fahrer_fahrzeug(fahrer_id, fahrzeug_id) " +
				        			 	"VALUES('" +
				        			 classProject.getDriverId() + "', '" +
				        			 classProject.getCarId() + "')"
				        			 			);
				      }
				      System.out.println(parent.classProjectMap.size() + "written Relations...");
	}
	
	@SuppressWarnings("deprecation")
	public void runTestSzenario(){
		try {
			
			System.out.println("--- TESTFALL 1 ---");
			System.out.println("--- TABELLE 1 ---");
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM fahrer");
			
			System.out.println("--- TABELLE 2 ---");
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM fahrzeug");
			
			System.out.println("--- TABELLE 3 ---");
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM fahrer_fahrzeug");
			
			System.out.println("--- TESTFALL 2 ---");
			System.out.println("--- TABELLE 1 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs1 = dataBase.query("SELECT * FROM fahrer");
			
			while (rs1.next()) {
		        String name = rs1.getString("name");
		        String fKlasse = rs1.getString("fklasse");
		        Date fSeit = rs1.getDate("fseit");
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(name);
				stringBuilder.append(", ");
				stringBuilder.append(fKlasse);
				stringBuilder.append(", ");
				stringBuilder.append(fSeit.getDay());
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getMonth());
				stringBuilder.append(".");
				stringBuilder.append(fSeit.getYear());
				System.out.println(stringBuilder.toString());
			}
			
			System.out.println("--- TABELLE 2 ---");
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs2 = dataBase.query("SELECT * FROM fahrzeug");
			
			while (rs2.next()) {
		        String kennzeichen = rs2.getString("kennzeichen");
		        Date erstzulassung = rs2.getDate("erstzulassung");
		        StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(kennzeichen);
				stringBuilder.append(", ");
				stringBuilder.append(erstzulassung.getDay());
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getMonth());
				stringBuilder.append(".");
				stringBuilder.append(erstzulassung.getYear());
				System.out.println(stringBuilder.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Database dbh = new Database();
		dbh.runTestSzenario();
		dbh.shutdown();
	}
}