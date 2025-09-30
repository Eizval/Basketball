import scala.io.StdIn.readLine

case class Team (val id: Int, val name: String, val teammates: List[Int]){
  override def toString: String =
    s"Team(id = $id, name = $name, teammates = ${teammates.mkString(", ")})"
}

val team1 = new Team(1, "KTV", List(13))
val team2 = new Team(2, "TTV", List(13,27))
var teams: List[Team] = List(team1,team2)



def getTeams(): List[Team] = {
  teams
}

def editTeams(team: Team): Unit = {
  println(team)
  println("")
  println("Give me the New values like => name Paul, teammates 2 7 8 9")
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

  val newName = pairs.getOrElse("name", team.name)

  val newTeammates =
    pairs.get("teammates")
      .map(_.trim)
      .filter(_.nonEmpty)
      .map(_.split("\\s+").toList.map(_.toInt))
      .getOrElse(team.teammates)

  val alteredTeam = team.copy(name = newName, teammates = newTeammates)

  teams = teams.map(t => if (t.id == team.id) alteredTeam else t)

  println("")
  coloredPrint("Edited team", "yellow")
  coloredPrint(s"\t $alteredTeam", "yellow")
  println("")
}


def deleteTeams(team: Team): Unit = {
  val deletedTeam = teams.filter(p => p.id == team.id)
  teams = teams.filter(p => p.id != team.id)
  coloredPrint("Succsesfully deleted", "red")
  coloredPrint(s"\t $deletedTeam", "red")
  println("")
}

def addTeams(string: String): Unit = {
  val value = string.split(" ")
  val teammates = value.drop(1).map(t => t.toInt).toList
  val newTeam = new Team(teams.length + 1, value(0), teammates)
  teams = teams :+ newTeam
  coloredPrint("Succsesfully added", "green")
  coloredPrint(s"\t ${teams.last}", "green")
  println("")
}