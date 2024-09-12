package com.example.hospitalsystem.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceDatabase {

    private var appContext: Context? = null
    private const val SHARED_PREFERENCES_NAME_KEY = "hospital data"
    private const val LOGIN_KEY = "login"
    private const val USER_ACCESS_TOKEN_KEY = "access token"
    private const val USER_ADDRESS_KEY = "address"
    private const val USER_BIRTHDAY_KEY = "birthday"
    private const val USER_EMAIL_KEY = "email"
    private const val USER_FIRST_NAME_KEY = "first name"
    private const val USER_LAST_NAME_KEY = "last name"
    private const val USER_GENDER_KEY = "gender"
    private const val USER_ID_KEY = "id"
    private const val USER_MOBILE_KEY = "mobile"
    private const val USER_SPECIALIST_KEY = "specialist"
    private const val USER_TYPE_KEY = "type"
    private const val USER_STATUS_KEY = "status"


    fun initSharedPrefDatabase(context: Context?) {
        appContext = context
    }

    private fun getSharedPreference(): SharedPreferences {
        return appContext!!.getSharedPreferences(SHARED_PREFERENCES_NAME_KEY, Context.MODE_PRIVATE)
    }

    private fun setPreference(key: String, value: String) {
        val editor = getSharedPreference().edit()
        editor.putString(key,value)
        editor.apply()
    }

    private fun getPreference(key: String, defaultValue: String): String {
        return getSharedPreference().getString(key, defaultValue)!!
    }

    fun setAccessToken(accessToken: String) {
        setPreference(USER_ACCESS_TOKEN_KEY, accessToken)
    }

    fun getAccessToken(): String {
        return getPreference(USER_ACCESS_TOKEN_KEY, "default access token")
    }

    fun setAddress(address: String) {
        setPreference(USER_ADDRESS_KEY, address)
    }

    fun getAddress(): String {
        return getPreference(USER_ADDRESS_KEY, "default address")
    }

    fun setBirthday(birthday: String) {
        setPreference(USER_BIRTHDAY_KEY, birthday)
    }

    fun getBirthday(): String {
        return getPreference(USER_BIRTHDAY_KEY, "default birthday")
    }

    fun setEmail(email: String) {
        setPreference(USER_EMAIL_KEY, email)
    }

    fun getEmail(): String {
        return getPreference(USER_EMAIL_KEY, "default email")
    }

    fun setFirstName(firstName: String) {
        setPreference(USER_FIRST_NAME_KEY, firstName)
    }

    fun getFirstName(): String {
        return getPreference(USER_FIRST_NAME_KEY, "default first name")
    }

    fun setLastName(firstName: String) {
        setPreference(USER_LAST_NAME_KEY, firstName)
    }

    fun getLastName(): String {
        return getPreference(USER_LAST_NAME_KEY, "default last name")
    }

    fun setGender(firstName: String) {
        setPreference(USER_GENDER_KEY, firstName)
    }

    fun getGender(): String {
        return getPreference(USER_GENDER_KEY, "default gender")
    }

    fun setId(id: Int) {
        val editor = getSharedPreference().edit()
        editor.putInt(USER_ID_KEY, id)
        editor.apply()
    }

    fun setMobile(mobile: String) {
        setPreference(USER_MOBILE_KEY, mobile)
    }

    fun getMobile(): String {
        return getPreference(USER_MOBILE_KEY, "default mobile")
    }

    fun setSpecialist(specialist: String) {
        setPreference(USER_SPECIALIST_KEY, specialist)
    }

    fun getSpecialist(): String {
        return getPreference(USER_SPECIALIST_KEY, "default specialist")
    }

    fun setType(type: String) {
        setPreference(USER_TYPE_KEY, type)
    }

    fun getType(): String {
        return getPreference(USER_TYPE_KEY, "default type")
    }

    fun setStatus(status: String) {
        setPreference(USER_STATUS_KEY, status)
    }

    fun getStatus(): String {
        return getPreference(USER_STATUS_KEY, "default status")
    }

    fun setLogin(login: Boolean) {
        val editor = getSharedPreference().edit()
        editor.putBoolean(LOGIN_KEY, login)
        editor.apply()
    }

    fun getLogin(): Boolean {
        return getSharedPreference().getBoolean(LOGIN_KEY, false)
    }
}