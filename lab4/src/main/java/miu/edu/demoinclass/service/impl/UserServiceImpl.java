package miu.edu.demoinclass.service.impl;

import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import miu.edu.demoinclass.dto.PostResponse;
import miu.edu.demoinclass.dto.UserCriteriaRequest;
import miu.edu.demoinclass.dto.UserRequest;
import miu.edu.demoinclass.dto.UserResponse;
import miu.edu.demoinclass.entity.Comment;
import miu.edu.demoinclass.entity.Post;
import miu.edu.demoinclass.entity.User;
import miu.edu.demoinclass.entity.aop.Exceptions;
import miu.edu.demoinclass.helper.ListMapper;
import miu.edu.demoinclass.repo.UserRepo;
import miu.edu.demoinclass.repo.UserSearchDao;
import miu.edu.demoinclass.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    public List<UserResponse> findUsers(){

        return listMapper.mapList(userRepo.findAll(),new UserResponse());
    }
    public UserResponse findByUser(long userId){
        return modelMapper.map(userRepo.findById(userId).get(),UserResponse.class);
    }

    public void save(UserRequest userRequest){
        User user = modelMapper.map(userRequest, User.class);
//        List<Post> posts = listMapper.mapList(userRequest.getPosts(), new Post());
//        posts.forEach(post -> {
//            List<Comment> comments = listMapper.mapList(post.getComments(), new Comment());
//            post.setComments(comments);
//        });
//
//        user.setPosts(posts);
        userRepo.save(user);
    }

    public List<PostResponse> findPostsByUser(long userId){
        return listMapper.mapList(userRepo.findPostsByUser(userId),new PostResponse());

    }

    public List<UserResponse> findUsersMoreThanOnePost(){
        return listMapper.mapList(userRepo.findUsersMoreThanOnePost(),new UserResponse());
    }

    public List<UserResponse> findUsersMoreThanNPost(int numberOfPost){
        return listMapper.mapList(userRepo.findUsersMoreThanNPost(numberOfPost),new UserResponse());
    }

    public void delete(long userId){
        userRepo.delete(userRepo.findById(userId).get());
    }

    public List<UserResponse> findUsersByPostWithGivenTitle(String title){
        return listMapper.mapList(userRepo.findUsersByPostWithGivenTitle(title), new UserResponse());
    }

    @Autowired
    UserSearchDao userSearchDao;
    public List<UserResponse> searchReviewCriteria(long postId, long commentId, long userId){
        var dtoRequest = new UserCriteriaRequest();
        dtoRequest.setUserId(userId);
        dtoRequest.setCommentId(commentId);
        dtoRequest.setPostId(postId);

        return listMapper.mapList(userSearchDao.findAllByCriteria(dtoRequest),new UserResponse());
     //   return modelMapper.map(findAllByCriteria(userId,postId,commentId),UserResponse.class);
    }

    public User findAllByCriteria(long userId, Long postId, Long commentId) {
        Optional<User> userOptional = userRepo.findById(userId);
        return userOptional.map(u -> {
            u.setPosts(u.getPosts().stream()
                    .filter(p -> postId == null || p.getId() == postId)
                    .peek(p -> p.setComments(p.getComments().stream()
                            .filter(c -> commentId == null || c.getId() == commentId)
                            .collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList())
            );
            return u;
        }).orElse(null);
    }

    public List<UserResponse> findUsersExe(){
        throw new RuntimeException("this is a test exception");
    }
}
