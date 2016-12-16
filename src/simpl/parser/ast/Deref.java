package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = e.typecheck(E);

        if (tr1.t instanceof RefType) { // tr1 is reference type
            return TypeResult.of(tr1.s,((RefType)tr1.t).t);
        }
        else if (tr1.t instanceof TypeVar) { // tr1 is a type variable
            TypeVar tv1 = new TypeVar(false);
            RefType t1 = new RefType(tv1);
            Substitution s=tr1.s.compose(tr1.t.unify(t1));
            return TypeResult.of(s, tv1);
        }

        throw new TypeError("Ref type error");
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        RefValue r1 = (RefValue)e.eval(s);
        return s.M.get(r1.p);
    }
}
