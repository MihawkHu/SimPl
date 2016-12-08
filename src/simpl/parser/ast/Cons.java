package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(tr1.s.compose(E));
        TypeVar tv1 = new TypeVar(true);
        ListType t1 = new ListType(tv1);

        Substitution sub1 = tr2.s.compose(tr1.s);
        Substitution sub2 = sub1.apply(tr2.t).unify(sub1.apply(t1));
        sub1 = sub1.compose(sub2);
        Substitution sub3 = sub1.apply(tr1.t).unify(sub1.apply(t1.t));
        sub1 = sub1.compose(sub3);

        return TypeResult.of(sub1, sub1.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return new ConsValue(v1, v2);
    }
}
