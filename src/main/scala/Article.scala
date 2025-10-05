import scala.io.StdIn.readLine
import java.io._

class Article(var id: Int, var name: String, var price: Double, var comment: String) {
  override def toString: String =
    s"Article(id= $id, Name= $name, Price = $price, commentary = $comment)"
}

// Beispielartikel
val article1 = new Article(1, "Basketball", 6.50, "It's a Basketball")
val article2 = new Article(2, "T-Shirt", 21.99, "a XXL T-Shirt")

var articles: List[Article] = List(article1, article2)

def getArticle(): List[Article] = {
  articles
}

// Artikel erstellen
def createArticle(): Unit = {
  val newId = if (articles.isEmpty) 1 else articles.map(_.id).max + 1
  val name = readLine("Enter article name: ")
  val price = readPrice()
  val comment = readLine("Enter comment: ")

  val newArticle = new Article(newId, name, price, comment)

  articles = articles :+ newArticle
  saveArticles()
}

// Speichern der Artikel
def saveArticles(): Unit = {
  val pw = new PrintWriter(new File("articles.txt"))
  for (a <- articles) {
    pw.println(s"${a.id};${a.name};${a.price};${a.comment}")
  }
  pw.close()
}

// Preis lesen (nur Zahlen)
def readPrice(): Double = {
  var valid = false
  var price = 0.0

  while (!valid) {
    val input = readLine("Enter price: ")
    try {
      price = input.toDouble
      valid = true
    } catch {
      case _: NumberFormatException =>
        println("Invalid input. Please enter numbers only.")
    }
  }

  price
}


// Artikel beim Start laden
def loadArticles(): Unit = {
  val file = new File("articles.txt")
  if (file.exists()) {
    val lines = scala.io.Source.fromFile(file).getLines()
    articles = lines.map { line =>
      val parts = line.split(";")
      new Article(parts(0).toInt, parts(1), parts(2).toDouble, parts(3))
    }.toList
  }
}

def editArticle(): Unit = {
  var validId = false
  var id = 0

  // Solange fragen, bis eine gültige Zahl eingegeben wird
  while (!validId) {
    println("Enter the ID of the article you want to edit:")
    val input = readLine()
    try {
      id = input.toInt
      validId = true
    } catch {
      case _: NumberFormatException =>
        println("Invalid input. Please enter a valid number for the ID.")
    }
  }

  // Suche den Artikel mit der ID
  val articleOpt = articles.find(_.id == id)

  articleOpt match {
    case Some(article) =>
      println(s"Editing Article: ${article.toString}")
      val newName = readLine(s"Enter new name (Current: ${article.name}): ")
      val newPrice = readPrice()
      val newComment = readLine(s"Enter new comment (Current: ${article.comment}): ")

      // Artikel bearbeiten
      article.name = newName
      article.price = newPrice
      article.comment = newComment

      println(s"Article updated: ${article.toString}")

      // Änderungen speichern
      saveArticles()

    case None =>
      println(s"No article found with ID $id.")
  }
}

def deleteArticle(): Unit = {
  var validId = false
  var id = 0

  // Solange fragen, bis eine gültige Zahl eingegeben wird
  while (!validId) {
    println("Enter the ID of the article you want to delete:")
    val input = readLine()
    try {
      id = input.toInt
      validId = true
    } catch {
      case _: NumberFormatException =>
        println("Invalid input. Please enter a valid number for the ID.")
    }
  }

  // Suche den Artikel mit der ID
  val articleOpt = articles.find(_.id == id)

  articleOpt match {
    case Some(article) =>
      // Artikel löschen
      articles = articles.filterNot(_.id == id)
      println(s"Article deleted: $article")

      // Änderungen speichern
      saveArticles()

    case None =>
      println(s"No article found with ID $id.")
  }
}