package com.hashedin.service

trait MovieServiceTrait {

  def movieData(fileName: String)

  def generateReportTitles(directorName: String, fromYear: Int, toYear: Int)

  def generateReportOfEnglishWithUserReview(userReview: Int)

  def generateHighestBudgetTitlesForGivenYear(country: String, year: Int)

  def generateReportOfLongestTitleDuration(country: String, vote: Int)

  def generateLanguageWiseReportToCountForBudgetRange(startBudgetRange: Long, endBudgetRange: Long)
}
