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

    void delete(long userId);

    List<UserResponse> findUsersMoreThanNPost(int numberOfPost);

    List<UserResponse> findUsersByPostWithGivenTitle(String title);

    List<UserResponse> searchReviewCriteria(long postId, long commentId, long userId);

    List<UserResponse> findUsersExe();
}
