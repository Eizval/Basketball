import scala.io.StdIn.readLine

@main def main(): Unit = {
  var done = false
  while !done do {
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
  }
}

def teamsView(): Unit = {
  println("teams")
}


def match_history(): Unit = {
  println("match")
}


def player(): Unit = {

}


def verein(): Unit = {
  println("verein")
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


