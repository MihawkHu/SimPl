package simpl.typing;

final class IntType extends Type {

    protected IntType() {
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
        else if (t instanceof IntType) {
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
        return Type.INT;
    }

    public String toString() {
        return "int";
    }
}
