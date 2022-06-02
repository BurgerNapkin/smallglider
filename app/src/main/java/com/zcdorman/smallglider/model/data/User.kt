package com.zcdorman.smallglider.model.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var userName: String,
    @SerializedName("id") var id: Int,
    @SerializedName("node_id") var nodeId: String? = null,
    @SerializedName("avatar_url") var urlAvatar: String? = null,
    @SerializedName("gravatar_id") var gravatarId: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("html_url") var urlHtml: String? = null,
    @SerializedName("followers_url") var urlFollowers: String? = null,
    @SerializedName("following_url") var urlFollowing: String? = null,
    @SerializedName("gists_url") var urlGists: String? = null,
    @SerializedName("starred_url") var urlStarred: String? = null,
    @SerializedName("subscriptions_url") var urlSubscriptions: String? = null,
    @SerializedName("organizations_url") var urlOrganizations: String? = null,
    @SerializedName("repos_url") var urlRepos: String? = null,
    @SerializedName("events_url") var urlEvents: String? = null,
    @SerializedName("received_events_url") var urlReceivedEvents: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("site_admin") var isSiteAdmin: Boolean? = null
)
