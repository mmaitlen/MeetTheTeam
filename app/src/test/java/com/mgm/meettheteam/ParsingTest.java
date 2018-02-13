package com.mgm.meettheteam;

import com.google.gson.Gson;

import com.mgm.meettheteam.model.Person;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by michaelmaitlen on 2/12/18.
 */

public class ParsingTest {

    private static final String AVATAR = "https://media.licdn.com/media/AAEAAQAAAAAAAAPuAAAAJDcxZGJjZDlhLTYzMjUtNDFhZi1hYzFjLWM1MmEzNGVlODRmMw.jpg";
    private static final String BIO = "I have zero cycles for this. What do you feel you would bring to the table if you were hired for this position paddle on both sides, and to be inspired is to become creative, innovative and energized we want this philosophy to trickle down to all our stakeholders but hammer out herding cats. Going forward drop-dead date.\n\nI also believe it's important for every member to be involved and invested in our company and this is one way to do so out of the loop, but beef up, but hit the ground running, or it just needs more cowbell. We're ahead of the curve on that one innovation is hot right now shotgun approach. Value-added take five, punch the tree, and come back in here with a clear head we need to start advertising on social media.";
    private static final String FIRSTNAME = "Stephen";
    private static final String LASTNAME = "Brandon";
    private static final String TITLE = "Lead DevOps";

    @Test
    public void parsePerson() throws UnsupportedEncodingException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("team.json");
        Reader reader = new InputStreamReader(in, "UTF-8");
        Gson gson = new Gson();
        Person[] person = gson.fromJson(reader, Person[].class);

        assertThat(person.length, is(19));

        Person p1 = person[0];

        assertThat(p1.avatar, is(AVATAR));
        assertThat(p1.bio, is(BIO));
        assertThat(p1.firstName, is(FIRSTNAME));
        assertThat(p1.lastName, is(LASTNAME));
        assertThat(p1.title, is(TITLE));
    }
}
