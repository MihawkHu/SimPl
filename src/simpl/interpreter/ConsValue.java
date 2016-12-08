package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    // get the length of the ConsValue
    private int length() {
        if (v2 instanceof NilValue) {
            return 1;
        }
        else if (v2 instanceof ConsValue) {
            return 1 + ((ConsValue)v2).length();
        }
        else {
            return 2;
        }
    }

    public String toString() {
        // TODO Done
        String r = String.Valueof(this.length());
        return "list@" + r;

    }

    @Override
    public boolean equals(Object other) {
        // TODO Done
        if (other instanceof ConsValue) {
            return v1.equals(((ConsValue)other).v1)
                    && v2.equals(((ConsValue)other).v2);
        }
        return false;
    }
}
