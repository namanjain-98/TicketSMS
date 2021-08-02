package com.namanjain.entity;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOauth2User implements OidcUser{

	private OidcUser oidcUser;
	
	public CustomOauth2User(OidcUser oidcUser) {
		super();
		this.oidcUser = oidcUser;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return oidcUser.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return oidcUser.getAuthorities();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return oidcUser.getFullName();
	}

	@Override
	public Map<String, Object> getClaims() {
		// TODO Auto-generated method stub
		return oidcUser.getClaims();
	}

	@Override
	public OidcUserInfo getUserInfo() {
		// TODO Auto-generated method stub
		return oidcUser.getUserInfo();
	}

	@Override
	public OidcIdToken getIdToken() {
		// TODO Auto-generated method stub
		return oidcUser.getIdToken();
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return oidcUser.getEmail();
	}
 
 
}
