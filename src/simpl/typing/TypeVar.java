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
        if (!(t instanceof TypeVar) && (t.contains(this))) {
            throw new TypeCircularityError();
        } // stuck happened

        Substitution sub1 = Substitution.of(this, t);
        try {
            Type t1 = TypeEnv.sub.apply(this);
            if (t1 != this) {
                Substitution s2 = t1.unify(t);
                return sub1.compose(s2);
            }
        }
        catch (Exception e) {}
        return sub1;
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
