package contacts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
    @Autowired
    private ContactRepository contactRepository;

    @Test
    @Transactional
    public void testFindAllReturnEmpty() throws Exception {
        List<Contact> contacts = contactRepository.findAll();
        assertThat(contacts, is(empty()));
    }

    @Test
    @Transactional
    public void testFindAllReturnOne() throws Exception {
        Contact contact = new Contact();
        contact.setFirstName("zhaosheng");
        contact.setLastName("tao");
        contact.setPhoneNumber("13315751502");
        contact.setEmailAddress("tzs@163.com");
        contactRepository.save(contact);
        List<Contact> contacts = contactRepository.findAll();

        assertThat(contacts, hasItem(contact));
    }

    @Test
    @Transactional
    public void testFindAllReturnTwo() throws Exception {
        Contact contact1 = new Contact();
        contact1.setFirstName("zhaosheng");
        contact1.setLastName("tao");
        contact1.setPhoneNumber("13315751502");
        contact1.setEmailAddress("tzs@163.com");
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("zhaosheng2");
        contact2.setLastName("tao2");
        contact2.setPhoneNumber("13315751502");
        contact2.setEmailAddress("tzs@163.com");
        contactRepository.save(contact2);

        List<Contact> contacts = contactRepository.findAll();

        assertThat(contacts, hasItems(contact1, contact2));
    }
}
