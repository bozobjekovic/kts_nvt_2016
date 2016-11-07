package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
