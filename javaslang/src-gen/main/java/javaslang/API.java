/*     / \____  _    _  ____   ______  / \ ____  __    _______
 *    /  /    \/ \  / \/    \ /  /\__\/  //    \/  \  //  /\__\   JΛVΛSLΛNG
 *  _/  /  /\  \  \/  /  /\  \\__\\  \  //  /\  \ /\\/ \ /__\ \   Copyright 2014-2016 Javaslang, http://javaslang.io
 * /___/\_/  \_/\____/\_/  \_/\__\/__/\__\_/  \_//  \__/\_____/   Licensed under the Apache License, Version 2.0
 */
package javaslang;

/*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*\
   G E N E R A T O R   C R A F T E D
\*-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-*/

import static javaslang.API.Match.*;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import javaslang.collection.Iterator;
import javaslang.control.Option;

/**
 * The most basic Javaslang functionality is accessed through this API class.
 *
 * <pre><code>
 * import static javaslang.API.*;
 * </code></pre>
 *
 * <h3>For-comprehension</h3>
 * <p>
 * The {@code For}-comprehension is syntactic sugar for nested for-loops. We write
 *
 * <pre><code>
 * // lazily evaluated
 * Iterator&lt;R&gt; result = For(iterable1, iterable2, ..., iterableN).yield(f);
 * </code></pre>
 *
 * or
 *
 * <pre><code>
 * Iterator&lt;R&gt; result =
 *     For(iterable1, v1 -&gt;
 *         For(iterable2, v2 -&gt;
 *             ...
 *             For(iterableN).yield(vN -&gt; f.apply(v1, v2, ..., vN))
 *         )
 *     );
 * </code></pre>
 *
 * instead of
 *
 * <pre><code>
 * for(T1 v1 : iterable1) {
 *     for (T2 v2 : iterable2) {
 *         ...
 *         for (TN vN : iterableN) {
 *             R result = f.apply(v1, v2, ..., VN);
 *             //
 *             // We are forced to perform side effects to do s.th. meaningful with the result.
 *             //
 *         }
 *     }
 * }
 * </code></pre>
 *
 * Please note that values like Option, Try, Future, etc. are also iterable.
 * <p>
 * Given a suitable function
 * f: {@code (v1, v2, ..., vN) -> ...} and 1 &lt;= N &lt;= 8 iterables, the result is a Stream of the
 * mapped cross product elements.
 *
 * <pre><code>
 * { f(v1, v2, ..., vN) | v1 &isin; iterable1, ... vN &isin; iterableN }
 * </code></pre>
 *
 * As with all Javaslang Values, the result of a For-comprehension can be converted
 * to standard Java library and Javaslang types.
 */
public final class API {

    private API() {
    }

    //
    // For-Comprehension
    //

    /**
     * A shortcut for {@code Iterator.ofAll(ts).flatMap(f)} which allows us to write real for-comprehensions using
     * {@code For(...).yield(...)}.
     * <p>
     * Example:
     * <pre><code>
     * For(getPersons(), person -&gt;
     *     For(person.getTweets(), tweet -&gt;
     *         For(tweet.getReplies())
     *             .yield(reply -&gt; person + ", " + tweet + ", " + reply)));
     * </code></pre>
     *
     * @param ts An iterable
     * @param f A function {@code T -> Iterable<U>}
     * @param <T> element type of {@code ts}
     * @param <U> component type of the resulting {@code Iterator}
     * @return A new Iterator
     */
    public static <T, U> Iterator<U> For(Iterable<T> ts, Function<? super T, ? extends Iterable<U>> f) {
        return Iterator.ofAll(ts).flatMap(f);
    }

    /**
     * Creates a {@code For}-comprehension of one Iterable.
     *
     * @param ts1 the 1st Iterable
     * @param <T1> component type of the 1st Iterable
     * @return a new {@code For}-comprehension of arity 1
     */
    public static <T1> For1<T1> For(Iterable<T1> ts1) {
        Objects.requireNonNull(ts1, "ts1 is null");
        return new For1<>(Iterator.ofAll(ts1));
    }

