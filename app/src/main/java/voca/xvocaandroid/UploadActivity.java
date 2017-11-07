package voca.xvocaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private final int RC_FILE_PICKED = 0;
    private final String TAG = "UploadActivity";

    Button uploadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadBtn = findViewById(R.id.upload_activity_upload);

        uploadBtn.setOnClickListener(this);

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onClick(View v) {
        if(v.equals(uploadBtn)) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            startActivityForResult(intent, RC_FILE_PICKED);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_FILE_PICKED:
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d(TAG, "onActivityResult: " + uri.toString());
                }
                break;
        }
    }
}
