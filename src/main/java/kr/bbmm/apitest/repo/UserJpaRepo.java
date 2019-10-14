package kr.bbmm.apitest.repo;

import kr.bbmm.apitest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<User, Long> {
}
