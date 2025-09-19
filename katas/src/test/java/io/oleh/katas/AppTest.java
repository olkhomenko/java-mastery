package io.oleh.katas;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void addsTwoNumbers() {
    assertThat(App.add(2, 3)).isEqualTo(5);
  }

  @Property
  boolean additionIsCommutative(@ForAll int a, @ForAll int b) {
    return App.add(a, b) == App.add(b, a);
  }
}
