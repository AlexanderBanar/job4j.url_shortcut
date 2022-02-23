package job4j.url_shortcut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shortcuts")
public class Shortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @JsonIgnore
    private String key;

    private String value;
    private int count;

    public static Shortcut of(String key, String value) {
        Shortcut shortcut = new Shortcut();
        shortcut.key = key;
        shortcut.value = value;
        shortcut.count = 0;
        return shortcut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shortcut shortcut = (Shortcut) o;
        return id == shortcut.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}