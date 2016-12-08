package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeVar tv1 = new TypeVar(false);
        E = TypeEnv.of(E, x, tv1);
        TypeResult tr1 = e.typecheck(E);

        Substitution sub1 = tr1.s.compose(tr1.t.unify(tv1));

        return TypeResult.of(tr1.s, sub1.apply(tr1.t));

    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        Value v = new RecValue(s.E, x, e);
        Env env1 = new Env(s.E, x, v);
        return e.eval(State.of(env1, s.M, s.p));
    }
}
