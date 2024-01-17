package miu.edu.demoinclass.service.impl;

import miu.edu.demoinclass.dto.PostRequest;
import miu.edu.demoinclass.dto.PostResponse;
import miu.edu.demoinclass.entity.Post;
import miu.edu.demoinclass.helper.ListMapper;
import miu.edu.demoinclass.repo.PostRepo;
import miu.edu.demoinclass.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public List<PostResponse> findAll(){
       return (List<PostResponse>) listMapper.mapList(postRepo.findAll(),new PostResponse());
    }

    public PostResponse findById(long id){
        return modelMapper.map(postRepo.getById(id),PostResponse.class);
    }

    public  void save(PostRequest postRequest){
        postRepo.save(modelMapper.map(postRequest,Post.class));
    }

    public void delete(long id){
        postRepo.delete(id);
    }

    public void update(long id, PostRequest postRequest){
        postRepo.update(id, modelMapper.map(postRequest, Post.class));
    }

    public List<PostResponse> findByAuthor(String author){
        return (List<PostResponse>) listMapper.mapList(postRepo.getByAuthor(author),new PostResponse());
    }

    public List<PostResponse> findByAuthorNameContain(String authors){
        return (List<PostResponse>) listMapper.mapList(postRepo.getByAuthorNameContain(authors),new PostResponse());
    }
}
