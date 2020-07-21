package com.group.cast.diffator.domain;

import javax.persistence.*;

@Entity
public class Diff {

    @Id
    private Long id;

    @Lob
    @Column(name = "left")
    private String left;

    @Lob
    @Column(name = "right")
    private String right;

    public Diff setLeft(String base64Decoded) {
        this.left = base64Decoded;
        return this;
    }

    public Diff setRight(String base64Decoded) {
        this.right = base64Decoded;
        return this;
    }

    public Diff setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLeft() {
        return this.left;
    }

    public String getRight() {
        return this.right;
    }
}
