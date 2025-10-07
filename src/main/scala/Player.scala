import scala.io.StdIn.readLine

case class Player (val id: Int, val name: String, val playerNumber: Int, val age: Int, val playerType: List[String]) {
  override def toString: String =
    s"Player(id=$id, name=$name, number=$playerNumber, age=$age, types=${playerType.mkString(", ")})"
}

val player1 = new Player(13, "Alice", 10, 25, List("Player", "Captain"))
val player2 = new Player(27, "Bob", 5, 28, List("Coach"))
var players: List[Player] = List(player1, player2)


// Returns the Player list
def getPlayer(): List[Player] = {
  players
}

// Request to give the parameters of the new Player
def editPlayer(player: Player): Unit = {
  println(player)
  println("")
  println("Give me the New values like => name paul, number 2, age 17, type Coach")
  val text = readLine()
  val value = text.split(",").map(_.trim).toList

  val pairs: Map[String, String] = value.map { pair =>
    val Array(k, v) = pair.split(" ").map(_.trim)
    (k.toLowerCase, v)
  }.toMap

  val newName = pairs.getOrElse("name", player.name)
  val newNumber = pairs.get("number").map(_.toInt).getOrElse(player.playerNumber)
  val newAge = pairs.get("age").map(_.toInt).getOrElse(player.age)
  val newType = pairs.get("type").map(t => List(t)).getOrElse(player.playerType)

  val alteredPlayer = player.copy(
    name = newName,
    playerNumber = newNumber,
    age = newAge,
    playerType = newType
  )

  // Spieler in Liste ersetzen
  players = players.map { p =>
    if (p.id == player.id) alteredPlayer else p
  }

  println("")
  coloredPrint("Edited player", "yellow")
  coloredPrint(s"\t $player", "yellow")
  println("")
}

// You give him a Player and it replaces the list without that player
def deletePlayer(player: Player): Unit = {
  val deletedPlayer = players.filter(p => p.id == player.id)
  players = players.filter(p => p.id != player.id)
  coloredPrint("Succsesfully deleted", "red")
  coloredPrint(s"\t $deletedPlayer", "red")
  println("")
}

// You give a specific player which will be altered to the parameters your about to type in
def addPlayer(string: String): Unit = {
  val value = string.split(" ")
  val roles = value.drop(3).toList
  val newPlayer = new Player(players.length +1, value(0), value(1).toInt, value(2).toInt, roles)
  players = players :+ newPlayer
  coloredPrint("Succsesfully added", "green")
  coloredPrint(s"\t ${players.last}", "green")
  println("")
}
