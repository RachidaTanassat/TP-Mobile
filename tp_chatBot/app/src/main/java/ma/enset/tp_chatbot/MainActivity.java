package ma.enset.tp_chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import ma.enset.tp_chatbot.adapter.ChatBotAdapter;
import ma.enset.tp_chatbot.api.BrainShopApi;
import ma.enset.tp_chatbot.model.BrainShopResponse;
import ma.enset.tp_chatbot.model.MessageModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<MessageModel> messageModelList = new ArrayList<>();
    private EditText editText;
    private ImageButton buttonSend;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        buttonSend = findViewById(R.id.buttonSend);
        recyclerView = findViewById(R.id.rvChBot);
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.brainshop.ai/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BrainShopApi brainShopApi = retrofit.create(BrainShopApi.class);
        ChatBotAdapter chatBotAdapter = new ChatBotAdapter(messageModelList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(chatBotAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        buttonSend.setOnClickListener(view->{
            String msg = editText.getText().toString();
            messageModelList.add(new MessageModel(msg, "user"));
            chatBotAdapter.notifyDataSetChanged();
            String url = "/get?bid=181802&key=Mnl5qZ3667mUWkE2&uid=[uid]&msg="+msg;
            Call<BrainShopResponse> response = brainShopApi.getMessage(url);
            editText.setText("");
            response.enqueue(new Callback<BrainShopResponse>() {
                @Override
                public void onResponse(Call<BrainShopResponse> call, Response<BrainShopResponse> response) {
                    Log.i("info", response.body().getCnt());
                    messageModelList.add(new MessageModel(response.body().getCnt(), "bot"));
                    chatBotAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<BrainShopResponse> call, Throwable throwable) {
                    Log.i("Error", throwable.getMessage());
                }
            });
        });
    }
}