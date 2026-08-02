package in.sbuilds.lift.model;

import in.sbuilds.lift.enums.Direction;

import java.util.TreeSet;

public class Lift {
    private int id;
    private int floor;
    private Direction direction;
    // sorted so we can do SCAN-order traversal
    private TreeSet<Integer> requestQueue;

    public Lift(int id, int floor, Direction direction) {
        this.id = id;
        this.floor = floor;
        this.direction = direction;
        this.requestQueue = new TreeSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void addRequest(int requestedFloor) {
        requestQueue.add(requestedFloor);
    }

    // returns next floor in current direction; wraps around if no more in that direction
    public Integer nextFloor() {
        if (requestQueue.isEmpty()) return null;
        if (direction == Direction.UP) {
            Integer next = requestQueue.ceiling(floor);
            return next != null ? next : requestQueue.first();
        } else if (direction == Direction.DOWN) {
            Integer next = requestQueue.floor(floor);
            return next != null ? next : requestQueue.last();
        }
        return requestQueue.first();
    }

    public TreeSet<Integer> getRequestQueue() {
        return requestQueue;
    }

    // moves lift one floor per call, serves the floor if reached
    public void step() {
        Integer target = nextFloor();
        if (target == null) {
            direction = Direction.IDLE;
            return;
        }
        if (target > floor) {
            floor++;
            direction = Direction.UP;
        } else if (target < floor) {
            floor--;
            direction = Direction.DOWN;
        }
        if (floor == target) {
            requestQueue.remove(target);
            if (requestQueue.isEmpty()) direction = Direction.IDLE;
        }
    }

    public String toString() {
        return "Lift[id=" + this.id + " floor=" + this.floor + " direction=" + this.direction.toString() + " queue=" + requestQueue + "]";
    }
}
