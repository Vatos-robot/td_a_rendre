package fr.esgi.iabd.data

import scopt.OParser

case class Arguments(path: String = "", gender: Option[String] = None)

object ArgumentsParser {
  val builder = OParser.builder[Arguments]

  val parser = {
    import builder._

    OParser.sequence(
      help('h', "help"),
      arg[String]("path")
        .text("Path of csv file to read")
        .action((path, config) => config.copy(path = path))
        opt [String] ('g', "gender")
          .text("Gender used to filter data")
          .action((gender, config) => config.copy(gender = Some(gender)))
          .validate(g =>
            if (g == "m" || g == "f" || g == "u") success
            else failure(s"Unknown gender $g")
          )
    )
  }

  def parse(args: Array[String]): Option[Arguments] =
    OParser.parse(parser, args, Arguments())
}
