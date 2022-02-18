package models;

import java.sql.Timestamp;

public class StatusModel {
    private UserModel userModel;

    private Timestamp time;
    private String caption;
    private String urlImage;
    private int totalReactions;
    private int nbComments;
    private int nbShares;

    public StatusModel(Timestamp time, String caption, String urlImage, int totalReactions, int nbComments, int nbShares) {
        this.time = time;
        this.caption = caption;
        this.urlImage = urlImage;
        this.totalReactions = totalReactions;
        this.nbComments = nbComments;
        this.nbShares = nbShares;
    }

    public StatusModel() {
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(int totalReactions) {
        this.totalReactions = totalReactions;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getNbShares() {
        return nbShares;
    }

    public void setNbShares(int nbShares) {
        this.nbShares = nbShares;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public String toString() {
        return "StatusModel{" +
                "userModel=" + userModel +
                ", time=" + time +
                ", caption='" + caption + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", totalReactions=" + totalReactions +
                ", nbComments=" + nbComments +
                ", nbShares=" + nbShares +
                '}';
    }
}
