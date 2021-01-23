module lab01{
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
    opens lab to javafx.fxml;
    exports lab;
}