package seedu.address.logic.parser.taskcommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.address.logic.commands.taskcommand.AddTaskCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_DESCRIPTION);
        boolean isPrefixPresent = argMultimap.getValue(PREFIX_TASK_DESCRIPTION).isPresent();

        if (!isPrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        TaskDescription description =
                ParserUtil.parseTaskDescription(argMultimap.getValue(PREFIX_TASK_DESCRIPTION).get());

        Task task = new Task(description);

        return new AddTaskCommand(task);
    }
}
