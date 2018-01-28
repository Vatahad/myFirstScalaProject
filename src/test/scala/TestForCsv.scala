import org.specs2._

class TestForCsv extends Specification {

  def is =
    s2"""

      Csv file from gzip:

      Must have 471949 rows     $e1
      First year is 2014        $e2
    """

  val readCsv = new ReadCsv
  var csv = readCsv.readCsvAndAddItToList("planes_log.csv")

  def e1 = csv.size must_== (471949)

  def e2 = csv(0).getYear must_== (2014)
}
