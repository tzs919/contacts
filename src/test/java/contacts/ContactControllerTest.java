package contacts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactRepository contactRepository;

    @Test
    public void home() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("zhaosheng");
        contact.setLastName("tao");
        contact.setPhoneNumber("13315751502");
        contact.setEmailAddress("tzs@163.com");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);

        given(contactRepository.findAll()).willReturn(contacts);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("contacts", contacts));

        verify(contactRepository).findAll();
    }

    @Test
    public void submit() throws Exception {

        Contact contact = new Contact();
        contact.setFirstName("zhaosheng");
        contact.setLastName("tao");
        contact.setPhoneNumber("13315751502");
        contact.setEmailAddress("tzs@163.com");

        mockMvc.perform(post("/")
                .param("firstName", "zhaosheng")
                .param("lastName", "tao")
                .param("phoneNumber", "13315751502")
                .param("emailAddress", "tzs@163.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(contactRepository).save(contact);
    }
}