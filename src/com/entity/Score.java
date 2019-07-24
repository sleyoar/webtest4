package com.entity;

public class Score {
    private int id;
    private String name;
    private String lesson;
    private int grade;

    public Score() {
    }

    public Score(int id, String name, String lesson, int grade) {
        this.id = id;
        this.name = name;
        this.lesson = lesson;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
