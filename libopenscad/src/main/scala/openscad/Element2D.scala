package openscad

import scala.language.implicitConversions

case class Element2D(expression: Expression) extends Element

object Element2D {
  implicit val canTransform2D: CanTransform2D[Element2D] =
    (element: Element2D, fn: Expression => Expression) => Element2D(fn(element.expression))

  implicit val canCombine: CanCombine[Element2D] =
    (elements: Seq[Element2D], fn: Seq[Expression] => Expression) => Element2D(fn(elements.map(_.expression)))

  implicit val canExtrude: CanExtrude[Element2D, Element3D] =
    (element: Element2D, fn: Expression => Expression) => Element3D(fn(element.expression))

  implicit val hasVoid: HasVoid[Element2D] = new HasVoid[Element2D] {
    override def void = Element2D(Expression('polygon)())
  }
}
