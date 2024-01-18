package miu.edu.demoinclass.service;

import miu.edu.demoinclass.dto.PostResponse;
import miu.edu.demoinclass.dto.UserRequest;
import miu.edu.demoinclass.dto.UserResponse;
import miu.edu.demoinclass.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> findUsers();

    UserResponse findByUser(long userId);

    void save(UserRequest userRequest);

    List<PostResponse> findPostsByUser(long userId);

    List<UserResponse> findUsersMoreThanOnePost();
}
