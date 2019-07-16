package greenhouse.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sensor3")
public class Sensor3 extends Sensor{

	private static final long serialVersionUID = 1L;

	
	public Sensor3(Integer id, String name, Integer humidity, String status, Integer trackmin, Integer trackmax,
			LocalDateTime dataTime) {
		super(id, name, humidity, status, trackmin, trackmax, dataTime);
	}
	
	
	public void valid(String email) {
		if((this.getHumidity()>=this.getTrackmin()) & (this.getHumidity()<=this.getTrackmax()))
		{this.setStatus("Normal");}
		else{this.setStatus(email);}
	}
	
	
}