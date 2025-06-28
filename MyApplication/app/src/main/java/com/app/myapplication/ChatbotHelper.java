package com.app.myapplication;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChatbotHelper {

    private static final Map<String, String[]> HEALTH_RESPONSES = new HashMap<>();

    static {
        // HEALTHCARE RESPONSES

        // Basic greetings and introductions
        HEALTH_RESPONSES.put("greeting", new String[] {
                "Hello! I'm your health assistant. How can I help you today?",
                "Hi there! What health-related questions do you have?",
                "Good day! I'm here to assist with your health questions.",
                "Welcome! I can help with health information, medication reminders, or finding nearby services."
        });

        // Medications and pharmacy
        HEALTH_RESPONSES.put("medicine", new String[] {
                "It's important to take medications as prescribed by your doctor. Would you like to set up a medication reminder?",
                "Always consult your doctor about any medication concerns. I can help you find information about common medications.",
                "Medication management is crucial for your health. Have you tried our medicine reminder feature?",
                "Remember to store your medications in a cool, dry place away from direct sunlight. Would you like information about specific medications?"
        });

        // Common symptoms
        HEALTH_RESPONSES.put("headache", new String[] {
                "For headaches, ensure you're hydrated and rested. Over-the-counter pain relievers might help. If headaches persist, consult a doctor.",
                "Common headache remedies include rest, hydration, and avoiding triggers like bright lights. Consider checking our first aid tips.",
                "Headaches can be caused by stress, dehydration, or lack of sleep. Try relaxation techniques and proper hydration.",
                "Tension headaches often respond well to over-the-counter pain relievers and rest. If you experience severe headaches with vision changes, seek medical attention."
        });

        HEALTH_RESPONSES.put("fever", new String[] {
                "For mild fevers, rest and drink plenty of fluids. If fever exceeds 103°F (39.4°C) or lasts more than three days, see a doctor.",
                "Fever is often your body fighting infection. Rest, fluids, and over-the-counter fever reducers can help. Consult a doctor if it's severe.",
                "Stay hydrated and get plenty of rest when you have a fever. Check our first aid section for more fever management tips.",
                "A fever may indicate infection. For adults, a temperature above 103°F (39.4°C) requires medical attention. For infants, consult a doctor for any fever."
        });

        HEALTH_RESPONSES.put("cold", new String[] {
                "Common cold symptoms can be managed with rest, hydration, and over-the-counter cold medications. Visit our health tips section for more.",
                "For colds, try warm liquids, rest, and saline nasal drops. Symptoms usually improve within a week.",
                "Cold remedies include rest, staying hydrated, and using a humidifier. Check our health tips for more information.",
                "Most colds resolve within 7-10 days. Zinc lozenges and vitamin C may help reduce the duration of symptoms if taken early."
        });

        // Lifestyle and wellness
        HEALTH_RESPONSES.put("exercise", new String[] {
                "Regular exercise is essential for health. Aim for at least 150 minutes of moderate activity weekly. Check our health tips for exercise routines.",
                "Exercise benefits include better mood, weight control, and reduced disease risk. Start slowly if you're new to exercise.",
                "Even light physical activity like walking can improve your health. Our health tips section has more exercise recommendations.",
                "Try to incorporate both cardio and strength training in your exercise routine. Start with 10-minute sessions if you're a beginner."
        });

        HEALTH_RESPONSES.put("diet", new String[] {
                "A balanced diet includes plenty of fruits, vegetables, whole grains, lean proteins, and healthy fats. Check our nutrition tips for more.",
                "Good nutrition is crucial for health. Try to limit processed foods and focus on whole, nutrient-dense options.",
                "Healthy eating habits include portion control and including a variety of food groups. Visit our health tips for nutrition guidance.",
                "The Mediterranean diet, rich in fruits, vegetables, whole grains, and olive oil, is associated with many health benefits."
        });

        HEALTH_RESPONSES.put("sleep", new String[] {
                "Adults need 7-9 hours of quality sleep. Establish a regular sleep schedule and create a restful environment.",
                "Good sleep habits include limiting screen time before bed and avoiding caffeine late in the day.",
                "Quality sleep is essential for health. Create a cool, dark sleeping environment and maintain consistent sleep times.",
                "If you have trouble falling asleep, try relaxation techniques like deep breathing or progressive muscle relaxation before bedtime."
        });

        HEALTH_RESPONSES.put("stress", new String[] {
                "Stress management techniques include deep breathing, meditation, physical activity, and adequate rest.",
                "To reduce stress, try relaxation techniques, regular exercise, and setting realistic goals.",
                "Chronic stress can affect your health. Consider meditation, yoga, or other relaxation practices to manage it.",
                "The 4-7-8 breathing technique can help reduce stress: inhale for 4 seconds, hold for 7, exhale for 8."
        });

        // Mental health
        HEALTH_RESPONSES.put("mental_health", new String[] {
                "Mental health is as important as physical health. If you're struggling, consider talking to a mental health professional.",
                "Regular exercise, proper sleep, and social connections can support good mental health.",
                "Mindfulness practices can help manage stress and improve mental well-being. Consider dedicating a few minutes each day to mindfulness.",
                "If you're experiencing persistent sadness or anxiety that interferes with daily life, please consider seeking professional help."
        });

        HEALTH_RESPONSES.put("anxiety", new String[] {
                "Anxiety can be managed with techniques like deep breathing, progressive muscle relaxation, and cognitive behavioral strategies.",
                "Regular exercise and limiting caffeine can help reduce anxiety symptoms.",
                "If anxiety interferes with daily activities, consider speaking with a healthcare provider about treatment options.",
                "Grounding techniques like the 5-4-3-2-1 method can help during anxious moments: name 5 things you see, 4 you feel, 3 you hear, 2 you smell, and 1 you taste."
        });

        HEALTH_RESPONSES.put("depression", new String[] {
                "Depression is a serious condition that may require professional treatment. Please consider speaking with a healthcare provider.",
                "Regular physical activity, good sleep habits, and social connections can help support mental health.",
                "If you're experiencing symptoms of depression, know that effective treatments are available.",
                "Please reach out to a mental health professional if you're experiencing persistent sadness or loss of interest in activities."
        });

        // First aid and emergencies
        HEALTH_RESPONSES.put("first_aid", new String[] {
                "Our First Aid section has information on treating common injuries and emergency situations. Would you like to access it?",
                "For minor injuries, proper first aid can prevent complications. Check our First Aid section for step-by-step guides.",
                "It's good to know basic first aid procedures. Our app provides guides for common situations like cuts, burns, and sprains.",
                "Remember, in case of serious injuries or medical emergencies, call emergency services immediately."
        });

        HEALTH_RESPONSES.put("emergency", new String[] {
                "For medical emergencies, call emergency services immediately. In Morocco, dial 15 for ambulance services.",
                "Signs of a medical emergency include severe chest pain, difficulty breathing, or sudden severe pain. Call emergency services right away.",
                "If someone is unconscious, not breathing, or bleeding severely, call emergency services immediately.",
                "Our app can help you locate the nearest hospital for emergencies. Would you like me to show you this feature?"
        });

        // Specialized health topics
        HEALTH_RESPONSES.put("diabetes", new String[] {
                "Diabetes management involves monitoring blood sugar, following a healthy diet, regular exercise, and taking prescribed medications.",
                "If you have diabetes, regular check-ups with your healthcare provider are essential to prevent complications.",
                "Symptoms of diabetes can include increased thirst, frequent urination, and unexplained weight loss. Consult a doctor if you notice these signs.",
                "Both Type 1 and Type 2 diabetes require careful management. Would you like information about specific aspects of diabetes care?"
        });

        HEALTH_RESPONSES.put("heart_health", new String[] {
                "Heart-healthy habits include regular exercise, a balanced diet low in saturated fats and sodium, not smoking, and limiting alcohol.",
                "Warning signs of heart attack include chest pain/pressure, shortness of breath, and pain in the arm, back, or jaw. Seek immediate help if these occur.",
                "Regular blood pressure and cholesterol checks are important for monitoring heart health.",
                "The Mediterranean diet and DASH diet are both recommended for heart health. They emphasize fruits, vegetables, whole grains, and healthy fats."
        });

        HEALTH_RESPONSES.put("pregnancy", new String[] {
                "During pregnancy, regular prenatal care is essential. Be sure to attend all scheduled check-ups with your healthcare provider.",
                "A healthy pregnancy diet includes plenty of fruits, vegetables, whole grains, lean proteins, and adequate hydration.",
                "Common pregnancy discomforts like morning sickness or back pain can often be managed with simple remedies. Consult your doctor for advice.",
                "If you experience severe abdominal pain, heavy bleeding, or decreased fetal movement during pregnancy, seek medical attention immediately."
        });

        // NON-HEALTHCARE TOPICS

        // Technology and gadgets
        HEALTH_RESPONSES.put("technology", new String[] {
                "While I specialize in health information, I can suggest some health-related technology like fitness trackers or meditation apps. What are you interested in?",
                "Technology can support health goals through apps for nutrition tracking, fitness, meditation, and sleep monitoring.",
                "Many smartphones now have built-in health features that can track steps, sleep patterns, and even heart rate.",
                "Smart watches and fitness trackers can help monitor physical activity and certain health metrics like heart rate."
        });

        // Travel and leisure
        HEALTH_RESPONSES.put("travel", new String[] {
                "When traveling, remember to pack any needed medications, stay hydrated, and adjust to new time zones gradually.",
                "Travel health tips include getting recommended vaccinations, packing a basic first aid kit, and having travel insurance.",
                "Jet lag can be minimized by gradually adjusting your sleep schedule before travel and getting plenty of sunlight at your destination.",
                "If you have chronic health conditions, consult your doctor before traveling and carry a letter describing your condition and medications."
        });

        // Education and learning
        HEALTH_RESPONSES.put("education", new String[] {
                "Health education is important for making informed decisions. Our app provides reliable information on various health topics.",
                "Learning about health and wellness can empower you to take better care of yourself and your loved ones.",
                "Our health tips section offers educational content on nutrition, exercise, sleep, and stress management.",
                "Understanding basic health concepts can help you communicate better with healthcare providers and make informed decisions."
        });

        // Career and professional development
        HEALTH_RESPONSES.put("career", new String[] {
                "Maintaining work-life balance is important for overall health. Remember to take breaks and practice self-care.",
                "Workplace wellness can include proper ergonomics, regular breaks, stress management, and staying active throughout the day.",
                "Sitting for long periods isn't good for health. Try to stand and move regularly during your workday.",
                "Job stress can affect physical health. Techniques like time management, setting boundaries, and relaxation can help."
        });

        // Entertainment and hobbies
        HEALTH_RESPONSES.put("entertainment", new String[] {
                "Hobbies can be great for mental health and stress relief. Activities like gardening, cooking, or crafting can be both enjoyable and therapeutic.",
                "Balance screen time with other activities for better physical and mental health.",
                "Creative activities like music, art, or writing can provide stress relief and emotional expression.",
                "Social hobbies like team sports or group classes offer both physical activity and social connection, which are good for overall health."
        });

        // Relationships and social life
        HEALTH_RESPONSES.put("relationships", new String[] {
                "Strong social connections contribute to better mental and physical health. Try to maintain regular contact with friends and loved ones.",
                "Healthy relationships involve good communication, mutual respect, and boundaries.",
                "Social support is important for mental health. Connecting with others can help reduce stress and improve mood.",
                "If you're feeling isolated, consider joining community groups, classes, or volunteer opportunities to meet new people."
        });

        // Personal finance
        HEALTH_RESPONSES.put("finance", new String[] {
                "Financial stress can affect health. Creating a budget and financial plan might help reduce this type of stress.",
                "Health expenses can be significant. Consider health insurance options and ask about generic medications to save on costs.",
                "Some preventive health services may be available at reduced or no cost through health insurance or community programs.",
                "Regular health maintenance can help prevent costly medical issues down the road."
        });

        // Personal development
        HEALTH_RESPONSES.put("personal_growth", new String[] {
                "Personal growth includes developing healthy habits and self-care routines.",
                "Setting realistic, achievable health goals can help you make sustainable lifestyle changes.",
                "Self-awareness about your health habits and triggers can help you make positive changes.",
                "Practicing gratitude and mindfulness can contribute to emotional well-being and stress reduction."
        });

        // Jokes and fun
        HEALTH_RESPONSES.put("joke", new String[] {
                "Why don't scientists trust atoms? Because they make up everything! Remember, laughter is good for health!",
                "I told my doctor I broke my arm in two places. He told me to stop going to those places! A good laugh can reduce stress.",
                "What do you call a bear with no teeth? A gummy bear! Smiling and laughing can actually boost your immune system.",
                "Why did the pillow go to the doctor? Because it was feeling all stuffed up! Laughter is a great stress reliever."
        });

        // Fallback responses
        HEALTH_RESPONSES.put("default", new String[] {
                "I'm your health assistant. You can ask me about medications, symptoms, health tips, or nearby hospitals.",
                "I can provide general health information. For medical emergencies, please contact a healthcare professional immediately.",
                "I'm here to help with general health questions. Try asking about medicines, exercise, diet, or first aid tips.",
                "I can assist with health information, but remember I'm not a substitute for professional medical advice.",
                "I'd be happy to help with health-related topics. You can ask about common symptoms, healthy lifestyle tips, or medication reminders."
        });
    }

    public static String getResponse(String userMessage) {
        try {
            // Convert to lowercase for easier matching
            String input = userMessage.toLowerCase();

            // Check for keywords in the input
            if (containsAny(input, "hi", "hello", "hey", "greetings", "howdy")) {
                return getRandomResponse("greeting");
            } else if (containsAny(input, "medicine", "medication", "drug", "pill", "prescription", "pharmacy")) {
                return getRandomResponse("medicine");
            } else if (containsAny(input, "headache", "head pain", "migraine", "head hurts")) {
                return getRandomResponse("headache");
            } else if (containsAny(input, "fever", "temperature", "hot", "chills", "thermometer")) {
                return getRandomResponse("fever");
            } else if (containsAny(input, "cold", "flu", "cough", "sneeze", "runny nose", "congestion", "sore throat")) {
                return getRandomResponse("cold");
            } else if (containsAny(input, "exercise", "workout", "fitness", "physical activity", "sport", "gym", "run", "jog", "training")) {
                return getRandomResponse("exercise");
            } else if (containsAny(input, "food", "diet", "nutrition", "eat", "meal", "cooking", "healthy eating", "calories")) {
                return getRandomResponse("diet");
            } else if (containsAny(input, "sleep", "insomnia", "rest", "tired", "fatigue", "nap", "bedtime", "drowsy")) {
                return getRandomResponse("sleep");
            } else if (containsAny(input, "stress", "anxiety", "worried", "tension", "pressure", "overwhelmed", "anxious")) {
                return getRandomResponse("stress");
            }
            // Mental health topics
            else if (containsAny(input, "mental health", "mental wellbeing", "therapy", "counseling", "psychiatrist", "psychologist")) {
                return getRandomResponse("mental_health");
            } else if (containsAny(input, "anxiety", "anxious", "panic", "worry", "nervousness", "nervous")) {
                return getRandomResponse("anxiety");
            } else if (containsAny(input, "depression", "depressed", "sad", "hopeless", "blues", "mood", "unhappy")) {
                return getRandomResponse("depression");
            }
            // First aid and emergency
            else if (containsAny(input, "first aid", "injury", "wound", "cut", "burn", "sprain", "bandage")) {
                return getRandomResponse("first_aid");
            } else if (containsAny(input, "emergency", "urgent", "ambulance", "911", "hospital", "er", "accident")) {
                return getRandomResponse("emergency");
            }
            // Specialized health topics
            else if (containsAny(input, "diabetes", "blood sugar", "insulin", "glucose", "diabetic")) {
                return getRandomResponse("diabetes");
            } else if (containsAny(input, "heart", "cardiac", "cardiovascular", "blood pressure", "cholesterol", "hypertension")) {
                return getRandomResponse("heart_health");
            } else if (containsAny(input, "pregnancy", "pregnant", "baby", "fetus", "trimester", "prenatal", "maternity")) {
                return getRandomResponse("pregnancy");
            }
            // Non-healthcare topics
            else if (containsAny(input, "technology", "tech", "gadget", "device", "app", "computer", "phone", "smartphone")) {
                return getRandomResponse("technology");
            } else if (containsAny(input, "travel", "vacation", "trip", "journey", "flight", "hotel", "tourism")) {
                return getRandomResponse("travel");
            } else if (containsAny(input, "education", "learn", "study", "school", "college", "university", "degree", "course")) {
                return getRandomResponse("education");
            } else if (containsAny(input, "career", "job", "work", "profession", "employment", "business", "office")) {
                return getRandomResponse("career");
            } else if (containsAny(input, "entertainment", "movie", "tv", "music", "game", "hobby", "fun", "leisure")) {
                return getRandomResponse("entertainment");
            } else if (containsAny(input, "relationship", "marriage", "partner", "friend", "family", "social", "dating")) {
                return getRandomResponse("relationships");
            } else if (containsAny(input, "money", "finance", "budget", "saving", "investment", "financial", "cost", "expense")) {
                return getRandomResponse("finance");
            } else if (containsAny(input, "personal growth", "self improvement", "development", "goals", "habits", "motivation")) {
                return getRandomResponse("personal_growth");
            } else if (containsAny(input, "joke", "funny", "humor", "laugh", "comedy")) {
                return getRandomResponse("joke");
            } else {
                return getRandomResponse("default");
            }
        } catch (Exception e) {
            return "I'm sorry, I couldn't process that. How can I help you with your health questions?";
        }
    }

    private static boolean containsAny(String input, String... keywords) {
        for (String keyword : keywords) {
            if (input.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private static String getRandomResponse(String category) {
        try {
            String[] responses = HEALTH_RESPONSES.getOrDefault(category, HEALTH_RESPONSES.get("default"));
            return responses[new Random().nextInt(responses.length)];
        } catch (Exception e) {
            return "I'm here to help with your health questions.";
        }
    }
}