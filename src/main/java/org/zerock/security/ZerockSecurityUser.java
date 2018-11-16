package org.zerock.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.domain.Member; //이거로 상속

import lombok.Data;

@Data
public class ZerockSecurityUser extends User {

	private Member member;
	//멤버 가져오기
	public ZerockSecurityUser(Member member) {
		super(member.getMid(), member.getMpw(),
				member.getAuthList().stream().map(auth->new SimpleGrantedAuthority("ROLE_"+auth.getAuth()))
				.collect(Collectors.toList())
				
				);
		this.member = member;
		
	}
	
/*	public ZerockSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
*/
}
