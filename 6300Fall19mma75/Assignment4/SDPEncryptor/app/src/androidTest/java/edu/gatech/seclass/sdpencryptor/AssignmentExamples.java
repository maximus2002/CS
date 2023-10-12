package edu.gatech.seclass.sdpencryptor;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * This is a Georgia Tech provided code example for use in assigned private GT repositories. Students and other users of this template
 * code are advised not to share it with other students or to make it available on publicly viewable websites including
 * repositories such as github and gitlab.  Such sharing may be investigated as a GT honor code violation. Created for CS6300.
 */


@RunWith(AndroidJUnit4.class)
public class AssignmentExamples {

    @Rule
    public ActivityTestRule<SDPEncryptorActivity> tActivityRule = new ActivityTestRule<>(
            SDPEncryptorActivity.class);


    @Test
    public void Screenshot1() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("Cat & Dog"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("3"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("Ffa & Mzt")));
    }

    @Test
    public void Screenshot2() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("Up with the White And Gold!"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("Vr zmyn apn Gsugs Pdu Yhfy!")));
    }

    @Test
    public void Screenshot3() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("abcdefg"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("cegikmo")));
    }

    @Test
    public void Screenshot4() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("XYZabcABCxyz"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("22"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("19"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("TNHbvpGAUicw")));
    }

    @Test
    public void ScreenshotErrors1() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("12345"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("4"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.messageInput)).check(matches(hasErrorText("Invalid Message")));
    }

    @Test
    public void ScreenshotErrors2a() {
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("30"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.keyNumber)).check(matches(hasErrorText("Invalid Key Number")));
    }

    @Test
    public void ScreenshotErrors2b() {
        onView(withId(R.id.messageInput)).perform(clearText());
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("30"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.messageInput)).check(matches(hasErrorText("Invalid Message")));
    }

    @Test
    public void ScreenshotErrors2c() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText(""));;
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("0"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("30"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.incrementNumber)).check(matches(hasErrorText("Invalid Increment Number")));
    }

    @Test
    public void ScreenshotErrors3a() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("35"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("88"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.incrementNumber)).check(matches(hasErrorText("Invalid Increment Number")));
    }

    @Test
    public void ScreenshotErrors3b() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("35"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("88"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.messageInput)).check(matches(hasErrorText("Invalid Message")));
    }

    @Test
    public void ScreenshotErrors3c() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("123"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("35"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("88"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.keyNumber)).check(matches(hasErrorText("Invalid Key Number")));
    }

    @Test
    public void ScreenshotLabel() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("Test Example!!."));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("20"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("2"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("Naqt Gbguzxs!!.")));
    }

    @Test
    public void ExtraTest1() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("Panda eats shoots and leaves."));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("5"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("5"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("Ukcxz ijhl qkwblp cup cabbpi.")));
    }

    @Test
    public void ExtraTest2() {
        onView(withId(R.id.messageInput)).perform(clearText(), replaceText("aaaaaa"));
        onView(withId(R.id.keyNumber)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.incrementNumber)).perform(clearText(), replaceText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.encryptButton)).perform(click());
        onView(withId(R.id.cipherText)).check(matches(withText("bcdefg")));
    }

}