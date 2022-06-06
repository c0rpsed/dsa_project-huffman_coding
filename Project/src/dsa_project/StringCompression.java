package dsa_project;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import dsa_project.BinaryFile;

// Class that utilizes the MinPriorityQ, Huffman Tree, and LinkedList classes
// for encoding the codes for characters of string and
// then decoding them to get the original characters.

public class StringCompression {
    MinPriorityQ charFreq;
    HuffmanTree huffmanTree;
    LinkedList<Character> uniqueChars;
    LinkedL encoded = new LinkedL();

    void compress(String str){

        // uniqueChars contains a list of all the unique characters in the incoming string.
        uniqueChars = getUniqueChars(str);
        // charFreq is the MinPriorityQ containing characters and their frequency.
        charFreq = getCharFreq(uniqueChars, str);
        huffmanTree = new HuffmanTree();
        // populating the huffman tree.
        huffmanTree.insert(charFreq);
        // encoding the codes for each unique character.
        encode(huffmanTree.root, "");
    }

    // method to iterate over the string and adds to the list each character only once.
    private LinkedList<Character> getUniqueChars(String str) {

        LinkedList<Character> uniqueChars = new LinkedList<>();
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if( !uniqueChars.contains(ch)){
                uniqueChars.add(ch);
            }
        }
        return uniqueChars;
    }

    // using the uniqueCharacters list, this method calculates the frequency of each character
    // by iterating over the same incoming string and add the character
    // and its frequency to the MinPriorityQ
    MinPriorityQ getCharFreq(LinkedList<Character> chars, String str){
        MinPriorityQ q = new MinPriorityQ();
        for( int i=0; i<chars.size(); i++){
            int count = 0;
            char ch = chars.get(i);
            for(int j=0; j<str.length(); j++){
                char symbol = str.charAt(j);
                if( ch == symbol ){
                    count++;
                }
            }
            q.Enqueue(ch,count);
        }
        return q;
    }

    // a recursive pre-order method to get the codes for each character.
     public void encode(Node node, String prefix){


        if (node == null) {
            return ;
        }
        // as each node with a character from the string is a leaf node
        // so when we reach a leaf node it means we have reached the character.
        // now we just feed the character and its code to the Linked List.
        else if ( node.left == null && node.right == null ){
            encoded.insert(node.val,prefix);
            return ;
        }
        // 0 if we go left
        // 1 if we go right.
        encode(node.left, prefix + "0");
        encode(node.right, prefix + "1");
    }

    // decoding a string containing the bits.
    public void decode(String bits){
       String match = "";
       for(int i=0 ; i<bits.length(); i++){
           match += bits.charAt(i);
           char ch = encoded.hasBits(match);
           if(ch != '\0'){
               System.out.print(ch);
               match = "";
           }
       }
        System.out.println('\0');

    }

    void getSize(String str){
        System.out.println("Actual bits: "+ str.length() * 8);
        System.out.print("Bits after compression: ");
        int bytes = 0;
        for(int i=0 ; i<str.length(); i++){
            char c = str.charAt(i);
            String st = encoded.getBits(c);
            bytes += st.length();
        }
        System.out.println(bytes);

    }

    void compressToFile(String fileName, String str){
        BinaryFile f = new BinaryFile(fileName, 'w');
        compress(str);
        String ss = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            LinkedL.Node temp = encoded.head;
            while (temp != null) {
                if (c == temp.ch) {
                    ss += temp.bits;
                    break;
                }
                temp = temp.next;
            }
        }
        for(int i=0; i<ss.length(); i++){
            if( ss.charAt(i) == '1')
                f.writeBit(true);
            else {
                f.writeBit(false);
            }
        }
        f.close();
    }

    void decompressFile(String fileName){
        BinaryFile f = new BinaryFile(fileName, 'r');
        String stt = "";
        do{
            if (f.readBit()) {
                stt += "1";
            } else {
                stt += "0";
            }
            char c = encoded.hasBits(stt);
            if (c != '\0') {
                System.out.print(c);
                stt = "";
            }

        }while(f.EndOfFile());
        System.out.println();
    }
}
