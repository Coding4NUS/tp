package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentName;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Session;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        Person alex = new Person(
                new Name("Alex Yeoh"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new MatricNumber("A0102035A"),
                getGroupNameSet("T01"),
                getTagSet("scholar"))
                .withUpdatedSession(new GroupName("T01"),
                        new Session(LocalDate.of(2025, 1, 15),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedSession(new GroupName("T01"),
                        new Session(LocalDate.of(2025, 1, 22),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedAssignmentGrade(new GroupName("T01"), new AssignmentName("Assignment 1"), 85)
                .withUpdatedAssignmentGrade(new GroupName("T01"), new AssignmentName("Assignment 2"), 90);

        Person bernice = new Person(
                new Name("Bernice Yu"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
                new MatricNumber("A9999999W"),
                getGroupNameSet("T01", "Project-Team"),
                getTagSet("leader"))
                .withUpdatedSession(new GroupName("T01"),
                        new Session(LocalDate.of(2025, 1, 15),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedSession(new GroupName("Project-Team"),
                        new Session(LocalDate.of(2025, 1, 18),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedAssignmentGrade(new GroupName("T01"), new AssignmentName("Assignment 1"), 88)
                .withUpdatedAssignmentGrade(new GroupName("T01"), new AssignmentName("Assignment 2"), 84)
                .withUpdatedAssignmentGrade(new GroupName("Project-Team"), new AssignmentName("Proposal"), 92);

        Person charlotte = new Person(
                new Name("Charlotte Oliveiro"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
                new MatricNumber("A1002345X"),
                getGroupNameSet("T02"),
                getTagSet("consultation"))
                .withUpdatedSession(new GroupName("T02"),
                        new Session(LocalDate.of(2025, 1, 16),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(3)))
                .withUpdatedSession(new GroupName("T02"),
                        new Session(LocalDate.of(2025, 1, 23),
                                new Attendance(Attendance.Status.ABSENT),
                                new Participation(0)))
                .withUpdatedAssignmentGrade(new GroupName("T02"), new AssignmentName("Assignment 1"), 50)
                .withUpdatedAssignmentGrade(new GroupName("T02"), new AssignmentName("Assignment 2"), 65);

        Person david = new Person(
                new Name("David Li"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
                new MatricNumber("A0408987E"),
                getGroupNameSet("T01"),
                getTagSet("exchange"))
                .withUpdatedSession(new GroupName("T01"),
                        new Session(LocalDate.of(2025, 1, 15),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(2)))
                .withUpdatedSession(new GroupName("T01"),
                        new Session(LocalDate.of(2025, 1, 22),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(4)))
                .withUpdatedAssignmentGrade(new GroupName("T01"), new AssignmentName("Assignment 1"), 73)
                .withUpdatedAssignmentGrade(new GroupName("T01"), new AssignmentName("Assignment 2"), 76);

        Person irfan = new Person(
                new Name("Irfan Ibrahim"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
                new MatricNumber("A0304556E"),
                getGroupNameSet("T02"),
                getTagSet("scholar"))
                .withUpdatedSession(new GroupName("T02"),
                        new Session(LocalDate.of(2025, 1, 16),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedSession(new GroupName("T02"),
                        new Session(LocalDate.of(2025, 1, 23),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedAssignmentGrade(new GroupName("T02"), new AssignmentName("Assignment 1"), 91)
                .withUpdatedAssignmentGrade(new GroupName("T02"), new AssignmentName("Assignment 2"), 89);

        Person roy = new Person(
                new Name("Roy Balakrishnan"),
                new Phone("92624417"),
                new Email("royb@example.com"),
                new MatricNumber("A0504887M"),
                getGroupNameSet("Project-Team"),
                getTagSet("leader", "scholar"))
                .withUpdatedSession(new GroupName("Project-Team"),
                        new Session(LocalDate.of(2025, 1, 18),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedSession(new GroupName("Project-Team"),
                        new Session(LocalDate.of(2025, 1, 25),
                                new Attendance(Attendance.Status.PRESENT),
                                new Participation(5)))
                .withUpdatedAssignmentGrade(new GroupName("Project-Team"), new AssignmentName("Proposal"), 87)
                .withUpdatedAssignmentGrade(new GroupName("Project-Team"), new AssignmentName("Final Report"), 90);

        return new Person[] { alex, bernice, charlotte, david, irfan, roy };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        Group t01 = new Group(
                new GroupName("T01"),
                Arrays.asList(
                        new Assignment(new AssignmentName("Assignment 1"), LocalDate.of(2025, 2, 1), 100),
                        new Assignment(new AssignmentName("Assignment 2"), LocalDate.of(2025, 3, 1), 100)
                )
        );

        Group t02 = new Group(
                new GroupName("T02"),
                Arrays.asList(
                        new Assignment(new AssignmentName("Assignment 1"), LocalDate.of(2025, 2, 2), 100),
                        new Assignment(new AssignmentName("Assignment 2"), LocalDate.of(2025, 3, 2), 100)
                )
        );

        Group projectTeam = new Group(
                new GroupName("Project-Team"),
                Arrays.asList(
                        new Assignment(new AssignmentName("Proposal"), LocalDate.of(2025, 2, 10), 100),
                        new Assignment(new AssignmentName("Final Report"), LocalDate.of(2025, 4, 10), 100)
                )
        );

        Group emptyGroup = new Group(new GroupName("EmptyGroup"));

        sampleAb.addGroup(t01);
        sampleAb.addGroup(t02);
        sampleAb.addGroup(projectTeam);
        sampleAb.addGroup(emptyGroup);

        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a group name set containing the list of strings given.
     */
    public static Set<GroupName> getGroupNameSet(String... strings) {
        return Arrays.stream(strings)
                .map(GroupName::new)
                .collect(Collectors.toSet());
    }
}