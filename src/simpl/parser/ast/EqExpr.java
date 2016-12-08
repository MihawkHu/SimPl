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
        TypeResult tr2 = r.typecheck(tr1.s.compose(E));

        // left and right exp should be equality type
        if (tr1.t.isEqualityType() && tr2.t.isEqualityType()){
            Substitution sub1 = tr2.s.compose(tr1.s);
            Substitution sub2 = sub1.apply(tr1.t).unify(sub1.apply(tr2.t));
            sub1 = sub1.compose(sub2);

            return TypeResult.of(sub1, Type.BOOL);
        }
        else {
            throw new TypeError("EqExpr type error");
        }
    }
}
