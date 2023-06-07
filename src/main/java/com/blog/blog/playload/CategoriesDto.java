package com.blog.blog.playload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesDto {

    private int catId;
    @NotEmpty
    @Size(min = 4,message = "minimum size 4")
    @JsonProperty("title")
    private String catTitle;
    @NotEmpty
    @Size(min = 10,message = "minimum size of 10 charter")
    @JsonProperty("description")
    private String catDescription;
}
