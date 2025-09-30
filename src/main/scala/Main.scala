import scala.io.StdIn.readLine

@main def main(): Unit = {
  var done = false
  while !done do {
    try
      println("Enter: \n 't' for Teams \n 'm' for Matches \n 'p' for player \n 'v' for Verein \n 'l' for Lager \n 'a' for Artikel \n 'q' to quit")

      val input = readLine().charAt(0).toLower
      input match
        case 'q' => done = finish(done)
        case 't' => teamsView()
        case 'm' => match_history()
        case 'p' => player()
        case 'v' => verein()
        case 'l' => lager()
        case 'a' => artikel()

    catch
        {
          case e: Exception =>
           println(s"Es ist ein Fehler aufgetreten: ${e.getMessage}")
           println("Stelle sicher das du die richtigen Eingabewerte eingiebst")
    }
  }
}

def teamsView(): Unit = {
  println("teams")
}


def match_history(): Unit = {
  println("match")
}


def player(): Unit = {
  println("")

  var stay = true
  while stay do {
    getPlayer().foreach(p => println(p))
    println("")

    println("Enter: \n 'e' to edit Players \n 'd' to delete a Player \n 'a' to add a new Player \n 'q' to quit this window ")
    val text = readLine().charAt(0).toLower
    text match {
      case 'q' => stay = false
      case 'e' => val p = choosePlayer()
        editPlayer(p)
      case 'd' => delPlayer()
      case 'a' => newPlayer()
    }
  }
}

def choosePlayer(): Player = {
  getPlayer().foreach(p => println(p.id + " " + p.name))
  val chooseThePlayer = readLine().toInt
  getPlayer().filter(p => p.id == chooseThePlayer).head
}

def newPlayer(): Unit = {
  println("Give me a new player like 'Name PlayerNumber age Role Role'")
  val player = readLine()
  addPlayer(player)
}

def delPlayer(): Unit = {
  println("Give me Player id to delete that Player, like: 1")
  getPlayer().foreach(p => println(p))
  val toDeletePlayer = readLine()
  deletePlayer(toDeletePlayer.toInt)
}


def verein(): Unit = {
  println("verein")
}


def lager(): Unit = {
  println("lager")
}


def artikel(): Unit = {
  println("artikel")
}


def finish(done: Boolean): Boolean = {
  println("Good Bye")
  true
}

def coloredPrint(text: String, color: String): Unit = {
  val colorCode = color.toLowerCase match {
    case "black"   => "\u001b[30m"
    case "red"     => "\u001b[31m"
    case "green"   => "\u001b[32m"
    case "yellow"  => "\u001b[33m"
    case "blue"    => "\u001b[34m"
    case "magenta" => "\u001b[35m"
    case "cyan"    => "\u001b[36m"
    case "white"   => "\u001b[37m"
    case _         => "\u001b[0m" // Standardfarbe bei ungültiger Eingabe
  }

  val reset = "\u001b[0m"
  println(s"$colorCode$text$reset")
}

