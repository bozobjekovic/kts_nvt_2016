package tim9.realEstate.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent DTO of Filter object
 *
 */
public class FilterDTO {

	private List<AdvertismentDTO> filteredAdvertisementsDTO;
	private int count;

	/**
	 * Constructor
	 */
	public FilterDTO() {
		super();
		filteredAdvertisementsDTO = new ArrayList<>();
	}

	/**
	 * Constructor
	 * 
	 * @param filteredAdvertisementsDTO
	 * @param count
	 */
	public FilterDTO(List<AdvertismentDTO> filteredAdvertisementsDTO, int count) {
		super();
		this.filteredAdvertisementsDTO = filteredAdvertisementsDTO;
		this.count = count;
	}

	@Override
	public String toString() {
		return "FilterDTO [filteredAdvertisementsDTO=" + filteredAdvertisementsDTO + ", count=" + count + "]";
	}

	public List<AdvertismentDTO> getFilteredAdvertisementsDTO() {
		return filteredAdvertisementsDTO;
	}

	public void setFilteredAdvertisementsDTO(List<AdvertismentDTO> filteredAdvertisementsDTO) {
		this.filteredAdvertisementsDTO = filteredAdvertisementsDTO;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
