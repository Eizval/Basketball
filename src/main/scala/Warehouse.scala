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
