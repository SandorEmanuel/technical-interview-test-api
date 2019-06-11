package org.fasttrackit.technicalinterviewtest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.technicalinterviewtest.domanin.Question;
import org.fasttrackit.technicalinterviewtest.domanin.User;
import org.fasttrackit.technicalinterviewtest.exception.ResourceNotFoundException;
import org.fasttrackit.technicalinterviewtest.persistance.QuestionRepository;
import org.fasttrackit.technicalinterviewtest.persistance.UserRepository;
import org.fasttrackit.technicalinterviewtest.transfer.user.CreateUserRequest;
import org.fasttrackit.technicalinterviewtest.transfer.user.UpdateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, QuestionRepository questionRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.objectMapper = objectMapper;
    }

    public User createUser(CreateUserRequest request) {
        LOGGER.info("Creating user {}", request);
        User user = objectMapper.convertValue(request, User.class);

        user.setResult(getResult());

        return userRepository.save(user);
    }

    public User getUser(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving user {}", id);

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
    }

    public User updateUser(long id, UpdateUserRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating user {}, {}", id, request);
        User user = getUser(id);
        user.setResult(getResult());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());


        return userRepository.save(user);
    }


    public void deleteUser(long id) {
        LOGGER.info("Deleting user {}", id);
        userRepository.deleteById(id);
        LOGGER.info("Deleted user {}", id);
    }

    private int getResult(){

        int result = 0;
        List<Question> questionList = new ArrayList();
        questionList = (List<Question>) questionRepository.findAll();

        for (int i = 0; i <questionList.size(); i++) {
            if (questionList.get(i).getCorrectAnswer() == questionList.get(i).getGivenAnswer()) {
               result++;

            } else {
                result = result ;
            }
        }
        return result;
    }

}

