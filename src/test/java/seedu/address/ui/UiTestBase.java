package seedu.address.ui;

import org.junit.jupiter.api.BeforeAll;

import javafx.application.Platform;

/**
 * Sets up JavaFX toolkit for GUI tests to run.
 */
public abstract class UiTestBase {
    private static boolean toolKitInitialized = false;

    @BeforeAll
    public static void setUpJavaFx() {
        if (toolKitInitialized) {
            return;
        }

        Platform.startup(() -> {});
        toolKitInitialized = true;
    }
}
