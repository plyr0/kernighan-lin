package bipartition.model;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private int id;
    private List<Integer> neighbours;
    private boolean lock = false;

    public Vertex(int id) {
        this.id = id;
        neighbours = new ArrayList<>();
    }

    public void addNeighbour(int other) {
        neighbours.add(other);
    }

    public List<Integer> getNeighbours() {
        return neighbours;
    }

    public int getId() {
        return id;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public String toString() {
        return id + "-" + neighbours;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(id, o.id);
    }
}
