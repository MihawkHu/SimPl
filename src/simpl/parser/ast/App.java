package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return null;
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        FunValue fun = (FunValue)l.eval(s);
        Value v = r.eval(s);

        if (fun instanceof fst) {
            return ((PairValue)v).v1;
        }
        else if (fun instanceof snd) {
            return ((PairValue)v.v2);
        }
        else if (fun instanceof hd) {
            if (v instanceof NilValue) {
                throw new RuntimeError("Nil can't in function hd");
            }
            else {
                return ((ConsValue)v).v1;
            }
        }
        else if (fun instanceof tl) {
            if (v instanceof NilValue) {
                throw new RuntimeError("Nil can't in function tl");
            }
            else {
                return ((ConsValue) v).v2;
            }
        }
        else {
            Env env1 = new Env(fun.E, fun.x, v);
            return fun.e.eval(State.of(env1, s.M, s.p));
        }
    }
}
