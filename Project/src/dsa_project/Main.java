package dsa_project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StringCompression sc = new StringCompression();
        Scanner s = new Scanner(System.in);
        System.out.print("Enter String to compress:");
        String str = s.nextLine();
        sc.compress(str);
        char ch;
        do{
        System.out.print("Choose One" +
                "\n1.Get size before and after compression." +
                "\n2.Get Huffman Tree" +
                "\n3.Get codes for each Character." +
                "\n4.Create a compressed file of the String." +
                "\n5.Read the compressed file. " +
                "\nEnter: ");
        int inp = s.nextInt();s.nextLine();
        switch(inp){
            case 1:
                System.out.println("\n__________________________________");
                sc.getSize(str);
                System.out.println("__________________________________\n");
                break;
            case 2:
                System.out.println("\n__________________________________");
                sc.huffmanTree.print();
                System.out.println("__________________________________\n");
                break;
            case 3:
                System.out.println("\n__________________________________");
                sc.encoded.print();
                System.out.println("__________________________________\n");
                break;
            case 4:
                System.out.println("\n__________________________________");
                System.out.print("Enter your desired name:");
                String name = s.nextLine();
                sc.compressToFile(name+".huffed",str);
                System.out.println("__________________________________\n");
                break;
            case 5:
                System.out.println("\n__________________________________");
                System.out.print("Paste the file path: ");
                String n = s.nextLine();
                if(!n.endsWith(".huffed")){
                    System.out.println("Not a valid File.");
                } else {
                    sc.decompressFile(n);
                }

                System.out.println("__________________________________\n");
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
            System.out.print("Exit(Y/N)? ");
            ch = s.next().charAt(0); s.nextLine();

        }while(ch == 'N' || ch == 'n');

    }
}
