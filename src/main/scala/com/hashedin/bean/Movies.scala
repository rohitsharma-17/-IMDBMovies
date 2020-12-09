package com.hashedin.bean

class Movies(title: String, originalTitle: String, year: Int, datePublished: String, genre: String, duration: Int, country: String, language: String, director: String, writer: String, productionCompany: String, actors: String, description: String, avgVote: String, votes: Int, budget: Long, usaGrossIncome: String, worldWideGrossIncome: String, metaScore: String, reviewsFromUsers: Int, reviewsFromCritics: String, imdbTitleTd: String) {

  def getDirector(): String = {
    director
  }

  def getYear(): Int = {
    year
  }

  def getLanguage(): String = {
    language
  }

  def getReviewsFromUsers(): Int = {
    reviewsFromUsers
  }

  def getCountry(): String = {
    country
  }

  def getVotes(): Int = {
    votes
  }

  def getBudget(): Long = {
    budget
  }

  def getDuration(): Int = {
    duration
  }

  override def toString: String = {
    "imdbTitleTd = " + imdbTitleTd + " , title = " + title + " ,director = " + director + " ,originalTitle = " + originalTitle +
      " ,year = " + year + " ,reviewsFromUsers = " + reviewsFromUsers + " , country = " + country + " , votes = " + votes + " ,budget = " +
      budget + " , duration " + duration + " ,language = " + language
  }
}
