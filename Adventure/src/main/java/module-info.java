module lab01{
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires jakarta.json;
	requires com.fasterxml.jackson.databind;
    opens controller to javafx.fxml;
    opens item to com.fasterxml.jackson.databind;
    opens room to com.fasterxml.jackson.databind;
    opens game to com.fasterxml.jackson.databind;
    exports game;
}