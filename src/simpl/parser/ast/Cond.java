package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = e1.typecheck(E);
        TypeResult tr2 = e2.typecheck(E);
        TypeResult tr3 = e3.typecheck(E);

        Substitution s1=tr1.t.unify(Type.BOOL);
        Substitution s2=tr2.t.unify(tr3.t);
        TypeEnv.compose(s2);
        Substitution sub3 = tr3.s.compose(s1.compose(s2));

        return TypeResult.of(tr1.s.compose(tr2.s.compose(sub3)),
                s2.apply(tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        BoolValue v1 = (BoolValue)e1.eval(s);
        if (v1.b) {
            return e2.eval(s);
        }
        else {
            return e3.eval(s);
        }
    }
}
