package simpl.typing;

public final class ListType extends Type {

    public Type t;

    public ListType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO Done
        return t.isEqualityType();
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO Done
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        else if (t instanceof ListType) {
            return this.t.unify(((ListType)t).t);
        }
        else {
            throw new TypeMismatchError();
        }
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO Done
        return t.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO Done
        return new ListType(this.t.replace(a, t));
    }

    public String toString() {
        return t + " list";
    }

    @Override
    public boolean equals(Type t) {
        if (t instanceof ListType) {
            return this.t.equals(((ListType)t).t);
        }
        return false;
    }
}
