package com.codeup.pettential.models;

import com.codeup.pettential.repositories.PreferencesRepository;
import com.codeup.pettential.repositories.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;


public class Twillio {

        // Find your Account Sid and Token at twilio.com/user/account
        public static final String ACCOUNT_SID = "ACac2b4d5200d52339abe5d570fdbfcc28";
        public static final String AUTH_TOKEN = "1afd3e99cf55ec6173dbf2b3cf7ad0d6";


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

    public static void sendMessage(String userNumber) {

            String ACCOUNT_SID = "ACac2b4d5200d52339abe5d570fdbfcc28";
            String AUTH_TOKEN = "1afd3e99cf55ec6173dbf2b3cf7ad0d6";


            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                    new PhoneNumber("+" + userNumber),
                    new PhoneNumber("+18302660958"),
                    "This is the ship that made the Kessel Run in fourteen parsecs?").create();

            System.out.println(message.getSid());

    }

        public static void main(String[] args) {

        }



}
