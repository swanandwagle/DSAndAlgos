package in.sbuilds.lift.management.impl;

import in.sbuilds.lift.enums.Direction;
import in.sbuilds.lift.management.LiftDispatchStrategy;
import in.sbuilds.lift.model.Lift;

public class SimpleDispatchStrategy implements LiftDispatchStrategy {
    @Override
    public int computeCost(Lift lift, Direction requestedDirection, int requestedFloor) {
        int cost = Math.abs(requestedFloor - lift.getFloor());

        if(lift.getDirection() == Direction.IDLE) {
            return cost;
        }

        boolean movingTowardRequest =
                (lift.getDirection() == Direction.UP && lift.getFloor() <= requestedFloor) ||
                        (lift.getDirection() == Direction.DOWN && lift.getFloor() >= requestedFloor);

        boolean sameDirection = requestedDirection == lift.getDirection();

        // prefer lifts already heading the right way; penalize others heavily
        if(movingTowardRequest && sameDirection) {
            return cost;
        }

        return lift.getFloor() + 10_000;
    }
}
