import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ListComprehensionTest {

  @Test
  void of() {
    var listOfLists = ListComprehension.of(
            List::of,
            () -> IntStream.rangeClosed(0, 2).boxed().toList(),
            () -> IntStream.rangeClosed(2, 4).boxed().toList()
    );

    assertNotNull(listOfLists);
    listOfLists.forEach(System.out::println);

    var expected  = List.of(
            List.of(0,2),
            List.of(0,3),
            List.of(0,4),
            List.of(1,2),
            List.of(1,3),
            List.of(1,4),
            List.of(2,2),
            List.of(2,3),
            List.of(2,4)
    );

    assertEquals(expected, listOfLists);
  }

  @Test
  void testCartesian() {
    var inputLists = List.of(
            List.of(1, 2),
            List.of(3, 4),
            List.of(5, 6)
    );

    var expected = List.of(
            List.of(1, 3, 5),
            List.of(1, 3, 6),
            List.of(1, 4, 5),
            List.of(1, 4, 6),
            List.of(2, 3, 5),
            List.of(2, 3, 6),
            List.of(2, 4, 5),
            List.of(2, 4, 6)
    );

    var cartesian = ListComprehension.cartesian(inputLists);
    cartesian.forEach(System.out::println);
    assertEquals(expected, cartesian);
  }
}
