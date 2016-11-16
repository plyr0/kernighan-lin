package bipartition;

public class MyVertex {
    public enum Groups {Left, Right}

    private int id;
    Groups group = Groups.Left;
    boolean locked = false;

    public MyVertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "\n{" +
                id +
                ", " + group +
                ", " + locked +
                "}";
    }
}
