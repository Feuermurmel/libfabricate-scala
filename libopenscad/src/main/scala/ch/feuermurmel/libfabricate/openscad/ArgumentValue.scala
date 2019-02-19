package ch.feuermurmel.libfabricate.openscad

import ch.feuermurmel.libfabricate.vectors.{Vec2, Vec3}

trait ArgumentValue {
  def string: String
}

object ArgumentValue {
  implicit class StringAsArgumentValue(value: String) extends ArgumentValue {
    override def string = {
      val escapedString = value.replaceAll("[\\\"]", "\\\\\\1")

      "\"" + escapedString + "\""
    }
  }

  implicit class IntAsArgumentValue(value: Int) extends ArgumentValue {
    override def string = s"$value"
  }

  implicit class DoubleAsArgumentValue(value: Double) extends ArgumentValue {
    override def string = s"$value"
  }

  implicit class ArgumentValueSeqAsArgumentValue(value: Seq[ArgumentValue]) extends ArgumentValue {
    override def string = s"[${value.map(_.string).mkString(", ")}]"
  }

  implicit class Vec2AsArgumentValue(value: Vec2) extends ArgumentValueSeqAsArgumentValue(Seq(value.x, value.y))

  implicit class Vec3AsArgumentValue(value: Vec3) extends ArgumentValueSeqAsArgumentValue(Seq(value.x, value.y, value.z))
}
