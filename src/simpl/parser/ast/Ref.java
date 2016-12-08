package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = e.typecheck(E);
        RefType t1 = new RefType(tr1.t);
        return TypeResult.of(tr1.s, t1);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        int p1 = s.p.get();
        s.p.set(p1 + 1);
        s.M.put(p1, e.eval(s));
        return new RefValue(p1);
    }
}
