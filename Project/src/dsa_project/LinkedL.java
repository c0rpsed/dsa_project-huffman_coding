package dsa_project;

import java.util.Arrays;
import java.util.BitSet;

// Class to contain the codes for the characters of the string.
// A linked list holding characters and a string that contains
// the compressed binary code for each unique character.
public class LinkedL {

    // ch has unique characters from string
    // bits has the code representing its position in the Huffman tree.
    class Node {
        char ch;
        String bits;
        Node next;
        Node(char ch, String bits){
            this.ch = ch;
            this.bits = bits;
            this.next = null;
        }
    }

    Node head, tail;
    int size;

    // populating the linked list.
    void insert(char ch, String bits){
        Node node = new Node(ch,bits);
        if( head == null ){
            head = node;
            tail = node;
            size++;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }

    // a method to check if the list has the character for the code provided to it.
    char hasBits(String match){
        Node temp = head;
        boolean flag = false;

        while(temp!=null){
            if(temp.bits.equals(match)){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            return temp.ch;
        } else {
            return '\0';
        }
    }

    String getBits(char c){
        Node temp = head;
        while(temp!=null){
            if(temp.ch == c){
                return temp.bits;
            }
            temp = temp.next;
        }
        return null;
    }
    // displaying the list.
    void print(){
        Node temp = head;
        while ( temp != null ){
            System.out.println(temp.ch + " | " + temp.bits);
            temp = temp.next;
        }
    }

}
