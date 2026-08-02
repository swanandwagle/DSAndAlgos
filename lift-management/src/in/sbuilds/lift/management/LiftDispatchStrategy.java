package in.sbuilds.lift.management;

import in.sbuilds.lift.enums.Direction;
import in.sbuilds.lift.model.Lift;

// plug in different algorithms without touching the dispatcher
public interface LiftDispatchStrategy {

    public int computeCost(Lift lift, Direction requestedDirection, int requestedFloor);
}
