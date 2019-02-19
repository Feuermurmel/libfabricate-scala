package vectors

import java.lang.Math.sqrt

case class Vec2(x: Double, y: Double) {
  def +(v: Vec2) = Vec2(x + v.x, y + v.y)
  def -(v: Vec2) = this + -v

  def *(v: Double) = Vec2(x * v, y * v)
  def /(v: Double) = this * (1 / v)

  def dot(v: Vec2) = x * v.x + y * v.y

  def unary_- = this * -1

  def normSq = this dot this
  def norm = sqrt(normSq)
}

object Vec2 {
  implicit class TupleAsVec2[A: CanConvertToDouble](value: (A, A)) extends Vec2(value._1, value._2)
}
