package greenhouse.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import greenhouse.models.Plant;
import greenhouse.models.Sensor;
import greenhouse.models.Type;
import greenhouse.repositories.PlantRepository;
import greenhouse.repositories.SensorRepository;
import greenhouse.repositories.TypeRepository;

@Controller
public class PlantController {
	
	private static final String VIEWS_PLANT_CREATE_OR_UPDATE_FORM = "plant/createOrUpdatePlantForm";
	private static final String VIEWS_SENSOR_CREATE_OR_UPDATE_FORM = "plant/createOrUpdateSensortForm";
	private PlantRepository plants;
	private SensorRepository sensors;
	private TypeRepository types;
	
	public PlantController(PlantRepository plants, SensorRepository sensors, TypeRepository types) {
		super();
		this.plants = plants;
		this.sensors = sensors;
		this.types = types;
	}
	
	@GetMapping("/")
    public String welcome() {
        return "welcome";
    }
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@GetMapping("/plants/read")
	public String listReadPlants(Model model) {
		Collection<Plant> readList = plants.findAll();	
		model.addAttribute("plants", readList);
		return "read/readList";
	}
	
	@GetMapping("/sensors")
	public String listSensors(Model model) {
		Collection<Sensor> sensorList = sensors.findAll();	
		model.addAttribute("sensors", sensorList);
		return "sensor/sensorList";
	}
	@GetMapping("/plants")
	public String listPlants(Model model) {
		Collection<Type> typeList = types.findAll();	
		model.addAttribute("types", typeList);
		return "type/typeList";
	}
	
	@GetMapping("/plants/{plantId}")
	public String showPlant(@PathVariable("plantId") Integer plantId,Model model) {
		Collection<Plant> plantDetails = plants.findByType(plantId);	
		model.addAttribute("plant", plantDetails);
		return "plant/plantDetails";
	}
	@GetMapping("/sensors/{sensorId}")
	public String showSensor(@PathVariable("sensorId") Integer sensorId,Model model) {
		Collection<Plant> sensorDetails = plants.findBySensor(sensorId);	
		model.addAttribute("sensor", sensorDetails);
		return "sensor/sensorDetails";
	}
	
	
	@PostMapping("/plants/{plantId}/edit")
    public String createTypePlanty(@Valid Type type, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("type", type);
            return VIEWS_PLANT_CREATE_OR_UPDATE_FORM;
        } else {
            this.types.save(type);
            return "redirect:/plants/{plantId}";
        }
    }
}
