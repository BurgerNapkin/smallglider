package com.zcdorman.smallglider.model.data

import com.google.gson.annotations.SerializedName

/**
 * ユーザーのPlan
 */
data class Plan(
    @SerializedName("private_repos")
    val privateReposCount: Int,
    val name: String,
    @SerializedName("collaborators")
    val collaboratorsCount: Int,
    val space: Int
)