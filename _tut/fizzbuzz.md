---
layout: default
title:  "FizzBuzz"
section: "tutorials"
source: "core/src/main/scala/fizz/FizzBuzz.scala"
scaladoc: "#fizz.FizzBuzz"
---
# FizzBuzz

`FizzBuzz` has a single method `sequence` returing the FizzBuzz sequence for integers from `1` to `n` inclusive.

## Standard FizzBuzz

The standard FizzBuzz test includes integers up to 100.

```scala
import fizz.FizzBuzz
val stdFB = FizzBuzz.sequence(100)
```

which provides the following result (which we abbreviate here):

```scala
scala> stdFB.take(10) ++ Seq("...") ++ stdFB.takeRight(10)
res0: Seq[String] = Vector(1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, ..., 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, Buzz)
```
