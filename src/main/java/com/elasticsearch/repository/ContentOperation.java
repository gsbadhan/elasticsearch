package com.elasticsearch.repository;

import java.util.List;

import com.elasticsearch.model.Content;
import com.elasticsearch.model.Tag;
import com.elasticsearch.vo.SearchablePlaylist;
import com.elasticsearch.vo.SearchableTags;

public interface ContentOperation {
    Content save(Content content);

    Iterable<Content> saveAllContent(List<Content> contents);

    List<SearchablePlaylist> searchPlaylistByTitle(String text, int from, int to);

    List<SearchablePlaylist> searchPlaylistByTag(String text, int from, int to);

    List<SearchablePlaylist> searchPlaylistByTagTitle(String text, int from, int to);

    void deleteAllContent();

    Tag save(Tag tag);

    Iterable<Tag> saveAllTag(List<Tag> tags);

    List<SearchableTags> searchTag(String text, int from, int to);

    void deleteAllTag();

}
