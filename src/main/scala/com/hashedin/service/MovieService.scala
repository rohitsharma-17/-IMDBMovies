package com.hashedin.service

import com.hashedin.bean.Movies
import com.hashedin.util.FileReader

import scala.collection.immutable.ListMap
import scala.collection.mutable.ListBuffer

class MovieService {

  private var moviesDetails: ListBuffer[Movies] = new ListBuffer[Movies]()

  def movieData(fileName : String) {
    val fileReader = new FileReader()
    moviesDetails = fileReader.fileContainReading(fileName)
  }

  def generateReportTitles(directorName: String, fromYear: Int, toYear: Int) {
    var noOutputFlag = true
    for (movie <- moviesDetails) {
      if (movie.getYear() >= fromYear && movie.getYear() <= toYear && movie.getDirector().toLowerCase.contains(directorName.toLowerCase)) {
        println(movie)
        noOutputFlag = false
      }
    }
    if(noOutputFlag == true){
      println("No match data found")
    }
  }

  def generateReportOfEnglishWithUserReview(userReview: Int) {
    val movieReviewList: ListBuffer[Movies] = new ListBuffer[Movies]()
    for (movie <- moviesDetails) {
      if (movie.getLanguage().toLowerCase.contains("english") && movie.getReviewsFromUsers() > userReview) {
        movieReviewList += movie
      }
    }
    var noOutputFlag = true
    val sortedMovieListBaseOnReview = movieReviewList sortWith (_.getReviewsFromUsers() > _.getReviewsFromUsers())
    for (movie <- sortedMovieListBaseOnReview) {
      noOutputFlag = false
      println(movie)
    }
    if(noOutputFlag == true){
      println("No match data found")
    }
  }

  def generateHighestBudgetTitlesForGivenYear(country: String, year: Int) {
    val budgetMovie: ListBuffer[Movies] = new ListBuffer[Movies]()
    var noOutputFlag = true
    for (movie <- moviesDetails) {
      if (country.equalsIgnoreCase(movie.getCountry()) && movie.getYear() == year) {
        budgetMovie += movie
        noOutputFlag = false
      }
    }
    if(noOutputFlag == true){
      println("No match data found")
    }else{
      println(budgetMovie.reduceLeft(maxBudget))
    }
  }

  def generateReportOfLongestTitleDuration(country: String, vote: Int) {
    val movieList: ListBuffer[Movies] = new ListBuffer[Movies]()
    var noOutputFlag = true
    for (movie <- moviesDetails) {
      if (movie.getCountry().toLowerCase.contains(country.toLowerCase()) && movie.getVotes() == vote) {
        noOutputFlag = false
        movieList += movie
      }
    }
    val sortedMovieListBaseOnDuration = movieList sortWith (_.getDuration() > _.getDuration())
    for (movie <- sortedMovieListBaseOnDuration) {
      println(movie)
    }
    if(noOutputFlag == true){
      println("No match data found")
    }
  }

  def generateLanguageWiseReportToCountForBudgetRange(startBudgetRange: Long, endBudgetRange: Long) {
    var budgetMovieMap = Map[String, Int]()
    var noOutputFlag = true
    for (movie <- moviesDetails) {
      if (movie.getBudget() > startBudgetRange && movie.getBudget() > endBudgetRange) {
        noOutputFlag = false
        if (budgetMovieMap.contains(movie.getLanguage().trim)) {
          budgetMovieMap += (movie.getLanguage().trim -> (budgetMovieMap.get(movie.getLanguage().trim).get + 1))
        } else {
          budgetMovieMap += (movie.getLanguage().trim -> 1)
        }
      }
    }
    val sortLanguageBasedOnCount = ListMap(budgetMovieMap.toSeq.sortWith(_._2 > _._2): _*)
    for (e <- sortLanguageBasedOnCount) {
      println("Language:- " + e._1 + " || count:- " + e._2)
    }
    if(noOutputFlag == true){
      println("No match data found")
    }
  }

  def maxBudget(movie1: Movies, movie2: Movies): Movies = if (movie1.getBudget() > movie2.getBudget()) movie1 else movie2
}
