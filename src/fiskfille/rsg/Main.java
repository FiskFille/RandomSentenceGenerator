package fiskfille.rsg;

public class Main
{
	public static String[] keys = {"people", "sentences", "statements", "verbs", "things", "food", "adverbs", "places", "adjectives", "times"};
	
	public static void main(String[] args)
	{
		System.out.println(generateRandomSentence());
	}
	
	private static String generateRandomSentence()
	{
		String sentence = FileHelper.getRandomEntry("sentences.txt");
		
		while (sentence.contains("[") && sentence.contains("]"))
		{
			sentence = format(sentence);
		}
		
		return sentence = sentence + ".";
	}

	public static String format(String s)
	{
		String[] words = s.split(" ");
		String text = "";
		
		for (int i = 0; i < words.length; ++i)
		{
			String word = words[i];
			
			for (String key : keys)
			{
				if (word.equals("[" + key + "]"))
				{
					String randomEntry = FileHelper.getRandomEntry(word.replace("[", "").replace("]", "") + ".txt");
					word = word.replace(word, randomEntry);
				}
			}
			
			text += (i == 0 ? word.substring(0, 1).toUpperCase() + word.substring(1) : word) + " ";
		}
		
		return text.substring(0, text.length() - 1);
	}
}