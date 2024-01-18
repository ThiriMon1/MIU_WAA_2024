package miu.edu.demoinclass.service.impl;

import miu.edu.demoinclass.dto.PostResponse;
import miu.edu.demoinclass.dto.UserRequest;
import miu.edu.demoinclass.dto.UserResponse;
import miu.edu.demoinclass.entity.Post;
import miu.edu.demoinclass.entity.User;
import miu.edu.demoinclass.helper.ListMapper;
import miu.edu.demoinclass.repo.UserRepo;
import miu.edu.demoinclass.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setPosts(listMapper.mapList(userRequest.getPosts(), new Post()));
        userRepo.save(user);
    }

    public List<PostResponse> findPostsByUser(long userId){
        return listMapper.mapList(userRepo.findPostsByUser(userId),new PostResponse());

    }

    public List<UserResponse> findUsersMoreThanOnePost(){
        return listMapper.mapList(userRepo.findUsersMoreThanOnePost(),new UserResponse());
    }
}
