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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = e1.typecheck(E);
        TypeResult t2 = e2.typecheck(E);

        Substitution sub1 = tr1.t.unify(Type.BOOL);
        Substitution sub2 = t2.s.compose(sub1);

        return TypeResult.of(tr1.s.compose(sub2), Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        BoolValue v1 = (BoolValue)e1.eval(s);
        while (v1.b) {
            e2.eval(s);
            v1 = (BoolValue)e1.eval(s);
        }
        return Value.UNIT;
    }
}
