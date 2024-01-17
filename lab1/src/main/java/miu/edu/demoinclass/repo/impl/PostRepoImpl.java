package miu.edu.demoinclass.repo.impl;

import miu.edu.demoinclass.entity.Post;
import miu.edu.demoinclass.repo.PostRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepoImpl implements PostRepo {
    private static List<Post> postList;
    private static long postId = 200;
    static {
        postList = new ArrayList<>();

        Post p1 = new Post(1, "title1","Content1","author1");
        Post p2 = new Post(2, "title2","Content2","author2");
        postList.add(p1);
        postList.add(p2);

    }

    public List<Post> findAll(){
        return postList;
    }

    public Post getById(long id){
        return postList.stream()
                .filter(post -> post.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public void save(Post post){
        post.setId(postId);
        postId++;
        postList.add(post);
    }

    public void delete(long id){
        var post = postList
                .stream()
                .filter(p->p.getId()==id)
                .findFirst().get();
        postList.remove(post);
    }

    public void update(long id, Post post){
        Post toUpdate = getById(id);
        toUpdate.setAuthor(post.getAuthor());
        toUpdate.setContent(post.getContent());
        toUpdate.setTitle(post.getTitle());
    }

    public List<Post> getByAuthor(String author){
        return postList.stream()
                .filter(p->p.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Post> getByAuthorNameContain(String authors){
        return postList.stream()
                .filter(p->p.getAuthor().contains(authors))
                .collect(Collectors.toList());
    }
}
