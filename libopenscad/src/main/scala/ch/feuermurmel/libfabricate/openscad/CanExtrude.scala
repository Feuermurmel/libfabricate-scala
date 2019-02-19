package ch.feuermurmel.libfabricate.openscad

trait CanExtrude[A, B] {
  def extrude(element: A, fn: Expression => Expression): B
}

object CanExtrude {
  def extrude[A, B](element: A, fn: Expression => Expression)(implicit ce: CanExtrude[A, B]) =
    ce.extrude(element, fn)
}
