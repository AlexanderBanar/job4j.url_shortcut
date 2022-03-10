package job4j.url_shortcut.control;

import job4j.url_shortcut.DTO.CodeDTO;
import job4j.url_shortcut.DTO.RegDTO;
import job4j.url_shortcut.model.Shortcut;
import job4j.url_shortcut.model.Site;
import job4j.url_shortcut.repository.ShortcutRepo;
import job4j.url_shortcut.repository.SiteRepo;
import job4j.url_shortcut.service.CredGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Validated
@RestController
@RequestMapping("/shortcut")
public class SiteController {
    private SiteRepo siteRepo;
    private ShortcutRepo shortcutRepo;
    private BCryptPasswordEncoder encoder;
    private CredGenerator credGenerator;

    public SiteController(SiteRepo siteRepo, ShortcutRepo shortcutRepo,
                          BCryptPasswordEncoder encoder, CredGenerator credGenerator) {
        this.siteRepo = siteRepo;
        this.shortcutRepo = shortcutRepo;
        this.encoder = encoder;
        this.credGenerator = credGenerator;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegDTO> register(@RequestParam String siteName) {
        if (siteName == null) {
            throw new NullPointerException("SiteName must not be empty");
        }
        Site site = siteRepo.findByName(siteName);
        if (site != null) {
            throw new NullPointerException("The site with this name is already registered");
        }
        String login = credGenerator.getUniqueLogin();
        String password = credGenerator.getUniquePassword();
        Site resultSite = Site.of(siteName, login, encoder.encode(password));
        siteRepo.save(resultSite);
        return new ResponseEntity<>(
                RegDTO.of(true, login, password),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        if (!shortcutRepo.existsByKey(code)) {
            throw new NullPointerException("code is not found");
        }
        Shortcut shortcut = shortcutRepo.findByKey(code);
        String address = shortcut.getValue();
        shortcutRepo.updateCount(code);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(address)).build();
    }

    @PostMapping("/convert")
    public ResponseEntity<CodeDTO> convert(@RequestParam String url) {
        if (!(url.startsWith("https://") || url.startsWith("http://"))) {
            throw new NullPointerException("url must start with 'https://' or 'http://'");
        }
        String code = credGenerator.getUniqueUrlKey();
        shortcutRepo.save(Shortcut.of(code, url));
        return new ResponseEntity<>(
                CodeDTO.of(code),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/statistics")
    public List<Shortcut> getAllShortcuts() {
        return StreamSupport.stream(
                shortcutRepo.findAll().spliterator(),
                false
        ).collect(Collectors.toList());
    }
}