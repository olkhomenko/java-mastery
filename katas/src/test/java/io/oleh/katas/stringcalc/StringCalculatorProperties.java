package io.oleh.katas.stringcalc;

import java.util.Arrays;
import java.util.stream.Collectors;
import net.jqwik.api.*;

public class StringCalculatorProperties {

  @Property
  boolean sumOfNonNegativeCsvMatchedExpected(@ForAll("csvOfSmallInts") String csv) {
    int expected =
        Arrays.stream(csv.split(",")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).sum();
    return expected == StringCalculator.add(csv);
  }

  @Provide
  Arbitrary<String> csvOfSmallInts() {
    return Arbitraries.integers()
        .between(0, 50)
        .list()
        .ofSize(3)
        .map(list -> list.stream().map(String::valueOf).collect(Collectors.joining(",")));
  }
}
