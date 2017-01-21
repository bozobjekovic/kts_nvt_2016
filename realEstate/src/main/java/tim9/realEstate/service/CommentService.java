package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Comment;
import tim9.realEstate.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	/**
	* This method finds element with specified id and gets it
	* from the database.
	* @param		id  Element id
	* @return		Element if found, null if doesn't exist
	*/
	public Comment findOne(Long id) {
		return commentRepository.findOne(id);
	}
	
	/**
	* This method finds all elements from specified Table.
	* @return		List of elements
	*/
	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	public List<Comment> findByAdvertisment(Advertisment advertisment) {
		return commentRepository.findByAdvertismentOrderByDateDesc(advertisment);
	}
	
	/**
	* This method saves element to the database.
	* @param		comment element to be saved
	* @return		Saved element
	*/
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
	
	/**
	* This method removes element from the database.
	* @param		id id of element to be removed
	*/
	public void remove(Long id) {
		commentRepository.delete(id);
	}

}
