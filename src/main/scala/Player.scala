class Player (var id: Int, var name: String, var playerNumber: Int, var age: Int, var playerType: List[String]) {
  override def toString: String =
    s"Player(id=$id, name=$name, number=$playerNumber, age=$age, types=${playerType.mkString(", ")})"
}

val player1 = new Player(1, "Alice", 10, 25, List("Player", "Captain"))
val player2 = new Player(2, "Bob", 5, 28, List("Coach"))
var players: List[Player] = List(player1, player2)



def getPlayer(): List[Player] = {
  players
}


def editPlayer(): Unit = {

}


def deletePlayer(): Unit = {

}
