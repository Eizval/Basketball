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

    println("Enter: \n 'e' to edit Players \n 'd' to delete a Player \n 'q' to quit this window")
    val text = readLine().charAt(0).toLower
    text match {
      case 'q' => stay = false
      case 'e' => editPlayer()
      case 'd' => deletePlayer()
    }
  }
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


