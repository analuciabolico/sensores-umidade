package org.springframework.samples.petclinic.sensor;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

public interface SensorService {
	Sensor findById(int id) throws DataAccessException;
	Collection<Sensor> findAll() throws DataAccessException;
	void saveSensor(Sensor sensor) throws DataAccessException;
	void deleteSensor(Sensor sensor) throws DataAccessException;
}