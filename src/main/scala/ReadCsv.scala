class ReadCsv {

  def readCsvAndAddItToList(path: String): List[OneFly] = {
    var resultList = List[OneFly]()

    val bufferedSource = io.Source.fromFile(path)
    var x = 0;

    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)

      if (x > 0)
        resultList = new OneFly(cols(0).toInt, cols(1).toInt, cols(2).toInt, cols(3).toInt, cols(4).toInt, cols(5), cols(6), cols(7)) :: resultList
      x += 1
    }

    bufferedSource.close
    return resultList
  }
}
