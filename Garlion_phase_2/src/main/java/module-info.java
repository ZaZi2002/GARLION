module com.example.garlion_phase_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.garlion_phase_2 to javafx.fxml;
    exports com.example.garlion_phase_2;
}