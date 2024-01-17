package miu.edu.demoinclass.repo;

import miu.edu.demoinclass.dto.PostRequest;
import miu.edu.demoinclass.entity.Post;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();

    Post getById(long id);


    void save(Post post);

    void delete(long id);

    void update(long id, Post post);

    List<Post> getByAuthor(String author);

    List<Post> getByAuthorNameContain(String authors);
}
