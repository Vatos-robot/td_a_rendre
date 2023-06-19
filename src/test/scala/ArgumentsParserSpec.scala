package fr.esgi.iabd.data

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ArgumentsParserSpec extends AnyFlatSpec with Matchers {

    class ArgumentsParserTest extends AnyFunSuite {
    
        test("Parsing valid arguments with gender 'm'") {
            val args = Array("--path", "/path/to/file.csv", "--gender", "m")
            val result = ArgumentsParser.parse(args)

            assert(result.isDefined)
            val parsedArgs = result.get
            assert(parsedArgs.path == "/path/to/file.csv")
            assert(parsedArgs.gender.contains("m"))
    }

        test("Parsing valid arguments with gender 'f'") {
            val args = Array("--path", "/path/to/file.csv", "--gender", "f")
            val result = ArgumentsParser.parse(args)

            assert(result.isDefined)
            val parsedArgs = result.get
            assert(parsedArgs.path == "/path/to/file.csv")
            assert(parsedArgs.gender.contains("f"))
    }

    test("Parsing valid arguments with gender 'u'") {
        val args = Array("--path", "/path/to/file.csv", "--gender", "u")
        val result = ArgumentsParser.parse(args)

        assert(result.isDefined)
        val parsedArgs = result.get
        assert(parsedArgs.path == "/path/to/file.csv")
        assert(parsedArgs.gender.contains("u"))
    }

    test("Parsing missing path argument") {
        val args = Array("--gender", "m")
        val result = ArgumentsParser.parse(args)

        assert(result.isEmpty)
    }

    test("Parsing invalid gender argument") {
        val args = Array("--path", "/path/to/file.csv", "--gender", "x")
        val result = ArgumentsParser.parse(args)

        assert(result.isEmpty)
    }

}


class TestDataSpec extends AnyFunSuite {
  // Les tests seront ajoutés ici
}
