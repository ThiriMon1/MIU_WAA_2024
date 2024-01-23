package miu.edu.demoinclass.repo;

import miu.edu.demoinclass.entity.aop.Exceptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionsRepo extends JpaRepository<Exceptions,Long> {
}
