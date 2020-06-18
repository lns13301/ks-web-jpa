package kr.ac.ks.app.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LessonForm {
    private Long id;
    private String name;
    private int quota;
}
