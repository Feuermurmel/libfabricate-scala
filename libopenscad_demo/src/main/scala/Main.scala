import java.nio.file.Paths

import ch.feuermurmel.libfabricate.openscad._

object Main extends App {
  def main(): Unit = {
    val footprint1 = circle(20) - rectangle((0, 0), 10, 10)
    val footprint2 = rectangle((-10, 0), 10, 10)
    val tower = footprint1.extrude(0, 5) | footprint2.extrude(0, 15)

    writeToOpenSCADFile(tower, Paths.get("libopenscad_demo/output/example.scad"))
  }

  main()
}
