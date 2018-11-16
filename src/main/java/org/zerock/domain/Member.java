package org.zerock.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="t_member")
public class Member {
	
	@Id
	private String mid;
	private String mpw;
	private String mname;
	
	//단방향
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) //가운데 테이블 생김. 조인 필요
	@JoinColumn(name="member")
	private List<MemberAuth> authList;

}
