package io.oleh.katas.stringcalc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {
  @Test
  void addNull() {
    assertThat(StringCalculator.add(null)).isEqualTo(0);
  }

  @Test
  void addEmptyString() {
    assertThat(StringCalculator.add("")).isEqualTo(0);
  }

  @Test
  void addSingleNumber() {
    assertThat(StringCalculator.add("1")).isEqualTo(1);
  }

  @Test
  void addTwoNumbers() {
    assertThat(StringCalculator.add("1,2")).isEqualTo(3);
  }

  @Test
  void addMultipleNumbers() {
    assertThat(StringCalculator.add("1,2,3,4")).isEqualTo(10);
  }
}
