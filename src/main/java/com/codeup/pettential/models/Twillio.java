package com.codeup.pettential.models;

import com.codeup.pettential.repositories.PreferencesRepository;
import com.codeup.pettential.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Twillio {

        // Find your Account Sid and Token at twilio.com/user/account
        public static final String ACCOUNT_SID = "AC050d3ee45f32fc40984e890d5f296135";
        public static final String AUTH_TOKEN = "165123320bd83b5d9a447e0f99160e70";


    public static User checkPreferences(PreferencesRepository preferenceDao, Pet pet) {


//        ArrayList<Preferences> preferences = new ArrayList<Preferences>();
//
//        preferences.add( new Preferences("dog", 5, "white", "male", 10));
//        preferences.add( new Preferences("cat", 5, "white", "male", 10));
//        preferences.add( new Preferences("goldfish", 5, "white", "male", 10));
//        preferences.add( new Preferences("iguana", 5, "white", "male", 10));
//
//        Iterable<Preferences> iterable = preferences;
//
//        for (Preferences preference : iterable) {
//            System.out.println(preference.getAge());
//        }


        for (Preferences preference : preferenceDao.findAll()) {
            if (preference.getBreed().equals(pet.getBreed())) {
                if (preference.getAge() == (pet.getAge())) {
                    if (preference.getColor().equals (pet.getColor())) {
                        if (preference.getSex() .equals (pet.getSex())) {
                            if (preference.getWeight() == (pet.getWeight())) {
                                return preference.getOwner();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return null;

    }

    public static void sendMessage(String userNumber, String textMessage) {

            String ACCOUNT_SID = "AC050d3ee45f32fc40984e890d5f296135";
            String AUTH_TOKEN = "165123320bd83b5d9a447e0f99160e70";


            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                    new PhoneNumber("+1" + userNumber),
                    new PhoneNumber("+19312631097"),
                    textMessage).create();

            System.out.println(message.getSid());

    }

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+13259984721"),
                new PhoneNumber("+19312631097"),
                "This is the ship that made the Kessel Run in fourteen parsecs?").create();

        System.out.println(message.getSid());
    }



}
