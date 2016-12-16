package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.snd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.succ;
import simpl.interpreter.pcf.pred;


public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        if (tr1.t instanceof ArrowType) { // l is a function
            Substitution sub1 = ((ArrowType)tr1.t).t1.unify(tr2.t);
            TypeEnv.compose(sub1);
            return TypeResult.of(tr1.s.compose(tr2.s.compose(sub1)),
                    ((ArrowType)sub1.apply(tr1.t)).t2);
        }
        else if (tr1.t instanceof TypeVar) { // l is a type variable
            Substitution sub2 = tr1.t.unify(new ArrowType(tr2.t,new TypeVar(false)));
            TypeEnv.compose(sub2);
            return TypeResult.of(tr1.s.compose(tr2.s.compose(sub2)),
                    TypeEnv.sub.apply(((ArrowType)sub2.apply(tr1.t)).t2));
        }

        throw new TypeError("App type wrong");
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
            return ((PairValue)v).v2;
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
                return ((ConsValue)v).v2;
            }
        }
        else if (fun instanceof iszero) {
            IntValue i1 = new IntValue(0);
            boolean b1 = ((IntValue)v).equals(i1);
            return new BoolValue(b1);
        }
        else if (fun instanceof succ) {
            int t1 = ((IntValue)v).n;
            return new IntValue(t1 + 1);
        }
        else if (fun instanceof pred) {
            int t1 = ((IntValue)v).n;
            return new IntValue(t1 - 1);
        }
        else {
            Env env1 = new Env(fun.E, fun.x, v);
            return fun.e.eval(State.of(env1, s.M, s.p));
        }
    }
}
