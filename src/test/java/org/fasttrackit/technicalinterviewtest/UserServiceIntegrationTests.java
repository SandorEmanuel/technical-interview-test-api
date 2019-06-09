package org.fasttrackit.technicalinterviewtest;

import org.fasttrackit.technicalinterviewtest.domanin.User;
import org.fasttrackit.technicalinterviewtest.exception.ResourceNotFoundException;
import org.fasttrackit.technicalinterviewtest.service.UserService;
import org.fasttrackit.technicalinterviewtest.transfer.user.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser_whenValidRequest_thenReturnUserWithId() {

        User user = createUser();

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
    }

    private User createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setFirstName("Ems");
        request.setLastName("Snd");
        request.setEmail("test@test.com");
        request.setResult(1);
        return userService.createUser(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteUser_whenExistingId_thenUserIsDeleted() throws ResourceNotFoundException {
        User createdUser = createUser();

        userService.deleteUser(createdUser.getId());

        userService.getUser(createdUser.getId());
    }
}
