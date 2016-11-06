package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person implements Serializable{

	private static final long serialVersionUID = 1156219148979456655L;
	
	@Id
	@GeneratedValue
	private int id;
	private String email;
	private String username;
	private String password;
	private String name;
	private String surname;
	private String phoneNumber;
	private String address;
	private String city;
	@Lob
	private String image;
	
	public Person() {
		super();
	}

	public Person(String email, String username, String password, String name, String surname, String phoneNumber,
			String address, String city, String image) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", name="
				+ name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", address=" + address + ", city="
				+ city + "]";
	}
	
	
	
}
