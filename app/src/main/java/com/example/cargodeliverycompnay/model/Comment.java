package com.example.cargodeliverycompnay.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private String title;
    private String commentText;

    private Forum forum;

    private List<Comment> replies;
    private Comment parentComment;
    private Forum parentForum;

    public Comment(String title, String commentText, Forum parentForum) {
        this.title = title;
        this.commentText = commentText;
        this.parentForum = parentForum;
    }
    public Comment(String title, String commentText, Comment parentComment) {
        this.title = title;
        this.commentText = commentText;
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "Title= " + title + ", Text ='" + commentText;
    }
}
