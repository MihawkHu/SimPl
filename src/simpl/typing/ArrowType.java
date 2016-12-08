package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO Done
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO Done
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        else if (t instanceof ArrowType) {
            Substitution s1 = ((ArrowType)t).t2.unify(this.t2);
            Substitution s2 = s1.apply(((ArrowType)t).t1).unify(s1.apply(this.t1));

            return s2.compose(s1);
        }
        else {
            throw new TypeMismatchError();
        }
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO Done
        return t1.contains(tv) || t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO Done
        Type t3 = t1.replace(a, t);
        Type t4 = t2.replace(a, t);
        return new ArrowType(t3, t4);
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
