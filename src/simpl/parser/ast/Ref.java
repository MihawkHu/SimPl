package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.interpreter.Env;

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

        // garbage collection
        if (p1 > 20) { // when heap overflow occurs

            // Mark all nodes that are accessible
            // from the stack by setting their MB=1
            Env env1 = s.E;
            while (env1 != Env.empty) {
                Value v1 = env1.v;
                while (v1 instanceof RefValue
                        && ((RefValue)v1).mb == 0) {
                    ((RefValue)v1).mb = 1;
                    v1 = s.M.get(((RefValue)v1).p);
                }
                v1.mb = 1;
                env1 = env1.E;
            }

            // Sweep through the entire heap and return
            // all unmarked (MB=0) nodes to the free list.
            for (int i = 0; i < p1; ++i) {
                if (s.M.get(i).mb == 0) {
                    s.M.put(i, null);
                }
            }
        }

        s.p.set(p1 + 1);
        s.M.put(p1, e.eval(s));
        return new RefValue(p1);


    }
}
