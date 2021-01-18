package com.hacybeyker.repository.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suggestion")
open class SuggestionModel(
    @PrimaryKey
    val term: String
) {
    companion object {
        fun toString(suggestionList: List<SuggestionModel>): List<String> {
            val response = arrayListOf<String>()
            for (item in suggestionList)
                response.add(item.term)
            return response
        }
    }
}