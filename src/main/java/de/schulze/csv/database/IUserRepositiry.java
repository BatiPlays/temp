package de.schulze.csv.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepositiry extends JpaRepository<User,Integer>
{
    @Query(value = "SELECT * FROM `benutzer` WHERE username = ?1 AND userpassword = ?2", nativeQuery = true)
    int findUser(String userName, String userPassword);
}
