package onlinejudge.oauth.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import onlinejudge.domain.User;
import onlinejudge.repository.UserRepository;

@RestController
public class UserController {
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public @ResponseBody String getWellcome() {
		return "Hello";
	}
	
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	@ResponseBody
	public Principal currentUser(Principal principal){
		return principal;
	}
}
