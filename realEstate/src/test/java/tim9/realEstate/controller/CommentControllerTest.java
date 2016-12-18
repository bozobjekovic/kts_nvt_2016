package tim9.realEstate.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.CommentConstants.ADV_ID;
import static tim9.realEstate.constants.CommentConstants.COMM_COUNT;
import static tim9.realEstate.constants.CommentConstants.DB_DESCRIPTION;
import static tim9.realEstate.constants.CommentConstants.DB_ID;
import static tim9.realEstate.constants.CommentConstants.NEW_DATE;
import static tim9.realEstate.constants.CommentConstants.NEW_DESCRIPTION;

import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import tim9.realEstate.RealEstateApplication;
import tim9.realEstate.TestUtil;
import tim9.realEstate.dto.CommentDTO;
import tim9.realEstate.service.AdvertismentService;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RealEstateApplication.class)
@WebIntegrationTest
@TestPropertySource(locations="classpath:test.properties")
public class CommentControllerTest {

	private static final String URL_PREFIX = "/realEstate/comments";
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    
    @Autowired
    AdvertismentService advertismentService;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @PostConstruct
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    /**
	 * This method tests getting all comments from the database.
	 * Expected: method get, status OK, specified size and content
     * @throws Exception 
	 **/
    @Test
    public void testGetAllComments() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/all?id=" + ADV_ID))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$", hasSize(COMM_COUNT)))
	        .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DB_DESCRIPTION)));
    }
    
    /**
  	* This method tests adding new Comment and
  	* saving it to the database.
  	* Expected all input fields to be valid.
  	**/
    @Test
    @Ignore
    @Transactional
    @Rollback(true)
    public void testSaveComment() throws Exception {
    	CommentDTO comment = new CommentDTO();
    	comment.setDate(NEW_DATE);
    	comment.setDescription(NEW_DESCRIPTION);
    	
    	String json = TestUtil.json(comment);
        this.mockMvc.perform(post(URL_PREFIX + "?id=" + tim9.realEstate.constants.AdvertismentConstants.DB_ID)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }

    /**
  	* This method tests adding new Comment
  	* and saving it to the database,
  	* but with no Description,
  	* which has to be given.
  	**/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveCommentNoDescription() throws Exception {
    	CommentDTO comment = new CommentDTO();
    	comment.setDate(NEW_DATE);
    	
    	String json = TestUtil.json(comment);
        this.mockMvc.perform(post(URL_PREFIX + "?id=" + tim9.realEstate.constants.AdvertismentConstants.DB_ID)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    /**
  	* This method tests adding new Comment
  	* and saving it to the database,
  	* but with no Advertisement,
  	* which has to be given.
  	**/
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveCommentNoAdvertisement() throws Exception {
    	CommentDTO comment = new CommentDTO();    	
    	comment.setDate(NEW_DATE);
    	comment.setDescription(NEW_DESCRIPTION);
    	
    	String json = TestUtil.json(comment);
        this.mockMvc.perform(post(URL_PREFIX + tim9.realEstate.constants.AdvertismentConstants.DB_NONEXISTING_ID)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isNotFound());
    }
}
