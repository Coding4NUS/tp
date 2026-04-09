package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/**
 * Switches the current displayed view to all students or a group.
 */
// @@author ongrussell
public class SwitchGroupCommand extends Command {

    public static final String COMMAND_WORD = "switchgroup";
    public static final String ALL_VIEW_KEYWORD = "all";
    public static final String COMMAND_PARAMETERS = "g/GROUP_NAME | " + ALL_VIEW_KEYWORD;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches the current displayed view.\n"
            + "Parameters: " + COMMAND_PARAMETERS + "\n"
            + "Examples:\n"
            + COMMAND_WORD + " all\n"
            + COMMAND_WORD + " g/T01";

    public static final String MESSAGE_SWITCHED_TO_ALL = "Switched to all students view.";
    public static final String MESSAGE_SWITCHED_TO_GROUP = "Switched to group: %1$s";
    public static final String MESSAGE_GROUP_NOT_FOUND = "This group does not exist.";

    /** The group to switch to, or {@code null} to switch to the all-students view. */
    private final GroupName requestedGroupName;

    public SwitchGroupCommand() {
        this.requestedGroupName = null;
    }

    public SwitchGroupCommand(GroupName requestedGroupName) {
        this.requestedGroupName = requestedGroupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setAttendanceViewActive(false);
        if (requestedGroupName == null) {
            model.switchToAllStudentsView();
            return new CommandResult(MESSAGE_SWITCHED_TO_ALL);
        }

        Optional<Group> foundGroup = model.findGroupByName(requestedGroupName);
        if (foundGroup.isEmpty()) {
            throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
        }

        GroupName foundGroupCanonicalName = foundGroup.get().getGroupName();
        model.switchToGroupView(foundGroupCanonicalName);
        return new CommandResult(String.format(MESSAGE_SWITCHED_TO_GROUP, foundGroupCanonicalName.value));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SwitchGroupCommand)) {
            return false;
        }
        SwitchGroupCommand otherCommand = (SwitchGroupCommand) other;
        return Objects.equals(requestedGroupName, otherCommand.requestedGroupName);
    }
}
// @@author
