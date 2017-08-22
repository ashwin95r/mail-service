package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void postSendMailEmptyBodyError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/sendmail").content("{\"to\":[\"me.ashwin.r@gmail.com\"],\"cc\":[\"\"],\"bcc\":[\"\"],\"subject\":\"\",\"message\":\"\"}")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(equalTo("Empty Body")));
    }

    @Test
    public void postSendMailEmptyToError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/sendmail").content("{\"cc\":[\"\"],\"bcc\":[\"\"],\"subject\":\"\",\"message\":\"Hi\"}")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(equalTo("Missing To address")));
    }

    @Test
    public void postSendMailInvalidToError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/sendmail").content("{\"to\":[\"me.ashwin.r@gmaiom\"],\"cc\":[\"\"],\"bcc\":[\"\"],\"subject\":\"\",\"message\":\"Hi\"}")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(equalTo("Invalid email id: me.ashwin.r@gmaiom")));
    }
}