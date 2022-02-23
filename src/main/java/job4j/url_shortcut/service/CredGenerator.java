package job4j.url_shortcut.service;

import job4j.url_shortcut.repository.ShortcutRepo;
import job4j.url_shortcut.repository.SiteRepo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CredGenerator {
    private SiteRepo siteRepo;
    private ShortcutRepo shortcutRepo;

    public CredGenerator(SiteRepo siteRepo, ShortcutRepo shortcutRepo) {
        this.siteRepo = siteRepo;
        this.shortcutRepo = shortcutRepo;
    }

    public String getUniqueLogin() {
        String login = "";
        boolean isUnique = false;
        while(!isUnique) {
            login = template(5);
            if (!siteRepo.existsByLogin(login)) {
                isUnique = true;
            }
        }
        return login;
    }

    public String getUniquePassword() {
        String password = "";
        boolean isUnique = false;
        while(!isUnique) {
            password = template(10);
            if (!siteRepo.existsByPassword(password)) {
                isUnique = true;
            }
        }
        return password;
    }

    public String getUniqueUrlKey() {
        String key = "";
        boolean isUnique = false;
        while(!isUnique) {
            key = template(7);
            if (!shortcutRepo.existsByKey(key)) {
                isUnique = true;
            }
        }
        return key;
    }

    private String template(int targetStringLength) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}