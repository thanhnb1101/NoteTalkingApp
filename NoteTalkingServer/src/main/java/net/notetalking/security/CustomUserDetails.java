package net.notetalking.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;

	public CustomUserDetails(long userId, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
		super(userName, password, authorities);
		this.userId = userId;
		this.userName = userName;
	}
	
	public CustomUserDetails(long userId, String userName, Collection<? extends GrantedAuthority> authorities) {
		super(userName, "", authorities);
		this.userId = userId;
		this.userName = userName;
	}

}
