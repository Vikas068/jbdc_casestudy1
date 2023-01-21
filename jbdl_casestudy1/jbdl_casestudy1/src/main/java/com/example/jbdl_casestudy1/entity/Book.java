package com.example.jbdl_casestudy1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int isbn;
    private String name;
    private String cost;
    private String author;
}
