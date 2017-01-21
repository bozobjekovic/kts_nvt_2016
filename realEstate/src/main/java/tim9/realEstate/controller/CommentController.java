package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.CommentDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Comment;
import tim9.realEstate.model.User;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.CommentService;

/**
 * This class represents controller for Comment and manages with all Comment
 * functionalities.
 */
@RestController
@RequestMapping(value = "realEstate/comments")
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	AdvertismentService advertismentService;

	@Autowired
	UserUtils userUtils;

	/**
	 * This method gets all Comments for specified Advertisement.
	 * 
	 * @param advertisement
	 *            Advertisement that contains this comments
	 * @return ResponseEntity List with all DTO Comments and HttpStatus OK
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getAllComments(@RequestParam Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Advertisment advertisment = advertismentService.findOne(id);
		if (advertisment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Comment> comments = commentService.findByAdvertisment(advertisment);

		List<CommentDTO> commentDTO = new ArrayList<>();
		for (Comment c : comments) {
			commentDTO.add(new CommentDTO(c));
		}

		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}

	/**
	 * This method creates new Comment and saves it to the database.
	 * 
	 * @param commentDTO
	 *            comment to be inserted
	 * @return ResponseEntity DTO Advertisements and HttpStatus CREATED
	 */
	@RequestMapping(value = "/{id}/new", method = RequestMethod.POST)
	public ResponseEntity<CommentDTO> saveComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
			ServletRequest request) {
		if (id == null || commentDTO.getDescription() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Advertisment advertisment = advertismentService.findOne(id);
		if (advertisment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Comment comment = new Comment();
		comment.setDate(new Date());
		comment.setDescription(commentDTO.getDescription());
		comment.setAdvertisment(advertisment);
		comment.setUser((User) userUtils.getLoggedUser(request));

		comment = commentService.save(comment);

		return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.CREATED);
	}
}
