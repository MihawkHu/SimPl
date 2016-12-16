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


public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        RefType tv2 = new RefType(tr2.t);
        Substitution sub1 = tr1.s.compose(tr2.s.compose(
                tr1.t.unify(tv2)));
        TypeEnv.compose(((RefType)(tr1.t)).t.unify(tr2.t));
        Type t1 = Type.UNIT;

        return TypeResult.of(sub1, t1);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        RefValue r1 = (RefValue)l.eval(s);
        Value v = r.eval(s);
        s.M.put(r1.p, v);
        return Value.UNIT;
    }
}
