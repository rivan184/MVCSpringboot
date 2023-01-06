package com.example.demo.model;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "tb_m_region")
public class Region {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "region")
    private List<Division> division;


    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
