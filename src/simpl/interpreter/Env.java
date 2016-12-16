package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    public final Env E;
    public final Symbol x;
    public final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        // TODO Done
        if (x.toString().equals(y.toString())) {
            return v;
        }
        else {
            if (this.E == null) {
                return null;
            }
            else {
                return E.get(y);
            }
        }
    }

    public Env clone() {
        // TODO Done
        if (this == null) {
            return null;
        }
        return new Env(this.E.clone(),this.x,this.v);
    }
}
