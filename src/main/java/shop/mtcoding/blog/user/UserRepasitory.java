package shop.mtcoding.blog.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepasitory {
    private EntityManager em;

    public UserRepasitory(EntityManager em) {
        this.em = em;
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO){
        Query query= em.createNativeQuery("select * from user_tb where username=? and password=?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        User user=(User)query.getSingleResult();
        return user;
    }
    @Transactional
    public void save(UserRequest.JoinDTO requestDTO){
        Query query= em.createNativeQuery("insert into user_tb(username, password, email,created_at) values (?,?,?,now())", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();
    }
}
