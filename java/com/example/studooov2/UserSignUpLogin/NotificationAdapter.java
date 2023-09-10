package com.example.studooov2.UserSignUpLogin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studooov2.R;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationCardViewObjectHolder> {

    private NotificationRecyclerviewInterface notificationRecyclerviewInterface;

    private Context context;
    private ArrayList<String> duyuruBody;

    private ArrayList<String> duyuruMonth;

    private ArrayList<String> duyuruDay;


    public NotificationAdapter(NotificationRecyclerviewInterface notificationRecyclerviewInterface ,Context context, ArrayList<String> duyuruBody, ArrayList<String> duyuruMonth, ArrayList<String> duyuruDay ) {
        this.notificationRecyclerviewInterface = notificationRecyclerviewInterface;
        this.context = context;
        this.duyuruBody = duyuruBody;
        this.duyuruMonth = duyuruMonth;
        this.duyuruDay = duyuruDay;
    }

    @NonNull
    @Override
    public NotificationCardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card_desgin, parent, false);
        return new NotificationCardViewObjectHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotificationCardViewObjectHolder holder, int position) {
        holder.textViewNotificationBody.setText(duyuruBody.get(position));
        holder.textViewNotificationDate.setText(duyuruDay.get(position) + " "+ duyuruMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return duyuruDay.size();
    }




    public class NotificationCardViewObjectHolder extends RecyclerView.ViewHolder{

        private TextView textViewNotificationDate;
        private TextView textViewNotificationBody;
        private ImageView imageViewNotification;


        public NotificationCardViewObjectHolder(@NonNull View itemView) {
            super(itemView);

            textViewNotificationBody = itemView.findViewById(R.id.textViewNotificationBody);
            textViewNotificationDate = itemView.findViewById(R.id.textViewNotificationDate);
            imageViewNotification = itemView.findViewById(R.id.imageViewNotification);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(notificationRecyclerviewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            notificationRecyclerviewInterface.onItemClick(position);
                        }
                    }
                }
            });


        }
    }


}
