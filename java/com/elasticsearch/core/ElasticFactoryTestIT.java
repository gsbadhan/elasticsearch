package com.elasticsearch.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:elastic-context.xml" })
public class ElasticFactoryTestIT {

    @Inject
    private ElasticFactory elasticFactory;

    @Test
    public void testTemplateInstance() {
        assertNotNull(elasticFactory);
        assertNotNull(elasticFactory.geElasticsearchTemplate());
        assertTrue(elasticFactory.geElasticsearchTemplate() instanceof ElasticsearchTemplate);
    }
}
