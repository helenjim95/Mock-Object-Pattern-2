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
    private Discussion discussion = new Discussion();

    @Mock
    private Comment commentMock;
    @Mock
    private Course courseMock;

    @Test
    void testComment() {
        int expectedNumberOfComments = discussion.getNumberOfComments() + 1;
        expect(commentMock.save()).andReturn(true);
        replay(commentMock);
        discussion.addComment(commentMock);
        assertEquals(expectedNumberOfComments, discussion.getNumberOfComments());
    }

    @Test
    void testCommentIfSavingFails() {
        int expectedNumberOfComments = discussion.getNumberOfComments();
        expect(commentMock.save()).andReturn(false);
        replay(commentMock);
        assertFalse(discussion.addComment(commentMock));
        assertEquals(expectedNumberOfComments, discussion.getNumberOfComments());
    }

    @Test
    void testStartCourseDiscussion() {
        Student shawn = new Student("Shawn", "Mendes", LocalDate.parse("1998-08-08"), "Music", "Portuguese");
        expect(courseMock.isDiscussionAllowed(shawn)).andReturn(true);
        replay(courseMock);
        assertTrue(discussion.startCourseDiscussion(courseMock, shawn, "Wonder"));
        String expectedTopic = "Wonder";
        String observedTopic = discussion.getTopic();
        assertEquals(expectedTopic, observedTopic);

        Student charlie = new Student("Charlie", "Puth", LocalDate.parse("1991-12-02"), "Music", "Vocals");
        expect(courseMock.isDiscussionAllowed(shawn)).andReturn(false);
        replay(courseMock);
        assertFalse(discussion.startCourseDiscussion(courseMock, charlie, "LightSwitch"));
    }
}
