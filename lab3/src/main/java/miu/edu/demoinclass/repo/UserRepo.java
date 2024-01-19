package miu.edu.demoinclass.repo;

import miu.edu.demoinclass.entity.Post;
import miu.edu.demoinclass.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select u.posts from User u where u.id=:userId")
    List<Post> findPostsByUser(@Param("userId") long userId);


    @Query("select u from User u where SIZE(u.posts) >1")
    List<User> findUsersMoreThanOnePost();

    @Query("select u from User u where SIZE(u.posts) > :numberOfPost")
    List<User> findUsersMoreThanNPost(@Param("numberOfPost") int numberOfPost);

    @Query("select u from User u join u.posts p where p.title=:title")
    List<User> findUsersByPostWithGivenTitle(@Param("title") String title);
}
