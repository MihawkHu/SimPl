package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO Done
        TypeVar a = new TypeVar(true);
        TypeVar b = new TypeVar(true);
        TypeVar e = new TypeVar(true);
        TypeVar f = new TypeVar(true);

        E = TypeEnv.of(E, Symbol.symbol("fst"),
                new ArrowType(new PairType(a, b), a));
        E = TypeEnv.of(E, Symbol.symbol("snd"),
                new ArrowType(new PairType(a, b), a));
        E = TypeEnv.of(E, Symbol.symbol("hd"),
                new ArrowType(new ListType(e), e));
        E = TypeEnv.of(E, Symbol.symbol("tl"),
                new ArrowType(new ListType(e), new ListType(e)));
        E = TypeEnv.of(E, Symbol.symbol("iszero"),
                new ArrowType(Type.INT, Type.BOOL));
        E = TypeEnv.of(E, Symbol.symbol("pred"),
                new ArrowType(Type.INT, Type.INT));
        E = TypeEnv.of(E, Symbol.symbol("succ"),
                new ArrowType(Type.INT, Type.INT));
    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
