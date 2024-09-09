package com.example.garlion_phase_2;

class Timed<T> {
    T recent;
    T old;
    long wait;
    // Last time we ticked.
    long time = 0;

    public Timed (T recent, T old, long wait ) {
        this.recent = recent;
        this.old = old;
        this.wait = wait;
    }

    public T get () {
        return System.currentTimeMillis() < time + wait ? recent: old;
    }
    public void tick () {
        time = System.currentTimeMillis();
    }
}