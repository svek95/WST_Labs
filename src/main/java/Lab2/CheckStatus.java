package Lab2;

public enum  CheckStatus {

    SUCCESS_UPDATE("Successfully updated"),
    SUCCESS_DELETE("Successfully deleted"),
    FAIL("Something went wrong ): ");

    String value;

    CheckStatus(String value) {
        this.value = value;
    }

    public String getStringValue() {
        return value;
    }


}
