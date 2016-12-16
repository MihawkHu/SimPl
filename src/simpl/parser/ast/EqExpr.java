package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);

        Substitution s=tr1.t.unify(tr2.t);
        TypeEnv.compose(s);
        if ((!(tr1.t instanceof TypeVar) && !(tr2.t instanceof TypeVar))
            && (!(tr1.t.isEqualityType() && tr2.t.isEqualityType()))) {
            throw new TypeError("not equality type");
        }
        Substitution sub1 = tr2.s.compose(s).compose(s);
        return TypeResult.of(tr1.s.compose(sub1), Type.BOOL);
    }
}
