package miu.edu.demoinclass.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import miu.edu.demoinclass.dto.UserCriteriaRequest;
import miu.edu.demoinclass.entity.Comment;
import miu.edu.demoinclass.entity.Post;
import miu.edu.demoinclass.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSearchDao {
    private final EntityManager em;
    public List<User> findAllByCriteria(UserCriteriaRequest userCriteriaRequest){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();

        // select * from User
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Post> postJoin = root.join("posts", JoinType.INNER);

        Predicate userIdPredicate = criteriaBuilder.equal(root.get("id"), userCriteriaRequest.getUserId());
        predicates.add(userIdPredicate);

        if (userCriteriaRequest.getPostId() != 0) {
           // Predicate postIdPredicate = criteriaBuilder.equal(root.get("id"), userCriteriaRequest.getPostId());
            Predicate postIdPredicate = criteriaBuilder.equal(postJoin.get("id"), userCriteriaRequest.getPostId());
            predicates.add(postIdPredicate);
        }

        if (userCriteriaRequest.getCommentId() != 0) {
            Join<Post, Comment> commentJoin = postJoin.join("comments", JoinType.INNER);
            Predicate commentIdPredicate = criteriaBuilder.equal(commentJoin.get("id"), userCriteriaRequest.getCommentId());
            predicates.add(commentIdPredicate);
        }

        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();

    }


}
