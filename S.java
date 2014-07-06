import java.io.*;
import java.util.*;

public class S
{
    static ArrayList<TreeNode> in;
    
    static class TreeNode {
        char letter;
        int freq;
        TreeNode left,right;
        TreeNode(char l,int f) {
            letter=l;
            freq=f;
        }
    }
    
    public static TreeNode insert(TreeNode root, TreeNode target) {
        if(root==null)
            return target;
        if(target.freq>root.freq)
            root.left=insert(root.left,target);
        else if(target.freq<root.freq)
            root.right=insert(root.right,target);
        else if(target.freq==root.freq) {
            if(target.letter<root.letter)
                root.left=insert(root.left,target);
            else {
                root.right=insert(root.right,target);
            }
        }
        return root;
    }
    
    public static void inorder(TreeNode root) {
        if(root==null)
            return;
        inorder(root.left);
        in.add(root);
        inorder(root.right);
    }
    
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
            TreeNode root=null;
            for(int i=0;i<buc.length;i++) {
                if(buc[i]>0) {
                    char letter=(char)(i+'a');
                    int freq=buc[i];
                    TreeNode n=new TreeNode(letter,freq);
                    root=insert(root,n);
                }
            }
            in=new ArrayList<TreeNode>();
            inorder(root);
            writer.write("Case #" + (caseNum++) + "\n");
            for(int j=0;j<in.size();j++) {
                TreeNode t=in.get(j);
                writer.write(t.letter+" "+t.freq+"\n");   
            }
        }
        
        
        writer.close();
        reader.close();
    }
}
