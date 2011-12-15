package de.fhswf.verwaltung;


public class ClassProject {
    
    private Integer Bez_ID; 
    private Integer Driver_ID;
    private Integer Car_ID;
    private Boolean neu;
    
    public ClassProject()
    {
        
	}
    
    public ClassProject(Integer driverId, Integer carId)
    {
        setDriverId(driverId);
        setCarId(carId);
        setNeu(false);
    }

    public Integer getBez_ID() {
        return Bez_ID;
    }

    public void setBez_ID(Integer Bez_Id) {
        Bez_ID = Bez_Id;
    }

    public Integer getDriverId() {
        return Driver_ID;
    }

    public void setDriverId(Integer driverId) {
        Driver_ID = driverId;
    }

    public Integer getCarId() {
        return Car_ID;
    }

    public void setCarId(Integer carId) {
        Car_ID = carId;
    }
    
    public Boolean getNeu() {
		return neu;
	}

	public void setNeu(Boolean neu) {
		this.neu = neu;
	}

	public String toString()
    {
       return "Fahrer_ID: " + Driver_ID.toString() + " Fahrzeug_ID: " + Car_ID.toString();
    }
}
