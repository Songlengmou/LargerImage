package com.anningtex.largerimage.bean;

import java.io.Serializable;

/**
 * @Author Song
 * @Date：2021-08-24
 */
public class PictureEntity implements Serializable {
    private String imgOriginUrl;
    private String imgMiddleUrl;
    private String imgColorUrl;
    private String name;

    public String getImgOriginUrl() {
        return imgOriginUrl;
    }

    public void setImgOriginUrl(String imgOriginUrl) {
        this.imgOriginUrl = imgOriginUrl;
    }

    public String getImgMiddleUrl() {
        return imgMiddleUrl;
    }

    public void setImgMiddleUrl(String imgMiddleUrl) {
        this.imgMiddleUrl = imgMiddleUrl;
    }

    public String getImgColorUrl() {
        return imgColorUrl;
    }

    public void setImgColorUrl(String imgColorUrl) {
        this.imgColorUrl = imgColorUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PictureEntity{" +
                "imgOriginUrl='" + imgOriginUrl + '\'' +
                ", imgMiddleUrl='" + imgMiddleUrl + '\'' +
                ", imgColorUrl='" + imgColorUrl + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}