package com.example.poems;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/*
This is for chengyudiangu detail page
 */
public class FrameDetailActivity extends AppCompatActivity {
    public static final String FRAME_ID = "frame_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_detail);
        int frame_id = (Integer) getIntent().getExtras().get(FRAME_ID);
        Frame frame = Frame.frames[frame_id];
        TextView name = findViewById(R.id.frameNameDetail);
        name.setText(frame.getFrameName());
        TextView source = findViewById(R.id.frameSourceDetail);
        source.setText(frame.getFrameSource());
        TextView meaning = findViewById(R.id.frameMeaningDetail);
        meaning.setText(frame.getFrameMeaning());

    }
}
