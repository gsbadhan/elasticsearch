package com.elasticsearch.repository;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elasticsearch.model.Content;
import com.elasticsearch.model.Tag;
import com.elasticsearch.vo.SearchablePlaylist;
import com.elasticsearch.vo.SearchableTags;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:elastic-context.xml" })
public class ModelDrivenCRUDRepositoryTestIT {

    @Inject
    private ContentOperation contentOperation;

    @Before
    public void setup() {
        contentOperation.deleteAllContent();
        contentOperation.deleteAllTag();

        // save content data

        Content xx =
                new Content(101L, "dili wali girl friend shod shaad ke", "bollywood,song,party", "c://ppapa/xx.avi");
        contentOperation.save(xx);
        contentOperation.save(new Content(102L, "ey kabiraa maan ja", "bollywood,love song", "c://ppapa/xx.avi"));
        contentOperation.save(new Content(103L, "sri radhe radhe", "bollywood,song,divine", "c://ppapa/xx.avi"));
        contentOperation.save(new Content(104L, "revenent", "hollywood,movie,thrill", "c://ppapa/xx.avi"));
        contentOperation.save(new Content(105L, "rock", "hollywood,movie,action", "c://ppapa/xx.avi"));
        contentOperation
                .save(new Content(106L, "ay dil hai mushkil", "bollywood,movie,romantic,love", "c://ppapa/xx.avi"));
        contentOperation.save(new Content(107L, "conjuring part-1", "hollywood,movie,horror", "c://ppapa/xx.avi"));
        contentOperation
                .save(new Content(108L, "american pie part -3", "hollywood,movie,comedy,adult", "c://ppapa/xx.avi"));
        contentOperation.save(new Content(109L, "mohenjo daro", "bollywood,movie,historical", "c://ppapa/xx.avi"));
        contentOperation.save(new Content(110L, "bhagbaan", "bollywood,movie,family drama", "c://ppapa/xx.avi"));

        // save tag data
        contentOperation.save(new Tag(1L, "bollwood", 6L));
        contentOperation.save(new Tag(2L, "song", 2L));
        contentOperation.save(new Tag(24L, "love song", 1L));
        contentOperation.save(new Tag(9L, "party", 1L));
        contentOperation.save(new Tag(6L, "divine", 1L));
        contentOperation.save(new Tag(8L, "hollywood", 4L));
        contentOperation.save(new Tag(10L, "movie", 7L));
        contentOperation.save(new Tag(19L, "family drama", 1L));
        contentOperation.save(new Tag(22L, "historical", 1L));
        contentOperation.save(new Tag(7L, "comedy", 1L));
        contentOperation.save(new Tag(20L, "adult", 1L));
        contentOperation.save(new Tag(18L, "horror", 1L));
        contentOperation.save(new Tag(21L, "action", 1L));
        contentOperation.save(new Tag(14L, "love", 1L));
        contentOperation.save(new Tag(13L, "romantic", 1L));
        contentOperation.save(new Tag(15L, "thrill", 1L));
    }

    @Test
    public void searchFromText() {
        String userInput = "song";
        List<SearchableTags> tags = contentOperation.searchTag(userInput, 0, 5);
        System.out.println(tags);

        List<SearchablePlaylist> playlists = contentOperation.searchPlaylistByTagTitle(userInput, 0, 5);
        System.out.println(playlists);
    }
}
