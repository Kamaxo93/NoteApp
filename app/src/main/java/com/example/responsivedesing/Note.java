package com.example.responsivedesing;

public class Note {

    private String title;
    private String content;
    private String favorite;
    private int color;

    public Note (String title, String content, String favorite, int color) {
        this.title = title;
        this.content = content;
        this.favorite = favorite;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    
  
}
