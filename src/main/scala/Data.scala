package fr.esgi.iabd.data

import scala.io.Source

case class Row(userId: String, gender: String, age: Int)

object Row {
  def parse(line: String): Row = {
    val parsedLine = line.split(",").toSeq
    Row(parsedLine(0), parsedLine(1), parsedLine(2).toInt)
  }
}

case class Data(data: Vector[Row]) {

  // Calculate the nubmer of user with or without gender filter
  def nbUser(genderO: Option[String] = None): Int = {
    genderO match {
      case Some(gender) => data.size
      case _            => data.size
    }
  }

  // Calculate mean age of all users with or without gender filter
  def meanAge(genderO: Option[String] = None): Option[Double] = {
    val users = genderO match {
      case Some(gender) => data.filter(row => row.gender == gender)
      case _            => data
    }
    if (users.isEmpty) None
    else
      Some(
        users
          .map(row => row.age)
          .reduce(_ + _) / users.size
      )
  }
}

object Data {
  def parse(lines: Vector[String]): Data = {
    Data(
      lines.map(Row.parse)
    )
  }

  def read(path: String): Data =
    Data.parse(
      Source
        .fromFile(path)
        .getLines()
        .toVector
    )
}
