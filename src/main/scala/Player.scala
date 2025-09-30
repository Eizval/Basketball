class Player (val id: Int, val name: String, val playerNumber: Int, val age: Int, val playerType: List[String]) {
  override def toString: String =
    s"Player(id=$id, name=$name, number=$playerNumber, age=$age, types=${playerType.mkString(", ")})"
}

val player1 = new Player(13, "Alice", 10, 25, List("Player", "Captain"))
val player2 = new Player(27, "Bob", 5, 28, List("Coach"))
var players: List[Player] = List(player1, player2)



def getPlayer(): List[Player] = {
  players
}


def editPlayer(player: Player): Unit = {
  println(player)
  println("")
  println("Give me the New values like => name: paul, number: 2, age: 17, type: Coach")
//Logic in here

//
  println("")
  println("Edited player")
  println("")
  println(player)
}


def deletePlayer(player: Player): Unit = {
  val deletedPlayer = players.filter(p => p.id == player.id)
  players = players.filter(p => p.id != player.id)
  coloredPrint("Succsesfully deleted", "red")
  coloredPrint(s"\t $deletedPlayer", "red")
  println("")
}

def addPlayer(string: String): Unit = {
  val value = string.split(" ")
  val roles = value.drop(3).toList
  val newPlayer = new Player(players.length +1, value(0), value(1).toInt, value(2).toInt, roles)
  players = players :+ newPlayer
  coloredPrint("Succsesfully added", "green")
  coloredPrint(s"\t ${players.last}", "green")
  println("")
}
