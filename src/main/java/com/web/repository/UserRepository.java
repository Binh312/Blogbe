package com.web.repository;

import com.web.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.username = ?1 and u.id <> ?2")
    Optional<User> findByUsernameAndId(String username, Long userId);

    @Query(value = "select u from User u where u.username = ?1 and u.password = ?2")
    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query(value = "select u.* from users u where u.id = ?1", nativeQuery = true)
    Optional<User> findById(Long id);

    @Query("select u from User u")
    Page<User> getAllUser(Pageable pageable);

    @Query("select u from User u where u.actived = true")
    Page<User> getUserActived(Pageable pageable);

    @Query("select u from User u where u.actived = false")
    Page<User> getUserUnactived(Pageable pageable);

    @Query("select u from User u where u.role = ?1")
    Page<User> findByRole(String role, Pageable pageable);

    @Query("select u from User u where u.username like %?1% or u.fullName like %?1%")
    Page<User> findByName(String keywords, Pageable pageable);

    @Query("select u from User u where (u.fullName like %?1% or u.username like %?1%) and u.role like ?2")
    Page<User> findByParamAndRole(String param,String role, Pageable pageable);

    @Query("select  u from User u where u.username like %?1% and u.fullName like %?2% and u.role like ?3 " +
            "and u.createdDate = ?4 and u.actived = ?5")
    Page<User> filterAll(String userName, String fullName, String role, LocalDate createdDate, Boolean active, Pageable pageable);

    @Query(value = "select u.* from chat c inner join users u\n" +
            "where (c.sender = ?1 or c.receiver = ?1) and u.id != ?1 group by u.id", nativeQuery = true)
    public List<User> listUserChated(Long userId);

    Page<User> findAll(Specification<User> userSpecification, Pageable pageable);
}
