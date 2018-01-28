import java.io.{BufferedWriter, File, FileWriter}
import java.text.SimpleDateFormat
import java.util.Calendar

import scala.collection.mutable

class ParsingAndOutputsData {

  def getListFromEachWeek(list: List[OneFly]): List[List[OneFly]] = {
    val sdf = new SimpleDateFormat("yyyy-mm-dd")
    var firstWeek = List[OneFly]()
    var secondWeek = List[OneFly]()
    var thirdWeek = List[OneFly]()
    var fourthWeek = List[OneFly]()
    var fivesWeek = List[OneFly]()
    var weeks = List[List[OneFly]]()

    for (row <- list) {
      val date = sdf.parse(row.getFl_date)
      val cal = Calendar.getInstance()
      cal.setTime(date)
      val week = cal.get(Calendar.WEEK_OF_MONTH)

      week match {
        case 1 => firstWeek = row :: firstWeek
        case 2 => secondWeek = row :: secondWeek
        case 3 => thirdWeek = row :: thirdWeek
        case 4 => fourthWeek = row :: fourthWeek
        case 5 => fivesWeek = row :: fivesWeek
      }

    }

    weeks = firstWeek :: weeks
    weeks = secondWeek :: weeks
    weeks = thirdWeek :: weeks
    weeks = fourthWeek :: weeks
    weeks = fivesWeek :: weeks

    return weeks
  }

  def outputDestForEachWeek(weeks: List[List[OneFly]]): Unit = {
    val file = new File("totalNumberOfPlanesForEachWeek.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    var count = 1;

    for (numberOfWeek <- weeks) {
      val oneWeekMap = getMapDest(numberOfWeek)

      bw.write(s"Week ${count}\n")

      for ((k, v) <- oneWeekMap) {
        bw.write(s"\t${k} : ${v}\n")
      }
      bw.write("\n\n")
      count += 1
    }
    bw.close()
  }

  def outputDestFlying(list: List[OneFly]): mutable.Map[String, Int] = {
    val map = getMapDest(list)

    val file = new File("totalNumberOfPlanes.txt")
    val bw = new BufferedWriter(new FileWriter(file))

    for ((k, v) <- map) {
      bw.write(k + ": " + v + "\n")
    }
    bw.close()
    return map
  }

  def outputNonZeroDiff(list: List[OneFly]): Unit = {
    val mapDest = getMapDest(list)
    val mapOrigin = getMapOrigin(list)
    var resultMap = scala.collection.mutable.Map("ValueForCreateMap" -> 0)

    for ((x, y) <- mapDest) {
      val diff = y - mapOrigin(x)

      if (diff != 0) {
        resultMap += x -> diff
      }
    }
    resultMap -= ("ValueForCreateMap")

    val file = new File("nonZeroDifference.txt")
    val bw = new BufferedWriter(new FileWriter(file))

    for ((k, v) <- resultMap) {
      bw.write(k + ": " + v + "\n")
    }
    bw.close()
  }

  def getMapDest(list: List[OneFly]): mutable.Map[String, Int] = {
    var map = scala.collection.mutable.Map("ValueForCreateMap" -> 0)

    for (each <- list) {
      var dest = each.getDest.replaceAll("\"", "")
      if (map.contains(dest)) {
        map += (dest) -> (map(dest) + 1)
      } else {
        map += (dest -> 1)
      }
    }
    map -= ("ValueForCreateMap")
  }

  def getMapOrigin(list: List[OneFly]): mutable.Map[String, Int] = {
    var map = scala.collection.mutable.Map("ValueForCreateMap" -> 0)

    for (each <- list) {
      var origin = each.getOrigin.replaceAll("\"", "")
      if (map.contains(origin)) {
        map += (origin) -> (map(origin) + 1)
      } else {
        map += (origin -> 1)
      }
    }
    map -= ("ValueForCreateMap")
  }
}
