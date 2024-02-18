import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var studentIdEditText: EditText
    private lateinit var studentNameEditText: EditText
    private lateinit var studentAgeEditText: EditText
    private lateinit var studentAddressEditText: EditText
    private lateinit var studentEmailEditText: EditText
    private lateinit var registerButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentIdEditText = findViewById(R.id.studentIdEditText)
        studentNameEditText = findViewById(R.id.studentNameEditText)
        studentAgeEditText = findViewById(R.id.studentAgeEditText)
        studentAddressEditText = findViewById(R.id.studentAddressEditText)
        studentEmailEditText = findViewById(R.id.studentEmailEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val studentId = studentIdEditText.text.toString()
            val studentName = studentNameEditText.text.toString()
            val studentAge = studentAgeEditText.text.toString().toInt()
            val studentAddress = studentAddressEditText.text.toString()
            val studentEmail = studentEmailEditText.text.toString()

            val database = FirebaseDatabase.getInstance()
            val studentsRef = database.getReference("students")

            val studentData = HashMap<String, Any>()
            studentData["studentId"] = studentId
            studentData["studentName"] = studentName
            studentData["studentAge"] = studentAge
            studentData["studentAddress"] = studentAddress
            studentData["studentEmail"] = studentEmail

            studentsRef.child(studentId).setValue(studentData)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Registration successful
                        // Add any additional logic or UI updates here
                    } else {
                        // Registration failed
                        // Handle error cases here
                    }
                }
        }
    }
}