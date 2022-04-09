package com.zcdorman.smallglider.model.data

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("login")
    val userName : String,
    @SerializedName("type")
    val type : String,
    @SerializedName("site_admin")
    val isSiteAdmin : Boolean,
    @SerializedName("id")
    val id : Int,
    @SerializedName("gravatar_id")
    val gravatarId : String,
    @SerializedName("node_id")
    val nodeId : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("organizations_url")
    val urlOrganizations : String,
    @SerializedName("gists_url")
    val urlGists : String,
    @SerializedName("repos_url")
    val urlRepos : String,
    @SerializedName("following_url")
    val urlFollowing : String,
    @SerializedName("starred_url")
    val urlStarred : String,
    @SerializedName("subscriptions_url")
    val urlSubscriptions : String,
    @SerializedName("received_events_url")
    val urlReceivedEvents : String,
    @SerializedName("avatar_url")
    val urlAvatar : String,
    @SerializedName("events_url")
    val urlEvents : String,
    @SerializedName("html_url")
    val urlHtml : String,
    @SerializedName("followers_url")
    val urlFollowers : String,
)
