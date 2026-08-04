# Trapped Water Problem

Given an array of bar heights, calculate how much water would be trapped between the bars after it rains.

## Example

```
Input:  {0, 1, 0, 2, 1, 0, 3, 1, 0, 1, 2}
Output: 8
```

```
      |
  |   |       |
  | ~ |   | ~ |
| | ~ | ~ | | | ~ | |
0 1 0 2 1 0 3 1 0 1 2
```

## Approaches

### 1. Brute Force — O(n²) time, O(1) space

For each bar, scan left and right to find the tallest bar on each side. Water above the current bar = `min(maxLeft, maxRight) - height[i]`.

### 2. Two-Pointer — O(n) time, O(1) space

Use two pointers starting from both ends. The side with the shorter bar is the bottleneck — process that side and move the pointer inward. Track running max on each side to compute trapped water on the fly.

## Classes

- `TrappedWaterCalculator` — contains both algorithm implementations
- `TrappedWaterDemo` — runs the brute force algorithm with a sample input
