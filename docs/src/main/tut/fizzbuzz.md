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

```tut:silent
import fizz.FizzBuzz
val stdFB = FizzBuzz.sequence(100)
```

which provides the following result (which we abbreviate here):

```tut
stdFB.take(10) ++ Seq("...") ++ stdFB.takeRight(10)
```
