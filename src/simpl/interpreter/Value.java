package simpl.interpreter;

public abstract class Value {

    public static final Value NIL = new NilValue();
    public static final Value UNIT = new UnitValue();
    public int mb = 0;

    public abstract boolean equals(Object other);
}
