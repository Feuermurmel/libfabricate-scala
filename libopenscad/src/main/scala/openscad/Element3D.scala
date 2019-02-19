package openscad

import scala.language.implicitConversions

case class Element3D(expression: Expression) extends Element

object Element3D {
  implicit val canTransform3D: CanTransform3D[Element3D] =
    (element: Element3D, fn: Expression => Expression) => Element3D(fn(element.expression))

  implicit val canCombine: CanCombine[Element3D] =
    (elements: Seq[Element3D], fn: Seq[Expression] => Expression) => Element3D(fn(elements.map(_.expression)))

  implicit val hasVoid: HasVoid[Element3D] = new HasVoid[Element3D] {
    override def void = HasVoid.void[Element2D].extrude(0, 0)
  }
}
