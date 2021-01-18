package com.hacybeyker.repository.network.implement

import androidx.paging.PagingSource
import com.hacybeyker.entities.Music
import com.hacybeyker.repository.network.model.response.MusicResponse
import com.hacybeyker.repository.network.services.MusicServices
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import java.io.IOException

private const val MUSIC_STARTING_PAGE_INDEX = 1
private const val MUSIC_LIMIT_DEFAULT = 20

class MusicPagingSource(private val term: String) : PagingSource<Int, Music>(), KoinComponent {

    private val musicServices: MusicServices by inject()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Music> {
        val position = params.key ?: MUSIC_STARTING_PAGE_INDEX
        return try {
            val response =
                musicServices.fetchMusic(term = term, limit = MUSIC_LIMIT_DEFAULT, page = position)
            val musics = MusicResponse.toMusicList(response.body()?.results ?: arrayListOf())
            LoadResult.Page(
                data = musics,
                prevKey = if (position == MUSIC_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (musics.isEmpty()) null else position + 1
            )
        } catch (ex: IOException) {
            LoadResult.Error(ex)
        } catch (ex: HttpException) {
            LoadResult.Error(ex)
        }
    }

}