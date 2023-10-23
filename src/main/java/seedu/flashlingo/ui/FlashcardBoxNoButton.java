package seedu.flashlingo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * An UI component that displays information of a {@code FlashCard}.
 * @author Nathanael M. Tan
 * @version 1.2
 * @since 1.2
 */
public class FlashcardBoxNoButton extends UiPart<Region> {

    private static final String FXML = "FlashcardBoxNoButton.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final FlashCard flashCard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label original;
    @FXML
    private Label translation;
    @FXML
    private Label level;
    @FXML
    private Button reveal;

    private boolean isRevealed = false;

    /**
     * Creates a {@code FlashCard code} with the given {@code FlashCard} and index to display.
     */
    public FlashcardBoxNoButton(FlashCard fc, int displayedIndex) {
        super(FXML);
        this.flashCard = fc;
        id.setText(displayedIndex + ") ");
        original.setText(fc.getOriginalWord().toString() + ": ");
        translation.setText("");
        level.setText("Current Level: " + fc.getProficiencyLevel().getLevel());
    }

    /**
     * Reveals translation and changes text in box to Hide if it is not displayed
     * Hides translation and changes text to reveal if it is displayed
     */
    @FXML
    public void toggleReveal() {
        if (isRevealed) {
            translation.setText("");
            reveal.setText("Reveal");
        } else {
            translation.setText(flashCard.getTranslatedWord().toString());
            reveal.setText(" Hide ");
        }
        isRevealed = !isRevealed;
    }
}