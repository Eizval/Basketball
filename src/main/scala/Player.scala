class Player (var id: Int, var name: String, var playerNumber: Int, var age: Int, var playerType: List[String]) {
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


def deletePlayer(): Unit = {

}
