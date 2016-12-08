package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution sub1 = tr2.s.compose(tr1.s);
        Substitution sub2 = sub1.apply(tr1.t).unify(Type.INT);
        sub1 = sub1.compose(sub2);
        Substitution sub3 = sub1.apply(tr2.t).unify(Type.INT);
        sub1 = sub1.compose(sub3);
        Type t1 = Type.INT;
        return TypeResult.of(sub1, t1);
    }
}
