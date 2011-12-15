package de.fhswf;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.fhswf.db.Testdb;

public class DataBaseHelper {

	private static final String DataBaseFileName = "hsql_db";
	private static final File DataBaseFile = new File(DataBaseFileName+".script");
	private Testdb dataBase;
	
	/**
	 * Initialisiert eine HSQL-Datenbank. Beim erstmaligem Starten der Datenbank wird eine neue 
	 * Struktur angelegt. Andernfalls wird die bestehende Struktur geladen.
	 */
	private void initDataBase() {
		// Abfangen der moeglichen SQL-Exceptions
		boolean DB_exists = DataBaseFile.isFile();
		try {
			// Starten der Datenbank
			dataBase = new Testdb(DataBaseFileName);
			
			// Prüfen ob DataBaseFile noch nicht angelegt wurde
			if(!DB_exists){
				// Anlegen einer neuen Tabellenstruktur, falls die Datenbank noch nicht besteht.
				update(
						"CREATE TABLE personen (" +
						"id INTEGER IDENTITY, " +
						"vorname VARCHAR(30), " +
						"nachname VARCHAR(30)" +
						")");
			// Füllen der Tabelle mit Einträgen
				update("INSERT INTO personen(vorname, nachname) VALUES('Harald','Schmidt')");
				update("INSERT INTO personen(vorname, nachname) VALUES('Heinz','Erhardt')");
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
	public DataBaseHelper(){
		initDataBase();
	}
	
	private void runTestSzenario(){
		try {
			
			System.out.println("--- TESTFALL 1 ---");
			
			// Ausgeben aller Personen im Terminal
			dataBase.dumpQuery("SELECT * FROM personen");
		
			System.out.println("--- TESTFALL 2 ---");
			
			// Ausgeben aller Personen über ein ResultSet
			ResultSet rs = dataBase.query("SELECT * FROM personen");
			
			while (rs.next()) {
		        String vorname = rs.getString("vorname");
		        String nachname = rs.getString("nachname");
		        System.out.println(nachname + ", " + vorname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DataBaseHelper dbh = new DataBaseHelper();
		dbh.runTestSzenario();
		dbh.shutdown();
	}
}
