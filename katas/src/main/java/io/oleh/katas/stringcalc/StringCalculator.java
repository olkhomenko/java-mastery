package io.oleh.katas.stringcalc;

import com.google.common.base.Splitter;
import java.util.List;

public class StringCalculator {
  public static int add(String input) {
    int answer = 0;
    if (input == null || input.isEmpty()) {
      return answer;
    }

    List<String> numbers = Splitter.on(',').splitToList(input);
    for (String number : numbers) {
      answer += Integer.parseInt(number);
    }
    return answer;
  }
}
