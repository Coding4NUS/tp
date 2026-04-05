package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonMatchesFieldsPredicateTest {

    @Test
    public void equals() {
        PersonMatchesFieldsPredicate firstPredicate = new PersonMatchesFieldsPredicate(
                Collections.singletonList("Alex"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        PersonMatchesFieldsPredicate secondPredicate = new PersonMatchesFieldsPredicate(
                Collections.singletonList("David"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        // EP: identical predicate -> returns true
        assertTrue(firstPredicate.equals(new PersonMatchesFieldsPredicate(
                Collections.singletonList("Alex"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList())));

        // EP: predicate equals number -> returns false
        assertFalse(firstPredicate.equals(1));

        // EP: predicate equals null -> returns false
        assertFalse(firstPredicate.equals(null));

        // EP: different search values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_noCriteria_returnsFalse() {
        Person person = new PersonBuilder().withName("Alex Yeoh").build();
        PersonMatchesFieldsPredicate predicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        // EP: search lists are empty -> returns false
        assertFalse(predicate.test(person));

        // EP: zero search parameters match -> return 0
        assertEquals(0, predicate.getMatchedCriteriaCount(person));

        // EP: zero exact matches -> return 0
        assertEquals(0, predicate.getExactMatchCount(person));
    }

    @Test
    public void test_namePartialMatch_returnsTrue() {
        Person person = new PersonBuilder().withName("Alex Yeoh").build();
        PersonMatchesFieldsPredicate predicate = new PersonMatchesFieldsPredicate(
                Collections.singletonList("Alex"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        // EP: partial name matching -> returns true
        assertTrue(predicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, predicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 0
        assertEquals(0, predicate.getExactMatchCount(person));
    }

    @Test
    public void test_nameExactMatch_returnsTrue() {
        Person person = new PersonBuilder().withName("Alex Yeoh").build();
        PersonMatchesFieldsPredicate predicate = new PersonMatchesFieldsPredicate(
                Collections.singletonList("Alex Yeoh"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        // EP: exact name matching -> returns true
        assertTrue(predicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, predicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 1
        assertEquals(1, predicate.getExactMatchCount(person));
    }

    @Test
    public void test_phonePartialAndExactMatch_returnsTrue() {
        Person person = new PersonBuilder().withPhone("87438807").build();

        PersonMatchesFieldsPredicate partialPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.singletonList("8743"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        PersonMatchesFieldsPredicate exactPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.singletonList("87438807"),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        // EP: partial phone matching -> returns true
        assertTrue(partialPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, partialPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 0
        assertEquals(0, partialPredicate.getExactMatchCount(person));

        // EP: exact phone matching -> returns true
        assertTrue(exactPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, exactPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 1
        assertEquals(1, exactPredicate.getExactMatchCount(person));
    }

    @Test
    public void test_emailPartialAndExactMatch_returnsTrue() {
        Person person = new PersonBuilder().withEmail("alexyeoh@example.com").build();

        PersonMatchesFieldsPredicate partialPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.singletonList("alexyeoh"),
                Collections.emptyList(),
                Collections.emptyList());

        PersonMatchesFieldsPredicate exactPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.singletonList("alexyeoh@example.com"),
                Collections.emptyList(),
                Collections.emptyList());

        // EP: partial email matching -> returns true
        assertTrue(partialPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, partialPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 0
        assertEquals(0, partialPredicate.getExactMatchCount(person));

        // EP: exact email matching -> returns true
        assertTrue(exactPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, exactPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 1
        assertEquals(1, exactPredicate.getExactMatchCount(person));
    }

    @Test
    public void test_matricPartialAndExactMatch_returnsTrue() {
        Person person = new PersonBuilder().withMatricNumber("A1234567X").build();

        PersonMatchesFieldsPredicate partialPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.singletonList("A123"),
                Collections.emptyList());

        PersonMatchesFieldsPredicate exactPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.singletonList("A1234567X"),
                Collections.emptyList());

        // EP: partial matriculation matching -> returns true
        assertTrue(partialPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, partialPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 0
        assertEquals(0, partialPredicate.getExactMatchCount(person));

        // EP: exact matriculation matching -> returns true
        assertTrue(exactPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, exactPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 1
        assertEquals(1, exactPredicate.getExactMatchCount(person));
    }

    @Test
    public void test_tagPartialAndExactMatch_returnsTrue() {
        Person person = new PersonBuilder()
                .withTags("friends")
                .build();

        PersonMatchesFieldsPredicate partialPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.singletonList("frie"));

        PersonMatchesFieldsPredicate exactPredicate = new PersonMatchesFieldsPredicate(
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.singletonList("friends"));

        // EP: partial tag matching -> returns true
        assertTrue(partialPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, partialPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 0
        assertEquals(0, partialPredicate.getExactMatchCount(person));

        // EP: exact tag matching -> returns true
        assertTrue(exactPredicate.test(person));

        // EP: one criteria matches -> returns 1
        assertEquals(1, exactPredicate.getMatchedCriteriaCount(person));

        // EP: exact matches -> returns 0
        assertEquals(1, exactPredicate.getExactMatchCount(person));
    }

    @Test
    public void test_caseInsensitiveMatch_returnsTrue() {
        Person person = new PersonBuilder()
                .withName("Alex Yeoh")
                .withEmail("alexyeoh@example.com")
                .withMatricNumber("A1234567X")
                .withPhone("87438807")
                .withTags("friends")
                .build();

        PersonMatchesFieldsPredicate predicate = new PersonMatchesFieldsPredicate(
                Collections.singletonList("alex"),
                Collections.singletonList("8743"),
                Collections.singletonList("ALEXYEOH"),
                Collections.singletonList("a123"),
                Collections.singletonList("FRIENDS"));

        // EP: case-insensitive -> returns true
        assertTrue(predicate.test(person));

        // EP: 5 case-insensitive criteria matches -> returns 5
        assertEquals(5, predicate.getMatchedCriteriaCount(person));
    }

    @Test
    public void test_multipleCriteria_countsAllMatchedCriteria() {
        Person person = new PersonBuilder()
                .withName("Alex Yeoh")
                .withPhone("87438807")
                .withEmail("alexyeoh@example.com")
                .withMatricNumber("A1234567X")
                .withTags("friends")
                .build();

        PersonMatchesFieldsPredicate predicate = new PersonMatchesFieldsPredicate(
                Arrays.asList("Alex", "Nobody"),
                Collections.singletonList("87438807"),
                Collections.singletonList("alexyeoh@example.com"),
                Collections.singletonList("A123"),
                Arrays.asList("friends", "enemy"));

        // EP: match at least 1 criterion -> returns true
        assertTrue(predicate.test(person));

        // EP: matches 5 criteria partially -> returns 5
        assertEquals(5, predicate.getMatchedCriteriaCount(person));

        // EP: matches 3 criteria exactly -> returns 3
        assertEquals(3, predicate.getExactMatchCount(person));
    }

    @Test
    public void test_nonMatchingCriteria_returnsFalse() {
        Person person = new PersonBuilder()
                .withName("Alex Yeoh")
                .withPhone("87438807")
                .withEmail("alexyeoh@example.com")
                .withMatricNumber("A1234567X")
                .withTags("friends")
                .build();

        PersonMatchesFieldsPredicate predicate = new PersonMatchesFieldsPredicate(
                Collections.singletonList("David"),
                Collections.singletonList("9999"),
                Collections.singletonList("other@example.com"),
                Collections.singletonList("B0000000Z"),
                Collections.singletonList("family"));

        // EP: no matches -> returns false
        assertFalse(predicate.test(person));

        // EP: matches 0 criteria partially -> returns 0
        assertEquals(0, predicate.getMatchedCriteriaCount(person));

        // EP: matches 0 criteria exactly -> returns 0
        assertEquals(0, predicate.getExactMatchCount(person));
    }
}
