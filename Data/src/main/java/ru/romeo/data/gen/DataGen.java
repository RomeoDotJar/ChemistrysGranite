package ru.romeo.data.gen;

import java.util.Random;

import ru.romeo.data.pref.PrefHelper;
import ru.romeo.domain.entity.chapter.Chapter;
import ru.romeo.domain.entity.lesson.Lesson;

public class DataGen {
    public static final String alphabet = "        qwertyuiopasdfghjklzxcvbnm";

    public static String newString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static Chapter newChapter() {
        int order = PrefHelper.getInt(PrefHelper.LAST_CHAPTER, 0)+1;
        PrefHelper.put(PrefHelper.LAST_CHAPTER, order);
        PrefHelper.put(PrefHelper.LAST_LESSON, 0);
        Random rng = new Random();
        return new Chapter(
            order,
            newString(rng, alphabet, 24),
                newString(rng, alphabet, 64),
                0,
                (int) (100*rng.nextDouble()),
                order
        );
    }

    public static Lesson newLesson() {
        int order = PrefHelper.getInt(PrefHelper.LAST_LESSON, 0);
        PrefHelper.put(PrefHelper.LAST_LESSON, order+1);
        order += PrefHelper.getInt(PrefHelper.LAST_CHAPTER, 0)*10000;
        Random rng = new Random();
        return new Lesson(
                order,
                newString(rng, alphabet, 20),
                newString(rng, alphabet, 2048),//2048),
                newString(rng, alphabet, 60*PrefHelper.getInt(PrefHelper.LAST_LESSON, 0)),
                0,
                (int) (100*rng.nextDouble()),
                order
        );
    }
}
