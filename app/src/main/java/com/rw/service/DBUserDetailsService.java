package com.rw.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rw.entity.Role;
import com.rw.entity.User;
import com.rw.repository.UserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		
		User user = userRepository.findByPhone(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		
		Set<GrantedAuthority> auth = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : user.getRoles()) {
			auth.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), auth);
	}

}
