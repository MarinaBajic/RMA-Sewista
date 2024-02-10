package com.sewista.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pattern")
public class Pattern {

    @ColumnInfo(name = "pattern_id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "materials")
    private String materials;

    @ColumnInfo(name = "instructions")
    private String instructions;

    public Pattern(String title, String desc, String materials, String instructions) {
        this.id = 0;
        this.title = title;
        this.desc = desc;
        this.materials = materials;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }
}
