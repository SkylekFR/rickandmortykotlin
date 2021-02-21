package com.emilien.rickandmortykotlin.data

object CardRepository {
    //Creating Auth Interceptor to add api_key query in front of all the requests.
    val factory = ApiFactory.cardService


    suspend fun fetchCardList() = factory.getCharactersList().results

    suspend fun fetchCardListFromPage(page: Int) = factory.getCharacterListFromPage(page).results

    suspend fun fetchCharacterFromId(id: Int) = factory.getCharacterFromId(id)


}