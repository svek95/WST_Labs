package Lab2;

public enum  CheckStatus {

    SUCCESS_UPDATE("Row was successfully updated"),
    SUCCESS_DELETE("Row eas successfully deleted"),
    FAIL("Something went wrong");

    String value;

    CheckStatus(String value) {
        this.value = value;
    }

    public String getStringValue() {
        return value;
    }


}
