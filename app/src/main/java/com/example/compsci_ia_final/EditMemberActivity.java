package com.example.compsci_ia_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditMemberActivity extends AppCompatActivity {


    private EditText editMemberName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);


        // Get the reference to the EditText and Button views
        editMemberName = findViewById(R.id.edit_member_name);
        btnSave = findViewById(R.id.button_save);

        // Set a click listener for the Save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the updated member name from the EditText view
                String updatedMemberName = editMemberName.getText().toString();

                // Create a new intent to send the updated member name back to AttendanceTrackerActivity
                Intent intent = new Intent();
                intent.putExtra("updated_member_name", updatedMemberName);

                // Set the result of the EditMemberActivity to be RESULT_OK and the intent with the updated member name
                setResult(RESULT_OK, intent);

                // Finish the EditMemberActivity to return to AttendanceTrackerActivity
                finish();
            }
        });
    }
}