package tim9.realEstate.dto;

public class LoggedUserDTO {
	
	private String name;
	private String surname;
	private String role;
	
	public LoggedUserDTO() {
		super();
	}

	public LoggedUserDTO(String name, String surname, String role) {
		super();
		this.name = name;
		this.surname = surname;
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoggedUserDTO [name=" + name + ", surname=" + surname + ", role=" + role + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
