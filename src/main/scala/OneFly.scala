
class OneFly(year: Int, quarter: Int, month: Int, day_of_month: Int, day_of_week: Int, fl_date: String, origin: String, dest: String) {

  override def toString: String = s"${year}, ${quarter}, ${month}, ${day_of_month}, ${day_of_week}, ${fl_date}, ${origin}, ${dest}"

  def getDest = dest

  def getOrigin = origin

  def getFl_date = fl_date

  def getYear = year
}
