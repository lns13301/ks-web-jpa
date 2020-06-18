package kr.ac.ks.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class StudentForm {
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식을 확인해주세요")
    private String email;
}
