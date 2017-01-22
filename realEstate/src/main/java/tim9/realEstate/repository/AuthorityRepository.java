package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Authority;

/**
 * This interface represents Authority repository
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	/**
	 * This method finds Authority with specified authority name
	 * 
	 * @param name
	 * @return object of Authority
	 */
	public Authority findByName(String name);

}
