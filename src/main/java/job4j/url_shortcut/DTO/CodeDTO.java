package job4j.url_shortcut.DTO;

public class CodeDTO {
    private String code;

    public static CodeDTO of(String code) {
        CodeDTO codeDTO = new CodeDTO();
        codeDTO.code = code;
        return codeDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}