package com.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "tag", type = "tagInfo", useServerConfiguration = true, createIndex = true)
public class Tag {

    @Id
    private Long id;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Long, index = FieldIndex.analyzed, analyzer = "standard")
    private Long occurence;

    public Tag() {
    }

    public Tag(Long id, String name, Long occurence) {
        super();
        this.id = id;
        this.name = name;
        this.occurence = occurence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOccurence() {
        return occurence;
    }

    public void setOccurence(Long occurence) {
        this.occurence = occurence;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Tag other = (Tag) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Tag [id=" + id + ", name=" + name + ", occurence=" + occurence + "]";
    }

}
