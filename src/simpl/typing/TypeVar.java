package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO Done
        if (t instanceof TypeVar) {
            if (((TypeVar)t).name.equals(name)) {
                return Substitution.IDENTITY;
            }
            else {
                throw new TypeCircularityError();
            }
        }
        else if (t.contains(this)) {
            throw new TypeCircularityError();
        }
        else {
            Substitution.of(this, t);
        }
    }

    public String toString() {
        return "" + name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO Done
        return tv.name.equals(name);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO Done
        if (a.name.equals(name)) {
            return t;
        }
        else {
            return this;
        }
    }
}
