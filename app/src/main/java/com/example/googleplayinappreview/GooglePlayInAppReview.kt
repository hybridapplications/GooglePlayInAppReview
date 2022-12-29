package com.example.googleplayinappreview

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewManagerFactory


class GooglePlayInAppReview(activity: Activity, context: Context) {

    val activity = activity
    val context = context

    fun show(){
        val manager = ReviewManagerFactory.create(context)
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // We got the ReviewInfo object
                val reviewInfo = task.result
                val flow = manager.launchReviewFlow(activity, reviewInfo)
                flow.addOnCompleteListener { _ ->
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                }
            }
        }
    }
}