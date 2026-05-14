package com.my.member_app.dto;

import com.my.member_app.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String name;
    private int age;
    private String address;

    // memberDto(DTO) -> Member Entity 로 변환
    public static Member toEntity(MemberDto dto) {
        // 빈 깡통 Member 생성 후 하나씩 넣어주고 리턴
        Member member = new Member();
        member.setId(dto.getId() );
        member.setName((dto.getName()));
        member.setAge(dto.getAge());
        member.setAddress(dto.getAddress());
        return member;
    }
    // Member Entity -> memberDto(DTO)
    public static MemberDto toDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getAddress()
        );
    }
}
