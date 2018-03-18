package com.example.hp.yelp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class ChatActivity extends AppCompatActivity {
    private static int SIGN_IN_REQUEST_CODE=1;
    private FirebaseListAdapter<ChatMessage>adapter;
    RelativeLayout activity_chat;
    FloatingActionButton fab;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        activity_chat = (RelativeLayout) findViewById(R.id.activity_chat);
        fab=(FloatingActionButton) findViewById(R.id.fab);


            Snackbar.make(activity_chat, "Welcome" + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Snackbar.LENGTH_SHORT).show();

        fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        EditText input =(EditText)findViewById(R.id.input);
        FirebaseDatabase.getInstance().getReference() .push().setValue(new ChatMessage(input.getText().toString(),
                FirebaseAuth.getInstance().getCurrentUser().getEmail()));
        input.setText("");
    }
});

        displayChatMessage();
    }
     private void displayChatMessage(){
         ListView listOfMessage = (ListView ) findViewById(R.id.list_of_message);
      adapter=new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item, FirebaseDatabase.getInstance().getReference())
      {

          @Override
          protected void populateView(View v, ChatMessage model, int position) {
              TextView messageText,messageUser,messageTime;
              messageText= (TextView) v.findViewById(R.id.message_text);
              messageUser= (TextView)v.findViewById(R.id.message_user);
              messageTime= (TextView)v.findViewById(R.id.message_time);
              messageText.setText(model.getMessageText());
              messageUser.setText(model.getMessageUser());
              messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",model.getMessageTime()));


          }
      };
    listOfMessage.setAdapter(adapter );

    }
}
