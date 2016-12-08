package simpl.parser.ast;

import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public abstract class RelExpr extends BinaryExpr {

    public RelExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(tr1.s.compose(E));

        Substitution sub1 = tr2.s.compose(tr1.s);
        Substitution sub2 = sub1.apply(tr1.t).unify(Type.BOOL);
        sub1 = sub1.compose(sub2);
        Substitution sub3 = sub1.apply(tr2.t).unify(Type.BOOL);
        sub1 = sub1.compose(sub3);
        Type t1 = Type.BOOL;

        return TypeResult.of(sub1, t1);
    }
}
