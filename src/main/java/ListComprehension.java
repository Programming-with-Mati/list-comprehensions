import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class ListComprehension {

  public static <T,V,R> List<R> of(
          BiFunction<T,V,R> merger,
          Supplier<List<T>> gen1,
          Supplier<List<V>> gen2
  ) {
    var ts = gen1.get();
    var vs = gen2.get();
    return ts.stream()
            .flatMap(t -> vs.stream().map(v -> merger.apply(t, v))).toList();
  }

  public static <T> List<List<T>> cartesian(List<List<T>> lists) {
    if (lists.isEmpty()) return List.of(emptyList());

    return ListComprehension.of(
            (y,ys) -> Stream.concat(Stream.of(y),ys.stream()).toList(),
            () -> lists.get(0),
            () -> cartesian(lists.stream().skip(1).toList())
    );
  }
}
