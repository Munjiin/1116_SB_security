package org.zerock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Member;
import org.zerock.domain.MemberAuth;
import org.zerock.persistence.MemberRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {
	
	@Setter(onMethod_=@Autowired)
	private MemberRepository repo;
	
	//페스워드
	@Setter(onMethod_=@Autowired)	
	private PasswordEncoder encoder;
	
	@Test
	public void testUpdate() {
		repo.findAll().forEach(member ->{
			member.setMpw(encoder.encode("1111")); //mpw가 1111 에서 인코딩 된 패스워드로 들어감
			
			repo.save(member);
		});
		
	}
	
	@Transactional //한번에 세이브
	@Test
	public void testInsert() {
		
		IntStream.range(0, 200).forEach(i -> {
			
			Member member = new Member();
			member.setMid("user"+i);
			
			
			member.setMname("사용자" +i);
			member.setMpw("1111");
			
			List<MemberAuth> list = new ArrayList<>();
			
			list.add(new MemberAuth("MEMBER"));
			
			if(i % 3 == 0) {
				list.add(new MemberAuth("BUYER"));
			}
			
			if(i % 10 == 0) {
				list.add(new MemberAuth("ADMIN"));
			}
			
			member.setAuthList(list);
			
			repo.save(member);
			

			
		});
		
		
	}

}
