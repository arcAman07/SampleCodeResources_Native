package com.example.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

public class ChatPage extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
//    TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
    Button button;
    TextView textView;
    Bitmap imageBitmap;
    Button button2;

//    private void dispatchTakePictureIntent(View view) {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        try {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        } catch (ActivityNotFoundException e) {
//            // display error state to the user
//        }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult();
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//        }
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView);
        button2 = (Button) findViewById(R.id.button5);
        if (ContextCompat.checkSelfPermission(ChatPage.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ChatPage.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detectText();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            InputImage image = InputImage.fromBitmap(imageBitmap, 10);
            imageView.setImageBitmap(imageBitmap);


        }}
//        InputImage image = InputImage.fromBitmap(imageBitmap, 0);



        public void detectText() {
        InputImage image = InputImage.fromBitmap(imageBitmap,0);
        TextRecognizer textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        Task<Text> result = textRecognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(@NonNull Text text) {
                StringBuilder result = new StringBuilder();
                for (Text.TextBlock block : text.getTextBlocks()) {
                    String blockText = block.getText();
//                    Log.i("text",blockText);
//                    textView.append(blockText);
                    Point[] blockCornerPoints = block.getCornerPoints();
                    Rect blockFrame = block.getBoundingBox();
                    for (Text.Line line : block.getLines()) {
                        String lineText = line.getText();
//                        Log.i("text",lineText);
//                        Point[] lineCornerPoints = line.getCornerPoints();
                        Rect lineFrame = line.getBoundingBox();
                        for (Text.Element element : line.getElements()) {
                            String elementText = element.getText();
                            result.append(elementText);
                            Log.i("value", elementText);
//                            Log.i("text",elementText               );
                            Point[] elementCornerPoints = element.getCornerPoints();
                            Rect elementFrame = element.getBoundingBox();
                        }
                        textView.setText(result);
                    }
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        }




//        Task<Text> result =
//                recognizer.process(image)
//                        .addOnSuccessListener(new OnSuccessListener<Text>() {
//                            @Override
//                            public void onSuccess(Text visionText) {
//                                // Task completed successfully
//                                String resultText = visionText.getText();
//                                for (Text.TextBlock block : visionText.getTextBlocks()) {
//                                    String blockText = block.getText();
//                                    Log.i("text",blockText);
//                                    textView.append(blockText);
//                                    Point[] blockCornerPoints = block.getCornerPoints();
//                                    Rect blockFrame = block.getBoundingBox();
//                                    for (Text.Line line : block.getLines()) {
//                                        String lineText = line.getText();
//                                        Log.i("text",lineText);
//                                        Point[] lineCornerPoints = line.getCornerPoints();
//                                        Rect lineFrame = line.getBoundingBox();
//                                        for (Text.Element element : line.getElements()) {
//                                            String elementText = element.getText();
//                                            Log.i("text",elementText               );
//                                            Point[] elementCornerPoints = element.getCornerPoints();
//                                            Rect elementFrame = element.getBoundingBox();
//                                        }
//                                    }
//                                }
//
//                                // ...
//                            }
//                        })
//                        .addOnFailureListener(
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        // Task failed with an exception
//                                        Log.i("error","There is an error");
//                                        // ...
//                                    }
//                                });




}






