package greenhouse.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo nome obrigatorio.")
	@Column(name = "username", unique = true)
    private String username;
	
    @NotBlank(message = "Campo senha obrigatorio.")
    @Column(name = "password", nullable = false)
    private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column(name = "enabled", nullable = false)
    private Boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
	
	public User() {
		super();
	}
	
	public User(@NotBlank(message = "Campo nome obrigatorio.") String username,
			@NotBlank(message = "Campo senha obrigatorio.") String password, String passwordConfirm, Boolean enabled,
			Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.enabled = enabled;
		this.roles = roles;
	}



	@Override
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	
	@Override
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
    public String getPasswordConfirm() {return this.passwordConfirm;}
    public void setPasswordConfirm(String passwordConfirm) {this.passwordConfirm = passwordConfirm;}
	
    public Collection<Role> getRoles() {return roles;}
    public void setRoles(Collection<Role> roles) {this.roles = roles;}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {return getRoles();}
	@Override
	public boolean isAccountNonExpired() {return true;}
	@Override
	public boolean isAccountNonLocked() {return true;}
	@Override
	public boolean isCredentialsNonExpired() {return true;}
	@Override
	public boolean isEnabled() {return enabled;}
	
	public void addRole(String roleName) {
	  if(this.roles == null) {this.roles = new ArrayList<Role>();}
	  Role role = new Role();
	  role.setName(roleName);
	  this.roles.add(role); 
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", passwordConfirm=" + passwordConfirm
				+ ", enabled=" + enabled + ", roles=" + roles + ", id=" + id + ", isNew()=" + isNew() + "]";
	}

	
}
