package controller;

/**
 * Enum for operations to be performed on a filter. Not sure what operations we want to include, will ask group at
 * next meeting.
 */
public enum Operations {
    /** controller.Operations */
    EQUALS("=="), CONTAINS("~=");

    private final String operator;

    Operations(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static Operations fromOperator(String operator) {
        for (Operations op : Operations.values()) {
            if (op.getOperator().equals(operator)) {
                return op;
            }
        }
        throw new IllegalArgumentException("No operator with name " + operator);
    }

    public static Operations getOperatorFromStr(String str) {
        if (str.contains("==")) {
            return Operations.EQUALS;
        } else if (str.contains("~=")) {
            return Operations.CONTAINS;
        } else {
            return null;
        }
    }

}
