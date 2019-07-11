package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.sensor.Sensor;
import org.springframework.samples.petclinic.sensor.SensorRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl implements SensorService{
	private SensorRepository sensorRepository;
	@Autowired
	public SensorServiceImpl(SensorRepository sensorRepository) {this.sensorRepository = sensorRepository;}
	
	@Override
	public Sensor findById(int id) throws DataAccessException {
		Sensor sensor =null;
		try {
			sensor = sensorRepository.findById(id);
		} catch(ObjectRetrievalFailureException|EmptyResultDataAccessException e){
			return null;
		}
		return sensor;
	}
	@Override
	public Collection<Sensor> findAll() throws DataAccessException {
		return sensorRepository.findAll();
	}
	@Override
	public void saveSensor(Sensor sensor) throws DataAccessException {
		sensorRepository.save(sensor);
		
	}
	@Override
	public void deleteSensor(Sensor sensor) throws DataAccessException {
		sensorRepository.delete(sensor);
	}
	
}



