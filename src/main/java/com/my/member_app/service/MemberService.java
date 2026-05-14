package com.my.member_app.service;

import com.my.member_app.dto.MemberDto;
import com.my.member_app.entity.Member;
import com.my.member_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    // 의존성 주입 : 필요한 컴포넌트(인스턴스)를 불러오는 작업
    // 1. 첫 번째 주입방법
    // @Autowired
    // MemberRepository memberRepository;
    // 2. 생성자 주입 방법
    // 3. @RequiredArgsConstructor
    private final MemberRepository memberRepository;

//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public List<MemberDto> findAll() {
        // Repository에서 필요한 정보를 가져온다.
        // 단, Repo는 Entity만 사용한다.
        List<Member> members = memberRepository.findAll();
        // Entity List -> Dto List로 변환한 후 리턴한다.
        // 깡통 Dto 리스트 만들기
//        List<MemberDto> dtoList = new ArrayList<>();
//        for (Member member : members) {
//            dtoList.add(MemberDto.toDto(member));
//        }
//        return dtoList;
        // 2. 스트림을 이용해서 처리
        return members
                .stream()
                .map(x -> MemberDto.toDto(x))
                .toList();
    }

    public void insert(MemberDto dto) {
        // Dto -> Member 변환
        Member member = MemberDto.toEntity(dto);
        memberRepository.save(member);
    }

    public void delete(Long deleteId) {
        memberRepository.deleteById(deleteId);
    }
}
