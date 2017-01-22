package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Comment;

/**
 * This interface represents Comment repository
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

	/**
	 * This method finds all comments with specified advertisement
	 * 
	 * @param advertisment
	 * @return list objects of Comment
	 */
	List<Comment> findByAdvertismentOrderByDateDesc(Advertisment advertisment);

}
