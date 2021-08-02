package com.namanjain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.namanjain.dao.PersonDAO;
import com.namanjain.entity.Person;


@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;
	
	 @Autowired
	 BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public List<Person> getPersons() {
		return (List<Person>) personDAO.findAll();
	}

	@Transactional
	public void savePerson(Person thePerson) {
		personDAO.save(thePerson);
	}

	@Transactional
	public Optional<Person> getPerson(int theId) {
		return Optional.of(personDAO.findById(theId));
	}

	@Transactional
	public void deletePerson(int theId) {
		personDAO.deleteById(theId);
	}
	


	@SuppressWarnings("serial")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Person person=personDAO.findByUsername(username);
		if(person!=null) {
			
			return new UserDetails() {
			


				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public boolean isCredentialsNonExpired() {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public boolean isAccountNonLocked() {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public boolean isAccountNonExpired() {
					// TODO Auto-generated method stub
					return true;
				}
				
				@Override
				public String getUsername() {
					// TODO Auto-generated method stub
					return person.getUsername();
				}
				
				@Override
				public String getPassword() {
					// TODO Auto-generated method stub
					return passwordEncoder.encode(person.getPassword());
				}
				
				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			        list.add(new SimpleGrantedAuthority("ROLE_"+person.getRole()));
			        return list;
				}
			};
			
		}
		else {
			return null;
		}
		
		
	}

	@Override
	public Person findByUsername(String username) {
		return personDAO.findByUsername(username);
	}



}
