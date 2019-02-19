package ch.feuermurmel.libfabricate

import java.nio.file.Path

import better.files.File
import ch.feuermurmel.libfabricate.vectors.{Vec2, Vec3}

// TODO: Reorder methods
// TODO: add cut and project operations
// TODO: add mirror and scale operations
// TODO: add rotate 3D operation
// TODO: add minkowski operation
// TODO: add sphere primitive
// TODO: add cube primitive
// TODO: add polygon primitive
// TODO: add polyhedron primitive
package object openscad {
  def union[A: CanCombine: HasVoid](elements: Seq[A]) =
    elements.fold(HasVoid.void[A])(_ | _)

  def intersection[A: CanCombine: HasVoid](elements: Seq[A]) =
    elements.fold(HasVoid.void[A])(_ & _)

  // TODO: Maybe accept multiple elements in transformation functions too?
  def hull[A: CanCombine: HasVoid](elements: A*) =
    CanCombine.combine(Seq(union(elements)), Expression('hull))

  def circle(radius: Double, center: Vec2 = (0, 0)): Element2D =
    Element2D(Expression('circle, 'r -> radius, '$fn -> 16)()).translate(center)

  // TODO: Fix this interface
  def rectangle(corner1: Vec2, corner2: Vec2) =
    Element2D(Expression('square, 'size -> (corner2 - corner1))()).translate(corner1)

  def writeToOpenSCADFile(element: Element, path: Path) =
    File(path).write(element.expression.lines.map(_ + "\n").mkString)

  implicit class CanCombineOps[A](value: A) {
    def |[B: CanCombine](other: B)(implicit cast: A => B) =
      CanCombine.combine(Seq[B](value, other), Expression('union))

    def &[B: CanCombine](other: B)(implicit cast: A => B) =
      CanCombine.combine(Seq[B](value, other), Expression('intersection))

    def -[B: CanCombine](other: B)(implicit cast: A => B) =
      CanCombine.combine(Seq[B](value, other), Expression('difference))
  }

  implicit class CanTransform2DOps[A: CanTransform2D](value: A) {
    def translate(offset: Vec2): A =
      CanTransform2D.transform(value, Expression('translate, 'v -> offset)(_))

    def translate(x: Double = 0, y: Double = 0): A =
      value.translate((x, y))

    def rotate(angle: Double, origin: Vec2 = (0, 0)): A =
      withOrigin(origin, CanTransform2D.transform(_, Expression('rotate, 'a -> angle)(_)))

    private def withOrigin(origin: Vec2, operation: A => A) =
      operation(value.translate(-origin)).translate(origin)
  }

  implicit class CanTransform3DOps[A: CanTransform3D](value: A) {
    def translate(offset: Vec3): A =
      CanTransform3D.transform(value, Expression('translate, 'v -> offset)(_))

    def translate(x: Double = 0, y: Double = 0, z: Double = 0): A =
      value.translate((x, y, z))
  }

  implicit class CanExtrudeOps[A, B](value: A)(implicit ce: CanExtrude[A, B], ct: CanTransform3D[B]) {
    def extrude(zmin: Double, zmax: Double) =
      ce.extrude(value, Expression('linear_extrude, 'height -> (zmax - zmin))(_)).translate(z = zmin)
  }
}
