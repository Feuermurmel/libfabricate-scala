package openscad

trait CanTransform2D[A] {
  def transform(element: A, fn: Expression => Expression): A
}

object CanTransform2D {
  def transform[A](element: A, fn: Expression => Expression)(implicit ct: CanTransform2D[A]) =
    ct.transform(element, fn)
}
