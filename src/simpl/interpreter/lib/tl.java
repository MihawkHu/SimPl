package simpl.interpreter.lib;

import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Expr;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class tl extends FunValue {

    public tl() {
        // TODO Done
        // I impletented it in other place
        // So here it only need to initialize
        super(null, null, null);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof tl);
    }
}
