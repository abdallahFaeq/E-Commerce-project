package com.training.ecommercetrainingproject.data.sources.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object DataStoreKeys {
    const val E_COMMERCE_PREFERNCES = "e_commerce_prefernces"
    val IS_USER_LOGGED_IN = booleanPreferencesKey("is_user_logged_in")
    val USER_ID = stringPreferencesKey("user_id")
}
val Context.appDataStore : DataStore<Preferences> by preferencesDataStore(DataStoreKeys.E_COMMERCE_PREFERNCES)