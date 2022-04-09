package com.zcdorman.smallglider.model.data

import com.google.gson.annotations.SerializedName

/**
 * 詳細ユーザー情報
 */
data class DetailedUser(
    val id: Int,
    @SerializedName("login")
    val nameLogin: String,
    @SerializedName("name")
    val nameFull: String,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    val email: String,
    val bio: String,
    @SerializedName("public_repos")
    val publicReposCount: Int,
    @SerializedName("gravatar_id")
    val avatarId: String,
    val type: String,
    val blog: String,
    val company: String,
    val location: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("site_admin")
    val isSiteAdmin: Boolean,
    @SerializedName("hireable")
    val isHireable: Boolean,
    @SerializedName("following")
    val followingCount: Int,
    @SerializedName("followers")
    val followersCount: Int,
    @SerializedName("organizations_url")
    val urlOrganizations: String,
    @SerializedName("starred_url")
    val urlStarred: String,
    @SerializedName("followers_url")
    val urlFollowers: String,
    @SerializedName("public_gists")
    val publicGistsCount: Int,
    @SerializedName("url")
    val urlUser: String,
    @SerializedName("received_events_url")
    val urlReceivedEvents: String,
    @SerializedName("avatar_url")
    val urlAvatar: String,
    @SerializedName("events_url")
    val urlEvents: String,
    @SerializedName("html_url")
    val urlHtml: String,
    @SerializedName("gists_url")
    val urlGists: String,
    @SerializedName("repos_url")
    val urlRepos: String,
    @SerializedName("following_url")
    val urlFollowing: String,
    @SerializedName("subscriptions_url")
    val urlSubscriptions: String,
    val plan: Plan,
)