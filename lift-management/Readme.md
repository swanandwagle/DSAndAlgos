# Lift Management System

## Problem Statement

Design a lift (elevator) management system for a multi-floor building with multiple lifts operating simultaneously.

When a user presses the call button on a floor, the system should assign the most suitable lift based on where each lift currently is and which direction it is heading.

## Requirements

- There are multiple lifts in the building, each at some floor and moving in a direction (UP, DOWN, or IDLE)
- A user requests a lift from a floor, specifying the direction they want to go
- The system must pick the best lift to serve the request
- Once assigned, the lift should remember the requested floor and move toward it
- A lift can hold multiple pending floor requests at the same time
- The lift should service floors in SCAN order (like a disk scheduling algorithm) — completing floors in the current direction before reversing

## What We Built

- **`Lift`** — represents a lift with its current floor, direction, and a queue of pending floor stops (backed by a `TreeSet` for sorted access)
- **`LiftDispatchStrategy`** — interface to plug in different dispatch algorithms
- **`SimpleDispatchStrategy`** — scores each lift using distance and direction; heavily penalizes lifts moving away or in the wrong direction
- **`LiftDispatcher`** — uses a min-heap (`PriorityQueue`) over scored lifts to find and assign the best one
- **`ScoredList`** — a record pairing a lift with its computed cost, used inside the priority queue

## Key Design Decisions

- **Strategy pattern** for dispatch — makes it easy to swap in smarter algorithms (e.g. load-aware, zone-based) later
- **`TreeSet` for request queue** — gives sorted floor order for free, enabling SCAN traversal with `ceiling()` and `floor()` lookups
- **`step()` method** — moves the lift one floor at a time, updates direction, and clears served floors; allows external simulation without baking a thread model in
