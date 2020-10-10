package com.example.luxtravel.view.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.luxtravel.R
import com.pixplicity.easyprefs.library.Prefs

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val username = findViewById<EditText>(R.id.username)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        var data = Prefs.getString("address", "")

        username.afterTextChanged {
            if (username != null) {
                login.isEnabled = username.text.isNotBlank()
                Prefs.putString("address", username.text.toString())
            }
        }
        username.setText(data.toString())
        login.setOnClickListener {
            if (username.text.isNotBlank()) {
                Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }


}


fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}