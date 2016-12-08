package simpl.typing;

final class BoolType extends Type {

    protected BoolType() {
    }

    @Override
    public boolean isEqualityType() {
        // TODO Done
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO Done
        if (t instanceof TypeVar) {
            return t.unify(this);
        }
        else if (t instanceof BoolType) {
            return Substitution.IDENTITY;
        }
        else {
            throw new TypeMismatchError();
        }
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO Done
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO Done
        return Type.BOOL;
    }

    public String toString() {
        return "bool";
    }
}
