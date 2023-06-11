package com.blog.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
@Entity
public class CategoriesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catId;

    @Column(name = "title",length = 100,nullable = false)
    private String catTitle;

    @Column(name = "description")
    private String catDescription;

    @OneToMany(mappedBy = "categoriesModel",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PostModel> posts=new ArrayList<>();


}

