package fizzdoc

/** Base trait defining the strings used in [[FizzBuzz]].
  * 
  * This trait is there to have generation of type hierarchy diagrams.
  */
trait FizzBuzzStrings {

  def string3 = "Fizz"
  def string5 = "Buzz"
  def string35 = "FizzBuzz"

}

object FizzBuzz extends FizzBuzzStrings {

  /** Returns the FizzBuzz sequence for the integers from `1` to `n` inclusive.
    * 
    * Example:
    * {{{
    * scala> import fizzdoc.FizzBuzz
    * scala> FizzBuzz.sequence(3)
    * res0: Seq[String] = Vector(1, 2, Fizz)
    * }}}
    */
  def sequence(n: Int): Seq[String] =
    (1 to n).map(i => (i % 3, i % 5) match {
      case (0, 0) => string35
      case (0, _) => string3
      case (_, 0) => string5
      case _ => i.toString
    })

}
