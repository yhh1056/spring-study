package com.example.dbreplication.domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void save() {
        memberService.save("hoho");
    }

    @Test
    void findAll() {
        List<Member> members = memberService.findAll();
    }
}
