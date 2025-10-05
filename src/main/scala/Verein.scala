import scala.io.StdIn.readLine

case class Verein(val id: Int, val name: String, val teamIds: List[Int], val warehouseIds: List[Int]) {
  override def toString: String =
    s"Verein(id = $id, name = $name, teamIds = ${teamIds.mkString(", ")}, warehouseId = $warehouseIds)"
}

val verein1 = new Verein(1, "Swiss Hoopers", teams.filter(t => t.name.contains("KTV")).map(_.id), List(warehouse.head.id))
val verein2 = new Verein(2, "Basketball United", teams.filter(t => t.name.contains("TTV")).map(_.id), List(warehouse.last.id))

var vereine: List[Verein] = List(verein1, verein2)


def getVerein(): List[Verein] = {
  vereine
}


def editVerein(verein: Verein): Unit = {
  println(verein)
  println("")
  println("Give me the new values like => name NewName, teamIds 1 2 3, warehouseIds 1 2")
  val text  = readLine()
  val value = text.split(",").map(_.trim).toList

  val pairs: Map[String, String] =
    value.flatMap { pair =>
      pair.split("\\s+", 2) match {
        case Array(k, v) => Some(k.toLowerCase -> v)
        case Array(k)    => Some(k.toLowerCase -> "")
        case _           => None
      }
    }.toMap

  val newName = pairs.getOrElse("name", verein.name)

  val newTeamIds =
    pairs.get("teamids")
      .map(_.trim)
      .filter(_.nonEmpty)
      .map(_.split("\\s+").toList.map(_.toInt))
      .getOrElse(verein.teamIds)

  val newWarehouseIds =
    pairs.get("warehouseids")
      .map(_.trim)
      .filter(_.nonEmpty)
      .map(_.split("\\s+").toList.map(_.toInt))
      .getOrElse(verein.warehouseIds)

  val alteredVerein = verein.copy(
    name = newName,
    teamIds = newTeamIds,
    warehouseIds = newWarehouseIds
  )

  vereine = vereine.map(v => if (v.id == verein.id) alteredVerein else v)

  println("")
  coloredPrint("Edited Verein", "yellow")
  coloredPrint(s"\t $alteredVerein", "yellow")
  println("")
}