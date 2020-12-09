package com.hashedin.util

import com.hashedin.bean.Movies

import java.io.FileNotFoundException
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex

class FileReader {

  def fileContainReading(fileName: String): ListBuffer[Movies] = {
    var moviesDetails = new ListBuffer[Movies]()
    val pattern = new Regex("[a-zA-z\\$]+")
    try {
      val fileSource = Source.fromFile(fileName)
      for (line <- fileSource.getLines.drop(1)) {
        val row = line.split("\t").map(_.trim)
        val budget = pattern.replaceAllIn(row(15), "")
        try {
          moviesDetails += new Movies(row(0), row(1), row(2).toInt, row(3), row(4), row(5).toInt, row(6), row(7), row(8)
            , row(9), row(10), row(11), row(12), row(13), row(14).toInt, if (budget.isBlank) 0 else budget.trim.toLong, row(16), row(17), row(18), if (row(19).isEmpty) 0 else row(19).toInt
            , row(20), row(21))
        } catch {
          case e: NumberFormatException => {
            println(e)
          }
          case f: Exception => {
            println(f)
          }
        }

      }
      fileSource.close()
    } catch {
      case i: FileNotFoundException => {
        throw new FileNotFoundException()
      }
    }
    moviesDetails
  }
}
