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
        TypeResult tr2 = r.typecheck(E);

        Substitution sub1=tr1.s.compose(tr1.t.unify(Type.INT));
        Substitution sub2=tr2.s.compose(tr2.t.unify(Type.INT));
        Substitution sub3=sub1.compose(sub2);
        Type t1 = Type.BOOL;

        return TypeResult.of(sub3, t1);
    }
}
