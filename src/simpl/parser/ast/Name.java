package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.Substitution;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
//        if (E.get(x) == null){
//            throw new TypeError("Name not found");
//        }
        return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        Value v = s.E.get(x);
        // notice that there may be rec value
        if (v instanceof RecValue) {
            RecValue vv = (RecValue)v;
            Rec r = new Rec(vv.x, vv.e);
            return r.eval(State.of(vv.E, s.M, s.p));
        }
        else {
            return v;
        }
    }
}
