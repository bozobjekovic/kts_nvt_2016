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
	
	/*****   1. TEST FIND ONE   *****/
	@Test
	public void testFindOne() {
		Comment dbComment = commentService.findOne(DB_ID);
		assertThat(dbComment).isNotNull();
		
		assertThat(dbComment.getId()).isEqualTo(DB_ID);
		assertThat(dbComment.getDescription()).isEqualTo(DB_DESCRIPTION);
		assertThat(dbComment.getTitle()).isEqualTo(DB_TITLE);
	}
	
	/*****   2. TEST FIND ALL   *****/
	@Test
	public void testFindAll() {
		List<Comment> comments = commentService.findAll();
		assertThat(comments).hasSize(DB_COUNT);
	}
	
	/*****   3. TEST SAVE   *****/
	@Test
	@Transactional
	@Rollback(true)
	public void testSave() {
		Comment inappropriate = new Comment();
		inappropriate.setDescription(NEW_DESCRIPTION);
		inappropriate.setTitle(NEW_TITLE);
		
		int dbSizeBeforeAdd = commentService.findAll().size();
		
		Comment dbComment = commentService.save(inappropriate);
		assertThat(dbComment).isNotNull();
		
		List<Comment> comments = commentService.findAll();
		assertThat(comments).hasSize(dbSizeBeforeAdd + 1);
		
		dbComment = comments.get(comments.size() - 1);
		assertThat(dbComment.getDescription()).isEqualTo(NEW_DESCRIPTION);
		assertThat(dbComment.getTitle()).isEqualTo(NEW_TITLE);
	}
	
	/*****   4. TEST UPDATE   *****/
	@Test
    @Transactional
    @Rollback(true)
	public void testUpdate() {
		Comment dbComment = commentService.findOne(DB_ID);
		
		dbComment.setDescription(NEW_DESCRIPTION);
		dbComment.setTitle(NEW_TITLE);
		
		dbComment = commentService.save(dbComment);
		assertThat(dbComment).isNotNull();
		
		dbComment = commentService.findOne(DB_ID);

		assertThat(dbComment.getDescription()).isEqualTo(NEW_DESCRIPTION);
		assertThat(dbComment.getTitle()).isEqualTo(NEW_TITLE);
	}
	
	/*****   5. TEST REMOVE   *****/
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
}
