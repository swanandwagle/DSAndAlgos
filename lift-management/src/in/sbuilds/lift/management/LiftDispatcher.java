package in.sbuilds.lift.management;

import in.sbuilds.lift.enums.Direction;
import in.sbuilds.lift.management.impl.SimpleDispatchStrategy;
import in.sbuilds.lift.model.Lift;
import in.sbuilds.lift.model.ScoredList;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LiftDispatcher {

    private LiftDispatchStrategy dispatchStrategy;

    public LiftDispatcher(LiftDispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
    }

    // defaults to simple strategy if none provided
    public LiftDispatcher() {
        this.dispatchStrategy = new SimpleDispatchStrategy();
    }

    public Lift fetchBestLift(List<Lift> lifts, Direction requestedDirection, int requestedFloor) {
        // min-heap on score, so poll() gives the best lift directly
        PriorityQueue<ScoredList> listQueue = new PriorityQueue<>(Comparator.comparingInt(ScoredList::score));

        for (Lift lift: lifts) {
            listQueue.offer(new ScoredList(lift, dispatchStrategy.computeCost(lift, requestedDirection, requestedFloor)));
        }

        ScoredList bestLift = listQueue.poll();
        if (bestLift != null) {
            bestLift.lift().addRequest(requestedFloor);
            return bestLift.lift();
        }
        return null;
    }
}
