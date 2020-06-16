package com.blog.domin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tb_recently_visited")
public class Visited {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private int visited_user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVisited_user_id() {
        return visited_user_id;
    }

    public void setVisited_user_id(int visited_user_id) {
        this.visited_user_id = visited_user_id;
    }

    @Override
    public String toString() {
        return "Visited{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", visited_user_id=" + visited_user_id +
                '}';
    }
}
