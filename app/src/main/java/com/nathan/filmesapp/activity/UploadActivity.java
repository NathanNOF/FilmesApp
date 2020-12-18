package com.nathan.filmesapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.nathan.filmesapp.R;
import com.nathan.filmesapp.model.VideoUploadDetalhes;

import java.util.ArrayList;
import java.util.List;

public class UploadActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Uri videoUri;
    TextView video_selecte;
    String video_categoria;
    String videoTitulo;
    String idAtual;
    StorageReference mstorageRef;
    StorageTask mUploadsTask;
    DatabaseReference referenceVideos;
    EditText video_descricacao;

    Button btnSelect;
    Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        video_selecte = findViewById(R.id.textVideoSelecionado);
        video_descricacao = findViewById(R.id.DescriçãoFilme);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpload = findViewById(R.id.buttonUpload);

        referenceVideos = FirebaseDatabase.getInstance().getReference().child("videos");
        mstorageRef = FirebaseStorage.getInstance().getReference().child("videos");

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categorias = new ArrayList<>();
        categorias.add("Action");
        categorias.add("Adventure");
        categorias.add("Sports");
        categorias.add("Romantic");
        categorias.add("Comedy");

        ArrayAdapter<String> dataAdpter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        dataAdpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdpter);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openvideosFiles();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFileToFirebase();
            }
        });

    }

    public void uploadFileToFirebase() {
        if (video_selecte.equals("video select")) {
            Toast.makeText(this, "selecione um video", Toast.LENGTH_SHORT).show();
        } else {
            if (mUploadsTask != null && mUploadsTask.isInProgress()) {
                Toast.makeText(this, "Upload de vídeos está em andamento..", Toast.LENGTH_SHORT).show();
            } else {
                uploadFiles();
            }
        }

    }
    private void uploadFiles(){
        if(videoUri!=null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Video uplando...");
            progressDialog.show();
            final StorageReference storageReference = mstorageRef.child("video01");
            mUploadsTask = storageReference.putFile(videoUri).addOnSuccessListener
                    (new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                                @Override
                                public void onSuccess(Uri uri) {
                                    String video_url = uri.toString();

                                    VideoUploadDetalhes VideoUploadDetalhes = new VideoUploadDetalhes("","","",
                                            video_url, videoTitulo,video_descricacao.getText().toString(),video_categoria);

                                    String uploadsid = referenceVideos.push().getKey();
                                    referenceVideos.child(uploadsid).setValue(VideoUploadDetalhes);
                                    idAtual = uploadsid;
                                    progressDialog.dismiss();

                                }
                            });
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Upload"+((int)progress)+"%...");
                }
            });
        }else{
            Toast.makeText(this,"nenhum vídeo selecionado para enviar",Toast.LENGTH_SHORT).show();
        }
    }

    public void openvideosFiles() {

        Intent in = new Intent(Intent.ACTION_GET_CONTENT);
        in.setType("video/*");
        startActivityForResult(in, 101);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        video_categoria = adapterView.getItemAtPosition(i).toString();

        Toast.makeText(this,"cat: "+video_categoria,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && data.getData() != null) {
            videoUri = data.getData();
        }

        String path;
        Cursor cursor;
        int colum_index_data;
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME
                , MediaStore.Video.Media._ID, MediaStore.Video.Thumbnails.DATA};

        final String orderby = MediaStore.Video.Media.DEFAULT_SORT_ORDER;
        cursor = UploadActivity.this.getContentResolver().query(videoUri, projection, null, null, orderby);
        colum_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        videoTitulo = System.currentTimeMillis()+"";
        video_selecte.setText(videoTitulo);


    }
}
