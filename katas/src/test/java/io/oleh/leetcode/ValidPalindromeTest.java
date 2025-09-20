package io.oleh.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValidPalindromeTest {
  private ValidPalindrome validPalindrome = new ValidPalindrome();

  @Test
  void isPalindrome() {
    assertThat(validPalindrome.isPalindrome("A man, a plan, a canal: Panama")).isTrue();
  }

  @Test
  void isNotPalindrome() {
    assertThat(validPalindrome.isPalindrome("race a car")).isFalse();
  }

  @Test
  void singleCharPalindrome() {
    assertThat(validPalindrome.isPalindrome("a,")).isTrue();
  }

  @Test
  void emptyStringPalindrome() {
    assertThat(validPalindrome.isPalindrome(" ")).isTrue();
  }

  @Test
  void numericPalindrome() {
    assertThat(validPalindrome.isPalindrome("1221")).isTrue();
  }

  @Test
  void alphanumericPalindrome() {
    assertThat(validPalindrome.isPalindrome("ab2a")).isFalse();
  }
}
