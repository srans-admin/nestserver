package com.srans.nestserver.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.srans.nestserver.model.Complaints;
import com.srans.nestserver.model.User;
@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints, Long> {

@Query(value="SELECT u FROM User u WHERE u.name=?1")
public User findByName(String name);

@Query(value="SELECT user_id FROM user WHERE id=?1", nativeQuery=true)
public Long user_id(Long user_id);



}