---
layout: default
title:  "FizzBuzz"
section: "tutorials"
source: "core/src/main/scala/fizzdoc/FizzBuzz.scala"
scaladoc: "#fizzdoc.FizzBuzz"
---
# FizzBuzz

`FizzBuzz` has a single method `sequence` returing the FizzBuzz sequence for integers from `1` to `n` inclusive.

## Standard FizzBuzz

The standard FizzBuzz test includes integers up to 100.

```tut:silent
import fizzdoc.FizzBuzz
val stdFB = FizzBuzz.sequence(100)
```

which provides the following result (which we abbreviate here):

```tut
stdFB.take(10) ++ Seq("...") ++ stdFB.takeRight(10)
```
