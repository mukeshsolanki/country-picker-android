package com.mukesh.countrypicker

import android.content.Context
import android.text.TextUtils
import java.util.*

class Country {
    // region Variables
    private var code: String? = null
    var name: String? = null
    var dialCode: String? = null
    var flag = 0

    // endregion
    // region Getter/Setter
    var currency: String? = null

    // endregion
    // region Constructors
    internal constructor()
    internal constructor(code: String?, name: String?, dialCode: String?, flag: Int, currency: String?) {
        this.code = code
        this.name = name
        this.dialCode = dialCode
        this.flag = flag
        this.currency = currency
    }

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
        if (TextUtils.isEmpty(name)) {
            name = Locale("", code).displayName
        }
    }

    fun loadFlagByCode(context: Context) {
        if (flag != -1) {
            return
        }
        flag = try {
            context.resources
                    .getIdentifier("flag_" + code!!.toLowerCase(Locale.ENGLISH), "drawable",
                            context.packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            -1
        }
    } // endregion
}