package models;

import java.util.List;

public class CourseModel {
    private String name;
    private String sourceImageSrc;
    private List<String> content;

    public CourseModel(String name, String sourceImageSrc, List<String> content) {
        this.name = name;
        this.sourceImageSrc = sourceImageSrc;
        this.content = content;
    }

    public CourseModel(String name, String sourceImageSrc) {
        this.name = name;
        this.sourceImageSrc = sourceImageSrc;
    }

    public CourseModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceImageSrc() {
        return sourceImageSrc;
    }

    public void setSourceImageSrc(String sourceImageSrc) {
        this.sourceImageSrc = sourceImageSrc;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
