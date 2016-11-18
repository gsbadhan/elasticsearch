package com.elasticsearch.repository;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import com.elasticsearch.model.Content;
import com.elasticsearch.model.Tag;
import com.elasticsearch.vo.SearchablePlaylist;
import com.elasticsearch.vo.SearchableTags;

@Repository
public class ContentOperationImpl implements ContentOperation {

    @Inject
    private ContentRepository contentRepository;
    @Inject
    private TagRepository tagRepository;
    @Inject
    private ElasticsearchTemplate elasticsearchTemplate;

    public ContentOperationImpl() {
    }

    public Content save(Content content) {
        return contentRepository.save(content);
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Iterable<Content> saveAllContent(List<Content> contents) {
        return contentRepository.save(contents);
    }

    public Iterable<Tag> saveAllTag(List<Tag> tags) {
        return tagRepository.save(tags);
    }

    public List<SearchablePlaylist> searchPlaylistByTitle(String text, int from, int to) {
        SearchRequestBuilder requestBuilder = elasticsearchTemplate.getClient().prepareSearch("content").setFrom(from)
                .setSize(to).setQuery(QueryBuilders.regexpQuery("title", ".*" + text + ".*"))
                .addSort(SortBuilders.scoreSort().order(SortOrder.DESC));

        return getPlaylist(requestBuilder);
    }

    public List<SearchablePlaylist> searchPlaylistByTag(String text, int from, int to) {
        SearchRequestBuilder requestBuilder = elasticsearchTemplate.getClient().prepareSearch("content").setFrom(from)
                .setSize(to).setQuery(QueryBuilders.regexpQuery("tagMetaData", ".*" + text + ".*"))
                .addSort(SortBuilders.scoreSort().order(SortOrder.DESC));

        return getPlaylist(requestBuilder);
    }

    public List<SearchablePlaylist> searchPlaylistByTagTitle(String text, int from, int to) {
        SearchRequestBuilder requestBuilder = elasticsearchTemplate.getClient().prepareSearch("content").setFrom(from)
                .setSize(to).setQuery(QueryBuilders.regexpQuery("tagAndTitle", ".*" + text + ".*"))
                .addSort(SortBuilders.scoreSort().order(SortOrder.DESC));

        return getPlaylist(requestBuilder);
    }

    public List<SearchableTags> searchTag(String text, int from, int to) {
        List<SearchableTags> out = null;
        SearchRequestBuilder requestBuilder = elasticsearchTemplate.getClient().prepareSearch("tag").setFrom(from)
                .setSize(to).setQuery(QueryBuilders.regexpQuery("name", ".*" + text + ".*"))
                .addSort(SortBuilders.fieldSort("occurence").order(SortOrder.DESC));

        SearchResponse response = requestBuilder.execute().actionGet();
        if (response != null) {
            out = new LinkedList<>();
            Iterator<SearchHit> iterator = response.getHits().iterator();
            while (iterator.hasNext()) {
                Map<String, Object> map = iterator.next().sourceAsMap();
                out.add(new SearchableTags(map.get("name").toString(), map.get("occurence").toString()));
            }
        }
        return out;
    }

    public void deleteAllContent() {
        contentRepository.deleteAll();
    }

    public void deleteAllTag() {
        tagRepository.deleteAll();
    }

    private List<SearchablePlaylist> getPlaylist(SearchRequestBuilder requestBuilder) {
        List<SearchablePlaylist> playlists = null;
        SearchResponse response = requestBuilder.execute().actionGet();
        if (response != null) {
            playlists = new LinkedList<>();
            Iterator<SearchHit> iterator = response.getHits().iterator();
            while (iterator.hasNext()) {
                Map<String, Object> map = iterator.next().getSource();
                playlists.add(new SearchablePlaylist(Long.valueOf(map.get("contentId").toString()),
                        map.get("title").toString()));
            }
        }
        return playlists;
    }

}
