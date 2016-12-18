package tim9.realEstate.dto;

/**
 * This class represents data transfer object for changing password
 *
 */
public class ChangePasswordDTO {
	
	private Long id;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	/**
	 * Class constructor
	 */
	public ChangePasswordDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "ChangePasswordDTO [id=" + id + ", oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
