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


class NbUserSpec extends FlatSpec with Matchers {
  // Données de test
  val data = List("John", "Jane", "Alex", "Emily")

  // Test pour le cas où il n'y a pas de filtre sur le genre
  "nbUser" should "retourner le nombre total d'utilisateurs" in {
    val result = nbUser(None)
    result should be (data.size)
  }
}


class MeanAgeSpec extends FlatSpec with Matchers {
  // Données de test
  val data = List(
    User("John", "Male", 25),
    User("Jane", "Female", 30),
    User("Alex", "Male", 35),
    User("Emily", "Female", 40)
  )

  case class User(name: String, gender: String, age: Int)

  // Test pour le cas où il n'y a pas de filtre sur le genre
  "meanAge" should "retourner la moyenne d'âge de tous les utilisateurs" in {
    val result = meanAge(None)
    result should be (Some(32.5)) // Moyenne des âges (25 + 30 + 35 + 40) / 4 = 32.5
  }

  // Test pour le cas où le filtre est appliqué sur le genre "Male"
  it should "retourner la moyenne d'âge des utilisateurs de genre 'Male'" in {
    val result = meanAge(Some("Male"))
    result should be (Some(30.0)) // Moyenne des âges (25 + 35) / 2 = 30.0
  }

  // Test pour le cas où le filtre est appliqué sur le genre "Female"
  it should "retourner la moyenne d'âge des utilisatrices de genre 'Female'" in {
    val result = meanAge(Some("Female"))
    result should be (Some(35.0)) // Moyenne des âges (30 + 40) / 2 = 35.0
  }

  // Test pour le cas où aucun utilisateur ne correspond au filtre
  it should "retourner None si aucun utilisateur ne correspond au filtre" in {
    val result = meanAge(Some("Other"))
    result should be (None)
  }
}