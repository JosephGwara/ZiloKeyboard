package com.josephgwara.zilokeyboard

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputConnection

class KeyBoardInputMethodService:InputMethodService(),KeyboardView.OnKeyboardActionListener {

    private var keyBoardView : KeyboardView? = null
    private var keyboard:Keyboard? = null

    override fun onCreateInputView(): View {
        keyBoardView = layoutInflater.inflate(R.layout.keyboard_view,null) as KeyboardView
        keyboard = Keyboard(this,R.xml.keys_layout)
        keyBoardView!!.keyboard = keyboard
        keyBoardView!!.setOnKeyboardActionListener(this)
        return keyBoardView!!
    }

    override fun onPress(primaryCode: Int) {

    }

    override fun onRelease(primaryCode: Int) {
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
       val inputConnection:InputConnection = currentInputConnection
        when(primaryCode){
            Keyboard.KEYCODE_DELETE -> {
                inputConnection.deleteSurroundingText(1,0)
            }
            Keyboard.KEYCODE_DONE ->{
                inputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER))
            }
            Keyboard.KEYCODE_SHIFT->{
                //Excluded functionality
            }
            else ->{
                val code :Char = primaryCode.toChar()
                inputConnection.commitText(code.toString(),1)

            }
        }

    }

    override fun onText(text: CharSequence?) {
    }

    override fun swipeLeft() {

    }

    override fun swipeRight() {
    }

    override fun swipeDown() {
    }

    override fun swipeUp() {
    }
}