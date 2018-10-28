package com.zad.JDK8.common;

public enum Operation {
    ADD {
        public int eval(int arg1, int arg2) {
            return arg1 + arg2;
        }
    },
    SUBTRACT {
        public int eval(int arg1, int arg2) {
            return arg1 - arg2;
        }
    },
    MULTIPLY {
        public int eval(int arg1, int arg2) {
            return arg1 * arg2;
        }
    },
    DIVIDE {
        public int eval(int arg1, int arg2) {
            return arg1 / arg2;
        }
    };

    public abstract int eval(int arg1, int arg2);
}
