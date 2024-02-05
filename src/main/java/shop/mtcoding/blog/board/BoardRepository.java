package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(){ //보드테이블의 모든 것 가지고 오기
        Query query=em.createNativeQuery("select * from board_tb order by id desc limit 0,3", Board.class);
        return query.getResultList(); //여러건을 들고 오기 위해서
    }

}
