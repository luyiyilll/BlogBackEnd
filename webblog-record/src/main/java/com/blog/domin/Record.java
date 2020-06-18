package com.blog.domin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="tb_record")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private int user_id;
    private String content;
    private String create_time;

    public int getId() {
        return rid;
    }

    public void setId(int id) {
        this.rid = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
