package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FIVE_PARTICIPATION;
import static seedu.address.logic.commands.CommandTestUtil.ONE_PARTICIPATION;
import static seedu.address.logic.commands.CommandTestUtil.TWO_PARTICIPATION;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class SessionListTest {

    private final LocalDate date1 = LocalDate.of(2026, 3, 16);
    private final LocalDate date2 = LocalDate.of(2026, 3, 17);
    private final Session session1 = new Session(date1, new Attendance(Attendance.Status.PRESENT),
            new Participation(ONE_PARTICIPATION));
    private final Session session2 = new Session(date2, new Attendance(Attendance.Status.ABSENT),
            new Participation(TWO_PARTICIPATION));

    @Test
    public void addSession_newSession_addsSuccessfully() {
        SessionList sessionList = new SessionList();
        sessionList.addSession(session1);
        assertEquals(1, sessionList.getSessions().size());
        assertEquals(session1, sessionList.getSession(date1).get());
    }

    @Test
    public void addSession_existingDate_overwritesSuccessfully() {
        SessionList sessionList = new SessionList();
        sessionList.addSession(session1);

        // Create new session for the same date.
        Session updatedSession1 = new Session(date1, new Attendance(Attendance.Status.ABSENT),
                new Participation(FIVE_PARTICIPATION));
        sessionList.addSession(updatedSession1);

        // Size should remain 1, and the session should be the updated one.
        assertEquals(1, sessionList.getSessions().size());
        assertEquals(updatedSession1, sessionList.getSession(date1).get());
    }

    @Test
    public void getSession_existingDate_returnsSession() {
        SessionList sessionList = new SessionList(Arrays.asList(session1, session2));
        Optional<Session> result = sessionList.getSession(date1);
        assertTrue(result.isPresent());
        assertEquals(session1, result.get());
    }

    @Test
    public void getSession_nonExistentDate_returnsEmpty() {
        SessionList sessionList = new SessionList(Collections.singletonList(session1));
        Optional<Session> result = sessionList.getSession(date2);
        assertFalse(result.isPresent());
    }

    @Test
    public void getAttendance_existingSession_returnsAttendance() {
        SessionList sessionList = new SessionList(Collections.singletonList(session1));
        Optional<Attendance> attendance = sessionList.getAttendance(date1);
        assertTrue(attendance.isPresent());
        assertEquals(session1.getAttendance(), attendance.get());
    }

    @Test
    public void getParticipation_existingSession_returnsParticipation() {
        SessionList sessionList = new SessionList(Collections.singletonList(session1));
        Optional<Participation> participation = sessionList.getParticipation(date1);
        assertTrue(participation.isPresent());
        assertEquals(session1.getParticipation(), participation.get());
    }

    @Test
    public void getSessions_modifyList_throwsUnsupportedOperationException() {
        SessionList sessionList = new SessionList(Collections.singletonList(session1));
        List<Session> unmodifiableList = sessionList.getSessions();
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableList.add(session2));
    }
}
