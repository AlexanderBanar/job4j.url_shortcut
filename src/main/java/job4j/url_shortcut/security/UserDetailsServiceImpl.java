package job4j.url_shortcut.security;

import job4j.url_shortcut.model.Site;
import job4j.url_shortcut.repository.SiteRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private SiteRepo siteRepo;

    public UserDetailsServiceImpl(SiteRepo siteRepo) {
        this.siteRepo = siteRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Site site = siteRepo.findByLogin(login);
        if (site == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(site.getName(), site.getPassword(), emptyList());
    }
}