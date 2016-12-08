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
	
	public Comment findOne(Long id) {
		return commentRepository.findOne(id);
	}

	public List<Comment> findAll() {
		return commentRepository.findAll();
	}
	
	public List<Comment> findByAdvertisment(Advertisment advertisment) {
		return commentRepository.findByAdvertisment(advertisment);
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	public void remove(Long id) {
		commentRepository.delete(id);
	}

}
