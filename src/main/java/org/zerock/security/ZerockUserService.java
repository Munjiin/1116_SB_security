package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.domain.Member;
import org.zerock.persistence.MemberRepository;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
public class ZerockUserService implements UserDetailsService {
	
	@Setter(onMethod_= @Autowired)
	private MemberRepository repo;

	//인터페이스의 추상 메소드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warning("=========================================");
		log.warning("=========================================");
		log.warning("loadUser " +username);
		
		/*Member member = repo.findById(username).get();
		log.warning("" + member);
		
		log.warning("=========================================");
		log.warning("=========================================");
		
		
		return null;*/
		
		return repo.findById(username).filter(vo -> vo != null).map(vo->new ZerockSecurityUser(vo)).get();
	}

}
