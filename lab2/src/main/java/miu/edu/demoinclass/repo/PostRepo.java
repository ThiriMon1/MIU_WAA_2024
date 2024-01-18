package miu.edu.demoinclass.repo;

import miu.edu.demoinclass.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {

    List<Post> findByAuthor(String author);

    List<Post> findByAuthorContains(String authors);
}
