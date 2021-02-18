module lab01{
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires jakarta.json;
	requires com.fasterxml.jackson.databind;
    opens lab to javafx.fxml;
    exports lab;
}