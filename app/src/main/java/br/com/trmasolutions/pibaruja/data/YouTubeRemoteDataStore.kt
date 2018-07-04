package br.com.trmasolutions.pibaruja.data

import br.com.trmasolutions.pibaruja.domain.services.network.RemoteApiService
import br.com.trmasolutions.pibaruja.model.YouTubeResponse
import io.reactivex.Single

class YouTubeRemoteDataStore {
    fun getYouTubeVideos(channelId: String, order: String, part: String, pageToken: String, key: String): Single<YouTubeResponse> {
        return RemoteApiService.getInstance().getApiServiceYouTube().searchYouTubeVideos(channelId, order, part, pageToken, key)
    }
}