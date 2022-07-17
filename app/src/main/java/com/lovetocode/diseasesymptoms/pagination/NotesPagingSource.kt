package com.lovetocode.diseasesymptoms.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lovetocode.diseasesymptoms.interfaces.DataAccessObject
import com.lovetocode.diseasesymptoms.models.ToDoNotesDTO
import kotlinx.coroutines.delay

class NotesPagingSource(var dao:DataAccessObject): PagingSource<Int, ToDoNotesDTO>()
{
    override fun getRefreshKey(state: PagingState<Int, ToDoNotesDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ToDoNotesDTO> {
        val page = params.key ?: 0

        return try {
            val entities = dao.getNotesDataWithPager(params.loadSize, page * params.loadSize)

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}