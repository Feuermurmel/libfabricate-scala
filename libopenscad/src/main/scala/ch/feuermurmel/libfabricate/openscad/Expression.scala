package ch.feuermurmel.libfabricate.openscad

// Sad attempt at resolving issues with overload resolution in conjunction with the explicit definition of apply(). Shadowing the generated apply() with a private method confuses IntelliJ IDEA.
case class Expression(data: (Symbol, Map[Symbol, ArgumentValue], Seq[Expression])) {
  val (function, arguments, nestedExpressions) = data

  def lines: Seq[String] = {
    val argumentsStr = arguments.map({ case (k, v) => s"${k.name} = ${v.string}" }).mkString(", ")
    val prefix = s"${function.name}($argumentsStr)"

    if (nestedExpressions.isEmpty) {
      Seq(s"$prefix;")
    } else {
      val indentedLines = nestedExpressions.flatMap(_.lines).map("    " + _)

      Seq(s"$prefix {") ++ indentedLines ++ Seq("}")
    }
  }
}

object Expression {
  def apply(function: Symbol, arguments: (Symbol, ArgumentValue)*)(nestedExpressions: Expression*): Expression =
    new Expression(function, arguments.toMap, nestedExpressions)
}
