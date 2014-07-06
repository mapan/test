import java.io.*;
import java.util.*;

public class L
{    
    static class Letter implements Comparable<Letter> {
        char letter;
        int freq;
        Letter(char l,int f) {
            letter=l;
            freq=f;
        }
        public int compareTo(Letter l2) {
            int freqCompare=l2.freq-this.freq; // decending
            return freqCompare; // it's stable
            if(freqCompare!=0)
                return freqCompare;
            return this.letter-l2.letter;
        }
    }
    
    /*static class LetterComparator implements Comparator<Letter> {
        public int compare(Letter l1,Letter l2) {
            int freqCompare=l1.freq-l2.freq;
            if(freqCompare!=0)
                return freqCompare;
            return l1.letter-l2.letter;
        }
    }*/
    
    public static int[] bucket(String s)
    {
        int[] buc=new int[26];
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)==' ')
                continue;
            buc[s.charAt(i)-'a']++;
        }
        return buc;
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("letter_frequencies.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("p.out"));
        
        int numCases = Integer.parseInt(reader.readLine());
        
        int caseNum=1;
        while(caseNum<=numCases) {
            String s=reader.readLine();
            int[] buc=bucket(s);
            ArrayList<Letter> le=new ArrayList<Letter>();
            for(int i=0;i<buc.length;i++) {
                if(buc[i]>0) {
                    char letter=(char)(i+'a');
                    int freq=buc[i];
                    Letter l=new Letter(letter,freq);
                    le.add(l);
                }
            }
            Collections.sort(le);
            Collections.sort(le, new Comparator<Letter>() {
                public int compare(Letter l1, Letter l2) {
                    int freqCompare=l1.freq-l2.freq;
                    if(freqCompare!=0)
                        return freqCompare;
                    return l1.letter-l2.letter;
                }
            });
            writer.write("Case #" + (caseNum++) + "\n");
            for(int j=0;j<le.size();j++) {
                writer.write(le.get(j).letter+" "+le.get(j).freq+"\n");   
            }
        }
        
        
        writer.close();
        reader.close();
    }
}
