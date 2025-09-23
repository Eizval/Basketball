import scala.io.StdIn.readLine
import java.io._

class Article(var id: Int, var name: String, var price: Double, var comment: String) {
  override def toString: String =
    s"Article(id= $id, Name= $name, Price = $price, commentary = $comment)"
}

val article1 = new Article(1, "Basketball", 6.50, "Its a Basketball")
val article2 = new Article(2, "T-Shirt", 21.99, "a XXL T-Shirt")

var articles: List[Article] = List(article1, article2)

def getArticle(): List[Article] = {
  articles
}

def createArticle(): Unit = {
 // automatic id
 val newId = if (articles.isEmpty) 1 else articles.map(_.id).max + 1
 val name = readLine("Enter article name: ")
 val price = readLine("Enter price: ").toDouble
 val comment = readLine("Enter comment: ")

 val newArticle = new Article(newId, name, price, comment)

 articles = articles :+ newArticle

 // save article
  saveArticles()
}

def editArticle(): Unit = {

}

def deleteArticle(): Unit = {

}