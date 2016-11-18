package com.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "content", type = "contentInfo", useServerConfiguration = true, createIndex = true)
public class Content {
    @Id
    private Long contentId;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "standard")
    private String title;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "standard")
    private String tagMetaData;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "standard")
    private String tagAndTitle;

    @Field(type = FieldType.String, index = FieldIndex.no)
    private String path;

    public Content() {
    }

    public Content(Long contentId, String title, String tagMetaData, String path) {
        super();
        this.contentId = contentId;
        this.title = title;
        this.tagMetaData = tagMetaData;
        this.path = path;
        this.tagAndTitle = constructTagAndTitle();
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagMetaData() {
        return tagMetaData;
    }

    public void setTagMetaData(String tagMetaData) {
        this.tagMetaData = tagMetaData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTagAndTitle() {
        return tagAndTitle;
    }

    private String constructTagAndTitle() {
        String tagAndTitleMeta = "";
        if (tagMetaData != null) {
            tagAndTitleMeta += tagMetaData;
        }
        if (title != null) {
            tagAndTitleMeta += title;
        }
        return tagAndTitleMeta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contentId == null) ? 0 : contentId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Content other = (Content) obj;
        if (contentId == null) {
            if (other.contentId != null)
                return false;
        } else if (!contentId.equals(other.contentId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Content [contentId=" + contentId + ", title=" + title + ", tagMetaData=" + tagMetaData + ", path="
                + path + "]";
    }

}
