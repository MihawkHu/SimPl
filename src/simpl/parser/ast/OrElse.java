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

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution sub1=tr1.s.compose(tr1.t.unify(Type.BOOL));
        Substitution sub2=tr2.s.compose(tr2.t.unify(Type.BOOL));
        Substitution sub3=sub1.compose(sub2);
        Type t1 = Type.BOOL;

        return TypeResult.of(sub3, t1);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        BoolValue v1 = (BoolValue)l.eval(s);
        if (v1.b) {
            return new BoolValue(true);
        }
        else {
            BoolValue v2 = (BoolValue)r.eval(s);
            if (v2.b) {
                return new BoolValue(true);
            }
            else {
                return new BoolValue(false);
            }
        }
    }
}
