package job4j.url_shortcut.repository;

import job4j.url_shortcut.model.Site;
import org.springframework.data.repository.CrudRepository;

public interface SiteRepo extends CrudRepository<Site, Integer> {
    Site findByName(String name);

    Boolean existsByLogin(String login);

    Boolean existsByPassword(String password);

    Site findByLogin(String login);
}