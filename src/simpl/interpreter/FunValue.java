package simpl.interpreter;

import simpl.parser.Symbol;
import simpl.parser.ast.Expr;

public class FunValue extends Value {

    public final Env E;
    public final Symbol x;
    public final Expr e;

    public FunValue(Env E, Symbol x, Expr e) {
        this.E = E;
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "fun";
    }

    @Override
    public boolean equals(Object other) {
        // TODO Done
        if (other instanceof FunValue) {
            FunValue temp = (FunValue)other;
            String r1 = temp.e.toString();
            String r2 = r1.replaceAll(temp.x.toString(), x.toString());
            if (e.toString().equals(r2)) {
                return true;
            }
        }
        return false;
    }
}
