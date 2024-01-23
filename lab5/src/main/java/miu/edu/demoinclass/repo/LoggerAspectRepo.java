package miu.edu.demoinclass.repo;

import miu.edu.demoinclass.entity.aop.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerAspectRepo extends JpaRepository<Logger,Long> {
}
