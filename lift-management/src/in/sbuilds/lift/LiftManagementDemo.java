package in.sbuilds.lift;

import in.sbuilds.lift.enums.Direction;
import in.sbuilds.lift.management.LiftDispatcher;
import in.sbuilds.lift.model.Lift;

import java.util.List;

public class LiftManagementDemo {
    public static void main(String[] args) {
        List<Lift> lifts = List.of(
            new Lift(1, 2, Direction.IDLE),
            new Lift(2, 8, Direction.DOWN),
            new Lift(3, 1, Direction.UP),
            new Lift(4, 5, Direction.UP)
        );
 
        LiftDispatcher dispatcher = new LiftDispatcher();
 
        int requestFloor = 4;
        Direction requestDirection = Direction.UP;

        Lift best = dispatcher.fetchBestLift(lifts, requestDirection, requestFloor);
        System.out.println("Request: floor " + requestFloor + ", going " + requestDirection);
        System.out.println("Best lift assigned: " + best);

        // simulate lift moving to the requested floor
        while (!best.getRequestQueue().isEmpty()) {
            best.step();
            System.out.println(best);
        }
    }
}