import scala.io.StdIn.readLine

@main def main(): Unit = {
  var done = false
  while !done do {
    try
      println("Enter: \n 't' for Teams \n 'p' for player \n 'v' for Verein \n 'l' for Lager \n 'a' for Artikel \n 'q' to quit")

      val input = readLine().charAt(0).toLower
      input match
        case 'q' => done = finish(done)
        case 't' => teamsView()
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
  var stay = true
  while stay do {
    printTeamsRecursive(getTeams())
    println("")
    println("Enter: \n 'e' to edit a Team \n 'd' to delete a Team \n 'a' to add a new Team \n 'q' to quit this window ")
    val text = readLine().charAt(0).toLower
    text match {
      case  'q' => stay = false
      case  'e' => val t = chooseTeam()
        editTeams(t)
      case  'd' => val t = chooseTeam()
        deleteTeams(t)
      case  'a' => newTeam()
    }
  }
}

def chooseTeam(): Team = {
  printTeamsRecursive(getTeams())
  println("Give me the id as a number like '1'")
  val chooseTheTeam = readLine().toInt
  getTeams().filter(p => p.id == chooseTheTeam).head
}

def newTeam(): Unit = {
  println("Give me a new team like 'Name teammates teammates'")
  val team = readLine()
  addTeams(team)
}

def player(): Unit = {
  println("")

  var stay = true
  while stay do {
    getPlayer().foreach(p => coloredPrint(s"$p", "cyan"))
    println("")

    println("Enter: \n 'e' to edit Players \n 'd' to delete a Player \n 'a' to add a new Player \n 'q' to quit this window ")
    val text = readLine().charAt(0).toLower
    text match {
      case 'q' => stay = false
      case 'e' => val p = choosePlayer()
        editPlayer(p)
      case 'd' => val p = choosePlayer()
        deletePlayer(p)
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

def verein(): Unit = {
  println("")

  var stay = true
  while stay do {
    getVerein().foreach(p => coloredPrint(s"$p", "cyan"))
    println("")

    println("Enter: \n 'e' to edit Verein \n 'd' to delete a Verein \n 'a' to add a new Verein \n 'q' to quit this window ")
    val text = readLine().charAt(0).toLower
    text match {
      case 'q' => stay = false
      case 'e' => val p = chooseVerein()
        editVerein(p)
      case 'd' => val p = choosePlayer()
        deletePlayer(p)
      case 'a' => newPlayer()
    }
  }
}

def chooseVerein(): Verein = {
  getVerein().foreach(p => println(p.id + " " + p.name))
  val chooseTheVerein = readLine().toInt
  getVerein().filter(p => p.id == chooseTheVerein).head
}

def lager(): Unit = {
  var done = false
  while !done do {
    getWarehouse().foreach(a => println(a))
    println("Enter: \n 'c' for Create Warehouse \n 'm' for move Article in Warehouse \n 'd' for Delete \n 'q' to quit")

    val input = readLine().charAt(0).toLower
    input match
      case 'c' => createWarehouse()
      case 'm' => moveArticleInWarehouse()   // moveArticleInWarehouse hier aufrufen
      case 'd' => deleteWarehouse()          // deleteWarehouse hier aufrufen
      case 'q' => done = finish(done)
  }
}


def artikel(): Unit = {
  var done = false
  while !done do {
    getArticle().foreach(a => println(a))
    println("Enter: \n 'c' for Create Article \n 'e' for edit Article \n 'd' for Delete \n 'q' to quit")

    val input = readLine().charAt(0).toLower
    input match
      case 'c' => createArticle()
      case 'e' => editArticle()
      case 'd' => deleteArticle()
      case 'q' => done = finish(done)
  }

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

