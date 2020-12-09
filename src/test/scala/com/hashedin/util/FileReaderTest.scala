package com.hashedin.util

import org.scalatest.flatspec.AnyFlatSpec

import java.io.FileNotFoundException

class FileReaderTest extends AnyFlatSpec {

  it should "fileReadingTest" in {
    val fileName = "/home/hasher/Downloads/imdb_movies.csv"
    val fileReader = new FileReader()
    assert(81273 == fileReader.fileContainReading(fileName).length)
  }

  it should "fileNotFoundTest" in {
    val fileName = ""
    val fileReader = new FileReader()
    assertThrows[FileNotFoundException]{
      fileReader.fileContainReading(fileName)
    }
  }

}
