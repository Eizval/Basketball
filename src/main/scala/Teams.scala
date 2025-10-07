import scala.io.StdIn.readLine

case class Team (val id: Int, val name: String, val teammates: List[Player]){
  override def toString: String =
    s"Team(id = $id, name = $name, teammates = ${teammates.mkString(", ")})"
}

val team1 = new Team(1, "KTV", getPlayer().filter(p => p.id == 13))
val team2 = new Team(2, "TTV", getPlayer().filter(p => p.id == 13 || p.id == 27))
var teams: List[Team] = List(team1,team2)


// Gives a list of Teams back
def getTeams(): List[Team] = {
  teams
}

// Added recursive function for Teams
def printTeamsRecursive(teams: List[Team]): Unit = {
  if (teams.nonEmpty) {
    coloredPrint(s"${teams.head}", "cyan")
    printTeamsRecursive(teams.tail) // recursive call
  }
}

// Updates a team's name and teammates based on user input (e.g., "name Paul, teammates 2 7 8")
// Keeps old values if new ones aren't provided
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
    pairs.get("teammates") match {
      case Some(teammateStr) if teammateStr.trim.nonEmpty =>
        val ids = teammateStr.trim.split("\\s+").toList.map(_.toInt)
        getPlayer().filter(p => ids.contains(p.id)) // ✅ get List[Player]
      case _ =>
        team.teammates // ✅ keep old teammates if not provided
    }

  val alteredTeam = team.copy(name = newName, teammates = newTeammates)

  teams = teams.map(t => if (t.id == team.id) alteredTeam else t)

  println("")
  coloredPrint("Edited team", "yellow")
  coloredPrint(s"\t $alteredTeam", "yellow")
  println("")
}

// Removes the team that has the same ID as the given one
def deleteTeams(team: Team): Unit = {
  val deletedTeam = teams.filter(p => p.id == team.id)
  teams = teams.filter(p => p.id != team.id)
  coloredPrint("Succsesfully deleted", "red")
  coloredPrint(s"\t $deletedTeam", "red")
  println("")
}

// Adds a new team using the team name and player IDs from the input text
def addTeams(string: String): Unit = {
  val value = string.split(" ")
  val idOfLastTeam = teams.last.id
  val teammateIds = value.drop(1).map(_.toInt).toList
  val teammates = getPlayer().filter(p => teammateIds.contains(p.id))
  val newTeam = new Team(idOfLastTeam + 1, value(0), teammates)
  teams = teams :+ newTeam
  coloredPrint("Succsesfully added", "green")
  coloredPrint(s"\t ${teams.last}", "green")
  println("")
}