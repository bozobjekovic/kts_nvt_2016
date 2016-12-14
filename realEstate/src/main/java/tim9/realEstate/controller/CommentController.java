package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.CommentDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Comment;
import tim9.realEstate.service.CommentService;

/**
 * This class represents controller for Comment
 * and manages with all Comment functionalities.
 */
@RestController
@RequestMapping(value="realEstate/comments")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	/**
     * This method gets all Comments for specified Advertisement.
     * @param		advertisement Advertisement that contains this comments
     * @return      ResponseEntity List with all DTO Comments and HttpStatus OK
     */
	@RequestMapping(value="/all", method = RequestMethod.POST)
	public ResponseEntity<List<CommentDTO>> getAllComments(@RequestBody Advertisment advertisment) {
		List<Comment> comments = commentService.findByAdvertisment(advertisment);
		
		List<CommentDTO> commentDTO = new ArrayList<>();
		for(Comment c : comments){
			commentDTO.add(new CommentDTO(c));
		}
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}
	
	/**
     * This method creates new Comment and
     * saves it to the database.
     * @param		commentDTO comment to be inserted
     * @return      ResponseEntity DTO Advertisements and HttpStatus CREATED
     */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO){
		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setDescription(commentDTO.getDescription());
		comment.setTitle(commentDTO.getTitle());
		//set advert and user
		
		comment = commentService.save(comment);
		return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.CREATED);
	}
}
