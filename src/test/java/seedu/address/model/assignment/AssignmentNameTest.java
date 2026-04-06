package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // EP: null name
        assertThrows(NullPointerException.class, () -> new AssignmentName(null));
    }

    @Test
    public void constructor_emptyName_throwsIllegalArgumentException() {
        // EP: empty name
        assertThrows(IllegalArgumentException.class, () -> new AssignmentName(""));
    }

    @Test
    public void constructor_whiteSpace_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AssignmentName(" "));
    }

    @Test
    public void constructor_invalidAssignmentName_throwsIllegalArgumentException() {
        // EP: invalid names
        assertThrows(IllegalArgumentException.class, () -> new AssignmentName("Test#"));
        assertThrows(IllegalArgumentException.class, () -> new AssignmentName("Quiz!1"));
    }

    @Test
    public void isValidAssignmentName() {
        // EP: null name is not valid
        assertFalse(AssignmentName.isValidAssignmentName(null));

        // EP: invalid name — blank or empty
        assertFalse(AssignmentName.isValidAssignmentName("")); // empty string
        assertFalse(AssignmentName.isValidAssignmentName(" ")); // spaces only

        // EP: invalid — special characters
        assertFalse(AssignmentName.isValidAssignmentName("Test#")); // hash
        assertFalse(AssignmentName.isValidAssignmentName("Quiz!1")); // exclamation
        assertFalse(AssignmentName.isValidAssignmentName("Lab@2")); // at sign
        assertFalse(AssignmentName.isValidAssignmentName("#Assignment")); // starts with special char


        // EP: valid
        assertTrue(AssignmentName.isValidAssignmentName("Quiz")); // single word
        assertTrue(AssignmentName.isValidAssignmentName("Quiz 1")); // with space
        assertTrue(AssignmentName.isValidAssignmentName("Midterm Exam")); // multiple words
        assertTrue(AssignmentName.isValidAssignmentName("Assignment1")); // alphanumeric no space
        assertTrue(AssignmentName.isValidAssignmentName("Lab 2B")); // mixed alphanumeric with space
        assertTrue(AssignmentName.isValidAssignmentName("12345")); // numbers only
        assertTrue(AssignmentName.isValidAssignmentName(" Assignment")); // starts with space
    }

    @Test
    public void equals() {
        AssignmentName assignmentName = new AssignmentName("Quiz 1");

        // EP: same values -> returns true
        assertTrue(assignmentName.equals(new AssignmentName("Quiz 1")));

        // EP: same object -> returns true
        assertTrue(assignmentName.equals(assignmentName));

        // EP: case-insensitive -> returns true
        assertTrue(assignmentName.equals(new AssignmentName("QUIZ 1")));
        assertTrue(assignmentName.equals(new AssignmentName("quiz 1")));

        // null -> returns false
        assertFalse(assignmentName.equals(null));

        // EP: different types -> returns false
        assertFalse(assignmentName.equals(5.0f));

        // EP: different values -> returns false
        assertFalse(assignmentName.equals(new AssignmentName("Quiz 2")));
    }

    @Test
    public void hashCode_caseInsensitive() {
        // EP: Same name with different casing should produce same hashCode
        AssignmentName lower = new AssignmentName("quiz 1");
        AssignmentName upper = new AssignmentName("QUIZ 1");
        assertEquals(lower.hashCode(), upper.hashCode());
    }
}
