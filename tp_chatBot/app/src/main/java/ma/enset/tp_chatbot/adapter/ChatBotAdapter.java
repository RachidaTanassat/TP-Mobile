package ma.enset.tp_chatbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ma.enset.tp_chatbot.R;
import ma.enset.tp_chatbot.model.MessageModel;

public class ChatBotAdapter extends RecyclerView.Adapter{

private List<MessageModel> messageModelList;
private Context context;

    public ChatBotAdapter(List<MessageModel> messageModelList, Context context) {
        this.messageModelList = messageModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(context).inflate(R.layout.user_message, parent, false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.bot_message, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        String sender = messageModelList.get(position).getSender();
        switch (sender){
            case "user": return 0;
            case "bot": return 1;
            default: return -1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       MessageModel messageModel = messageModelList.get(position);
       switch (messageModel.getSender()){
           case "user": ((UserViewHolder)holder).textViewUserMsg.setText(messageModel.getMessage());
           break;
           case "bot": ((BotViewHolder)holder).textViewBotMsg.setText(messageModel.getMessage());
           break;
       }
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
         TextView textViewUserMsg;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewUserMsg = itemView.findViewById(R.id.textMessageUser);
        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
         TextView textViewBotMsg;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewBotMsg = itemView.findViewById(R.id.textMessageBot);
        }
    }
}
