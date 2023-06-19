package fr.esgi.iabd.data

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ArgumentsParserSpec extends AnyFlatSpec with Matchers {

    class ArgumentsParserTest extends AnyFunSuite {
        test("Parsing valid arguments") {
            val args = Array("--path", "/path/to/file.csv", "--gender", "m")
            val result = ArgumentsParser.parse(args)

            assert(result.isDefined)
            val parsedArgs = result.get
            assert(parsedArgs.path == "/path/to/file.csv")
            assert(parsedArgs.gender.contains("m"))
        }

        test("Parsing missing required argument") {
            val args = Array("--gender", "f")
            val result = ArgumentsParser.parse(args)

            assert(result.isEmpty)
        }

        test("Parsing invalid gender") {
            val args = Array("--path", "/path/to/file.csv", "--gender", "x")
            val result = ArgumentsParser.parse(args)

            assert(result.isEmpty)
        }

}
