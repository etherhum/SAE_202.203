module com.example.sae_202_203 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.sae_202_203 to javafx.fxml;
    exports com.example.sae_202_203;
    exports com.main.sae_202_203;
}