object Main {

  def main(args: Array[String]): Unit = {
    val reader = new ReadCsv
    val workWithData = new ParsingAndOutputsData
    val unzip = new Unzip

    unzip.gunzip.gunzip("planes_log.csv")

    val listWithRows = reader.readCsvAndAddItToList("planes_log.csv");

    workWithData.outputNonZeroDiff(listWithRows) //Task №1
    workWithData.outputDestFlying(listWithRows) //Task №2

    val weeks = workWithData.getListFromEachWeek(listWithRows)
    workWithData.outputDestForEachWeek(weeks) //Task №3
  }

}
