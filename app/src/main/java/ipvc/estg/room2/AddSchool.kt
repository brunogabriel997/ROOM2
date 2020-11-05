package ipvc.estg.room2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class AddSchool : AppCompatActivity() {

    private lateinit var schoolText: EditText
    private lateinit var distritText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_school)

        schoolText = findViewById(R.id.school)
        distritText = findViewById(R.id.distrit)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(schoolText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_SCHOOL, schoolText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DISTRIT, distritText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_SCHOOL = "com.example.android.school"
        const val EXTRA_REPLY_DISTRIT = "com.example.android.distrit"
    }
}