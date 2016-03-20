package fizzdoc

object FizzBuzz {

  /** Returns the FizzBuzz sequence for the integers from `1` to `n` inclusive.
    * 
    * Example:
    * {{{
    * scala> import fizzbuzz.FizzBuzz
    * scala> FizzBuzz.sequence(3)
    * res0: Seq[String] = Seq("1", "2", "Fizz")
    * }}}
    */
  def sequence(n: Int): Seq[String] =
    (1 to n).map(i => (i % 3, i % 5) match {
      case (0, 0) => "FizzBuzz"
      case (0, _) => "Fizz"
      case (_, 0) => "Buzz"
      case _ => i.toString
    })

}
