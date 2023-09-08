package com.example.booking.domain.util

import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.widget.EditText

// Гордо украденный у индуса код
class PhoneWatcher(private val editText: EditText) {
    fun setWatcher() {
        editText.addTextChangedListener(object : PhoneNumberFormattingTextWatcher() {
            private var backspacingFlag = false

            private var editedFlag = false

            private var cursorComplement = 0
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                cursorComplement = s.length - editText.selectionStart
                backspacingFlag = count > after
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val string = s.toString()
                val phone = string.replace("\\D".toRegex(), "")

                if (!editedFlag) {
                    if (phone.length >= 6 && !backspacingFlag) {
                        editedFlag = true
                        val ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) +
                                "-" + phone.substring(6)
                        editText.setText(ans)
                        editText.setSelection(
                            editText.text?.length?.minus(
                                cursorComplement
                            ) ?: 0
                        )

                    } else if (phone.length >= 3 && !backspacingFlag) {
                        editedFlag = true
                        val ans = "(" + phone.substring(0, 3) + ") " + phone.substring(3)
                        editText.setText(ans)
                        editText.setSelection(
                            editText.text?.length?.minus(
                                cursorComplement
                            ) ?: 0
                        )
                    }
                } else {
                    editedFlag = false
                }
            }
        })

    }
}