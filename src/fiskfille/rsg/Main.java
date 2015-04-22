package fiskfille.rsg;

import java.io.File;

public class Main
{
    public static String[] keys;

    public static void main(String[] args)
    {
        File categories = new File("categories");

        File[] listFiles = categories.listFiles();
        keys = new String[listFiles.length];

        for (int i = 0; i < listFiles.length; i++)
        {
            keys[i] = listFiles[i].getName().split("\\.")[0];
        }

        if (args.length > 0)
        {
            for (int i = 0; i < parseInt(args[0]); i++)
            {
                System.out.println(generateRandomSentence());
            }
        }
        else System.out.println(generateRandomSentence());
    }

    private static String generateRandomSentence()
    {
        String sentence = FileHelper.getRandomEntry("sentences.txt");

        while (sentence.contains("[") && sentence.contains("]"))
        {
            sentence = format(sentence);
        }

        return sentence + ".";
    }

    public static String format(String s)
    {
        String[] words = s.split(" ");
        String text = "";

        for (int i = 0; i < words.length; ++i)
        {
            String word = words[i];
            boolean name = word.equals("[people]");

            for (String key : keys)
            {
                if (word.equals("[" + key + "]"))
                {
                    String randomEntry = FileHelper.getRandomEntry(word.replace("[", "").replace("]", "") + ".txt");
                    word = word.replace(word, randomEntry);
                }
            }

            text += name ? word + " " : (i == 0 ? word.substring(0, 1).toUpperCase() + word.substring(1) : word) + " ";
        }

        return text.substring(0, text.length() - 1);
    }

    public static int parseInt(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException nfe)
        {
            return 1;
        }
    }
}