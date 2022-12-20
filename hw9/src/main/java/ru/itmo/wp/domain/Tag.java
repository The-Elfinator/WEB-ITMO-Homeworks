package ru.itmo.wp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(indexes = @Index(columnList = "nameOfTag", unique = true))
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    private String nameOfTag;

    /**
     * @noinspection unused
     */
    public Tag() {
    }

    public Tag(@NotNull String nameOfTag) {
        this.nameOfTag = nameOfTag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfTag() {
        return nameOfTag;
    }

    public void setNameOfTag(String nameOfTag) {
        this.nameOfTag = nameOfTag;
    }

}