    /**
     * Creates a {@code For}-comprehension of two Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @return a new {@code For}-comprehension of arity 2
     */
    public static <T1, T2> For2<T1, T2> For(Iterable<T1> ts1, Iterable<T2> ts2) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        return new For2<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2));
    }

    /**
     * Creates a {@code For}-comprehension of three Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param ts3 the 3rd Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @param <T3> component type of the 3rd Iterable
     * @return a new {@code For}-comprehension of arity 3
     */
    public static <T1, T2, T3> For3<T1, T2, T3> For(Iterable<T1> ts1, Iterable<T2> ts2, Iterable<T3> ts3) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        Objects.requireNonNull(ts3, "ts3 is null");
        return new For3<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2), Iterator.ofAll(ts3));
    }

    /**
     * Creates a {@code For}-comprehension of 4 Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param ts3 the 3rd Iterable
     * @param ts4 the 4th Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @param <T3> component type of the 3rd Iterable
     * @param <T4> component type of the 4th Iterable
     * @return a new {@code For}-comprehension of arity 4
     */
    public static <T1, T2, T3, T4> For4<T1, T2, T3, T4> For(Iterable<T1> ts1, Iterable<T2> ts2, Iterable<T3> ts3, Iterable<T4> ts4) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        Objects.requireNonNull(ts3, "ts3 is null");
        Objects.requireNonNull(ts4, "ts4 is null");
        return new For4<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2), Iterator.ofAll(ts3), Iterator.ofAll(ts4));
    }

    /**
     * Creates a {@code For}-comprehension of 5 Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param ts3 the 3rd Iterable
     * @param ts4 the 4th Iterable
     * @param ts5 the 5th Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @param <T3> component type of the 3rd Iterable
     * @param <T4> component type of the 4th Iterable
     * @param <T5> component type of the 5th Iterable
     * @return a new {@code For}-comprehension of arity 5
     */
    public static <T1, T2, T3, T4, T5> For5<T1, T2, T3, T4, T5> For(Iterable<T1> ts1, Iterable<T2> ts2, Iterable<T3> ts3, Iterable<T4> ts4, Iterable<T5> ts5) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        Objects.requireNonNull(ts3, "ts3 is null");
        Objects.requireNonNull(ts4, "ts4 is null");
        Objects.requireNonNull(ts5, "ts5 is null");
        return new For5<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2), Iterator.ofAll(ts3), Iterator.ofAll(ts4), Iterator.ofAll(ts5));
    }

    /**
     * Creates a {@code For}-comprehension of 6 Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param ts3 the 3rd Iterable
     * @param ts4 the 4th Iterable
     * @param ts5 the 5th Iterable
     * @param ts6 the 6th Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @param <T3> component type of the 3rd Iterable
     * @param <T4> component type of the 4th Iterable
     * @param <T5> component type of the 5th Iterable
     * @param <T6> component type of the 6th Iterable
     * @return a new {@code For}-comprehension of arity 6
     */
    public static <T1, T2, T3, T4, T5, T6> For6<T1, T2, T3, T4, T5, T6> For(Iterable<T1> ts1, Iterable<T2> ts2, Iterable<T3> ts3, Iterable<T4> ts4, Iterable<T5> ts5, Iterable<T6> ts6) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        Objects.requireNonNull(ts3, "ts3 is null");
        Objects.requireNonNull(ts4, "ts4 is null");
        Objects.requireNonNull(ts5, "ts5 is null");
        Objects.requireNonNull(ts6, "ts6 is null");
        return new For6<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2), Iterator.ofAll(ts3), Iterator.ofAll(ts4), Iterator.ofAll(ts5), Iterator.ofAll(ts6));
    }

    /**
     * Creates a {@code For}-comprehension of 7 Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param ts3 the 3rd Iterable
     * @param ts4 the 4th Iterable
     * @param ts5 the 5th Iterable
     * @param ts6 the 6th Iterable
     * @param ts7 the 7th Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @param <T3> component type of the 3rd Iterable
     * @param <T4> component type of the 4th Iterable
     * @param <T5> component type of the 5th Iterable
     * @param <T6> component type of the 6th Iterable
     * @param <T7> component type of the 7th Iterable
     * @return a new {@code For}-comprehension of arity 7
     */
    public static <T1, T2, T3, T4, T5, T6, T7> For7<T1, T2, T3, T4, T5, T6, T7> For(Iterable<T1> ts1, Iterable<T2> ts2, Iterable<T3> ts3, Iterable<T4> ts4, Iterable<T5> ts5, Iterable<T6> ts6, Iterable<T7> ts7) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        Objects.requireNonNull(ts3, "ts3 is null");
        Objects.requireNonNull(ts4, "ts4 is null");
        Objects.requireNonNull(ts5, "ts5 is null");
        Objects.requireNonNull(ts6, "ts6 is null");
        Objects.requireNonNull(ts7, "ts7 is null");
        return new For7<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2), Iterator.ofAll(ts3), Iterator.ofAll(ts4), Iterator.ofAll(ts5), Iterator.ofAll(ts6), Iterator.ofAll(ts7));
    }

    /**
     * Creates a {@code For}-comprehension of 8 Iterables.
     *
     * @param ts1 the 1st Iterable
     * @param ts2 the 2nd Iterable
     * @param ts3 the 3rd Iterable
     * @param ts4 the 4th Iterable
     * @param ts5 the 5th Iterable
     * @param ts6 the 6th Iterable
     * @param ts7 the 7th Iterable
     * @param ts8 the 8th Iterable
     * @param <T1> component type of the 1st Iterable
     * @param <T2> component type of the 2nd Iterable
     * @param <T3> component type of the 3rd Iterable
     * @param <T4> component type of the 4th Iterable
     * @param <T5> component type of the 5th Iterable
     * @param <T6> component type of the 6th Iterable
     * @param <T7> component type of the 7th Iterable
     * @param <T8> component type of the 8th Iterable
     * @return a new {@code For}-comprehension of arity 8
     */
    public static <T1, T2, T3, T4, T5, T6, T7, T8> For8<T1, T2, T3, T4, T5, T6, T7, T8> For(Iterable<T1> ts1, Iterable<T2> ts2, Iterable<T3> ts3, Iterable<T4> ts4, Iterable<T5> ts5, Iterable<T6> ts6, Iterable<T7> ts7, Iterable<T8> ts8) {
        Objects.requireNonNull(ts1, "ts1 is null");
        Objects.requireNonNull(ts2, "ts2 is null");
        Objects.requireNonNull(ts3, "ts3 is null");
        Objects.requireNonNull(ts4, "ts4 is null");
        Objects.requireNonNull(ts5, "ts5 is null");
        Objects.requireNonNull(ts6, "ts6 is null");
        Objects.requireNonNull(ts7, "ts7 is null");
        Objects.requireNonNull(ts8, "ts8 is null");
        return new For8<>(Iterator.ofAll(ts1), Iterator.ofAll(ts2), Iterator.ofAll(ts3), Iterator.ofAll(ts4), Iterator.ofAll(ts5), Iterator.ofAll(ts6), Iterator.ofAll(ts7), Iterator.ofAll(ts8));
    }

    /**
     * For-comprehension with one Iterable.
     */
    public static class For1<T1> {

        private final Iterator<T1> stream1;

        private For1(Iterator<T1> stream1) {
            this.stream1 = stream1;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function<? super T1, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return stream1.map(f::apply);
        }
    }

    /**
     * For-comprehension with two Iterables.
     */
    public static class For2<T1, T2> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;

        private For2(Iterator<T1> stream1, Iterator<T2> stream2) {
            this.stream1 = stream1;
            this.stream2 = stream2;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(BiFunction<? super T1, ? super T2, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 -> stream2.map(t2 -> f.apply(t1, t2)));
        }
    }

    /**
     * For-comprehension with three Iterables.
     */
    public static class For3<T1, T2, T3> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;
        private final Iterator<T3> stream3;

        private For3(Iterator<T1> stream1, Iterator<T2> stream2, Iterator<T3> stream3) {
            this.stream1 = stream1;
            this.stream2 = stream2;
            this.stream3 = stream3;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function3<? super T1, ? super T2, ? super T3, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 ->
                stream2.flatMap(t2 -> stream3.map(t3 -> f.apply(t1, t2, t3))));
        }
    }

    /**
     * For-comprehension with 4 Iterables.
     */
    public static class For4<T1, T2, T3, T4> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;
        private final Iterator<T3> stream3;
        private final Iterator<T4> stream4;

        private For4(Iterator<T1> stream1, Iterator<T2> stream2, Iterator<T3> stream3, Iterator<T4> stream4) {
            this.stream1 = stream1;
            this.stream2 = stream2;
            this.stream3 = stream3;
            this.stream4 = stream4;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 ->
                stream2.flatMap(t2 ->
                stream3.flatMap(t3 -> stream4.map(t4 -> f.apply(t1, t2, t3, t4)))));
        }
    }

    /**
     * For-comprehension with 5 Iterables.
     */
    public static class For5<T1, T2, T3, T4, T5> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;
        private final Iterator<T3> stream3;
        private final Iterator<T4> stream4;
        private final Iterator<T5> stream5;

        private For5(Iterator<T1> stream1, Iterator<T2> stream2, Iterator<T3> stream3, Iterator<T4> stream4, Iterator<T5> stream5) {
            this.stream1 = stream1;
            this.stream2 = stream2;
            this.stream3 = stream3;
            this.stream4 = stream4;
            this.stream5 = stream5;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 ->
                stream2.flatMap(t2 ->
                stream3.flatMap(t3 ->
                stream4.flatMap(t4 -> stream5.map(t5 -> f.apply(t1, t2, t3, t4, t5))))));
        }
    }

    /**
     * For-comprehension with 6 Iterables.
     */
    public static class For6<T1, T2, T3, T4, T5, T6> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;
        private final Iterator<T3> stream3;
        private final Iterator<T4> stream4;
        private final Iterator<T5> stream5;
        private final Iterator<T6> stream6;

        private For6(Iterator<T1> stream1, Iterator<T2> stream2, Iterator<T3> stream3, Iterator<T4> stream4, Iterator<T5> stream5, Iterator<T6> stream6) {
            this.stream1 = stream1;
            this.stream2 = stream2;
            this.stream3 = stream3;
            this.stream4 = stream4;
            this.stream5 = stream5;
            this.stream6 = stream6;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 ->
                stream2.flatMap(t2 ->
                stream3.flatMap(t3 ->
                stream4.flatMap(t4 ->
                stream5.flatMap(t5 -> stream6.map(t6 -> f.apply(t1, t2, t3, t4, t5, t6)))))));
        }
    }

    /**
     * For-comprehension with 7 Iterables.
     */
    public static class For7<T1, T2, T3, T4, T5, T6, T7> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;
        private final Iterator<T3> stream3;
        private final Iterator<T4> stream4;
        private final Iterator<T5> stream5;
        private final Iterator<T6> stream6;
        private final Iterator<T7> stream7;

        private For7(Iterator<T1> stream1, Iterator<T2> stream2, Iterator<T3> stream3, Iterator<T4> stream4, Iterator<T5> stream5, Iterator<T6> stream6, Iterator<T7> stream7) {
            this.stream1 = stream1;
            this.stream2 = stream2;
            this.stream3 = stream3;
            this.stream4 = stream4;
            this.stream5 = stream5;
            this.stream6 = stream6;
            this.stream7 = stream7;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 ->
                stream2.flatMap(t2 ->
                stream3.flatMap(t3 ->
                stream4.flatMap(t4 ->
                stream5.flatMap(t5 ->
                stream6.flatMap(t6 -> stream7.map(t7 -> f.apply(t1, t2, t3, t4, t5, t6, t7))))))));
        }
    }

    /**
     * For-comprehension with 8 Iterables.
     */
    public static class For8<T1, T2, T3, T4, T5, T6, T7, T8> {

        private final Iterator<T1> stream1;
        private final Iterator<T2> stream2;
        private final Iterator<T3> stream3;
        private final Iterator<T4> stream4;
        private final Iterator<T5> stream5;
        private final Iterator<T6> stream6;
        private final Iterator<T7> stream7;
        private final Iterator<T8> stream8;

        private For8(Iterator<T1> stream1, Iterator<T2> stream2, Iterator<T3> stream3, Iterator<T4> stream4, Iterator<T5> stream5, Iterator<T6> stream6, Iterator<T7> stream7, Iterator<T8> stream8) {
            this.stream1 = stream1;
            this.stream2 = stream2;
            this.stream3 = stream3;
            this.stream4 = stream4;
            this.stream5 = stream5;
            this.stream6 = stream6;
            this.stream7 = stream7;
            this.stream8 = stream8;
        }

        /**
         * Yields a result for elements of the cross product of the underlying Iterables.
         *
         * @param f a function that maps an element of the cross product to a result
         * @param <R> type of the resulting Stream elements
         * @return a Stream of mapped results
         */
        public <R> Iterator<R> yield(Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
            Objects.requireNonNull(f, "f is null");
            return
                stream1.flatMap(t1 ->
                stream2.flatMap(t2 ->
                stream3.flatMap(t3 ->
                stream4.flatMap(t4 ->
                stream5.flatMap(t5 ->
                stream6.flatMap(t6 ->
                stream7.flatMap(t7 -> stream8.map(t8 -> f.apply(t1, t2, t3, t4, t5, t6, t7, t8)))))))));
        }
    }

    //
    // Structural Pattern Matching
    //

    // -- static Match API

    /**
     * Entry point of the match API.
     *
     * @param value a value to be matched
     * @param <T> type of the value
     * @return a new {@code Match} instance
     */
    public static <T> Match<T> Match(T value) {
        return new Match<>(value);
    }

    // -- Cases

    public static <T, R> Case<T, R> Case(T value, Supplier<? extends R> f) {
        return new Case0<>(P0.eq(value), f);
    }

    // syntactic sugar
    public static <T, R> Case<T, R> Case(T value, R retVal) {
        return Case(value, () -> retVal);
    }

    public static <T, R> Case<T, R> Case(P0<T> pattern, Supplier<? extends R> f) {
        return new Case0<>(pattern, f);
    }

    // syntactic sugar
    public static <T, R> Case<T, R> Case(P0<T> pattern, R retVal) {
        return new Case0<>(pattern, () -> retVal);
    }

    public static <T, T1, R> Case<T, R> Case(P1<T, T1> pattern, Function<? super T1, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case1<>(pattern, f);
    }

    public static <T, T1, T2, R> Case<T, R> Case(P2<T, T1, T2> pattern, BiFunction<? super T1, ? super T2, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case2<>(pattern, f);
    }

    public static <T, T1, T2, T3, R> Case<T, R> Case(P3<T, T1, T2, T3> pattern, Function3<? super T1, ? super T2, ? super T3, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case3<>(pattern, f);
    }

    public static <T, T1, T2, T3, T4, R> Case<T, R> Case(P4<T, T1, T2, T3, T4> pattern, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case4<>(pattern, f);
    }

    public static <T, T1, T2, T3, T4, T5, R> Case<T, R> Case(P5<T, T1, T2, T3, T4, T5> pattern, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case5<>(pattern, f);
    }

    public static <T, T1, T2, T3, T4, T5, T6, R> Case<T, R> Case(P6<T, T1, T2, T3, T4, T5, T6> pattern, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case6<>(pattern, f);
    }

    public static <T, T1, T2, T3, T4, T5, T6, T7, R> Case<T, R> Case(P7<T, T1, T2, T3, T4, T5, T6, T7> pattern, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case7<>(pattern, f);
    }

    public static <T, T1, T2, T3, T4, T5, T6, T7, T8, R> Case<T, R> Case(P8<T, T1, T2, T3, T4, T5, T6, T7, T8> pattern, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
        Objects.requireNonNull(pattern, "pattern is null");
        return new Case8<>(pattern, f);
    }

    // atomic patterns $_, $(), $(val)

    /**
     * Wildcard pattern.
     * <p>
     * Matches any value but does not extract it.
     */
    public static final P0<Object> $_ = new P0<Object>() {
        @Override
        Option<Void> apply(Object obj) {
            return Option.nothing();
        }
    };

    /**
     * Wildcard extractor.
     * <p>
     * Matches any value and extracts it as named lambda parameter.
     *
     * @param <O> injected type of the underlying value
     * @return a new {@code Pattern1} instance
     */
    public static <O> P1<O, O> $() {
        return new P1<O, O>() {
            @Override
            Option<O> apply(O obj) {
                return Option.some(obj);
            }
        };
    }

    /**
     * Value extractor.
     * <p>
     * Matches a specific value and extracts it as named lambda parameter.
     *
     * @param <O>       type of the prototype
     * @param prototype the value that should be equal to the underlying object
     * @return a new {@code Pattern1} instance
     */
    public static <O> P1<O, O> $(O prototype) {
        return new P1<O, O>() {
            @Override
            Option<O> apply(O obj) {
                return Objects.equals(obj, prototype) ? Option.some(obj) : Option.none();
            }
        };
    }

    /**
     * Scala-like structural pattern matching for Java.
     *
     * <pre><code>
     * // Match API
     * import static javaslang.Match.*;
     *
     * // Match Patterns for Javaslang types
     * import static javaslang.match.Patterns.*;
     *
     * // Example
     * Match(list).of(
     *         Case(List($(), $()), (x, xs) -&gt; "head: " + x + ", tail: " + xs),
     *         Case($_, -&gt; "Nil")
     * );
     *
     * // Syntactic sugar
     * list.match(
     *         Case(List($(), $()), (x, xs) -&gt; "head: " + x + ", tail: " + xs),
     *         Case($_, -&gt; "Nil")
     * );
     * </code></pre>
     */
    public static final class Match<T> {

        private final T value;

        private Match(T value) {
            this.value = value;
        }

        @SuppressWarnings({ "unchecked", "varargs" })
        @SafeVarargs
        public final <R> R of(Case<? extends T, ? extends R>... cases) {
            return option(cases).getOrElseThrow(() -> new MatchError(value));
        }

        @SuppressWarnings({ "unchecked", "varargs" })
        @SafeVarargs
        public final <R> Option<R> option(Case<? extends T, ? extends R>... cases) {
            Objects.requireNonNull(cases, "cases is null");
            for (Case<? extends T, ? extends R> _case : cases) {
                final Option<R> it = ((Case<T, R>) _case).apply(value);
                if (it.isDefined()) {
                    return it;
                }
            }
            return Option.none();
        }

        // -- CASES

        // does not compile if interfaces are not fully qualified - wtf!?
        public interface Case<T, R> extends java.util.function.Function<T, javaslang.control.Option<R>> {
        }

        public static final class Case0<T, R> implements Case<T, R> {

            private final P0<T> pattern;
            private final Supplier<? extends R> f;

            private Case0(P0<T> pattern, Supplier<? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(ignored -> f.get());
            }
        }

        public static final class Case1<T, T1, R> implements Case<T, R> {

            private final P1<T, T1> pattern;
            private final Function<? super T1, ? extends R> f;

            private Case1(P1<T, T1> pattern, Function<? super T1, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(f);
            }
        }

        public static final class Case2<T, T1, T2, R> implements Case<T, R> {

            private final P2<T, T1, T2> pattern;
            private final BiFunction<? super T1, ? super T2, ? extends R> f;

            private Case2(P2<T, T1, T2> pattern, BiFunction<? super T1, ? super T2, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2));
            }
        }

        public static final class Case3<T, T1, T2, T3, R> implements Case<T, R> {

            private final P3<T, T1, T2, T3> pattern;
            private final Function3<? super T1, ? super T2, ? super T3, ? extends R> f;

            private Case3(P3<T, T1, T2, T3> pattern, Function3<? super T1, ? super T2, ? super T3, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2, t._3));
            }
        }

        public static final class Case4<T, T1, T2, T3, T4, R> implements Case<T, R> {

            private final P4<T, T1, T2, T3, T4> pattern;
            private final Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f;

            private Case4(P4<T, T1, T2, T3, T4> pattern, Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2, t._3, t._4));
            }
        }

        public static final class Case5<T, T1, T2, T3, T4, T5, R> implements Case<T, R> {

            private final P5<T, T1, T2, T3, T4, T5> pattern;
            private final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f;

            private Case5(P5<T, T1, T2, T3, T4, T5> pattern, Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2, t._3, t._4, t._5));
            }
        }

        public static final class Case6<T, T1, T2, T3, T4, T5, T6, R> implements Case<T, R> {

            private final P6<T, T1, T2, T3, T4, T5, T6> pattern;
            private final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f;

            private Case6(P6<T, T1, T2, T3, T4, T5, T6> pattern, Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2, t._3, t._4, t._5, t._6));
            }
        }

        public static final class Case7<T, T1, T2, T3, T4, T5, T6, T7, R> implements Case<T, R> {

            private final P7<T, T1, T2, T3, T4, T5, T6, T7> pattern;
            private final Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f;

            private Case7(P7<T, T1, T2, T3, T4, T5, T6, T7> pattern, Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7));
            }
        }

        public static final class Case8<T, T1, T2, T3, T4, T5, T6, T7, T8, R> implements Case<T, R> {

            private final P8<T, T1, T2, T3, T4, T5, T6, T7, T8> pattern;
            private final Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f;

            private Case8(P8<T, T1, T2, T3, T4, T5, T6, T7, T8> pattern, Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
                this.pattern = pattern;
                this.f = f;
            }

            @Override
            public Option<R> apply(T obj) {
                return pattern.apply(obj).map(t -> f.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8));
            }
        }

        // -- PATTERNS

        // These can't be @FunctionalInterfaces because of ambiguities.
        // For benchmarks lambda vs. abstract class see http://www.oracle.com/technetwork/java/jvmls2013kuksen-2014088.pdf

        public static abstract class P0<O> {

            // created with factory methods only for safety reasons
            private P0() {
            }

            abstract Option<Void> apply(O o);

            public static <O> P0<O> eq(O prototype) {
                return new P0<O>() {
                    @Override
                    Option<Void> apply(O o) {
                        return Objects.equals(o, prototype) ? Option.nothing() : Option.none();
                    }
                };
            }

            public static <O> P0<O> of(Class<O> type) {
                return new P0<O>() {
                    @Override
                    Option<Void> apply(O o) {
                        return (o != null && type.isAssignableFrom(o.getClass())) ? Option.nothing() : Option.none();
                    }
                };
            }

            private static <O> P0<O> of(Function<O, Option<Void>> f) {
                return new P0<O>() {
                    @Override
                    Option<Void> apply(O o) {
                        return f.apply(o);
                    }
                };
            }

            // TODO: these need to be generated for all variations...
            @SuppressWarnings("unchecked")
            public static <O, O1, T1> P0<O> of(P0<? extends O1> p1, Function<? super O, Tuple1<O1>> f) {
                return of(o -> f.apply(o).transform(((P0<O1>) p1)::apply));
            }
        }

        public static abstract class P1<O, T1> {

            // created with factory methods only for safety reasons
            private P1() {
            }

            abstract Option<T1> apply(O obj);

            private static <O, T1> P1<O, T1> of(Function<O, Option<T1>> f) {
                return new P1<O, T1>() {
                    @Override
                    protected Option<T1> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

                                      @SuppressWarnings("unchecked")
                                      public static <O, O1, T1> P1<O, T1> of(P1<? extends O1, T1> p1, Function<? super O, Tuple1<O1>> f) {
                                          return of(o -> f.apply(o).transform(((P1<O1, T1>) p1)::apply));
                                      }

        }

        public static abstract class P2<O, T1, T2> {

            // created with factory methods only for safety reasons
            private P2() {
            }

            abstract Option<Tuple2<T1, T2>> apply(O obj);

            private static <O, T1, T2> P2<O, T1, T2> of(Function<O, Option<Tuple2<T1, T2>>> f) {
                return new P2<O, T1, T2>() {
                    @Override
                    protected Option<Tuple2<T1, T2>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }

        public static abstract class P3<O, T1, T2, T3> {

            // created with factory methods only for safety reasons
            private P3() {
            }

            abstract Option<Tuple3<T1, T2, T3>> apply(O obj);

            private static <O, T1, T2, T3> P3<O, T1, T2, T3> of(Function<O, Option<Tuple3<T1, T2, T3>>> f) {
                return new P3<O, T1, T2, T3>() {
                    @Override
                    protected Option<Tuple3<T1, T2, T3>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }

        public static abstract class P4<O, T1, T2, T3, T4> {

            // created with factory methods only for safety reasons
            private P4() {
            }

            abstract Option<Tuple4<T1, T2, T3, T4>> apply(O obj);

            private static <O, T1, T2, T3, T4> P4<O, T1, T2, T3, T4> of(Function<O, Option<Tuple4<T1, T2, T3, T4>>> f) {
                return new P4<O, T1, T2, T3, T4>() {
                    @Override
                    protected Option<Tuple4<T1, T2, T3, T4>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }

        public static abstract class P5<O, T1, T2, T3, T4, T5> {

            // created with factory methods only for safety reasons
            private P5() {
            }

            abstract Option<Tuple5<T1, T2, T3, T4, T5>> apply(O obj);

            private static <O, T1, T2, T3, T4, T5> P5<O, T1, T2, T3, T4, T5> of(Function<O, Option<Tuple5<T1, T2, T3, T4, T5>>> f) {
                return new P5<O, T1, T2, T3, T4, T5>() {
                    @Override
                    protected Option<Tuple5<T1, T2, T3, T4, T5>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }

        public static abstract class P6<O, T1, T2, T3, T4, T5, T6> {

            // created with factory methods only for safety reasons
            private P6() {
            }

            abstract Option<Tuple6<T1, T2, T3, T4, T5, T6>> apply(O obj);

            private static <O, T1, T2, T3, T4, T5, T6> P6<O, T1, T2, T3, T4, T5, T6> of(Function<O, Option<Tuple6<T1, T2, T3, T4, T5, T6>>> f) {
                return new P6<O, T1, T2, T3, T4, T5, T6>() {
                    @Override
                    protected Option<Tuple6<T1, T2, T3, T4, T5, T6>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }

        public static abstract class P7<O, T1, T2, T3, T4, T5, T6, T7> {

            // created with factory methods only for safety reasons
            private P7() {
            }

            abstract Option<Tuple7<T1, T2, T3, T4, T5, T6, T7>> apply(O obj);

            private static <O, T1, T2, T3, T4, T5, T6, T7> P7<O, T1, T2, T3, T4, T5, T6, T7> of(Function<O, Option<Tuple7<T1, T2, T3, T4, T5, T6, T7>>> f) {
                return new P7<O, T1, T2, T3, T4, T5, T6, T7>() {
                    @Override
                    protected Option<Tuple7<T1, T2, T3, T4, T5, T6, T7>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }

        public static abstract class P8<O, T1, T2, T3, T4, T5, T6, T7, T8> {

            // created with factory methods only for safety reasons
            private P8() {
            }

            abstract Option<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> apply(O obj);

            private static <O, T1, T2, T3, T4, T5, T6, T7, T8> P8<O, T1, T2, T3, T4, T5, T6, T7, T8> of(Function<O, Option<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>> f) {
                return new P8<O, T1, T2, T3, T4, T5, T6, T7, T8>() {
                    @Override
                    protected Option<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> apply(O obj) {
                        return f.apply(obj);
                    }
                };
            }

            // TODO: methods need to be generated for all variations...

        }
    }
}