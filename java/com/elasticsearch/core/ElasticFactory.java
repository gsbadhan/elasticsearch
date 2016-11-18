package com.elasticsearch.core;

import javax.inject.Inject;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

@Component
public class ElasticFactory {

    @Inject
    private ElasticsearchTemplate elasticsearchTemplate;

    public ElasticsearchTemplate geElasticsearchTemplate() {
        return this.elasticsearchTemplate;
    }
}
