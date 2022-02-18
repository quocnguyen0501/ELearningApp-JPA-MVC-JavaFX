package helper;

import static helper.ColorConst.*;
import static helper.ImgConst.*;

public enum Reactions {
    NON(0, "Like", COLOR_NON, NON_URL),
    LIKE(1, "Like",COLOR_LIKE, LIKE_URL),
    LOVE(2, "Love",COLOR_LOVE, LOVE_URL),
    CARE(3, "Care",COLOR_CARE_HAHA_WOW_SAD, CARE_URL),
    HAHA(4, "Haha",COLOR_CARE_HAHA_WOW_SAD, HAHA_URL),
    WOW(5, "Wow",COLOR_CARE_HAHA_WOW_SAD, WOW_URL),
    SAD(6, "Sad",COLOR_CARE_HAHA_WOW_SAD, SAD_URL),
    ANGRY(7, "Angry",COLOR_ANGRY, ANGRY_URL);

    private int id;
    private String name;
    private String color;
    private String imgSrc;

    Reactions(int id, String name, String color, String imgSrc) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.imgSrc = imgSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
