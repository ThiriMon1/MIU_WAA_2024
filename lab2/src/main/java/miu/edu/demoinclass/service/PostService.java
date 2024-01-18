package miu.edu.demoinclass.service;

import miu.edu.demoinclass.dto.PostRequest;
import miu.edu.demoinclass.dto.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> findAll();

    PostResponse findById(long id);

    void save(PostRequest postRequest);

    void delete(long id);

    void update(long id, PostRequest postRequest);

    List<PostResponse> findByAuthor(String author);

    List<PostResponse> findByAuthorNameContain(String authors);
}
