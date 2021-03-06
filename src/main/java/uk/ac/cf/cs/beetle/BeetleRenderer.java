package uk.ac.cf.cs.beetle;

public interface BeetleRenderer {
    void visit(Head h);

    void visit(Body b);

    void visit(Tail t);

    void visit(Leg leg);

    void visit(Antenna antenna);

    void visit(Eye eye);

    void reset();
}
