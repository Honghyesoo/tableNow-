package zerobase.tablenow.domin.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.tablenow.domin.member.entity.Users;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>{

    //userId로 회원을 찾는 메서드
    Optional<Users> findByUserId(String userId);

}
