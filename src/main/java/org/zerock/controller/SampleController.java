package org.zerock.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/sample/*")
public class SampleController {
	
	@PreAuthorize("permitAll()")//로그인 안한 사용자만 isAnonymous / 로그인 상관 없이
	@GetMapping("/all")
	public void doAll() {
		log.info("doAll................");
	}
	
	@PreAuthorize("isAuthenticated()") //로그인 된 사용자만
	@GetMapping("/member")
	public void doMember() {
		log.info("doMember................");
	}
	
	@PreAuthorize("hasRole('ROLE_BUYER')") //바이어 권한을 가진 사용자만
	@GetMapping("/buyer")
	public void doBuyer() {
		log.info("doBuyer................");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("doAdmin................");
	}

}
