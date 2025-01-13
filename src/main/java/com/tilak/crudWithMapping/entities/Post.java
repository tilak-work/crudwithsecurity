package com.tilak.crudWithMapping.entities;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
