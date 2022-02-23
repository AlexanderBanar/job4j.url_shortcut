package job4j.url_shortcut.DTO;

public class RegDTO {
    private boolean registration;
    private String login;
    private String password;

    public static RegDTO of(boolean registration, String login,
                            String password) {
        RegDTO regDTO = new RegDTO();
        regDTO.registration = registration;
        regDTO.login = login;
        regDTO.password = password;
        return regDTO;
    }

    public boolean isRegistration() {
        return registration;
    }

    public void setRegistration(boolean registration) {
        this.registration = registration;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}