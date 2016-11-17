package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.model.Comment;
import tim9.realEstate.service.CommentService;

@RestController
@RequestMapping(value="realEstate/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> comments = commentService.findAll();
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
		comment = commentService.save(comment);
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}
}
