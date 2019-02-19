package ch.feuermurmel.libfabricate.openscad

trait CanTransform3D[A] {
  def transform(element: A, fn: Expression => Expression): A
}

object CanTransform3D {
  def transform[A](element: A, fn: Expression => Expression)(implicit ct: CanTransform3D[A]) =
    ct.transform(element, fn)
}
