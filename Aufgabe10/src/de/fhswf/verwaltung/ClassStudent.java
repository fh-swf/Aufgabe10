package de.fhswf.verwaltung;

import java.sql.Date;

public class ClassStudent {
    
    private Integer Fahrer_ID; 
    private String Name;
    private String Vorname;
    private String Email;
    private Integer edited;
    
    public ClassStudent()
    {
    }
    
    public ClassStudent(String name, String fuehrerscheinklasse, String email)
    {
        setName(name);
        setFueKlasse(fuehrerscheinklasse);
        setFueSeit(email);
    }

    public Integer getFahrer_ID() {
        return Fahrer_ID;
    }

    public void setFahrer_ID(Integer fahrer_ID) {
        Fahrer_ID = fahrer_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFueKlasse() {
        return Vorname;
    }

    public void setFueKlasse(String fuehrerscheinklasse) {
        Vorname = fuehrerscheinklasse;
    }

    public String getFueSeit() {
        return Email;
    }

    public void setFueSeit(String email) {
        Email = email;
    }
    
    public Integer getEdited() {
		return edited;
	}

	public void setEdited(Integer edited) {
		this.edited = edited;
	}

	public String toString()
    {
       return "Name: " + Name + " Fuehrerscheinklassen: " + Vorname + " Fuehrerscheindatum: " + Email.toString() + " Changed: " + edited.toString();
    }
    
}
