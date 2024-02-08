package com.sewista;

public class Pattern {

    private String title;
    private String desc;
    private String materials;
    private String instructions;
    private int image;

    public Pattern(String title, String desc, String materials, String instructions, int image) {
        this.title = title;
        this.desc = desc;
        this.materials = materials;
        this.instructions = instructions;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
