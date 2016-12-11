package tim9.realEstate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tim9.realEstate.constants.CommentConstants.*;

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
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Comment;
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
    
    @Test
    @Ignore
    public void testGetAllComments() throws Exception {
    	Advertisment advertisment = advertismentService.findOne(1L);
    	
    	String json = TestUtil.json(advertisment);
        this.mockMvc.perform(post(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isOk());
    }
    
    @Test
    @Transactional
    @Rollback(true)
    public void testSaveComment() throws Exception {
    	Comment comment = new Comment();
    	comment.setDate(NEW_DATE);
    	comment.setTitle(NEW_TITLE);
    	comment.setDescription(NEW_DESCRIPTION);
    	
    	String json = TestUtil.json(comment);
        this.mockMvc.perform(post(URL_PREFIX)
                .contentType(contentType)
                .content(json))
                .andExpect(status().isCreated());
    }
}
