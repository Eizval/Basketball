case class Verein(val id: Int, val name: String, val teamIds: List[Int], val warehouseId: Int) {
  override def toString: String =
    s"Verein(id = $id, name = $name, teamIds = ${teamIds.mkString(", ")}, warehouseId = $warehouseId)"
}

val verein1 = new Verein(1, "Swiss Hoopers", teams.filter(t => t.name.contains("KTV")).map(_.id), warehouse.head.id)
val verein2 = new Verein(2, "Basketball United", teams.filter(t => t.name.contains("TTV")).map(_.id), warehouse.last.id)

var vereine: List[Verein] = List(verein1, verein2)


def getVerein(): List[Verein] = {
  vereine
}
