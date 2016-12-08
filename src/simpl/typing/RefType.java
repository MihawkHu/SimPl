package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO Done
        // "t ref" is equality type directly
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO Done
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        else if (t instanceof RefType) {
            return this.t.unify(((RefType)t).t);
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
        return new RefType(t.replace(a, t));
    }

    public String toString() {
        return t + " ref";
    }
}
