import java.io.*;
import java.security.*;
import java.util.Scanner;
import java.util.Base64;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.lang.IllegalArgumentException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import javax.crypto.Cipher;

public class MSG {
   public static void main(String args[]) throws Exception {
      
      System.out.println("Enter some text");
      // Create a text file to store the original message
      PrintStream fileOut = new PrintStream(new File("Message1.txt"));
      System.setOut(fileOut);
       
      // Accepting text from user
      Scanner sc = new Scanner(System.in);
      String msg = sc.nextLine();
      System.out.println(msg);
 
       // Generate Key Pairs, a private key and a public key
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
      // Initialize the key pair generator
      keyGen.initialize(512);
      // Generate key pairs
      KeyPair keyPair = keyGen.generateKeyPair();
      // Generate Private key
      PrivateKey privateKey = keyPair.getPrivate();
      // Generate Public key
      PublicKey publicKey = keyPair.getPublic();
      // Get the Keys in Printable format
      Base64.Encoder encoder = Base64.getEncoder();
      // Create a text file to store Private key
      PrintStream fileOut2 = new PrintStream(new File("PRIVATE1.txt"));
      System.setOut(fileOut2);
      System.out.println("privateKey: \n" + encoder.encodeToString(privateKey.getEncoded()));
      // Create a text file to store Public key
      PrintStream fileOut3 = new PrintStream(new File("PUBLIC1.txt"));
      System.setOut(fileOut3);
      System.out.println("publicKey: \n" + encoder.encodeToString(publicKey.getEncoded()));

      PrintStream fileOut6 = new PrintStream(new File("ENCRYPT.txt"));
      System.setOut(fileOut6);
      Cipher c1 = Cipher.getInstance("RSA");
      c1.init(Cipher.ENCRYPT_MODE,privateKey);
      byte[] array = msg.getBytes();
      byte[] digest = c1.doFinal(array);
      System.out.println("Encrypted message: "+new String(digest));      
     
      // Create a text file to store decrypted digest
      PrintStream fileOut5 = new PrintStream(new File("DECRYPT.txt"));
      System.setOut(fileOut5);
      //We get a RSA cipher, initialize it with the Public key this time, and decrypt the bytes and turn them into a String again 
      Cipher c = Cipher.getInstance("RSA");
      c.init(Cipher.DECRYPT_MODE,publicKey);
      byte[] digest1 = c.doFinal(digest);
      System.out.println("After Decryption: "+new String(digest1)); 
      }
}
