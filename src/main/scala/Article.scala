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
