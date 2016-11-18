package com.elasticsearch.vo;

public class SearchableTags {
    private String tag;
    private String playlistCount;

    public SearchableTags(String tag, String playlistCount) {
        super();
        this.tag = tag;
        this.playlistCount = playlistCount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPlaylistCount() {
        return playlistCount;
    }

    public void setPlaylistCount(String playlistCount) {
        this.playlistCount = playlistCount;
    }

    @Override
    public String toString() {
        return "SearchableTags [tag=" + tag + ", playlistCount=" + playlistCount + "]";
    }

}
