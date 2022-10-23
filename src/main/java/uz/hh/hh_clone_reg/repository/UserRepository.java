package uz.hh.hh_clone_reg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.hh.hh_clone_reg.domain.AuthUser;



public interface UserRepository extends JpaRepository<AuthUser , Long> {
    boolean existsByEmail(String email);

}
