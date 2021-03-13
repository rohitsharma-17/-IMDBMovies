package com.hashedin.service

import com.hashedin.bean.Movies
import com.hashedin.util.FileReader

import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

class MovieService extends MovieServiceTrait {

  private var moviesDetails: ListBuffer[Movies] = null

  def movieData(fileName: String) {
    val fileReader = new FileReader()
    moviesDetails = fileReader.fileContainReading(fileName)
  }

  def generateReportTitles(directorName: String, fromYear: Int, toYear: Int) {
    val movie = moviesDetails.filter(movie => {
      movie.getYear() >= fromYear && movie.getYear() <= toYear && movie.getDirector().toLowerCase.contains(directorName.toLowerCase)
    })
    if (movie == null || movie.isEmpty) {
      println("No match data found")
    } else {
      movie.foreach(println)
    }
  }

  def generateReportOfEnglishWithUserReview(userReview: Int) {
    val movieReviewList = moviesDetails.filter(movie => {
      movie.getLanguage().toLowerCase.contains("english") && movie.getReviewsFromUsers() > userReview
    })
    val sortedMovieListBaseOnReview = movieReviewList sortWith (_.getReviewsFromUsers() > _.getReviewsFromUsers())
    if (sortedMovieListBaseOnReview == null || sortedMovieListBaseOnReview.isEmpty) {
      println("No match data found")
    } else {
      sortedMovieListBaseOnReview.foreach(println)
    }
  }

  def generateHighestBudgetTitlesForGivenYear(country: String, year: Int) {
    val budgetMovie = moviesDetails.filter(movie => {
      country.equalsIgnoreCase(movie.getCountry()) && movie.getYear() == year
    })
    if (budgetMovie == null || budgetMovie.isEmpty) {
      println("No match data found")
    } else {
      println(budgetMovie.reduceLeft(maxBudget))
    }
  }

  def generateReportOfLongestTitleDuration(country: String, vote: Int) {
    val movieList = moviesDetails.filter(movie => {
      movie.getCountry().toLowerCase.contains(country.toLowerCase()) && movie.getVotes() == vote
    })
    val sortedMovieListBaseOnDuration = movieList sortWith (_.getDuration() > _.getDuration())
    if (sortedMovieListBaseOnDuration == null || sortedMovieListBaseOnDuration.isEmpty) {
      println("No match data found")
    } else {
      sortedMovieListBaseOnDuration.foreach(println)
    }
  }

  def generateLanguageWiseReportToCountForBudgetRange(startBudgetRange: Long, endBudgetRange: Long) {
    var budgetMovieMap = Map[String, Int]()
    for (movie <- moviesDetails) {
      if (movie.getBudget() > startBudgetRange && movie.getBudget() > endBudgetRange) {
        if (budgetMovieMap.contains(movie.getLanguage().trim)) {
          budgetMovieMap += (movie.getLanguage().trim -> (budgetMovieMap.get(movie.getLanguage().trim).get + 1))
        } else {
          budgetMovieMap += (movie.getLanguage().trim -> 1)
        }
      }
    }
    val sortLanguageBasedOnCount = ListMap(budgetMovieMap.toSeq.sortWith(_._2 > _._2): _*)
    if (sortLanguageBasedOnCount == null || sortLanguageBasedOnCount.isEmpty) {
      println("No match data found")
    }else{
      for (e <- sortLanguageBasedOnCount) {
        println("Language:- " + e._1 + " || count:- " + e._2)
      }
    }
  }

  def maxBudget(movie1: Movies, movie2: Movies): Movies = if (movie1.getBudget() > movie2.getBudget()) movie1 else movie2
}
