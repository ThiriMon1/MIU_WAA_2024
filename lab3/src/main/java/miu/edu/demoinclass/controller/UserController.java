package miu.edu.demoinclass.controller;

import miu.edu.demoinclass.dto.PostResponse;
import miu.edu.demoinclass.dto.UserRequest;
import miu.edu.demoinclass.dto.UserResponse;
import miu.edu.demoinclass.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserResponse> findUsers(){
        return userService.findUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findByUser(@PathVariable("id") long userId){
        return ResponseEntity.ok(userService.findByUser(userId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody UserRequest userRequest){
        userService.save(userRequest);
    }

    @GetMapping("/{id}/posts")
    public List<PostResponse> findPostsByUser(@PathVariable("id") long userId){
        return userService.findPostsByUser(userId);
    }

    @GetMapping("/more-than1")
    public List<UserResponse> findUsersMoreThanOnePost(){
        return userService.findUsersMoreThanOnePost();
    }

    @GetMapping("/posts/{n}")
    public List<UserResponse> findUsersMoreThanNPost(@PathVariable("n") int numberOfPost){

        return userService.findUsersMoreThanNPost(numberOfPost);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long userId ){
        userService.delete(userId);
    }

    @GetMapping("/posts")
    public List<UserResponse> findUsersByPostWithGivenTitle(@RequestParam(value = "title") String title){
        return userService.findUsersByPostWithGivenTitle(title);
    }

    @GetMapping("/{id}/filter")
    public UserResponse searchReviewCriteria(
            @PathVariable("id") long userId,
            @RequestParam(value = "post", required = false) long postId,
            @RequestParam(value = "comment", required = false) long commentId){
        return userService.searchReviewCriteria(postId,commentId,userId);

    }


}
