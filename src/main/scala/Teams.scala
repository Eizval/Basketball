case class Team (val id: Int, val name: String, val teammates: List[Int]){
  override def toString: String =
    s"Teams(id = $id, name = $name, teammates = ${teammates.mkString(", ")})"
}

val team1 = new Team(1, "KTV", List(13))
val team2 = new Team(1, "KTV", List(13,27))
val teams: List[Team] = List(team1,team2)


def getTeams(id: Int): List[Team] = {
  teams.filter(p => p.id == id)
}

def getAllTeams(): List[Team] = {
  teams
}

def editTeams(): Unit = {

}

def deleteTeams(): Unit = {

}

def addTeams(): Unit = {

}