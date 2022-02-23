package job4j.url_shortcut.repository;

import job4j.url_shortcut.model.Shortcut;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ShortcutRepo extends CrudRepository<Shortcut, Integer> {
    Boolean existsByKey(String key);

    @Query("select distinct s from Shortcut s where s.key = :key")
    Shortcut findByKey(String key);
}