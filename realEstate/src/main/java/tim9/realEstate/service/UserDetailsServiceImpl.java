package tim9.realEstate.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Person;
import tim9.realEstate.model.User;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.repository.AdminRepository;
import tim9.realEstate.repository.UserRepository;
import tim9.realEstate.repository.VerifierRepository;

/**
 * This class represents UserDetailsService implementation
 *
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerifierRepository verifierRepository;

	@Autowired
	private AdminRepository adminRepository;

	/**
	 * This method does logic about get user, verifier, admin or clerk from data
	 * base with specified user name
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Person user = userRepository.findByUsername(username);
		Verifier verifier = verifierRepository.findByUsername(username);
		Person admin = adminRepository.findByUsername(username);

		if (user == null && verifier == null && admin == null) {
			throw new UsernameNotFoundException(String.format("No person found with username '%s'.", username));
		} else {
			if (user != null) {
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				if (((User) user).isClerk() && !((User) user).isApproved()) {
					return new org.springframework.security.core.userdetails.User(user.getUsername(),
							user.getPassword(), grantedAuthorities.stream().collect(Collectors.toList()));
				}
				grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthority().getName()));
				return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
						grantedAuthorities.stream().collect(Collectors.toList()));
			} else if (verifier != null) {
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				grantedAuthorities.add(new SimpleGrantedAuthority(verifier.getAuthority().getName()));
				return new org.springframework.security.core.userdetails.User(verifier.getUsername(),
						verifier.getPassword(), grantedAuthorities.stream().collect(Collectors.toList()));
			} else {
				List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
				grantedAuthorities.add(new SimpleGrantedAuthority(admin.getAuthority().getName()));
				return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
						grantedAuthorities.stream().collect(Collectors.toList()));
			}
		}
	}

}
