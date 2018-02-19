package com.lmonkiewicz.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lmonkiewicz.example.validation.InRange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by lmonkiewicz on 17.03.2017.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull(groups = Existing.class)
    @Null(groups = New.class)
    @Pattern(regexp = "[0-9]*",message = "必须为数字")
    private Long id;

    @Size(min = 1,max = 5,message = "length between 1 and 10", groups = {Existing.class,New.class})
    @NotNull(message = "First name is required", groups = {Existing.class, New.class}
    )
    private String firstName;

    @Pattern(regexp = "[0-9]*",message = "必须为数字")
    //@NotNull(groups = {Existing.class, New.class})
    private String lastName;

    @NotNull(groups = {Existing.class, New.class})
    @InRange(
        min=18,
        message = "User must be at least 18 years old",
        groups = {Existing.class, New.class}
    )
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate2;

    @Email(message = "Email is required",groups = {Existing.class, New.class})
    private String email;


    public interface Existing {
    }

    public interface New {
    }
}
