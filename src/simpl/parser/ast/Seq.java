package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.Substitution;


public class Seq extends BinaryExpr {

    public Seq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " ; " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(tr1.s.compose(E));
        Substitution sub1 = tr1.s.compose(tr2.s);
        return TypeResult.of(sub1, sub1.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        Value v1 = l.eval(s);
        Value v2 = r.eval(s);
        return v2;
    }
}
