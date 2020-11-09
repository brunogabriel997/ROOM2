package ipvc.estg.room2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class EditSchool : AppCompatActivity() {

    private lateinit var schoolText: EditText
    private lateinit var distritText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_school)

        schoolText = findViewById(R.id.school)
        distritText = findViewById(R.id.distrit)

        val intentes = intent.extras
        var ids = -10
        if (intentes != null) {
            var escolas = intent.getStringExtra("Escola")
            var distritos = intent.getStringExtra("Distrito")
            ids = intent.getIntExtra("Id", -10)

            schoolText.setText(escolas)
            distritText.setText(distritos)


        }

        val button = findViewById<Button>(R.id.button_save)
        val delete = findViewById<Button>(R.id.button_delete)

        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(schoolText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                if (ids != -10) replyIntent.putExtra(EXTRA_REPLY_ID, ids)
                replyIntent.putExtra(EXTRA_REPLY_SCHOOL, schoolText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DISTRIT, distritText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
        delete.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(schoolText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                if (ids != -10) replyIntent.putExtra(EXTRA_REPLY_ID, ids)
                replyIntent.putExtra(EXTRA_REPLY_DELETE, "delete")
                replyIntent.putExtra(EXTRA_REPLY_SCHOOL, schoolText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DISTRIT, distritText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_REPLY_ID = "com.example.android.id"
        const val EXTRA_REPLY_SCHOOL = "com.example.android.school"
        const val EXTRA_REPLY_DISTRIT = "com.example.android.distrit"
        const val EXTRA_REPLY_DELETE = "com.example.android.delete"
    }
}