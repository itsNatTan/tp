package flashlingo.logic.parser;

import static flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_AMY;
import static flashlingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static flashlingo.testutil.TypicalFlashCards.TRANSLATION_DESC_AMY;
import static flashlingo.testutil.TypicalFlashCards.TRANSLATION_DESC_BOB;
import static flashlingo.testutil.TypicalFlashCards.WORD_DESC_AMY;
import static flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;

import flashlingo.logic.parser.Parser;
import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.Command;
import seedu.flashlingo.logic.commands.EditCommand;
import seedu.flashlingo.logic.parser.EditCommandParser;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure((Parser<? extends Command>) parser, VALID_ORIGINAL_WORD_AMY, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure((Parser<? extends Command>) parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_FLASHCARD;
        String userInput = targetIndex.getOneBased() + WORD_DESC_AMY + TRANSLATION_DESC_BOB + TRANSLATION_DESC_AMY;

        assertParseFailure((Parser<? extends Command>) parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TRANSLATED_WORD));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + TRANSLATION_DESC_AMY + TRANSLATION_DESC_BOB;

        assertParseFailure((Parser<? extends Command>) parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TRANSLATED_WORD));
    }
}
