package miu.edu.demoinclass.controller;

import miu.edu.demoinclass.dto.PostRequest;
import miu.edu.demoinclass.dto.PostResponse;
import miu.edu.demoinclass.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostResponse> findPosts(@RequestParam(value = "author", required = false) String author
                                        ,@RequestParam(value = "authors", required = false) String authors
                                        ,@RequestParam(value = "title", required = false) String title){
        if(author!=null){
            return postService.findByAuthor(author);
        } else if (authors!=null) {
            return postService.findByAuthorNameContain(authors);
        } else if(title!=null){
            return postService.findByTitle(title);
        } else{
            return postService.findAll();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable("id") long id){
        var post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostRequest postRequest){
        postService.save(postRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody PostRequest postRequest){
        postService.update(id, postRequest);
    }

//    @GetMapping(params = "author")
//    public List<PostResponse> findByAuthor(@RequestParam String author){
//        return postService.findByAuthor(author);
//    }
//
//    @GetMapping(params = "authors")
//    public List<PostResponse> findByAuthorNameContain(@RequestParam String authors){
//        return postService.findByAuthorNameContain(authors);
//    }

}
