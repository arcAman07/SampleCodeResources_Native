package com.example.bookfetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    BookfetchApi bookfetchApi;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        gson = new GsonBuilder()
//                .setLenient()
//                .create();
        textView = (TextView) findViewById(R.id.textView);
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/").addConverterFactory(GsonConverterFactory.create(gson)).build();

        // Used for GET routes and maybe delete routes
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/").addConverterFactory(GsonConverterFactory.create()).build();
        bookfetchApi = retrofit.create(BookfetchApi.class);
//        getBooks();
        textView.append("\n");
        textView.append("\n");
        textView.append("\t");
        textView.append("\t");
//        getBook("Accidental Billionaires");
        createPost();
        textView.append("\n");
        textView.append("\n");
        textView.append("\n");
        textView.append("\t");
        textView.append("\t");
//        getBook("I hate luv stories");



    }
    private void getBook(String a) {
            Call<Book> call = bookfetchApi.getBook(a);
            call.enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    if(!response.isSuccessful()) {
                        textView.setText(response.code());
                        return;
                    }
                    Book oneBook = response.body();
                    String content = "";
                    content += "ID: "+oneBook.get_id()+"\n"+"\t"+"\t";
//                    textView.append("\t");
//                    textView.append("\t");
                    content += "Content: "+oneBook.getContent()+"\n"+"\t"+"\t";
//                    textView.append("\t");
//                    textView.append("\t");
                    content += "Title: "+oneBook.getTitle()+"\n"+"\t"+"\t";
//                    textView.append("\t");
//                    textView.append("\t");
                    content += "ID: "+oneBook.getRating()+"\n"+"\t"+"\t";
//                    textView.append("\t");
//                    textView.append("\t");
                    content += "Availability: "+oneBook.isAvail()+"\n"+"\t"+"\t";
//                    textView.append("\t");
//                    textView.append("\t");
                    textView.append(content);
//

                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
                    Log.i("error",t.toString());

                }
            });
    }
    private void getBooks() {
        Call<List<Book>> call = bookfetchApi.getBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()) {
                    textView.setText("Code: "+response.code());
                    return;
                }
                List<Book> books = response.body();
                for(Book book:books) {
                    String content = "";
                    content += "ID: "+book.get_id()+"\n"+"\n"+"\t"+"\t";
                    content += "Content: "+book.getContent()+"\n"+"\n"+"\t"+"\t";
                    content += "Title: "+book.getTitle()+"\n"+"\n"+"\t"+"\t";
                    content += "ID: "+book.getRating()+"\n"+"\n"+"\t"+"\t";
                    content += "Availability: "+book.isAvail()+"\n"+"\n"+"\t"+"\t";

                    textView.append(content);
                    Log.i("info",content);
                }


            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                textView.setText(t.getMessage());
                Log.i("error",t.toString());

            }
        });

    }
    private void createPost() {
        Book bookN = new Book("Who gonna stop me","Survival",3.6,false);
        Call<Book> call = bookfetchApi.createBook(bookN);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if(!response.isSuccessful()) {
                    textView.setText("Code: "+response.code());
                    return;
                }

                Book bookResponse = response.body();
                String content = "";
                content += "ID: "+bookResponse.get_id()+"\n"+"\n"+"\t"+"\t";
                content += "Content: "+bookResponse.getContent()+"\n"+"\n"+"\t"+"\t";
                content += "Title: "+bookResponse.getTitle()+"\n"+"\n"+"\t"+"\t";
                content += "ID: "+bookResponse.getRating()+"\n"+"\n"+"\t"+"\t";
                content += "Availability: "+bookResponse.isAvail()+"\n"+"\n"+"\t"+"\t";
                textView.append(content);
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.i("error",t.getMessage().toString());

            }
        });
    }
}