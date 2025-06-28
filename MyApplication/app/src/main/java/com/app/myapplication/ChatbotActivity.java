package com.app.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {

    private static final String TAG = "ChatbotActivity";
    private RecyclerView recyclerViewChat;
    private EditText etMessage;
    private FloatingActionButton btnSend;
    private List<ChatMessage> chatMessages = new ArrayList<>();
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.layout_chatbot);

            // Setup toolbar
            try {
                Toolbar toolbar = findViewById(R.id.toolbar);
                if (toolbar != null) {
                    setSupportActionBar(toolbar);
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                    }
                }

                // Set up back button
                ImageView backButton = findViewById(R.id.backButton);
                if (backButton != null) {
                    backButton.setOnClickListener(v -> finish());
                }
            } catch (Exception e) {
                Log.e(TAG, "Error setting up toolbar: " + e.getMessage());
            }

            // Initialize views
            try {
                recyclerViewChat = findViewById(R.id.recyclerViewChat);
                etMessage = findViewById(R.id.etMessage);
                btnSend = findViewById(R.id.btnSend);

                if (recyclerViewChat == null) {
                    Toast.makeText(this, "Error: Chat view not found", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }

                if (etMessage == null || btnSend == null) {
                    Toast.makeText(this, "Error: Message input elements not found", Toast.LENGTH_SHORT).show();
                    finish();
                    return;
                }
            } catch (Exception e) {
                Log.e(TAG, "Error initializing views: " + e.getMessage());
                Toast.makeText(this, "Error initializing chat interface", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Setup RecyclerView
            try {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerViewChat.setLayoutManager(layoutManager);
                chatAdapter = new ChatAdapter(chatMessages);
                recyclerViewChat.setAdapter(chatAdapter);
            } catch (Exception e) {
                Log.e(TAG, "Error setting up RecyclerView: " + e.getMessage());
                Toast.makeText(this, "Error setting up chat display", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Hide welcome card after a short delay
            try {
                CardView welcomeCard = findViewById(R.id.welcomeCard);
                if (welcomeCard != null) {
                    welcomeCard.postDelayed(() -> {
                        welcomeCard.animate()
                                .alpha(0f)
                                .setDuration(500)
                                .withEndAction(() -> welcomeCard.setVisibility(View.GONE))
                                .start();
                    }, 3000);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error with welcome card: " + e.getMessage());
            }

            // Setup suggestion chips
            setupSuggestionChips();

            // Setup clear text button
            ImageView btnClearText = findViewById(R.id.btnClearText);
            if (btnClearText != null && etMessage != null) {
                etMessage.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        btnClearText.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {}
                });

                btnClearText.setOnClickListener(v -> etMessage.setText(""));
            }

            // Add welcome message
            addBotMessage("Hello! I'm your health assistant. How can I help you today?");

            // Send button click
            btnSend.setOnClickListener(v -> sendMessage());

        } catch (Exception e) {
            Log.e(TAG, "Fatal error in onCreate: " + e.getMessage());
            Toast.makeText(this, "Could not initialize chat interface", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void setupSuggestionChips() {
        try {
            Chip chipHeadache = findViewById(R.id.chipHeadache);
            Chip chipSleep = findViewById(R.id.chipSleep);
            Chip chipStress = findViewById(R.id.chipStress);
            Chip chipDiet = findViewById(R.id.chipDiet);

            if (chipHeadache != null) {
                chipHeadache.setOnClickListener(v -> {
                    etMessage.setText("I have a headache, what should I do?");
                    sendMessage();
                });
            }

            if (chipSleep != null) {
                chipSleep.setOnClickListener(v -> {
                    etMessage.setText("How can I improve my sleep?");
                    sendMessage();
                });
            }

            if (chipStress != null) {
                chipStress.setOnClickListener(v -> {
                    etMessage.setText("How can I manage stress?");
                    sendMessage();
                });
            }

            if (chipDiet != null) {
                chipDiet.setOnClickListener(v -> {
                    etMessage.setText("What's a healthy diet?");
                    sendMessage();
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up suggestion chips: " + e.getMessage());
        }
    }

    private void sendMessage() {
        try {
            String message = etMessage.getText().toString().trim();
            if (!TextUtils.isEmpty(message)) {
                // Add user message
                addUserMessage(message);

                // Clear input field
                etMessage.setText("");

                // Show typing indicator
                showTypingIndicator(true);

                // Get bot response
                String response = ChatbotHelper.getResponse(message);

                // Add bot response (with small delay to feel more natural)
                recyclerViewChat.postDelayed(() -> {
                    // Hide typing indicator
                    showTypingIndicator(false);
                    // Add message
                    addBotMessage(response);
                }, 1500);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error sending message: " + e.getMessage());
            Toast.makeText(this, "Error sending message", Toast.LENGTH_SHORT).show();
        }
    }

    private void showTypingIndicator(boolean show) {
        try {
            CardView typingIndicator = findViewById(R.id.typingIndicator);
            if (typingIndicator != null) {
                typingIndicator.setVisibility(show ? View.VISIBLE : View.GONE);

                if (show) {
                    // Animate typing dots
                    TextView typingDots = findViewById(R.id.typingDots);
                    if (typingDots != null) {
                        animateTypingDots(typingDots);
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error with typing indicator: " + e.getMessage());
        }
    }

    private void animateTypingDots(TextView dots) {
        if (dots == null) return;

        final String[] dotPatterns = {".", "..", "..."};
        final int[] index = {0};

        dots.post(new Runnable() {
            @Override
            public void run() {
                dots.setText(dotPatterns[index[0]]);
                index[0] = (index[0] + 1) % dotPatterns.length;
                dots.postDelayed(this, 500);
            }
        });
    }

    private void addUserMessage(String message) {
        try {
            chatMessages.add(new ChatMessage(message, true));
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            scrollToBottom();
        } catch (Exception e) {
            Log.e(TAG, "Error adding user message: " + e.getMessage());
        }
    }

    private void addBotMessage(String message) {
        try {
            chatMessages.add(new ChatMessage(message, false));
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            scrollToBottom();
        } catch (Exception e) {
            Log.e(TAG, "Error adding bot message: " + e.getMessage());
        }
    }

    private void scrollToBottom() {
        try {
            if (chatAdapter.getItemCount() > 0) {
                recyclerViewChat.smoothScrollToPosition(chatAdapter.getItemCount() - 1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error scrolling to bottom: " + e.getMessage());
        }
    }

    // Chat message model
    static class ChatMessage {
        String message;
        boolean isUser; // true if user message, false if bot message

        ChatMessage(String message, boolean isUser) {
            this.message = message;
            this.isUser = isUser;
        }
    }

    // Chat adapter
    class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
        private List<ChatMessage> messages;

        ChatAdapter(List<ChatMessage> messages) {
            this.messages = messages;
        }

        @NonNull
        @Override
        public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            try {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_message, parent, false);
                return new ChatViewHolder(view);
            } catch (Exception e) {
                Log.e(TAG, "Error in onCreateViewHolder: " + e.getMessage());
                // Fallback to a simple view if there's an error
                TextView textView = new TextView(parent.getContext());
                textView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                return new ChatViewHolder(textView);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
            try {
                ChatMessage message = messages.get(position);

                if (message.isUser) {
                    // User message
                    if (holder.cardUserMessage != null) holder.cardUserMessage.setVisibility(View.VISIBLE);
                    if (holder.cardBotMessage != null) holder.cardBotMessage.setVisibility(View.GONE);
                    if (holder.tvUserMessage != null) holder.tvUserMessage.setText(message.message);
                } else {
                    // Bot message
                    if (holder.cardUserMessage != null) holder.cardUserMessage.setVisibility(View.GONE);
                    if (holder.cardBotMessage != null) holder.cardBotMessage.setVisibility(View.VISIBLE);
                    if (holder.tvBotMessage != null) holder.tvBotMessage.setText(message.message);
                }
            } catch (Exception e) {
                Log.e(TAG, "Error in onBindViewHolder: " + e.getMessage());
            }
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        class ChatViewHolder extends RecyclerView.ViewHolder {
            CardView cardUserMessage, cardBotMessage;
            TextView tvUserMessage, tvBotMessage;

            ChatViewHolder(View itemView) {
                super(itemView);
                try {
                    cardUserMessage = itemView.findViewById(R.id.cardUserMessage);
                    cardBotMessage = itemView.findViewById(R.id.cardBotMessage);
                    tvUserMessage = itemView.findViewById(R.id.tvUserMessage);
                    tvBotMessage = itemView.findViewById(R.id.tvBotMessage);
                } catch (Exception e) {
                    Log.e(TAG, "Error in ChatViewHolder: " + e.getMessage());
                }
            }
        }
    }
}