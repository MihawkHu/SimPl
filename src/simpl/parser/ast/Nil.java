package simpl.parser.ast;

import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.ListType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Nil extends Expr {

    public String toString() {
        return "nil";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO Done
        TypeVar tv1 = new TypeVar(true);
        ListType t1 = new ListType(tv1);
        return TypeResult.of(t1);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO Done
        return Value.NIL;
    }
}
