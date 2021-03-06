package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUEDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.task.DescContainsKeywordsPredicate;
import seedu.address.model.task.ReadOnlyTask;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {


    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE2_AMY = "33333333";
    public static final String VALID_PHONE2_BOB = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE2_DESC_AMY = " " + PREFIX_PHONE_2 + VALID_PHONE2_AMY;
    public static final String PHONE2_DESC_BOB = " " + PREFIX_PHONE_2 + VALID_PHONE2_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    //@@author zhangshuoyang
    public static final String VALID_DESCRIPTION_ASSIGNMENT = "CS2103T Assignment";
    public static final String VALID_DESCRIPTION_SHOPPING = "Go shopping";
    public static final String VALID_PRIORITY_ASSIGNMENT = "2";
    public static final String VALID_PRIORITY_SHOPPING = "0";
    public static final String VALID_DUEDATE_ASSIGNMENT = "30/10/2017";
    public static final String VALID_DUEDATE_SHOPPING = "01/01/2018";

    public static final String INVALID_PRIORITY_SHOPPING = "6";
    public static final String INVALID_DUEDATE_SHOPPING = "40/20/100";

    public static final String DESCRIPTION_DESC_ASSIGNMENT = " " +  PREFIX_DESCIPTION + VALID_DESCRIPTION_ASSIGNMENT;
    public static final String DESCRIPTION_DESC_SHOPPING = " " +  PREFIX_DESCIPTION + VALID_DESCRIPTION_SHOPPING;
    public static final String PRIORITY_DESC_ASSIGNMENT = " " + PREFIX_PRIORITY + VALID_PRIORITY_ASSIGNMENT;
    public static final String PRIORITY_DESC_SHOPPING = " " + PREFIX_PRIORITY + VALID_PRIORITY_SHOPPING;
    public static final String DUEDATE_DESC_ASSIGNMENT = " " + PREFIX_DUEDATE + VALID_DUEDATE_ASSIGNMENT;
    public static final String DUEDATE_DESC_SHOPPING = " " + PREFIX_DUEDATE + VALID_DUEDATE_SHOPPING;
    public static final String INVALID_PRIORITY_DESC_SHOPPING = " " + PREFIX_PRIORITY + INVALID_PRIORITY_SHOPPING;
    public static final String INVALID_DUEDATE_DESC_SHOPPING = " " + PREFIX_DUEDATE + INVALID_DUEDATE_SHOPPING;

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final String VALIDTASKDESCAMY = "Task Amy";
    public static final String VALIDTASKPRIORITYAMY = "1";
    public static final String VALIDTASKDUEDATEAMY = "30/12/2017";
    public static final String VALIDTASKDESCBOB = "Task Bob";
    public static final String VALIDTASKPRIORITYBOB = "1";
    public static final String VALIDTASKDUEDATEBOB = "30/12/2017";
    public static final String DESC_TASK_AMY = " " + PREFIX_DESCIPTION + VALIDTASKDESCAMY;
    public static final String PRIORITY_TASK_AMY = " " + PREFIX_PRIORITY + VALIDTASKPRIORITYAMY;
    public static final String DUEDATE_TASK_AMY = " " + PREFIX_DUEDATE + VALIDTASKDUEDATEAMY;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    public static final EditTaskCommand.EditTaskDescriptor DESC_TASK_A;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TASK_B;


    static {
        DESC_TASK_A = new EditTaskDescriptorBuilder().withDescription(VALIDTASKDESCAMY)
                .withPriority(VALIDTASKPRIORITYAMY)
                .withDueDate(VALIDTASKDUEDATEAMY).build();
        DESC_TASK_B = new EditTaskDescriptorBuilder().withDescription(VALIDTASKDESCBOB)
                .withPriority(VALIDTASKPRIORITYBOB)
                .withDueDate(VALIDTASKDUEDATEBOB).build();
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        try {
            CommandResult result = command.execute();
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered person list in the {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<ReadOnlyPerson> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        try {
            command.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
        }
    }

    //@@author JYL123
    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered task list in the {@code actualModel} remain unchanged
     */
    public static void assertTaskCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<ReadOnlyTask> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        try {
            command.execute();
            fail("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the first person in the {@code model}'s address book.
     */
    public static void showFirstPersonOnly(Model model) {
        ReadOnlyPerson person = model.getAddressBook().getPersonList().get(0);
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assert model.getFilteredPersonList().size() == 1;
    }

    /**
     * Updates {@code model}'s filtered list to show only the first task in the {@code model}'s address book.
     */
    public static void showFirstTaskOnly(Model model) {
        ReadOnlyTask task = model.getAddressBook().getTaskList().get(0);
        final String[] splitName = task.getDescription().descriptionName.split("\\s+");
        model.updateFilteredTaskList(new DescContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assert model.getFilteredTaskList().size() == 1;
    }

    /**
     * Deletes the first person in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstPerson(Model model) {
        ReadOnlyPerson firstPerson = model.getFilteredPersonList().get(0);
        try {
            model.deletePerson(firstPerson);
        } catch (PersonNotFoundException pnfe) {
            throw new AssertionError("Person in filtered list must exist in model.", pnfe);
        }
    }
}
