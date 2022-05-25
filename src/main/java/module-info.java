module com.example.sae_202_203 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.sae_202_203 to javafx.fxml;
    exports com.modele.sae_202_203;
    exports com.vue.sae_202_203;
    exports com;
}