package io.oleh.leetcode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class TwoSumTest {

  @Test
  void twoSum() {
    TwoSum twoSum = new TwoSum();
    int[] array = {2, 7, 11, 15};
    int[] output = {0, 1};
    assertThat(twoSum.twoSum(array, 9)).isEqualTo(output);
  }

  @Test
  void handlesNoSolution() {
    TwoSum solver = new TwoSum();
    int[] nums = {1, 2, 3};
    assertThatThrownBy(() -> solver.twoSum(nums, 10))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("No solution found");
  }

  @Test
  void worksWithDuplicates() {
    TwoSum solver = new TwoSum();
    int[] nums = {3, 3};
    assertThat(solver.twoSum(nums, 6)).isEqualTo(new int[] {0, 1});
  }
}
