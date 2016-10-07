import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chloe on 10/7/16.
 */
public class Start {

    public static void main(String[] args){
        // do better input checking

        int len = args.length;

        if (len == 0){
            throw new RuntimeException("Gimme some arguments please. Like a word to permute. Maybe a file...");
        }

        String defaultPathV1 = "./TestSmallDict.txt";
        String defaultPathV2 = "../../../TestSmallDict.txt";

        List<String> words = new ArrayList<>();
        int index = 0;
        String filePath = null;

        if (args[0].equals("--file")){
            filePath = args[1];
            index = 2;
        }

        while(index < len){
            words.add(args[index]);
            index++;
        }

        if (filePath == null){
            filePath = defaultPathV1;
        }

        WordTrie trie = new WordTrie();

        try {
            DictParser parser = new DictParser(filePath);
            List<String> dictWords = parser.wordsToList();
            trie.addWordList(dictWords);
        }
        catch(FileNotFoundException fnfe){
            filePath = defaultPathV2;
        }
        try {
            DictParser parser = new DictParser(filePath);
            List<String> dictWords = parser.wordsToList();
            trie.addWordList(dictWords);
        }
        catch(FileNotFoundException fnfe){
            throw new RuntimeException("tough luck");
        }

        List<String> permutedWords = Permuter.permutationsWordList(words);
        for (String word: permutedWords){
            if (trie.contains(word)){
                System.out.println(word);
            }
        }

    }
}