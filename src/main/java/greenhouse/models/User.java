package greenhouse.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	public User() {super();} //Constructor Default
	
	@Id
	@Column(name = "username", unique = true)
    @NotEmpty
    private String username;
	
	@Column
    @NotEmpty
    private String password;
	
	@Column(name = "passwrd_confirm")
	private String passwordConfirm;
	
	@Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName; 
	
	@Column
    @NotEmpty
    private String email;
    
    @Column
    private Boolean enabled;
    
    @ManyToMany
    @JoinColumn(name = "role_id")
    private Set<Role> roles;
    
	@OneToMany
	private Set<Sensor> sensors;
	
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	@Transient
    public String getPasswordConfirm() {return passwordConfirm;}
    public void setPasswordConfirm(String passwordConfirm) {this.passwordConfirm = passwordConfirm;}
	
	public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
	
    public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public Boolean getEnabled() {return enabled;}
    public void setEnabled(Boolean enabled) {this.enabled = enabled;}
	
 //-------------------------ROLES OF USER-------------------------
    public Set<Role> getRoles() {return roles;}
    public void setRoles(Set<Role> roles) {this.roles = roles;}
    
    public void addRole(String roleName) {
        if(this.roles == null) {
            this.roles = new HashSet<>();
        }
        Role role = new Role();
        role.setName(roleName);
        this.roles.add(role);
    }
  //------------------------------END------------------------------   
  
  //-------------------------SENSORS OF USER-------------------------
	protected Set<Sensor> getSensorsInternal() {//INTERNAL
        if (this.sensors == null) {this.sensors = new HashSet<>();}
        return this.sensors;
    }
	protected void setSensorsInternal(Set<Sensor> sensors) {this.sensors = sensors;}//INTERNAL
	
	public List<Sensor> getSensors() {
        List<Sensor> sortedSensors = new ArrayList<>(getSensorsInternal());
        PropertyComparator.sort(sortedSensors,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSensors);
    }
	
	public void addSensor(Sensor sensor) {
        if (sensor.isNew()) {getSensorsInternal().add(sensor);}
        sensor.setUser(this);
    }
	
	public Sensor getSensor(String name) {return getSensor(name, false);}
	
	public Sensor getSensor(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Sensor sensor : getSensorsInternal()) {
            if (!ignoreNew || !sensor.isNew()) {
                String compName = sensor.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {return sensor;}
            }
        }
        return null;
    }
//------------------------------END------------------------------
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", enabled=" + enabled + ", roles=" + roles + ", sensors=" + sensors
				+ "]";
	}
	
}
