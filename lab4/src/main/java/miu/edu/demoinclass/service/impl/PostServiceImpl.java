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
import java.util.Optional;

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

        return modelMapper.map(postRepo.findById(id).get(),PostResponse.class);
    }

    public  void save(PostRequest postRequest){
        postRepo.save(modelMapper.map(postRequest,Post.class));
    }

    public void delete(long id){
        postRepo.deleteById(id);
    }

    public void update(long id, PostRequest postRequest){
        postRepo.findById(id)
                .ifPresent(existingPost->{
                    modelMapper.map(postRequest,existingPost);
                    postRepo.save(existingPost);
                });
    }

    public List<PostResponse> findByAuthor(String author){
        return (List<PostResponse>) listMapper.mapList(postRepo.findByAuthor(author),new PostResponse());
    }

    public List<PostResponse> findByAuthorNameContain(String authors){
        return (List<PostResponse>) listMapper.mapList(postRepo.findByAuthorContains(authors),new PostResponse());
    }

    public List<PostResponse> findByTitle(String title){
        return (List<PostResponse>) listMapper.mapList(postRepo.findByTitle(title),new PostResponse());
    }
}
