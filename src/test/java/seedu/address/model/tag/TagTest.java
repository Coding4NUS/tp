package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        Tag tag = new Tag("friends");
        assertEquals(tag, tag);
    }

    @Test
    public void equals_null_returnsFalse() {
        Tag tag = new Tag("friends");
        assertNotEquals(tag, null);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        Tag tag = new Tag("friends");
        assertNotEquals(tag, "friends");
    }

    @Test
    public void equals_sameName_returnsTrue() {
        assertEquals(new Tag("friends"), new Tag("friends"));
    }

    @Test
    public void equals_differentName_returnsFalse() {
        assertNotEquals(new Tag("friends"), new Tag("colleagues"));
    }
}
