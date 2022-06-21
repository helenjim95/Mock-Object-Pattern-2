package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {

    // TODO implement the tests
    @TestSubject
    Discussion Discussion = new Discussion();

    @Mock
    Comment commentMock;
    Course courseMock;

    @Test
    void testComment() {
        int expectedNumberOfComments = Discussion.getNumberOfComments() + 1;
        expect(commentMock.save()).andReturn(true);
        replay(courseMock);
        Discussion.addComment(commentMock);
        assertEquals(expectedNumberOfComments, Discussion.getNumberOfComments());
    }

    @Test
    void testCommentIfSavingFails() {
        int expectedNumberOfComments = Discussion.getNumberOfComments();
        expect(commentMock.save()).andReturn(false);
        replay(courseMock);
        Discussion.addComment(commentMock);
        assertEquals(expectedNumberOfComments, Discussion.getNumberOfComments());
    }

    @Test
    void testStartCourseDiscussion() {
        Student shawn = new Student("Shawn", "Mendes", LocalDate.parse("1998-08-08"), "Music", "Portuguese");
        expect(courseMock.isDiscussionAllowed(shawn)).andReturn(true);
        replay(courseMock);
        assertTrue(Discussion.startCourseDiscussion(courseMock, shawn, "Wonder"));
        String expected_topic = "Wonder";
        String observed_topic = Discussion.getTopic();
        assertEquals(expected_topic, observed_topic);
    }
}
