package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;


// Reused from Guides for AB3 Tutorial: Adding a command
// with minor modification

/**
 * Edits the remark of an existing person in  the address book.
 */

public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edit the remark of the person identified by the index number used in the last displayed person list.\n"
            + "Most recent remark for the same person will overwrite the previous one."
            + "Parameters: INDEX (must be a positive integer) r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 r/ Goes to the cafe every Thursday";

    public static final String DEFAULT_REMARK = "No remark for this person.";
    public static final String ADD_REMARK_SUCCESS = "Successfully added remark for this person.";
    public static final String DELETE_REMARK_SUCCESS = "Successfully deleted remark for this person.";

    public static final String REMARK_MESSAGE = "Remark #%1$d %2$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param remark of the person to be updated to
     */
    public RemarkCommand(Index idx, Remark message) {
        requireNonNull(idx, message.value);

        index = idx;
        remark = message;
    }

    /**
     * Generate a success message on the addition or deletion of remark from {@code person}.
     */
    private String generateSuccessMessage(Person person) {
        String message = !remark.value.isEmpty() ? ADD_REMARK_SUCCESS : DELETE_REMARK_SUCCESS;
        return String.format(message, person);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPerson = lastList.get(index.getZeroBased());
        Person editedPerson = new Person(targetPerson.getName(), targetPerson.getPhone(), targetPerson.getEmail(),
                targetPerson.getAddress(), remark, targetPerson.getTags());

        model.setPerson(targetPerson, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        boolean isSameObj = other == this;
        boolean isNull = other instanceof RemarkCommand;
        boolean isSameState = index.equals(((RemarkCommand) other).index)
                && remark.equals(((RemarkCommand) other).remark);

        return isSameObj || (isNull && isSameState);
    }
}

// Reused from Guides for AB3 Tutorial: Adding a command
// with minor modification
