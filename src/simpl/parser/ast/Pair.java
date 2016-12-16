package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.PairType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.Substitution;


public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution sub1 = tr1.s.compose(tr2.s);
        PairType tp1 = new PairType(tr1.t, tr2.t);

        return TypeResult.of(sub1, tp1);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        return new PairValue(l.eval(s), r.eval(s));
    }
}
