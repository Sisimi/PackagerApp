package com.example.packagerapp.services

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseHelper(context: Context)
{
    private var firebaseAnalitics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    fun logStatusEventToFireBase(event: String, origin:String, item:String)
    {
        var bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ORIGIN, origin)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item)
        bundle.putString(FirebaseAnalytics.Param.VALUE, "1")
        firebaseAnalitics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundle)
    }

}