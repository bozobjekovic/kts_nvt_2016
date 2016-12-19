package tim9.realEstate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static tim9.realEstate.constants.CommentConstants.*;

import java.util.List;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.model.Comment;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class CommentServiceTest {

	@Autowired
	CommentService commentService;
	
	/**
	 * <h1> Positive tests </h1>
	 */
	
	/**
	 * method tests if an certain element from the data base can be found
	 **/
	@Test
	public void testFindOne() {
		Comment dbComment = commentService.findOne(DB_ID);
		assertThat(dbComment).isNotNull();
		
		assertThat(dbComment.getId()).isEqualTo(DB_ID);
		assertThat(dbComment.getDescription()).isEqualTo(DB_DESCRIPTION);
	}
	
	/**
	 * method test if all of certain elements from the data base can be found
	 **/
	@Test
	public void testFindAll() {
		List<Comment> comments = commentService.findAll();
		assertThat(comments).hasSize(DB_COUNT);
	}
	
	/**
	 * method tests if a new element can be saved into data base
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Comment comment = new Comment();
		comment.setDate(NEW_DATE);
		comment.setDescription(NEW_DESCRIPTION);
		
		int dbSizeBeforeAdd = commentService.findAll().size();
		
		Comment dbComment = commentService.save(comment);
		assertThat(dbComment).isNotNull();
		
		List<Comment> comments = commentService.findAll();
		assertThat(comments).hasSize(dbSizeBeforeAdd + 1);
		
		dbComment = comments.get(comments.size() - 1);
		assertThat(dbComment.getDate()).isEqualTo(NEW_DATE);
		assertThat(dbComment.getDescription()).isEqualTo(NEW_DESCRIPTION);
	}
	
	/**
	 * method tests if a certain element from the data base can be updated
	 **/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Comment dbComment = commentService.findOne(DB_ID);
		
		dbComment.setDescription(NEW_DESCRIPTION);
		
		dbComment = commentService.save(dbComment);
		assertThat(dbComment).isNotNull();
		
		dbComment = commentService.findOne(DB_ID);

		assertThat(dbComment.getDescription()).isEqualTo(NEW_DESCRIPTION);
	}
	
	/**
	 * method tests if a certain element from the data base can be removed
	 **/
	@Test
	@Transactional
	@Rollback(true)
	public void testRemove() {
		int dbSizeBeforeRemove = commentService.findAll().size();
		commentService.remove(DB_ID_REFERENCED);
		
		List<Comment> comments = commentService.findAll();
		assertThat(comments).hasSize(dbSizeBeforeRemove - 1);
		
		Comment dbComment = commentService.findOne(DB_ID_REFERENCED);
		assertThat(dbComment).isNull();
	}

	/**
	 * <h1> Negative tests </h1>
	 */
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullDate() {
		Comment comment = new Comment();
		comment.setDescription(NEW_DESCRIPTION);
		
		commentService.save(comment);
	}
	
	/**
	 * method tests if an certain element can be added into data base
	 * without field that is required,
	 * and if can throws an exception
	 * @exception DataIntegrityViolationException
	 **/
	@Test(expected = DataIntegrityViolationException.class)
	@Transactional
	@Rollback(true)
	public void testAddNullDecription() {
		Comment comment = new Comment();
		comment.setDate(NEW_DATE);
		
		commentService.save(comment);
	}
}
