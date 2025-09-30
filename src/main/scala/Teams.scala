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

def editTeams(): Unit = {

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