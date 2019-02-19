package openscad

trait HasVoid[A] {
  def void: A
}

object HasVoid {
  def void[A](implicit hv: HasVoid[A]) = hv.void
}
