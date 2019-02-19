package ch.feuermurmel.libfabricate.vectors

import java.lang.Math.sqrt

case class Vec3(x: Double, y: Double, z: Double) {
  def +(v: Vec3) = Vec3(x + v.x, y + v.y, z + v.z)
  def -(v: Vec3) = this + -v

  def *(v: Double) = Vec3(x * v, y * v, z * v)
  def /(v: Double) = this * (1 / v)

  def dot(v: Vec3) = x * v.x + y * v.y + z * v.z

  def unary_- = this * -1

  def normSq = this dot this
  def norm = sqrt(normSq)
}

object Vec3 {
  implicit class TupleAsVec2[A: CanConvertToDouble](value: (A, A, A)) extends Vec3(value._1, value._2, value._3)
}
