package controller;

/**
 * Enum for operations to be performed on a filter.
 */
public enum Operations {
    /** controller.Operations */
    EQUALS("=="), CONTAINS("~=");

    /** The operator */
    private final String operator;

    /**
     * Constructor for the operations.
     *
     * @param operator The operator.
     */
    Operations(String operator) {
        this.operator = operator;
    }

    /**
     * Gets the operator.
     *
     * @return The operator.
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Gets the operation from the operator.
     *
     * @param operator The operator.
     * @return The operation.
     */
    public static Operations fromOperator(String operator) {
        for (Operations op : Operations.values()) {
            if (op.getOperator().equals(operator)) {
                return op;
            }
        }
        throw new IllegalArgumentException("No operator with name " + operator);
    }

    /**
     * Gets the operator from a string that contains it.
     *
     * @param str The string that contains an operation.
     * @return The operator.
     */
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
