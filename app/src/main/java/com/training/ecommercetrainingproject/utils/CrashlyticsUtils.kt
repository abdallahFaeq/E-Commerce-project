package com.training.ecommercetrainingproject.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsUtils {

    const val ADD_TO_CART = "add to cart "
    fun sendLogToCrashlytics(
        mess: String, vararg keys: String
    ) {
        keys.forEach { key ->
            FirebaseCrashlytics.getInstance().setCustomKey(key, mess)
        }

        FirebaseCrashlytics.getInstance().recordException(CustomCrashlyticsLogException(mess))
    }

    /*
    fun sendLogToCrashlytics(
        mess: String, vararg keys: Pair<String, String>
    ) {
        keys.forEach { key ->
            FirebaseCrashlytics.getInstance().setCustomKey(key.first, key.second)
        }
        FirebaseCrashlytics.getInstance().recordException(CustomCrashlyticsLogException(mess))
    }
     */


    inline fun <reified T : Exception> sendLogToCrashlytics(
        mess: String, vararg keys: Pair<String, String>
    ) {
        keys.forEach { key ->
            FirebaseCrashlytics.getInstance().setCustomKey(key.first, key.second)
        }
        val exception = T::class.java.getConstructor(String::class.java).newInstance(mess)
        FirebaseCrashlytics.getInstance().recordException(exception)
    }
}

class CustomCrashlyticsLogException(message: String) : Exception(message)
class AddToCartException(message: String) : Exception(message)