import org.specs2._

class CheckTotalNumbersOfPlanes extends Specification {
  def is = s2"""

 Checking total numbers of planes
   |Number of planes for SFO equal 13539     $e1
   |Number of planes for CID equal 439       $e2
   |Number of planes for BOS equal 439       $e3
   |Number of planes for DBQ equal 8283      $e4
   |Number of planes for ADK not equal 0     $e5
                                 """

  val readCsv = new ReadCsv
  val parseCsv = new ParsingAndOutputsData
  val csv = readCsv.readCsvAndAddItToList("planes_log.csv")
  val totalNumberPlanes = parseCsv.getMapDest(csv)


  def e1 = totalNumberPlanes.get("SFO").get must_==(13539)
  def e2 = totalNumberPlanes.get("CID").get must_==(439)
  def e3 = totalNumberPlanes.get("BOS").get must_==(8283)
  def e4 = totalNumberPlanes.get("DBQ").get must_==(45)
  def e5 = totalNumberPlanes.get("ADK").get must_!=(0)

}