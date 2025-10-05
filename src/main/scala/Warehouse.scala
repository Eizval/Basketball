import scala.io.StdIn.readLine
import java.io._

class Warehouse(var id: Int, var location: String, var name: String, var articleIds: List[Int]) {
  override def toString: String =
    s"Warehouse(id = $id, Location = $location, Name = $name, Article_id = $articleIds)"
}

// Beispiel für 2 Lager
val warehouse1 = new Warehouse(1, "Bern", "BernerBasketball", Nil)
val warehouse2 = new Warehouse(2, "Earth", "RoyalWarehous", Nil)

var warehouse: List[Warehouse] = List(warehouse1, warehouse2)

// Diese Methode wird später im Main aufgerufen, um alle Lager zu bekommen
def getWarehouse(): List[Warehouse] = warehouse

// Diese Methode wird im Main aufgerufen, um ein neues Warehouse zu erstellen
def createWarehouse(): Unit = {
  // automatische ID für das neue Warehouse
  val newId = if (warehouse.isEmpty) 1 else warehouse.map(_.id).max + 1
  val location = readLine("Enter warehouse Location: ")
  val name = readLine("Enter warehouse name: ")

  val newWarehouse = new Warehouse(newId, location, name, Nil)

  // Füge das neue Warehouse zur Liste hinzu
  warehouse = warehouse :+ newWarehouse

  saveWarehouses()
}

// Speichern der Warehouses in einer Datei
def saveWarehouses(): Unit = {
  val pw = new PrintWriter(new File("warehouses.txt"))
  for (w <- warehouse) {
    pw.println(s"${w.id};${w.location};${w.name};${w.articleIds.mkString(",")}")
  }
  pw.close()
}

// Laden der Warehouses aus einer Datei
def loadWarehouses(): Unit = {
  val file = new File("warehouses.txt")
  if (file.exists()) {
    val lines = scala.io.Source.fromFile(file).getLines()
    warehouse = lines.map { line =>
      val parts = line.split(";")
      val articleIds = if (parts(3).isEmpty) Nil else parts(3).split(",").map(_.toInt).toList
      new Warehouse(parts(0).toInt, parts(1), parts(2), articleIds)
    }.toList
  }
}

// Move Artikel
def moveArticleInWarehouse(): Unit = {
  // Alle Artikel anzeigen (mit ausführlicher Beschreibung)
  println("Verfügbare Artikel:")
  getArticle().foreach(a => println(a.toString))

  var validId = false
  var id = 0

  // Schritt 1: Lager-ID eingeben und prüfen, ob eine Zahl
  while (!validId) {
    val input = readLine("Bitte Lager-ID eingeben: ")
    try {
      id = input.toInt
      if (warehouse.exists(_.id == id)) {
        validId = true
      } else {
        println("Kein Warehouse mit dieser ID.")
      }
    } catch {
      case _: NumberFormatException =>
        println("Bitte eine Zahl eingeben.")
    }
  }

  // Finde das Warehouse
  warehouse.find(_.id == id) match {
    case Some(w) =>
      // Wir duplizieren die IDs in eine lokale Variable
      var updatedArticleIds = w.articleIds
      var done = false
      while (!done) {
        println(s"Aktuelle Artikel-IDs im Warehouse: ${if (updatedArticleIds.isEmpty) "-" else updatedArticleIds.mkString(", ")}")
        val input = readLine("Artikel-ID hinzufügen (oder 'q' zum Beenden): ")
        if (input.toLowerCase == "q") {
          done = true
        } else {
          try {
            val artId = input.toInt
            if (getArticle().exists(_.id == artId)) {
              if (!updatedArticleIds.contains(artId)) {
                updatedArticleIds = updatedArticleIds :+ artId
                println(s"Artikel $artId hinzugefügt.")
              } else {
                println("Artikel ist schon im Warehouse.")
              }
            } else {
              println("Diese Artikel-ID existiert nicht!")
            }
          } catch {
            case _: NumberFormatException =>
              println("Bitte eine Zahl eingeben.")
          }
        }
      }
      // Warehouse-Liste aktualisieren (neues Objekt mit aktualisierter Artikel-Liste)
      warehouse = warehouse.map(wh =>
        if (wh.id == id) new Warehouse(wh.id, wh.location, wh.name, updatedArticleIds)
        else wh
      )
      saveWarehouses()
      println(s"Endgültige Artikel-IDs: ${if (updatedArticleIds.isEmpty) "-" else updatedArticleIds.mkString(", ")}")
    case None =>
      println("Warehouse nicht gefunden.")
  }
}



// delete warehous
def deleteWarehouse(): Unit = {
  var validId = false
  var id = 0

  while (!validId) {
    val input = readLine("Bitte die ID des zu löschenden Warehouses eingeben: ")
    try {
      id = input.toInt
      validId = true
    } catch {
      case _: NumberFormatException =>
        println("Bitte eine Zahl eingeben.")
    }
  }

  val warehouseOpt = warehouse.find(_.id == id)

  warehouseOpt match {
    case Some(w) =>
      if (w.articleIds.isEmpty) {
        // Warehouse löschen
        warehouse = warehouse.filterNot(_.id == id)
        println(s"Warehouse gelöscht: $w")
        saveWarehouses()
      } else {
        println("Warehouse enthält noch Artikel.")
        val input = readLine("Möchten Sie einen Artikel löschen? (yes/no): ")
        if (input.toLowerCase == "yes") {
          var done = false
          while (!done && w.articleIds.nonEmpty) {
            println("Artikel-IDs im Warehouse: " + w.articleIds.mkString(", "))
            val delInput = readLine("Geben Sie die zu löschende Artikel-ID ein (oder 'q' zum Beenden): ")
            if (delInput.toLowerCase == "q") {
              done = true
            } else {
              try {
                val artId = delInput.toInt
                if (w.articleIds.contains(artId)) {
                  w.articleIds = w.articleIds.filterNot(_ == artId)
                  println(s"Artikel $artId entfernt.")
                  saveWarehouses()
                  if (w.articleIds.isEmpty) {
                    println("Alle Artikel entfernt. Warehouse kann jetzt gelöscht werden!")
                    // Warehouse löschen
                    warehouse = warehouse.filterNot(_.id == id)
                    println(s"Warehouse gelöscht: $w")
                    saveWarehouses()
                    done = true
                  }
                } else {
                  println("Diese Artikel-ID ist nicht vorhanden.")
                }
              } catch {
                case _: NumberFormatException =>
                  println("Bitte eine Zahl eingeben.")
              }
            }
          }
        } else {
          println("Warehouse wurde nicht gelöscht.")
        }
      }
    case None =>
      println(s"Kein Warehouse mit ID $id gefunden.")
  }
}
