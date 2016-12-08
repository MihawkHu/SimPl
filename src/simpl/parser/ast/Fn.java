package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeVar tv1 = new TypeVar(true);
        TypeResult tr1 = e.typecheck(TypeEnv.of(E, x, tv1));
        Type t1 = new ArrowType(tr1.s.apply(tv1), tr1.t);
        return TypeResult.of(tr1.s, t1);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        return new FunValue(s.E, x, e);
    }
}
