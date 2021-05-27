package sharehandong.sharehandongapiserver.api.v1.comment.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sharehandong.sharehandongapiserver.api.v1.comment.domain.Entity.Comment;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Comment findById(long id){
        return em.find(Comment.class, id);
    }

    public void save(Comment comment){
        if(comment.getId()== null){
            em.persist(comment);
        }
        else{
            em.merge(comment);
        }
    }

    public void deleteById(long id){
        em.remove(findById(id));
    }

    public List<Comment> getCommentByOwner(String writer){
        List<Comment> selectedPosts = em.createQuery("select c from Comment c where c.writer =:writer",Comment.class)
                .setParameter("writer", writer)
                .getResultList();
        return selectedPosts;
    }
}

