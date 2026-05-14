package com.my.member_app.repository;

import com.my.member_app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 유일하게 얘는 적어도 되고, 안 적어도 된다.
@Repository
// 테이블과 연결할 Entity Class를 지정, 해당 클래스의 ID의 타입을 적는다.
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 1. 쿼리 메서드

    // 2. JPQL

    // 3. native query
    // 이름 검색
    @Query(value = "SELECT * FROM members WHERE name LIKE %:keyword% ORDER BY name ASC", nativeQuery = true)
    List<Member> searchByName(@Param("keyword")String keyword);
    // 주소 검색
    @Query(value = "SELECT * FROM members WHERE address LIKE %:keyword% ORDER BY address ASC", nativeQuery = true)
    List<Member> searchByAddress(@Param("keyword")String keyword);
}
