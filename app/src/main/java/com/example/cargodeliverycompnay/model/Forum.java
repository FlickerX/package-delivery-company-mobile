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
public class Forum {

    private int id;

    private String forumTitle;
    private String forumDescription;

    private List<Comment> comments;


    private Destination destination;

    public Forum(String forumTitle, String forumDescription, Destination destination) {
        this.forumTitle = forumTitle;
        this.forumDescription = forumDescription;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return forumTitle +
                ", " + forumDescription;
    }
}
