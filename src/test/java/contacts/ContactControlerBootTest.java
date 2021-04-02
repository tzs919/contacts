package contacts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactControlerBootTest {

    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    /**
     * web上下文
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 所有测试方法执行之前执行该方法
     */
    @Before
    public void before() {
        //获取mockmvc对象实例
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    public void home() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("zhaosheng");
        contact.setLastName("tao");
        contact.setPhoneNumber("13315751502");
        contact.setEmailAddress("tzs@163.com");

        contactRepository.save(contact);

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("contacts", contacts));
    }

    @Test
    @Transactional
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
    }
}